package com.thoughtworks;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.TestName;

import com.thoughtworks.model.RequestModel;
import com.thoughtworks.model.Reservation;
import com.thoughtworks.model.ResponseModel;
import com.thoughtworks.service.RoomAllocatorTemplate;
import com.thoughtworks.service.Impl.DefaultRoomAllocator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // json parsing issue
    // empty json file
    // no file passed
    // json data not in correct format
    // no rooms in the input json 
    // no reservations in the input json
    // no roomtypes in the input json
    // extra room with not given roomtype 
    // no specific type room
    // reservation type is not available
    // roomType does not have entry but others have

    private static final String RESOURCE = "src/test/resources/";

    @Rule
    public TestName name = new TestName();

    private RequestModel requestModel;
    
    @Before
    public void pre() throws IOException {
        System.out.println("\n***** Running Test****: " + name.getMethodName() + " *****");
        requestModel = App.parseFile(RESOURCE+name.getMethodName()+".json");
    }

    @Test
    public void testHappyScenario() {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(1,3));
        expected.put(2, Collections.singletonList(2));
        expected.put(4, Collections.singletonList(4));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);
        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }

    @Test
    public void testOverlapScenario()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2,3));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }

    @Test
    public void testOverlapScenario2()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }

    @Test
    public void testOverlapScenario3()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2,3));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }

    @Test
    public void testNoRoomAvailableForReservation()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(1));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }
 
    @Test
    public void testNoRoomAvailableForReservationDate()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(actualMap, expected);
    }

    private Map<Integer, List<Integer>> convertToMap(ResponseModel expected) {
        Map<Integer, List<Integer>> expectedMap = new HashMap<>();
        expected.getAllBookings().forEach( (k,v) -> {
            expectedMap.put(k, v.stream().map(Reservation::getReservationId).collect(Collectors.toList()));
        });
        return expectedMap;
    }
}
