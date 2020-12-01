package dsapdbmysqlconnector.main.controller;


import com.fasterxml.jackson.databind.JsonNode;
import dsapdbmysqlconnector.main.DbOperation.CreateOperation;
import dsapdbmysqlconnector.main.model.Table;
import dsapdbmysqlconnector.main.utility.ErrorLogger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * controller responsible for handling create record and create table operation
 */

@RestController
@RequestMapping("/api/v1/")
public class create {


    @Autowired
    CreateOperation createOperation;


    @PostMapping("/table")
    public ResponseEntity<Map<String, Object>> createCollection(
            @Valid @RequestBody(required = true) JsonNode tableObject) {
        System.out.println("create table");
        try {

            System.out.println("body: "+tableObject.toString());


        Map<String,Object>    map = createOperation.createTable(new JSONObject(tableObject.toString()));


            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorLogger.SendLog(e);
        }
        return null;
    }

}
