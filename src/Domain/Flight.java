package Domain;

public class Flight {
    private int Flight_Number,Arrival_Time,Departure_Time,Airtime,Layover_Time;
    private String Airline,Origin,Destination,Date,Layover_Location;


    public int getFlight_Number() {
        return Flight_Number;
    }

    public void setFlight_Number(int flight_Number) {
        Flight_Number = flight_Number;
    }

    public int getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(int arrival_Time) {
        Arrival_Time = arrival_Time;
    }

    public int getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(int departure_Time) {
        Departure_Time = departure_Time;
    }

    public int getAirtime() {
        return Airtime;
    }

    public void setAirtime(int airtime) {
        Airtime = airtime;
    }

    public int getLayover_Time() {
        return Layover_Time;
    }

    public void setLayover_Time(int layover_Time) {
        Layover_Time = layover_Time;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLayover_Location() {
        return Layover_Location;
    }

    public void setLayover_Location(String layover_Location) {
        Layover_Location = layover_Location;
    }

}
