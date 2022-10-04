package com.thoughtworks;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.model.RequestModel;

public final class App 
{
    public static void main( String[] args ) throws Exception
    {   
        ObjectMapper mapper = JsonMapper.builder()
        .findAndAddModules()
        .build();
        
        RequestModel requestModel = mapper.readValue(new File("reservation-input.json"),RequestModel.class);
        System.out.println(requestModel.toString());
    }
}
