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

    /* 
     * Sort reservations based on start and end dates. 
     */
    public void sortReservations() {
        reservations.sort((r1, r2) -> {
            if (r1.getStartDate().isBefore(r2.getStartDate())) {
                return -1;
            } else if (r1.getStartDate().isAfter(r2.getStartDate())) {
                return 1;
            } else {
                if (r1.getEndDate().isBefore(r2.getEndDate())) {
                    return -1;
                } else if (r1.getEndDate().isAfter(r2.getEndDate())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }
}
