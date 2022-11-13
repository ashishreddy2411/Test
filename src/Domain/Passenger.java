package Domain;

public class Passenger {
    private int Passenger_ID;
    private String Firstname,Lastname,DOB,Passport_Number;


    public int getPassenger_ID() {
        return Passenger_ID;
    }

    public void setPassenger_ID(int passenger_ID) {
        Passenger_ID = passenger_ID;
    }

    public String getPassport_Number() {
        return Passport_Number;
    }

    public void setPassport_Number(String passport_Number) {
        Passport_Number = passport_Number;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
