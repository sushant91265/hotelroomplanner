package com.thoughtworks.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Reservation implements Comparable<Reservation> {
    
    private int reservationId;
    private int roomType;
    @JsonIgnore
    private boolean isBooked;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate endDate;

    @Override
    public int compareTo(Reservation r2) {
        if (this.getStartDate().isBefore(r2.getStartDate())) {
            return -1;
        } else if (this.getStartDate().isAfter(r2.getStartDate())) {
            return 1;
        } else {
            if (this.getEndDate().isBefore(r2.getEndDate())) {
                return -1;
            } else if (this.getEndDate().isAfter(r2.getEndDate())) {
                return 1;
            } else {
                return 0;
            }
        }
    }
 }
