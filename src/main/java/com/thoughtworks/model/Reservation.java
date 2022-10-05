package com.thoughtworks.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Reservation {
    
    private int reservationId;
    private int roomType;
    @JsonIgnore
    private boolean isBooked;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate endDate;

    /*
     * Method to check if the reservation is overlapping with other reservation dates.
     * @param reservation to be checked
     * @return boolean
     */
    public boolean isRoomBookingOverlapping(Reservation reservation) {
        return this.startDate.isBefore(reservation.endDate) && this.endDate.isAfter(reservation.startDate);
    }
 }
