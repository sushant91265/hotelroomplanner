package com.thoughtworks.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Reservation {
    private int reservationId;
    private int roomType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-d")
    private LocalDate endDate;

    public boolean isRoomBookingOverlapping(Reservation reservation) {
        return this.startDate.isBefore(reservation.endDate) && this.endDate.isAfter(reservation.startDate);
    }
}
