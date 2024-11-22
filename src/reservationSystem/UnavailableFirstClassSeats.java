package reservationSystem;

public class UnavailableFirstClassSeats extends RuntimeException{
    public UnavailableFirstClassSeats(){
        super("Unavailable economy class seats, would you like to be placed in first class.");

    }
}
