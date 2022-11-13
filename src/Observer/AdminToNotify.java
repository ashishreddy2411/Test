package Observer;

import java.util.ArrayList;
import java.util.List;

public class AdminToNotify {
    private final List<List<Integer>> link1=new ArrayList<List<Integer>>();

    public void ToNotify(Integer flight_number,Integer passenger_id)
    {
        final List<Integer> link2=new ArrayList<Integer>(2);
        link2.add(flight_number);
        link2.add(passenger_id);
        link1.add(link2);
    }
    public List<List<Integer>> AfterNotify()
    {
        List<List<Integer>> link2=new ArrayList<>(link1);
        link1.removeAll(link1);
        return  link2;
    }
}





