package com.thoughtworks;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.model.Room;

public final class App 
{
    public static void main( String[] args ) throws Exception
    {
        ObjectMapper mapper = JsonMapper.builder()
        .findAndAddModules()
        .build();
        
        RequestModel requestModel = mapper.readValue(new File("reservation-input.json"),RequestModel.class);
        requestModel.sortReservations();

        ResponseModel responseModel = processReservations(requestModel);
        // print the response
        System.out.println(responseModel);
    }

    private static ResponseModel processReservations(RequestModel requestModel) {
        List<Room> rooms = requestModel.getRooms();
        List<Reservation> reservations = requestModel.getReservations();
        ResponseModel responseModel = new ResponseModel();
        responseModel.fillEmptyBookings(rooms);
        // for each reservation assign a room according to the type of reservation
        //  and also check if the dates are not overlapping
        //  and fill the ResponseModel
        for (Reservation reservation : reservations) {
            int reservationId = reservation.getReservationId();
            for (Room room : rooms) {
                int roomId = room.getId();
                if (isSameRoomType(reservation, room) && isRoomAvailable(room, reservation, reservations)) {
                    room.addReservation(reservationId);
                    responseModel.addBooking(roomId, reservationId); 
                }
            }
        }
        return responseModel;
    }

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

    private static Reservation getLatestReservation(Room room, List<Reservation> reservations) {
        int lastReservationId = room.getLatestReservationId();
        Reservation lastReservation = getReservation(reservations, lastReservationId);
        return lastReservation;
    }

    private static boolean isSameRoomType(Reservation reservation, Room room) {
        return reservation.getRoomType() == room.getRoomType();
    }

    private static Reservation getReservation(List<Reservation> reservations, int lastReservationId) {
        return reservations.parallelStream().filter(r -> r.getReservationId() == lastReservationId).findFirst().get();
    }
}
