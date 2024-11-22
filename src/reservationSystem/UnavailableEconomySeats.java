package reservationSystem;

public class UnavailableEconomySeats extends Exception{
    public UnavailableEconomySeats(){
        super("Unavailable economy class seats, would you like to be placed in first class.");
    }
}
