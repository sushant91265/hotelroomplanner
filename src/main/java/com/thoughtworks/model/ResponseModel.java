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
    private Map<Integer, List<Reservation>> bookings = new HashMap<>();

    /*
     * Add a reservation to the bookings map.
     * @param reservation to be added
     * @param roomId to be added
     * @return void
     */
    public void addBooking(int roomId, Reservation reservation) {
        bookings.computeIfAbsent(roomId, k -> new ArrayList<>()).add(reservation);
    }

    /*
     * Get the list of reservations for a room.
     * @param roomId to be searched
     * @return list of reservations
     */
    public List<Reservation> getBooking(int roomId) {
        return bookings.getOrDefault(roomId, new ArrayList<>());
    }

    /*
     * Get all of the bookings.
     * @return map of bookings
     */
    public Map<Integer, List<Reservation>> getAllBookings() {
        return bookings;
    }
}
