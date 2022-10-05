package com.thoughtworks.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
 * Model class for response, stores room to reservations mapping.
 * ROOM ID <room_id>: [<reservation_id1>,<reservation_id2>, ...]
 */
public class ResponseModel {
    private Map<Integer, List<Integer>> bookings = new HashMap<>();
    
    /*
     * Fill the bookings map for each room with an empty list of reservations.
     * @param rooms list of rooms
     * @return void
     */
    public void fillEmptyBookings(List<Room> rooms) {
        rooms.forEach(room -> bookings.put(room.getId(), new ArrayList<>()));
    }

    /*
     * Add a reservation to the bookings map.
     * @param reservationId to be added
     * @param roomId to be added
     * @return void
     */
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
