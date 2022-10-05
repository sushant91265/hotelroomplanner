package com.thoughtworks.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/*
 * Model class for room, modeling the room json object.
 * It also maintains a list of reservations for the room. 
 */
@Data
public class Room {
    private int id;
    private int roomType;
    
    @JsonIgnore
    private List<Integer> reservations = new ArrayList<>();
    /*
     * Get the number of reservations for the room.
     * @return number of reservations
     */
    public List<Integer> getReservations() {
        return reservations;
    }

    /*
     * Add a reservation to the room.
     * @param reservationId to be added 
     */
    public void addReservation(int reservationId) {
        reservations.add(reservationId);
    }

    /*
     * Get the latest or last reservationId for the room.
     * @return reservationId
     */
    public int getLatestReservationId() {
        return reservations.get(reservations.size() - 1);
    }
}
