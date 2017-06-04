package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 6/3/17.
 */
public class Heaters475 implements Tracker {


    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        //no heater
        if(heaters==null || heaters.length<1){
            return -1;
        }
        if(houses==null || houses.length<1){
            //no houses
            return 0;
        }
        int global = 0;
        int heater = 0;
        for(int h = 0; h<houses.length; h++){
            while(heaters[heater]<houses[h] && heater < heaters.length-1){
                heater++;
                cout("next heater: [" + heater+"]="+heaters[heater]);
            }
            //has previous heater v.s. first heater
            int range = Math.abs(houses[h]-heaters[heater]);
            if(heater>0){
                range = Math.min(Math.abs(houses[h]-heaters[heater-1]), range);
            }
            global = Math.max(range, global);
            cout(String.format("cur house=%d, dist to next heater=%d, to previous heater=%d, local=%d, global=%d", houses[h], Math.abs(houses[h]-heaters[heater]), heater>0?Math.abs(houses[h]-heaters[heater-1]):-1,range,global));
        }
        //we have interval now, let's consider the both end. first, more houses before heater
        // range = houses[0]<heaters[0]?Math.max(range, Math.abs(houses[0]-heaters[0])):range;   (covered)
        //more houses after heater
        // range = heaters[heaters.length-1]<houses[houses.length-1]?Math.max(range, Math.abs(houses[houses.length-1]-heaters[heaters.length-1])):range;

        return global;
    }

    public static void main(String [] args){
        Heaters475 h = new Heaters475();
        int [] input = {1,2,3,4};
        int [] heaters = {1,4};
        int res = h.findRadius(input, heaters);
        int [] input2 = {282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int [] heaters2 = {823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};
        h.cout(res);
        h.cout(h.findRadius(input2,heaters2));
    }
}
