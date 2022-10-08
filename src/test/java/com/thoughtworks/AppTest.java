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
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * Overlapping reservations where 2 reservations are overlapping.
     */
    @Test
    public void testOverlapScenario()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2,3));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * Overlapping reservations where 2 reservations are overlapping but the second reservation is
     * for less number of days.
     */
    @Test
    public void testOverlapScenario2()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * Overlapping reservations where 2 reservations are overlapping but the second reservation's
     * start date is same as first one. 
     */
    @Test
    public void testOverlapScenario3()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2,3));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * No rooom allotement for a reservation where roomType is different or a room of 
     * that type is not available.
     */
    @Test
    public void testNoRoomAvailableForReservation()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(1));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }
    
    /*
     * No room allotement for a reservation whose start date is earlier than second reservation
     * but end date is not.
     */
    @Test
    public void testNoRoomAvailableForReservationDate()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(2));

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * No room available for all the reservations. 
     */
    @Test
    public void testNegativeScenario()  {
        Map<Integer, List<Integer>> expected = new HashMap<>();

        RoomAllocatorTemplate roomAllocator = new DefaultRoomAllocator();
        ResponseModel actual = roomAllocator.processReservations(requestModel);

        Map<Integer, List<Integer>> actualMap = convertToMap(actual);
        Assert.assertEquals(expected, actualMap);
    }

    /*
     * Helper method to convert the response model to map
     * @param responseModel
     * @return map of room number and list of reservation ids
     */
    private Map<Integer, List<Integer>> convertToMap(ResponseModel expected) {
        Map<Integer, List<Integer>> expectedMap = new HashMap<>();
        expected.getAllBookings().forEach( (k,v) -> {
            expectedMap.put(k, v.stream().map(Reservation::getReservationId).collect(Collectors.toList()));
        });
        return expectedMap;
    }
}
