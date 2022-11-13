package Domain;

public class Booking {
    private String Booking_Reference,Passport_Number;
    private int Flight_Number,Passenger_ID;
    private Flight flight;
    private Passenger passenger;

    public String getBooking_Reference() {
        return Booking_Reference;
    }

    public void setBooking_Reference(String booking_Reference) {
        Booking_Reference = booking_Reference;
    }

    public String getPassport_Number() {
        return Passport_Number;
    }

    public void setPassport_Number(String passport_Number) {
        Passport_Number = passport_Number;
    }

    public int getFlight_Number() {
        return Flight_Number;
    }

    public void setFlight_Number(int flight_Number) {
        Flight_Number = flight_Number;
    }

    public int getPassenger_ID() {
        return Passenger_ID;
    }

    public void setPassenger_ID(int passenger_ID) {
        Passenger_ID = passenger_ID;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Booking_Reference='" + Booking_Reference + '\'' +
                ", Passport_Number='" + Passport_Number + '\'' +
                ", Flight_Number=" + Flight_Number +
                ", Passenger_ID=" + Passenger_ID +
                ", flight=" + flight +
                ", passenger=" + passenger +
                '}';
    }
}
