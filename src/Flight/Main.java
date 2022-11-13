package Flight;
import Domain.Admin;
import Domain.Availability;
import Domain.Booking;
import Domain.Flight;
import Observer.AdminToNotify;
import TableDataGateway.*;
import Observer.PassengerToNotify;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        PassengerToNotify notify1=new PassengerToNotify();
        AdminToNotify notify2=new AdminToNotify();
        int choice;
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
                            System.out.println("Login Successful.\n");
                            break;
                        }
                    }
                    if (status == 1) {
                        int choice2;
                        FlightsTDG flightsTDG = FlightsTDG.getInstance();
                        BookingTDG bookingTDG=BookingTDG.getInstance();
                        do {
                            System.out.println("1.View All Flights details\n2.Add Flight Details\n3.Modify Flight Details\n4.View Available Seats of a Flight\n5.View Newly Booked flights\n6.Exit");
                            choice2 = scan.nextInt();
                            switch (choice2) {
                                case 1:
                                    List<Flight> flights = flightsTDG.View_flight_details();
                                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.format("%-15s%-20s%11s%30s%19s%10s%17s\n","Flight Number","Airline" , "Date" , "Departure Time" , "Arrival Time" , "Origin" , "Destination");
                                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    for (Flight flight : flights) {
                                        System.out.format("%-15d%-20s%16s%21d%19d%12s%17s\n",flight.getFlight_Number() , flight.getAirline() , flight.getDate() , flight.getDeparture_Time() , flight.getArrival_Time() , flight.getOrigin() , flight.getDestination());
                                    }
                                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.println();
                                    break;
                                case 2:
                                    Flight flight = new Flight();
                                    System.out.println("Enter Flight Details as -> Flight_Number,Airline,Origin,Destination,Arrival_Time,Departure_Time,Airtime,Date,Layover_Location, Layover_Time");
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
                                    else
                                        System.out.println("Flight Could not be added, Please try again.");
                                    System.out.println();
                                    break;
                                case 3:
                                    flight = new Flight();
                                    System.out.println("Enter Flight Number");
                                    flight.setFlight_Number(scan.nextInt());
                                    System.out.println("Enter Flight Details as ->Airline,Origin,Destination,Arrival_Time,Departure_Time,Airtime,Date,Layover_Location, Layover_Time");
                                    scan.nextLine();
                                    f = scan.nextLine();
                                    fli = f.split(",");
                                    flight.setAirline(fli[0]);
                                    flight.setOrigin(fli[1]);
                                    flight.setDestination(fli[2]);
                                    flight.setArrival_Time(Integer.parseInt(fli[3]));
                                    flight.setDeparture_Time(Integer.parseInt(fli[4]));
                                    flight.setAirtime(Integer.parseInt(fli[5]));
                                    flight.setDate(fli[6]);
                                    flight.setLayover_Location(fli[7]);
                                    flight.setLayover_Time(Integer.parseInt(fli[8]));
                                    flag = flightsTDG.modify_flight_details(flight);
                                    if (flag == 1) {
                                        System.out.println("Flight Details Modified Successfully\n");
                                        List<Integer> NotifyIds=bookingTDG.getPassengerIDs(flight.getFlight_Number());
                                        for(int id: NotifyIds){
                                            notify1.addPassengerToNotify(flight.getFlight_Number(),id);
                                        }
                                    }
                                    else
                                        System.out.println("Flight Could not be Modified, Please try again.\n");
                                    break;
                                case 4:
                                    AvailabilityTDG availabilityTDG=AvailabilityTDG.getInstance();
                                    System.out.println("Enter Flight Number");
                                    int flight_No=scan.nextInt();
                                    Availability availability=availabilityTDG.viewAvailableSeats(flight_No);
                                    if(availability.getFlight_Number()==0)
                                    {
                                        System.out.println("Sorry Flight Number Entered Does not Exist, Please try again");
                                        break;
                                    }
                                    System.out.println("\nFlight Number = "+availability.getFlight_Number()+", Currently "+availability.getAvaliable_No_of_Seats()+" Seats are available out of "+availability.getTotal_No_of_Seats()+"\n");
                                    System.out.println();
                                    break;
                                case 5:
                                    List<Booking> bookings=bookingTDG.ViewNewlyBookedFlights(notify2.AfterNotify());
                                    if(bookings.size()==0) {
                                        System.out.println("No New Flights are added\n");
                                        break;
                                    }
                                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.format("%6s%14s%12s%12s%18s%25s%15s%11s%30s%19s%10s%17s\n", "Booking ID","Passenger ID", "First Name","Last Name","Passport Number","Flight Number","Airline" , "Date" , "Departure Time" , "Arrival Time" , "Origin" , "Destination");
                                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    for (Booking booking : bookings) {
                                        System.out.format("%-11s%-14d%-12s%-10s%14s%25s%20s%15s%22s%15s%17s%18s\n", booking.getBooking_Reference() , booking.getPassenger().getPassenger_ID(), booking.getPassenger().getFirstname(),booking.getPassenger().getLastname(),booking.getPassenger().getPassport_Number(),booking.getFlight().getFlight_Number(),
                                                booking.getFlight().getAirline() , booking.getFlight().getDate() , booking.getFlight().getDeparture_Time() , booking.getFlight().getArrival_Time() , booking.getFlight().getOrigin() , booking.getFlight().getDestination());
                                    }
                                    System.out.println();
                                    break;
                            }
                        } while (choice2 == 1 || choice2 == 2 || choice2 == 3|| choice2 == 4|| choice2 == 5);
                        break;
                    } else {
                        System.out.println("Login Failed");
                    }
                    break;
                case 2:
                    int choice3;
                    do {
                        BookingTDG bookingTDG=BookingTDG.getInstance();
                        System.out.println("1.View upcoming Booking\n2.New Booking\n3.Exit\n");
                        choice3 = scan.nextInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter Passenger ID");
                                int id=scan.nextInt();
                                List<Booking> bookings=bookingTDG.View_booking_details(id);
                                int flight_no=0;
                                if(bookings.size()==0)
                                {
                                    System.out.println("\nNo Bookings are available\n");
                                    break;
                                }
                                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.format("%6s%14s%12s%12s%18s%25s%15s%11s%30s%19s%10s%17s\n", "Booking ID","Passenger ID", "First Name","Last Name","Passport Number","Flight Number","Airline" , "Date" , "Departure Time" , "Arrival Time" , "Origin" , "Destination");
                                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                for (Booking booking : bookings) {
                                    if(notify1.removePassengerToNotify(booking.getFlight().getFlight_Number(),booking.getPassenger().getPassenger_ID())==1)
                                        flight_no=booking.getFlight().getFlight_Number();
                                    System.out.format("%-11s%-14d%-12s%-10s%14s%25s%20s%15s%22s%15s%17s%18s\n", booking.getBooking_Reference() , booking.getPassenger().getPassenger_ID(), booking.getPassenger().getFirstname(),booking.getPassenger().getLastname(),booking.getPassenger().getPassport_Number(),booking.getFlight().getFlight_Number(),
                                            booking.getFlight().getAirline() , booking.getFlight().getDate() , booking.getFlight().getDeparture_Time() , booking.getFlight().getArrival_Time() , booking.getFlight().getOrigin() , booking.getFlight().getDestination());
                                }
                                System.out.println();
                                if(flight_no==0)
                                    break;
                                System.out.println("Flight Details with Flight No "+flight_no+" has been modified, Please Verify thoroughly");
                                break;
                            case 2:
                                int flag;
                                AvailabilityTDG availabilityTDG=AvailabilityTDG.getInstance();
                                FlightsTDG flightsTDG = FlightsTDG.getInstance();
                                System.out.println("Enter Passenger ID");
                                id=scan.nextInt();
                                List<Flight> flights = flightsTDG.View_flight_details();
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.format("%-15s%-20s%11s%30s%19s%10s%17s\n","Flight Number","Airline" , "Date" , "Departure Time" , "Arrival Time" , "Origin" , "Destination");
                                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                for (Flight flight : flights) {
                                    System.out.format("%-15d%-20s%16s%21d%19d%12s%17s\n",flight.getFlight_Number() , flight.getAirline() , flight.getDate() , flight.getDeparture_Time() , flight.getArrival_Time() , flight.getOrigin() , flight.getDestination());
                                }
                                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println();
                                System.out.println("Enter a Flight Number to Book");
                                flight_no=scan.nextInt();
                                int flag2=availabilityTDG.reduceAvailableSeats(flight_no);
                                if(flag2==1) {
                                    flag=bookingTDG.newFlightBooking(flight_no,id);
                                    if(flag==1) {
                                        notify2.ToNotify(flight_no,id);
                                        System.out.println("Flight Booking Successful");
                                    }
                                    else
                                        System.out.println("Flight Booking unsuccessful, Please Try Again Later");
                                }
                                else
                                    System.out.println("Flight Booking unsuccessful, Please Try Again Later");
                                break;
                        }
                    }while (choice3==1 || choice3==2);
                    break;
            }
        }while (choice==1 || choice==2);
    }
}
