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
import java.util.UUID;

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
            String statement="select B.Booking_Reference P.Passenger_ID,P.Firstname,P.Lastname,P.DOB,P.Passport_Number,F.Flight_Number,F.Airline,F.Orgin,F.Destination,F.Departure_Time,F.Arrival_Time,F.Date,F.Layover_Location,F.Layover_Time from Passenger P join Booking B on P.Passenger_ID=B.Passenger_ID join Flights F on F.Flight_Number=B.Flight_Number where P.Passenger_ID=?;";
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
        }
        return booking;
    }
    public List<Booking> ViewNewlyBookedFlights(List<List<Integer>> data)
    {
        List<Booking> booking = new ArrayList<>();
        for (int i=0;i<data.size();i++) {
                try {
                    Connection conn = JDBConnection.getConnection();
                    String statement="select B.Booking_Reference,P.Passenger_ID,P.Firstname,P.Lastname,P.DOB,P.Passport_Number,F.Flight_Number,F.Airline,F.Orgin,F.Destination,F.Departure_Time,F.Arrival_Time,F.Date,F.Layover_Location,F.Layover_Time from Passenger P join Booking B on P.Passenger_ID=B.Passenger_ID join Flights F on F.Flight_Number=B.Flight_Number where P.Passenger_ID=? and F.flight_number=?;";
                    PreparedStatement stmt= conn.prepareStatement(statement);
                    stmt.setInt(2,data.get(i).get(0));
                    stmt.setInt(1,data.get(i).get(1));
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
                }
        }
        return booking;
    }
    public int newFlightBooking(int flight_no,int id){
        int flag = 0;
        try {
            UUID randomUUID = UUID.randomUUID();
            String randstr=randomUUID.toString().replaceAll("-", "").substring(0,5);
            Connection conn = JDBConnection.getConnection();
            String statement = "insert into booking(Booking_Reference) values (?);";
            PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setString(1,randstr);
            flag= stmt.executeUpdate();
            if(flag==1)
            {
                int passport = 0;
                String sql="select passport_number from passenger where passenger_id=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,id);
                ResultSet rs= stmt.executeQuery();
                while (rs.next())
                    passport=rs.getInt(1);
                sql="update booking set Passport_Number=?,Flight_Number=?,Passenger_ID=? where Booking_Reference=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,passport);
                stmt.setInt(2,flight_no);
                stmt.setInt(3,id);
                stmt.setString(4,randstr);
                flag= stmt.executeUpdate();
                return flag;
            }
        }catch (Exception e) {}
        return flag;
    }
    public List<Integer> getPassengerIDs(int flight_number){
        List<Integer> Ids=new ArrayList<Integer>();
        int flag=0,i=0;
        try {
            Connection conn = JDBConnection.getConnection();
            String sql = "select passenger_id from booking where flight_number=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,flight_number);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Ids.add(rs.getInt("passenger_id"));
            }
        }catch (Exception e) {}
        return Ids;
    }
}
