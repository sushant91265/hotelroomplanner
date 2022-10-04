package com.thoughtworks.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ResponseModel {
    private Map<Integer, List<Integer>> bookings = new HashMap<>();
    
    public void fillEmptyBookings(List<Room> rooms) {
        for (Room room : rooms) {
            bookings.put(room.getId(), new ArrayList<>());
        }
    }

    public void addBooking(int roomId, int reservationId) {
        bookings.get(roomId).add(reservationId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : bookings.entrySet()) {
            sb.append("\nROOM ID ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("");
        }
        return sb.toString();
    }
}
