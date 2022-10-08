package com.thoughtworks.service.Impl;

import java.util.Comparator;

import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.Room;
import com.thoughtworks.service.RoomAllocatorTemplate;

/*
 * This class will process the reservations and return the room allocation as per the 
 * required response model.
 */
public class DefaultRoomAllocator extends RoomAllocatorTemplate{
    
    @Override
    public Comparator<Reservation> getSortingLogic() {
        return Comparator.comparing(Reservation::getEndDate);
    }

    @Override
    public boolean checkRoomEligibility(Room room, Reservation reservation) {
        return isSameRoomType(reservation, room) && isRoomAvailable(room, reservation);
    }

    /*
     * This method will check if the room is available for the reservation.
     * @param room object
     * @param reservation object
     * @param list of reservations 
     * @return boolean
     */
    private boolean isRoomAvailable(Room room, Reservation currentReservation) {
        int existingReservationLength = room.getReservations().size();
        if (existingReservationLength == 0) {
            return true;
        } else {
            Reservation lastReservation = room.getLatestReservation();
            if (!isRoomBookingOverlapping(currentReservation, lastReservation)) {
                return true;
            }
        }
        return false;  
    }

    /*
     * This method will check if the reservation type is same as the room type.
     * @param reservation object
     * @param room
     * @return boolean
     */
    private boolean isSameRoomType(Reservation reservation, Room room) {
        return reservation.getRoomType() == room.getRoomType();
    }

    /*
     * Method to check if the reservation is overlapping with other reservation dates.
     * @param reservation to be checked
     * @return boolean
     */
    private boolean isRoomBookingOverlapping(Reservation reservation1, Reservation reservation2) {
        return reservation1.getStartDate().isBefore(reservation2.getEndDate()) 
        && reservation1.getEndDate().isAfter(reservation2.getStartDate());
    }
}
