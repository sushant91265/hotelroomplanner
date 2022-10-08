package com.thoughtworks.util;

import java.util.Arrays;

import org.junit.Test;

import com.thoughtworks.model.RequestModel;

/*
 * This class is used to unit test the validation of the input data
 * as per the rules defined in the Validation.java class. 
 */
public class ValidationTest {
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputIsNull() {
        RequestModel requestModel = null;
        Validation.validateInput(requestModel);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputIsEmpty() {
        RequestModel requestModel = new RequestModel();
        Validation.validateInput(requestModel);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputIsInvalid() {
        RequestModel requestModel = new RequestModel();
        requestModel.setRoomTypes(Arrays.asList(1));
        Validation.validateInput(requestModel);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputIsInvalid2() {
        RequestModel requestModel = new RequestModel();
        requestModel.setRoomTypes(Arrays.asList(1, 2, 3));
        requestModel.setRooms(Arrays.asList());
        requestModel.setReservations(Arrays.asList());
        Validation.validateInput(requestModel);
    }
}
