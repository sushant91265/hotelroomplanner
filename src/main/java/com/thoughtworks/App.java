package com.thoughtworks;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.service.RoomAllocator;
import com.thoughtworks.service.Validation;
import com.thoughtworks.service.Impl.DefaultRoomAllocator;

/*
 * This is the main class which will be executed.
 */
public final class App 
{
    private static final String DEFAULT = "reservation-input.json";

    /*
     * Main method which will be executed.
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws IOException
    {   
        RequestModel requestModel = parseFile(args.length>=1 ? args[0] : DEFAULT);
        Validation.validateInput(requestModel);

        Collections.sort(requestModel.getReservations());

        RoomAllocator roomAllocator = new DefaultRoomAllocator();
        ResponseModel responseModel = roomAllocator.processReservations(requestModel);
        App.toString(requestModel,responseModel);
    }

    /*
     * This method will parse the input file and return the request model object.
     * @param inputFileName
     * @return RequestModel object
     */
    static RequestModel parseFile(String inputFile) throws IOException {
        ObjectMapper mapper = JsonMapper.builder()
                            .findAndAddModules()
                            .build();
        return mapper.readValue(new File(inputFile),RequestModel.class);
    }

    /*
     * This method will print the response in the required format.
     * @param requestModel object
     * @param responseModel object
     */
    private static void toString(RequestModel requestModel, ResponseModel responseModel) {
        requestModel.getRooms().forEach(r -> {
            System.out.print("ROOM ID " + r.getId() + ": [");
            String response = responseModel.getBooking(r.getId())
            .stream()
            .map(r1 -> Integer.toString(r1.getReservationId()))
            .collect(Collectors.joining(", "));
            System.out.println(response+ "]");
        });
    }
}
