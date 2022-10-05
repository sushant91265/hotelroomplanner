package com.thoughtworks;

import java.util.List;

import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.model.Room;

/*
 * This class will process the reservations and return the room allocation as per the 
 * required response model.
 */
public class Processor {
    /*
     * If the reservation type is same as available room type and the dates are not
     * overlapping with the existing reservation then the room is booked for that particular
     * reservation.
     * @param requestModel object
     * @return ResponseModel object
     */
    static ResponseModel processReservations(RequestModel requestModel) {
        List<Room> rooms = requestModel.getRooms();
        List<Reservation> reservations = requestModel.getReservations();
        ResponseModel responseModel = new ResponseModel();
        responseModel.fillEmptyBookings(rooms);
        reservations.forEach(reservation -> {
            final int reservationId = reservation.getReservationId();
            rooms.forEach(room -> {
                final int roomId = room.getId();
                if (!reservation.isBooked() && isSameRoomType(reservation, room) 
                    && isRoomAvailable(room, reservation, reservations)) {
                    room.addReservation(reservationId);
                    reservation.setBooked(true);
                    responseModel.addBooking(roomId, reservationId); 
                }
            });
        });
        return responseModel;
    }

    /*
     * This method will check if the room is available for the reservation.
     * @param room object
     * @param reservation object
     * @param list of reservations 
     * @return boolean
     */
    private static boolean isRoomAvailable(Room room, Reservation reservation, List<Reservation> reservations) {
        int existingReservationLength = room.getReservations().size();
        if (existingReservationLength == 0) {
            return true;
        } else {
            // check if the last reservation is overlapping with the new reservation
            Reservation lastReservation = getLatestReservation(room, reservations);
            if (!reservation.isRoomBookingOverlapping(lastReservation)) {
                return true;
            }
        }
        return false;  
    }
    
    /*
     * This method will return the latest reservation for the room.
     * @param room object
     * @param list of reservations
     * @return Reservation object 
    */
    private static Reservation getLatestReservation(Room room, List<Reservation> reservations) {
        int lastReservationId = room.getLatestReservationId();
        Reservation lastReservation = getReservation(reservations, lastReservationId);
        return lastReservation;
    }

    /*
     * This method will check if the reservation type is same as the room type.
     * @param reservation object
     * @param room
     */
    private static boolean isSameRoomType(Reservation reservation, Room room) {
        return reservation.getRoomType() == room.getRoomType();
    }

    /*
     * This method will return the reservation object for the given reservation id.
     * @param list of reservations
     * @param reservationId
     * @return Reservation object
     */
    private static Reservation getReservation(List<Reservation> reservations, int lastReservationId) {
        return reservations.parallelStream().filter(r -> r.getReservationId() == lastReservationId).findFirst().get();
    }
}
