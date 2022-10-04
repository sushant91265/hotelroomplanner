package com.thoughtworks.model;

import java.util.List;

import lombok.Data;

@Data
public class RequestModel {
    private List<Integer> roomTypes;
    private List<Room> rooms;
    private List<Reservation> reservations;
}
