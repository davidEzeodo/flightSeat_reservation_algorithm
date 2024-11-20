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

        flight.assignSeat(userInput);
        boolean seatStatus = flight.getFlightSeats()[0];
        System.out.println(seatStatus);
        assertTrue(seatStatus);
    }
    @Test
    void testThat_seatCannot_beReassigned(){
        int userInput = 1;
        assertFalse(flight.getFlightSeats()[0]);
        boolean[] flightUpdated = flight.assignSeat(userInput);

        int[] seatsTaken = flight.findSeatsTaken();
        assertTrue(flightUpdated[0]);
        System.out.println(Arrays.toString(flightUpdated));

        System.out.println(Arrays.toString(seatsTaken));
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, seatsTaken);

        assertFalse(flightUpdated[1]);
        int newUserInput = 1;

        boolean[] flightUpdated2 = flight.assignSeat(newUserInput);
        assertTrue(flightUpdated2[1]);
        System.out.println(Arrays.toString(flightUpdated2));

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
        assignSeatsForFlight(1, 0);
        assignSeatsForFlight(1, 1);
        assignSeatsForFlight(1, 2);
        assignSeatsForFlight(1, 3);
        assignSeatsForFlight(1, 4);
    }
    @Test
    void testThat_exceptionIsThrown_whenFirstClassIsFilled(){
        assignSeatsForFlight(1, 0);
        assignSeatsForFlight(1, 1);
        assignSeatsForFlight(1, 2);
        assignSeatsForFlight(1, 3);
        assignSeatsForFlight(1, 4);
        RuntimeException message = assertThrows(RuntimeException.class, () ->{
            assignSeatsForFlight(1, 5);
        }, "There are no seats available in first class, would you mind being moved to economy?");
        System.out.println(message.toString());
    }
    @Test
    void testThat_userCanBeAssigned_economyClassSeats(){
        boolean[] result = flight.assignEconomyClassSeats();
        for(int i = 5; i < result.length; i++){
            assertTrue(result[i]);
        }
        System.out.println(Arrays.toString(result));
    }
    private void assignSeatsForFlight(int typeInput, int seatIndex){
        assertFalse(flight.getFlightSeats()[seatIndex]);
        boolean[] seatsUpdated = flight.assignSeat(typeInput);
        System.out.println(Arrays.toString(flight.findSeatsTaken()));
        assertTrue(seatsUpdated[seatIndex]);
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