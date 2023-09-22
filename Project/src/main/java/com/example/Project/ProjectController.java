package com.example.Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


import org.json.simple.parser.*;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

@RestController
@RequestMapping("/api")
public class ProjectController {

    ResourceLoader resourceLoader;
    @Autowired
    public ProjectController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/convert/{fileName}")
    public String convertJsonToCsv(@PathVariable("fileName") String fileName) {
        
        try (FileWriter writer = new FileWriter("output.csv");
                CSVWriter csvWriter = new CSVWriter(writer)) {
            List<Project> jsonData = convertion(fileName);
            // Write header
            String[] header = { "WARDNAME", "PERSONID", "FIRSTNAME", "LASTNAME", "FATHERNAME" };
            csvWriter.writeNext(header);

            // Write data
            for (Project data : jsonData) {
                String[] row = { "" + data.getWardName(),
                        "" + data.getPersonID().toUpperCase(),
                        "" + data.getFirstName().toUpperCase(),
                        "" + data.getLastName().toUpperCase(),
                        "" + data.getFatherName().toUpperCase() };
                csvWriter.writeNext(row);
            }

            return "Conversion complete: output.csv created";
        } catch (IOException e) {
            e.printStackTrace();
            return "File Not Found";
        }
        catch(ParseException e){
            return "Error occurred during Parsing";
        }
        // catch(FileNotFoundException e){
        //     return "File Not found";
        // }
    }

    // @GetMapping()
    public List<Project> convertion(String input) throws IOException, ParseException {
        FileReader file = new FileReader(resourceLoader.getResource("classpath:/"+input+".json").getFile());
        Object o = new JSONParser().parse(file);
        List<Project> l = new ArrayList<Project>();
        JSONArray j = (JSONArray) o;
        Iterator iterator = j.iterator();

        while (iterator.hasNext()) {
            JSONObject x = (JSONObject) iterator.next();
            List<String> li = new ArrayList<String>();
            Iterator i = ((JSONArray)x.get("WardName")).iterator();
            while(i.hasNext()){
                li.add((String)i.next());
            }
            String PersonID = (String) x.get("PersonID");
            String Firstname = (String) x.get("Firstname");
            String Lastname = (String) x.get("LastName");
            String Fathername = (String) x.get("Fathername");
            l.add(new Project(li,PersonID,Firstname,Lastname,Fathername));
        }
        file.close();
        return l;
    }

}
