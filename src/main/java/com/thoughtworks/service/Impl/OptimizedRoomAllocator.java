// package com.thoughtworks.service.Impl;

// import java.util.Map;
// import java.util.stream.Collectors;

// import com.thoughtworks.model.RequestModel;
// import com.thoughtworks.model.ResponseModel;
// import com.thoughtworks.service.RoomAllocator;

// public class OptimizedRoomAllocator implements RoomAllocator {
    
//     @Override
//     public ResponseModel processReservations(RequestModel requestModel) {
//         Map<Integer, RoomType> roomTypeMap = request.getRoomTypes().stream()
//                 .collect(Collectors.toMap(e -> e, RoomType::new));

//         request.getRooms()
//                 .forEach(e -> roomTypeMap.get(e.getRoomType())
//                         .addRoom(new Room(e.getId(), e.getRoomType()))
//                 );

//         request.getReservations()
//                 .forEach(e -> roomTypeMap.get(e.getRoomType()).addReservation(e));

//         roomTypeMap.values().forEach(RoomType::assignReservationsOptimally);


//         return new Response(
//                 roomTypeMap.values().stream()
//                         .map(RoomType::getAssignedReservations)
//                         .collect(Collectors.toList())
//                         .stream()
//                         .flatMap(e -> e.entrySet().stream())
//                         .collect(
//                                 Collectors.toMap(
//                                         Map.Entry::getKey,
//                                         Map.Entry::getValue)
//                         )
//         );
//         return null;
//     } 
// }
