package Flight;
import Domain.Admin;
import Domain.Booking;
import Domain.Flight;
import TableDataGateway.AdminTDG;
import TableDataGateway.BookingTDG;
import TableDataGateway.FlightsTDG;

import java.util.List;
import java.util.Scanner;

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
                    AdminTDG adminTDG = AdminTDG.getInstance();
                    List<Admin> admin = adminTDG.Admin_Login();
                    for (Admin ad : admin) {
                        if (ad.getUsername().equals(user) && ad.getPassword().equals(password)) {
                            status = 1;
                            System.out.println("Login Successful.");
                            break;
                        }
                    }
                    if (status == 1) {
                        int choice2 = 1;
                        do {
                            FlightsTDG flightsTDG = FlightsTDG.getInstance();
                            System.out.println("1.View All Flights details\n2.Add Flight Details\n3.Exit\n");
                            choice2 = scan.nextInt();
                            switch (choice2) {
                                case 1:
                                    List<Flight> flights = flightsTDG.View_flight_details();
                                    for (Flight flight : flights) {
                                        System.out.println(flight.getFlight_Number() + " " + flight.getAirline() + " " + flight.getDate() + " " + flight.getDeparture_Time() + " " + flight.getArrival_Time());
                                    }
                                    break;
                                case 2:
                                    Flight flight = new Flight();
                                    System.out.println("Enter Flight Details as -> Flight_Number,Airline,Orgin,Destination,Arrival_Time,Departure_Time,Airtime,Date,Layover_Location, Layover_Time");
                                    scan.nextLine();
                                    String f = scan.nextLine();
                                    String[] fli = f.split(",");
                                    flight.setFlight_Number(Integer.parseInt(fli[0]));
                                    flight.setAirline(fli[1]);
                                    flight.setOrigin(fli[2]);
                                    flight.setDestination(fli[3]);
                                    flight.setArrival_Time(Integer.parseInt(fli[4]));
                                    flight.setDeparture_Time(Integer.parseInt(fli[5]));
                                    flight.setAirtime(Integer.parseInt(fli[6]));
                                    flight.setDate(fli[7]);
                                    flight.setLayover_Location(fli[8]);
                                    flight.setLayover_Time(Integer.parseInt(fli[9]));
                                    int flag = flightsTDG.Add_Flight_Details(flight);
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
                        BookingTDG bookingTDG=BookingTDG.getInstance();
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
