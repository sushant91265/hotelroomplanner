package com.thoughtworks;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.service.RoomAllocator;
import com.thoughtworks.service.Validation;
import com.thoughtworks.service.Impl.DefaultRoomAllocator;

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
            Validation.validateInput(requestModel);

            Collections.sort(requestModel.getReservations());

            RoomAllocator roomAllocator = new DefaultRoomAllocator();
            ResponseModel responseModel = roomAllocator.processReservations(requestModel);
            App.toString(requestModel,responseModel);
        } catch (IOException exception) {
            System.out.println("I/O Error occured!\n" + exception.getMessage());
        }
    }

    /*
     * This method will print the response in the required format.
     * @param requestModel object
     * @param responseModel object
     */
    private static void toString(RequestModel requestModel, ResponseModel responseModel) {
        requestModel.getRooms().forEach(r -> {
            System.out.print("ROOM ID " + r.getId() + ": [");
            String response = responseModel.getBookings(r.getId())
            .stream()
            .map(r1 -> Integer.toString(r1.getReservationId()))
            .collect(Collectors.joining(", "));
            System.out.println(response+ "]");
        });
    }
}
