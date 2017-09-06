package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 9/4/17.
 */
public class ThirdMax414 implements Tracker{


    /**
     * Use null as flag can save troubles
     */
    public int thirdMax(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }
    /**
     * Cannot rely on the point that set value, need multi flags for 1st, 2nd and 3rd
     * @param nums
     * @return
     */
    public int thirdMaxWrongOnExistFlag(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        boolean e = false;
        for(int i : nums){
            if(i==first||i==second||i==third){
                cout(String.format("[X] i=%d, first=%d, second=%d, third=%d", i, first, second,third));
                continue;
            }
            if(i>first){
                third = second;
                second = first;
                first = i;
            } else if(i>second){
                third = second;
                second = i;
                e=true;
            } else if(i>third){
                third = i;
            }
            cout(String.format("[O] i=%d, first=%d, second=%d, third=%d", i, first, second,third));
        }
        cout(String.format("END, first=%d, second=%d, third=%d", first, second,third));
        cout(""+e);
        return e==true?third:first;
    }

    public static void main(String [] args){
        int[] in = {2,2,3,1};
        ThirdMax414 t = new ThirdMax414();
        t.cout(t.thirdMax(in));
    }
}
