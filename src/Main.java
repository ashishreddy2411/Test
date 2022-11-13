import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Passenger {
    int Passenger_ID;
    String Firstname,Lastname,DOB,Passport_Number;
}
class JDBConnection {

    Connection conn;

    Connection getConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:D:\\InteliJ\\AppprojectV1\\src\\mDb.db";
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
        }
        return conn;
    }
}
class Booking {

    String Booking_Reference,Passport_Number;
    int Flight_Number,Passenger_ID;
    Flight flight;
    Passenger passenger;

    public List<Booking> View_booking_details(int id) {
        List<Booking> booking = new ArrayList<>();
        try {
            JDBConnection jdbc = new JDBConnection();
            Connection conn = jdbc.getConnection();
            String statement="select B.Booking_Reference," +
                    "P.Passenger_ID,P.Firstname,P.Lastname,P.DOB,P.Passport_Number," +
                    "F.Flight_Number,F.Airline,F.Orgin,F.Destination,F.Departure_Time,F.Arrival_Time,F.Date,F.Layover_Location,F.Layover_Time" +
                    " from Passenger P join Booking B on P.Passenger_ID=B.Passenger_ID join Flights F on F.Flight_Number=B.Flight_Number where P.Passenger_ID=?;";
            PreparedStatement stmt= conn.prepareStatement(statement);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Booking book= new Booking();
                book.flight = new Flight();
                book.passenger = new Passenger();
                book.Booking_Reference = rs.getString(1);
                book.passenger.Passenger_ID = rs.getInt(2);
                book.passenger.Firstname = rs.getString(3);
                book.passenger.Lastname = rs.getString(4);
                book.passenger.DOB = rs.getString(5);
                book.passenger.Passport_Number = rs.getString(6);
                book.flight.Flight_Number = rs.getInt(7);
                book.flight.Airline = rs.getString(8);
                book.flight.Origin = rs.getString(9);
                book.flight.Destination = rs.getString(10);
                book.flight.Departure_Time = rs.getInt(11);
                book.flight.Arrival_Time = rs.getInt(12);
                book.flight.Date = rs.getString(13);
                book.flight.Layover_Location = rs.getString(14);
                book.flight.Layover_Time = rs.getInt(15);
                booking.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
}

class Flight {

    int Flight_Number,Arrival_Time,Departure_Time,Airtime,Layover_Time;
    String Airline,Origin,Destination,Date,Layover_Location;

    public List<Flight> View_flight_details() {
        List<Flight> flight = new ArrayList<>();
        try {
            JDBConnection jdbc = new JDBConnection();
            Connection conn = jdbc.getConnection();
            String statement = "Select * from Flights;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                Flight flight_details = new Flight();
                flight_details.Flight_Number = rs.getInt("Flight_Number");
                flight_details.Airline = rs.getString("Airline");
                flight_details.Origin = rs.getString("Orgin");
                flight_details.Destination = rs.getString("Destination");
                flight_details.Departure_Time = rs.getInt("Departure_Time");
                flight_details.Arrival_Time = rs.getInt("Arrival_Time");
                flight_details.Airtime = rs.getInt("Airtime");
                flight_details.Date = rs.getString("Date");
                flight_details.Layover_Location = rs.getString("Layover_Location");
                flight_details.Layover_Time = rs.getInt("Layover_Time");
                flight.add(flight_details);
            }
        } catch (Exception e) {
        }
        return flight;
    }

    public int Add_Flight_Details(Flight flight) {
        int flag = 0;
        try {
            JDBConnection jdbc = new JDBConnection();
            Connection conn = jdbc.getConnection();
            String statement = "insert into Flights values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(statement);
            stmt.setInt(1,flight.Flight_Number);
            stmt.setString(2,flight.Airline);
            stmt.setString(3,flight.Origin);
            stmt.setString(4,flight.Destination);
            stmt.setInt(5,flight.Arrival_Time);
            stmt.setInt(6,flight.Departure_Time);
            stmt.setInt(7,flight.Airtime);
            stmt.setString(8,flight.Date);
            stmt.setString(9,flight.Layover_Location);
            stmt.setInt(10,flight.Layover_Time);
            flag= stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}
class Admin {

     String Firstname,Lastname,Username,Password;
     int Admin_ID;


    public List<Admin> Admin_Login()
    {
        List<Admin> admin = new ArrayList<>();
        try {
            JDBConnection jdbc = new JDBConnection();
            Connection conn = jdbc.getConnection();
            String statement = "Select * from Admin;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()){
                Admin admin_details=new Admin();
                admin_details.Admin_ID = rs.getInt("Admin_ID");
                admin_details.Firstname = rs.getString("Firstname");
                admin_details.Lastname = rs.getString("Lastname");
                admin_details.Username = rs.getString("Username");
                admin_details.Password = rs.getString("Password");
                admin.add(admin_details);
            }
        } catch (Exception e) {
        }
        return admin;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int choice=0;
        do {
            System.out.println("Hello, choose a Category");
            System.out.println("1. Admin\n2.Passenger\n3.Exit\n");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    int status = 0;
                    System.out.println("Enter Admin Username");
                    scan.nextLine();
                    String user = scan.nextLine();
                    System.out.println("Enter Password");
                    String password = scan.nextLine();
                    Admin adminT = new Admin();
                    List<Admin> admin = adminT.Admin_Login();
                    for (Admin ad : admin) {
                        if (ad.Username.equals(user) && ad.Password.equals(password)) {
                            status = 1;
                            System.out.println("Login Successful.");
                            break;
                        }
                    }
                    if (status == 1) {
                        int choice2 = 1;
                        do {
                            Flight flightsT = new Flight();
                            System.out.println("1.View All Flights details\n2.Add Flight Details\n3.Exit\n");
                            choice2 = scan.nextInt();
                            switch (choice2) {
                                case 1:
                                    List<Flight> flights = flightsT.View_flight_details();
                                    System.out.println("Flight Number"+ "Airline" + "Date" + "Departure Time" + "Arrival Time" + "Origin" + "Destination");
                                    for (Flight flight : flights) {
                                        System.out.println(flight.Flight_Number + " " + flight.Airline + " " + flight.Date + " " + flight.Departure_Time + " " + flight.Arrival_Time);
                                    }
                                    break;
                                case 2:
                                    Flight flight = new Flight();
                                    System.out.println("Enter Flight Details as -> Flight_Number,Airline,Orgin,Destination,Arrival_Time,Departure_Time,Airtime,Date,Layover_Location, Layover_Time");
                                    scan.nextLine();
                                    String f = scan.nextLine();
                                    String[] fli = f.split(",");
                                    flight.Flight_Number = Integer.parseInt(fli[0]);
                                    flight.Airline = fli[1];
                                    flight.Origin = fli[2];
                                    flight.Destination = fli[3];
                                    flight.Arrival_Time = Integer.parseInt(fli[4]);
                                    flight.Departure_Time = Integer.parseInt(fli[5]);
                                    flight.Airtime = Integer.parseInt(fli[6]);
                                    flight.Date = fli[7];
                                    flight.Layover_Location = fli[8];
                                    flight.Layover_Time = Integer.parseInt(fli[9]);
                                    int flag = flightsT.Add_Flight_Details(flight);
                                    if (flag == 1)
                                        System.out.println("Flight Added Successfully");
                                    break;
                            }
                        } while (choice2 == 1 || choice2 == 2);
                        break;
                    } else {
                        System.out.println("Login Failed");
                    }
                    break;
                case 2:
                    int choice3=1;
                    do {
                        Booking bookingTDG= new Booking();
                        System.out.println("1. View upcoming Booking\n2.Exit\n");
                        choice3 = scan.nextInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter Passenger ID");
                                int id=scan.nextInt();
                                List<Booking> bookings=bookingTDG.View_booking_details(id);
                                for (Booking booking : bookings) {
                                    System.out.println(booking);
                                }
                                break;
                        }
                    }while (choice3==1);
                    break;
            }
        }while (choice==1 || choice==2);
    }
}