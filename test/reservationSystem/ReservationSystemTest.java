package reservationSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReservationSystemTest {

    private Flight flight;

    @BeforeEach
    void setup(){
        flight = new Flight();
    }

    @Test
    void testThat_EachFlightHasTenSeats(){
        boolean[] numberOfSeats = flight.getFlightSeats();
        assertEquals(10, numberOfSeats.length);
    }
    @Test
    void testThat_flightSeatStatusIsFalse_whenFlightInstanceIsCreated(){
        boolean[] seats = flight.getFlightSeats();
        for (boolean seatStatus : seats) {
            assertFalse(seatStatus);
        }
        showSeats();
    }
    @Test
    void testThat_seatCanBeAssigned_basedOnUserInput(){
        int userInput = 1;
        System.out.println(flight.getFlightSeats()[0]);
        assertFalse(flight.getFlightSeats()[0]);
        try{
            flight.assignSeat(userInput);
        }catch(NoSeatsAvailableException exMsg){
            exMsg.getCause();
        }

        boolean seatStatus = flight.getFlightSeats()[0];
        System.out.println(seatStatus);
        assertTrue(seatStatus);
    }
    @Test
    void testThat_seatCannot_beReassigned(){
        assertFalse(flight.getFlightSeats()[0]);
        try{
            flight.assignSeat(1);
        }catch(NoSeatsAvailableException exMsg){
            exMsg.getCause();
        }

        int[] seatsTaken = flight.findSeatsTaken();
        assertTrue(flight.getFlightSeats()[0]);
        System.out.println(Arrays.toString(flight.getFlightSeats()));

        System.out.println(Arrays.toString(seatsTaken));
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, seatsTaken);

        assertFalse(flight.getFlightSeats()[1]);
        try{
            flight.assignSeat(1);
        }catch(NoSeatsAvailableException exMsg){
            exMsg.getCause();
        }
        assertTrue(flight.getFlightSeats()[1]);
        System.out.println(Arrays.toString(flight.getFlightSeats()));

        int[] seatStatus =  flight.findSeatsTaken();
        System.out.println(Arrays.toString(seatStatus));
        assertArrayEquals(new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, seatStatus);

    }
    @Test
    void testThat_exceptionIsThrownIf_userInputIs_neither_1_nor_2(){
        int userInput = 3;
        assertThrows(IllegalArgumentException.class, () -> {
            flight.assignSeat(userInput);
        }, "Wrong Input");

    }
    @Test
    void testThat_firstClassRow_has_5_trueValues_when5SeatsAreAssigned(){
        assertFalse(flight.getFlightSeats()[0]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[0]);

        assertFalse(flight.getFlightSeats()[1]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[1]);

        assertFalse(flight.getFlightSeats()[2]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[2]);

        assertFalse(flight.getFlightSeats()[3]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[3]);

        assertFalse(flight.getFlightSeats()[4]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[4]);
    }
    @Test
    void testThat_exceptionIsThrown_whenFirstClassIsFilled(){
        assertFalse(flight.getFlightSeats()[0]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[0]);

        assertFalse(flight.getFlightSeats()[1]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[1]);

        assertFalse(flight.getFlightSeats()[2]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[2]);

        assertFalse(flight.getFlightSeats()[3]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[3]);

        assertFalse(flight.getFlightSeats()[4]);
        assignSeatsForFlight(1);
        assertTrue(flight.getFlightSeats()[4]);
        RuntimeException message = assertThrows(RuntimeException.class, () ->{
            assignSeatsForFlight(5);
        }, "There are no seats available in first class, would you mind being moved to economy?");
        System.out.println(message.toString());
    }
    @Test
    void testThat_userCanBeAssigned_economyClassSeats(){
        assertFalse(flight.getFlightSeats()[5]);
        boolean[] result = null;
        try{
            result = flight.assignSeat(2);
        }catch(NoSeatsAvailableException exMsg){
             exMsg.getCause();
        }

        System.out.println(Arrays.toString(result));
        assertTrue(flight.getFlightSeats()[5]);
    }
    @Test
    void testThat_exceptionIsThrown_onceAllSeatsAreAssigned(){
        assertFalse(flight.getFlightSeats()[0]);

        assignSeatsForFlight(1);
    }
    private void assignSeatsForFlight(int typeInput){
        try{
            flight.assignSeat(1);
        }catch(NoSeatsAvailableException exMsg){
            exMsg.getCause();
        }
        System.out.println(Arrays.toString(flight.findSeatsTaken()));
    }
    private void showSeats(){
        boolean[] seats = flight.getFlightSeats();
        int counter = 1;
        for (boolean seatStatus : seats) {
            System.out.printf("Seat %d : %s%n", counter, seatStatus);
            counter++;
        }
    }
    private int showSeatIndex(){
        boolean[] seats = flight.getFlightSeats();
        int counter = 0;
        int assignedSeatIndex = -1;
        for(boolean seat : seats){
            if (seat){
                System.out.println("The index of the currently assigned seat is: " + counter);
                assignedSeatIndex = counter;
                return assignedSeatIndex;
            }
            counter++;
        }
        return assignedSeatIndex;
    }

}