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
    private List<Reservation> reservations = new ArrayList<>();
    
    /*
     * Add a reservation to the room.
     * @param reservation to be added 
     */
    @JsonIgnore
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /*
     * Get the latest reservation for the room.
     * @return reservation object
     */
    @JsonIgnore
    public Reservation getLatestReservation() {
        return reservations.size() > 0 ? reservations.get(reservations.size() - 1) : null;
    }
}
