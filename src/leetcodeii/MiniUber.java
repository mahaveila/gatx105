package leetcodeii;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 4/21/18.
 */
public class MiniUber {
  public class Trip {
      public int id; // trip's id, primary key
      public int driver_id, rider_id; // foreign key
      public double lat, lng; // pick up location
      public Trip(int rider_id, double lat, double lng){

      }
  }
//  Definition of Helper
  static class Helper {
      public static double get_distance(double lat1, double lng1,
                                         double lat2, double lng2) {
          // return distance between (lat1, lng1) and (lat2, lng2)
          return 0.0;
      }
  };
    private double dist(double lata, double latb, double lnga, double lngb){
        return Math.sqrt((lata-latb)*(lata-latb) + (lnga - lngb)*(lnga - lngb));
    }

    public static class Driver{
        public double lat, lng;
        public int id;
        public boolean status;
    }
    //by rider_id
    Map<Integer, Trip> requests;
    //by driver_id
    Map<Integer, Driver> drivers;

    public MiniUber() {
        // initialize your data structure here.
        requests = new HashMap<>();
        drivers = new HashMap<>();
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
        // Write your code here
        if(!drivers.containsKey(driver_id)){
            Driver d = new Driver();
            d.lat = lat;
            d.id = driver_id;
            d.status = false; //not on a trip
            d.lng = lng;
            drivers.put(driver_id, d);
        }
        //on a trip, or dispatched a trip
        if(drivers.get(driver_id).status){
            for(Trip t : requests.values()){
                if(t.driver_id == driver_id){
                    return t;
                }
            }
        }
        return null;
    }
    //main method for the matching and dispatching
    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        // Write your code here

        if(!requests.containsKey(rider_id)){
            //hasn't find a requests
            Trip t = new Trip(rider_id, lat, lng);
            t.rider_id = rider_id;
            requests.put(rider_id, t);
        }
        //update the coordinate of that request
        requests.get(rider_id).lat = lat;
        requests.get(rider_id).lng = lng;

        int driver = -1;
        double dist = Double.MAX_VALUE;
        for(Driver d : drivers.values()){
            if(!d.status){
                double tmp_d = Helper.get_distance(d.lat, d.lng, lat, lng);
                if(tmp_d < dist){
                    driver = d.id;
                    dist = tmp_d;
                }
            }
        }
        if(driver>=0){
            requests.get(rider_id).driver_id = driver;
            drivers.get(driver).status = true;
        }

        // requests.get(rider_id).driver_id = (int) dist;
        return requests.get(rider_id);
    }
}