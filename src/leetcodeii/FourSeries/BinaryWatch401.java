package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 9/12/17.
 */
public class BinaryWatch401 implements Tracker{

    //FIXME: the better solution
    public List<String> readBinaryWatchBetterSolution(int num) {
        List<String> times = new ArrayList<>();
        for (int h=0; h<12; h++)
            for (int m=0; m<60; m++)
                if (Integer.bitCount(h * 64 + m) == num)
                    times.add(String.format("%d:%02d", h, m));
        return times;
    }

    public static void main(String[] args){
        BinaryWatch401 b = new BinaryWatch401();
        b.cout(b.readBinaryWatch(1));
    }

    public List<String> readBinaryWatch(int num) {
        if(num<1){
            List<String> res = new ArrayList<>();
            res.add("0:00");
            return res;
        }
        int z = 8-num;
        char[] cs = "0000000000".toCharArray();
        List<String> keys = generateKeys(num, cs, 0);
        cout(keys);
        return keys.stream().map(s->{
            String t = getTime(s.toCharArray());
            cout(String.format("%s -> %s", s , t));
            return t;
        }).sorted((s1, s2)->{
            String[] s1s = s1.split(":");
            String[] s2s = s2.split(":");
            int m1 = Integer.parseInt(s1s[1]);
            int m2 = Integer.parseInt(s2s[1]);
            int h1 = Integer.parseInt(s1s[0]);
            int h2 = Integer.parseInt(s2s[0]);
            int res = h1==h2?m1-m2:h1-h2;
//            cout(String.format("comparing %s=%d:%d with %s=%d:%d, %s is larger", s1,h1,m1,s2,h2,m2, res<0?s2:s1));
            return res;
        }).filter(s->Integer.parseInt(s.split(":")[1])<60&&Integer.parseInt(s.split(":")[0])<12).collect(Collectors.toList());
    }

    private List<String> generateKeys(int num, char[] cs, int start){
        List<String> res = new ArrayList<>();
        if(num==0){
            res.add(new String(cs));
        } else if(start>=cs.length){
            return res;
        }
        for(int ii = start ; ii<cs.length; ii++){
            if(cs[ii]=='1'){
                continue;
            }
            cs[ii]='1';
            res.addAll(generateKeys(num-1,cs,ii+1));
            cs[ii]='0';
        }
        return res;
    }

    private String getTime(char [] cs){
        int min = 0;
        int hour = 0;
        int p = 0;
        for(int ii = cs.length-1; ii>=0; ii--,p++){
            if(cs[ii]=='1'){
                if(ii<4){
                    hour += (int)Math.pow(2, p-6);
                } else {
                    min += (int) Math.pow(2,p);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(hour).append(":").append(min>9?min:"0"+min);
        return sb.toString();
    }
}
