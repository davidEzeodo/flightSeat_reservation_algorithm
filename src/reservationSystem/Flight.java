package reservationSystem;

import java.util.Arrays;

public class Flight {
    public boolean[] getFlightSeats() {
        return flightSeats;
    }
    public boolean[] assignSeat(int userInput){
        if(userInput == 1 || userInput == 2){
            if(userInput==1) {
                boolean[] seatsUpdated = assignFirstClassSeats();
                System.out.println(Arrays.toString(seatsUpdated));
                return seatsUpdated;
            }
        }
        throw new IllegalArgumentException();
    }
    private boolean[] assignFirstClassSeats(){
        for (int i = 0; i < flightSeats.length/2; i++) {
            if (!this.getFlightSeats()[i]) {
                flightSeats[i] = true;
                this.setFlightSeats(flightSeats);
                return this.getFlightSeats();
            }
        }throw new RuntimeException("There are no seats available in first class, would you mind being moved to economy?");
    }
    public boolean[] assignEconomyClassSeats(){
        boolean[] seats = getFlightSeats();
        for(int i = 5; i < flightSeats.length; i++){
            seats[i] = true;
        }
        return flightSeats;
//        throw new RuntimeException("Next flight leaves in 3 hours.");
    }
    public int[] findSeatsTaken(){
        int[] seatsTaken = new int[flightSeats.length];
        for(int i = 0; i < this.flightSeats.length; i++){
            if(flightSeats[i] && seatsTaken[i]==0){
                seatsTaken[i] = 1;
            }
        }
        return seatsTaken;
    }

    private void setFlightSeats(boolean[] flightSeats) {
        this.flightSeats = flightSeats;
    }

    private boolean[] flightSeats = new boolean[10];
}
