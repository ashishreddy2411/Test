package Domain;

public class Availability {
    private int Flight_Number,Total_No_of_Seats,Avaliable_No_of_SeatsE,Avaliable_No_of_SeatsB;

    public int getFlight_Number() {
        return Flight_Number;
    }

    public void setFlight_Number(int flight_Number) {
        Flight_Number = flight_Number;
    }

    public int getTotal_No_of_Seats() {
        return Total_No_of_Seats;
    }

    public void setTotal_No_of_Seats(int total_No_of_Seats) {
        Total_No_of_Seats = total_No_of_Seats;
    }

    public int getAvaliable_No_of_SeatsE() {
        return Avaliable_No_of_SeatsE;
    }

    public void setAvaliable_No_of_SeatsE(int avaliable_No_of_SeatsE) {
        Avaliable_No_of_SeatsE = avaliable_No_of_SeatsE;
    }

    public int getAvaliable_No_of_SeatsB() {
        return Avaliable_No_of_SeatsB;
    }

    public void setAvaliable_No_of_SeatsB(int avaliable_No_of_SeatsB) {
        Avaliable_No_of_SeatsB = avaliable_No_of_SeatsB;
    }
}
