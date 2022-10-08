package com.thoughtworks.util;

import java.util.Objects;

import com.thoughtworks.model.RequestModel;

/*
 * This class will validate the input request model based on the following criteria:
 * 1. The request model should not be null.
 * 2. The request model should have at least one room.
 * 3. The request model should have at least one reservation.
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
