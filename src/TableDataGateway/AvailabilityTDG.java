package TableDataGateway;

import Domain.Availability;
import JDBCConnection.JDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvailabilityTDG {
    private static AvailabilityTDG instance = null;
    private AvailabilityTDG() {
    }
    public static AvailabilityTDG getInstance() {
        if(instance == null) {
            instance = new AvailabilityTDG();
        }
        return instance;
    }
    public Availability viewAvailableSeats(int flight_no)
    {
        Availability availability=new Availability();
        try {
            Connection conn = JDBConnection.getConnection();
            String sql = "select * from Availability where flight_number=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,flight_no);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                availability.setFlight_Number(rs.getInt(1));
                availability.setTotal_No_of_Seats(rs.getInt(2));
                availability.setAvaliable_No_of_Seats(rs.getInt(3));
            }
        }catch (Exception e) {}
        return availability;
    }
    public int reduceAvailableSeats(int flight_no)
    {
        int flag=0;
        try {
            Connection conn = JDBConnection.getConnection();
            String sql = "update Availability set Avaliable_no_seats=Avaliable_no_seats-1 where flight_number=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,flight_no);
            flag=stmt.executeUpdate();
        }catch (Exception e) {}
        return flag;
    }
}
