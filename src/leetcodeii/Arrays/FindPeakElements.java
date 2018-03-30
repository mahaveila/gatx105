package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/24/18.
 */
public class FindPeakElements implements Tracker {

    public int findPeak(int[] A) {
        // write your code here

        int l = 0;
        int r = A.length;
        while(l<r){
            int mid= (l+r)/2;
            cout("l="+l+", r=" + r + ", mid="+mid);
            int a = mid<1? Integer.MIN_VALUE : A[mid-1];
            int b = A[mid];
            int c = mid>=A.length-1? Integer.MIN_VALUE : A[mid+1];
            if(a<b && b>c){
                return mid;
            } else if(a>b && b>c){
                //going left
                r=mid-1;
                cout("can look right, r = mid-1 = " + r);
            } else {
                //either a<b && b<c, going right
                //or a>b, b<c, going either way
                l=mid+1;
                cout("can look left, l = mid+1 = " + l);
            }
        }
        return l;
    }

    public static void main(String [] args){
        FindPeakElements f = new FindPeakElements();
        int [] in = {1,2,1,3,4,5,7,6};
        f.cout(f.findPeak(in));
    }
}
