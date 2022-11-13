package TableDataGateway;

import Domain.Booking;
import Domain.Flight;
import Domain.Passenger;
import JDBCConnection.JDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingTDG {
    private static BookingTDG instance = null;

    private BookingTDG() {
    }

    public static BookingTDG getInstance() {
        if (instance == null) {
            instance = new BookingTDG();
        }
        return instance;
    }

    public List<Booking> View_booking_details(int id) {
        List<Booking> booking = new ArrayList<>();
        try {
            Connection conn = JDBConnection.getConnection();
            String statement="select B.Booking_Reference," +
                    "P.Passenger_ID,P.Firstname,P.Lastname,P.DOB,P.Passport_Number," +
                    "F.Flight_Number,F.Airline,F.Orgin,F.Destination,F.Departure_Time,F.Arrival_Time,F.Date,F.Layover_Location,F.Layover_Time" +
                    " from Passenger P join Booking B on P.Passenger_ID=B.Passenger_ID join Flights F on F.Flight_Number=B.Flight_Number where P.Passenger_ID=?;";
            PreparedStatement stmt= conn.prepareStatement(statement);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking book= new Booking();
                book.setFlight(new Flight());
                book.setPassenger(new Passenger());
                book.setBooking_Reference(rs.getString(1));
                book.getPassenger().setPassenger_ID(rs.getInt(2));
                book.getPassenger().setFirstname(rs.getString(3));
                book.getPassenger().setLastname(rs.getString(4));
                book.getPassenger().setDOB(rs.getString(5));
                book.getPassenger().setPassport_Number(rs.getString(6));
                book.getFlight().setFlight_Number(rs.getInt(7));
                book.getFlight().setAirline(rs.getString(8));
                book.getFlight().setOrigin(rs.getString(9));
                book.getFlight().setDestination(rs.getString(10));
                book.getFlight().setDeparture_Time(rs.getInt(11));
                book.getFlight().setArrival_Time(rs.getInt(12));
                book.getFlight().setDate(rs.getString(13));
                book.getFlight().setLayover_Location(rs.getString(14));
                book.getFlight().setLayover_Time(rs.getInt(15));
                booking.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
}
