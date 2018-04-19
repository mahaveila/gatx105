package leetcodeii.Strings;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/9/18.
 */
public class CompareVersions implements Tracker{

    public static void main(String [] args){
        CompareVersions c = new CompareVersions();
        c.cout(c.compareVersion("0", "1"));
    }

    public int compareVersion(String version1, String version2) {
        String [] v1 = version1.split("\\.");
        String [] v2 = version2.split("\\.");
        cout(v1);
        cout(v2);
        cout(v1.length);
        cout(v2.length);

        for(int ii = 0; ii < Math.max(v1.length, v2.length); ii ++){
            cout("ii " + ii);
            int first = 0;
            int second = 0;

            if(ii<v1.length){
                //v2 is longer
                first = Integer.parseInt(v1[ii]);
                cout(" -----> " + first);
            }
            if(ii<v2.length){
                //v1 is longer
                second = Integer.parseInt(v2[ii]);
                cout(" -----> " + second);
            }
            cout("1st = " + first + ", 2nd = " + second);
            if(first<second){
                return -1;
            } else if(first>second){
                return 1;
            }
        }
        return 0;
    }

}
