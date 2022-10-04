package com.thoughtworks.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Room {
    private int id;
    private int roomType;
    
    @JsonIgnore
    private List<Integer> reservations = new ArrayList<>();
    public List<Integer> getReservations() {
        return reservations;
    }

    public void addReservation(int reservationId) {
        reservations.add(reservationId);
    }

    public int getLatestReservationId() {
        return reservations.get(reservations.size() - 1);
    }
}
