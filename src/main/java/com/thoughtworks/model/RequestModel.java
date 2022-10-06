package com.thoughtworks.model;

import java.util.List;

import lombok.Data;

/* 
 * Model class for input request, modeling the input json file structure.
 */
@Data
public class RequestModel {
    private List<Integer> roomTypes;
    private List<Room> rooms;
    private List<Reservation> reservations;
}
