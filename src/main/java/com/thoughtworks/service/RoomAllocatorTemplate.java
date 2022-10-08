package com.thoughtworks.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.model.Room;

/*
 * This abstract class defines the template for the room allocation process.
 * It has the following abstract methods:
 * 1. getSortingLogic() - This method will return the sorting logic for the reservations.
 * 2. checkRoomEligibility() - This method will check if the room is eligible for the reservation.
 */
public abstract class RoomAllocatorTemplate {

    public abstract Comparator<Reservation> getSortingLogic();

    public abstract boolean checkRoomEligibility(Room room, Reservation reservation);        

    /*
     * Sort the reservations based on sorting criteria and if the reservation type is same 
     * as available room type and the dates are not overlapping with the existing reservation
     * then the room is booked for that particular reservation.
     * @param requestModel object
     * @return ResponseModel object
     */
    public ResponseModel processReservations(RequestModel requestModel) {
        Collections.sort(requestModel.getReservations(), getSortingLogic());

        List<Room> rooms = requestModel.getRooms();
        List<Reservation> reservations = requestModel.getReservations();
        ResponseModel responseModel = new ResponseModel();
        
        for(Reservation reservation : reservations) {
            rooms.stream().filter(r -> !reservation.isBooked() 
            && checkRoomEligibility(r, reservation))
            .findFirst()
            .ifPresentOrElse(room -> {
                room.addReservation(reservation);
                reservation.setBooked(true);
                responseModel.addBooking(room.getId(), reservation);
            },() -> {
                reservation.setBooked(false);
                System.out.println("Warning!No room available for reservation: " + reservation);
            });
            // .orElseThrow(() -> new RuntimeException("No room available for reservation id: " + reservation.getReservationId())); 
        }
        return responseModel;
    }
}
