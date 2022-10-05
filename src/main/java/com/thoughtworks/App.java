package com.thoughtworks;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.ResponseModel;

/*
 * This is the main class which will be executed.
 */
public final class App 
{
    /*
     * Main method which will be executed.
     * @param args
     * @throws Exception
     */
    public static void main( String[] args )
    {   
        try {
            ObjectMapper mapper = JsonMapper.builder()
                                .findAndAddModules()
                                .build();
            RequestModel requestModel = mapper.readValue(new File("reservation-input.json")
                                        ,RequestModel.class);
            requestModel.sortReservations();

            ResponseModel responseModel = Processor.processReservations(requestModel);
            System.out.println(responseModel);
        } catch (IOException exception) {
            System.out.println("I/O Error occured!\n" + exception.getMessage());
        }
    }
}
