package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import javax.sound.midi.Track;
import java.util.Arrays;
import java.util.function.Function;

/** TODO: analyze
 * Created by Youming on 4/19/2017.
 */
public class MagicalString481 implements Tracker{

    int oneInMagicString(int n){
        if(n<1){
            return 0;
        }
        if(n<=2){
            return 1;
        }
        int [] magicArray = new int[n];
        magicArray[0]=1;
        magicArray[1]=2;
//        int value = 1; carries by magic[tail-1]


        cout(magicArray);
        //starting at 1, preset at 0
        for(int cur = 1, tail = 1; tail < n; cur ++){
            int value = flipNumber(magicArray[tail -1]);
            cout(String.format("current at %d, tail at %d, next tail at %d, current value is %d", cur, tail, tail+magicArray[cur], value));
            fillIn(magicArray[cur], value, tail, magicArray);
            cout(magicArray);
            tail += magicArray[cur];
        }
        return (int) Arrays.stream(magicArray).filter(i -> i==1).count();
    }

    private void fillIn(int seq, int value, int tail, int [] input){

        for(int start = tail; start < tail + seq; start ++){
            if(start>=input.length){
                break;
            }
            cout(String.format("filling %d with %d", start, value));
            input[start] = value;
        }
    }

    private int flipNumber(int n){
        //flip between 10 and 01, use XOR
        return n^3;
    }

    public static void main(String [] args){

        MagicalString481 m = new MagicalString481();
//        m.cout(m.oneInMagicString(6));
        m.cout(m.oneInMagicString(12));
    }

}
