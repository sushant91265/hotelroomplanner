package com.thoughtworks.service;

import java.util.Objects;

import com.thoughtworks.model.RequestModel;

/*
 * This class will validate the input request model.
*/
public class Validation {
    public static void validateInput(RequestModel requestModel) {
        if(Objects.isNull(requestModel)) {
            throw new RuntimeException("Input is null");
        }
        if(Objects.isNull(requestModel.getRooms()) || requestModel.getRooms().isEmpty()) {
            throw new RuntimeException("Rooms are not present");
        }
        if (requestModel.getReservations().size() == 0) {
            throw new RuntimeException("No reservations available");
        }
    }
}
