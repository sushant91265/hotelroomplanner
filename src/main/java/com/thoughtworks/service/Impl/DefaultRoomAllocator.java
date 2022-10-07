package com.thoughtworks.service.Impl;

import java.util.List;

import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.model.Room;
import com.thoughtworks.service.RoomAllocator;

/*
 * This class will process the reservations and return the room allocation as per the 
 * required response model.
 */
public class DefaultRoomAllocator implements RoomAllocator{
    /*
     * If the reservation type is same as available room type and the dates are not
     * overlapping with the existing reservation then the room is booked for that particular
     * reservation.
     * @param requestModel object
     * @return ResponseModel object
     */
    @Override
    public ResponseModel processReservations(RequestModel requestModel) {
        List<Room> rooms = requestModel.getRooms();
        List<Reservation> reservations = requestModel.getReservations();
        ResponseModel responseModel = new ResponseModel();
        
        for(Reservation reservation : reservations) {
            rooms.stream().filter(r -> !reservation.isBooked() 
            && isSameRoomType(reservation, r) 
            && isRoomAvailable(r, reservation, reservations))
            .findFirst()
            .ifPresentOrElse(room -> {
                room.addReservation(reservation);
                reservation.setBooked(true);
                responseModel.addBooking(room.getId(), reservation);
            },() -> {
                reservation.setBooked(false);
            });
            // .orElseThrow(() -> new RuntimeException("No room available for reservation id: " + reservation.getReservationId())); 
        }
        return responseModel;
    }

    /*
     * This method will check if the room is available for the reservation.
     * @param room object
     * @param reservation object
     * @param list of reservations 
     * @return boolean
     */
    private boolean isRoomAvailable(Room room, Reservation currentReservation, List<Reservation> reservations) {
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
