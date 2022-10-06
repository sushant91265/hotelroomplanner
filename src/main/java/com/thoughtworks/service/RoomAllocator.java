package com.thoughtworks.service;

import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.ResponseModel;

/*
 * This is the interface for the room allocator service.
 */
public interface RoomAllocator {
    ResponseModel processReservations(RequestModel requestModel);
}
