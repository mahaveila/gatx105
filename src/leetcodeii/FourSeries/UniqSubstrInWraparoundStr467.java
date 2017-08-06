package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Erebus on 6/4/17.
 */
public class UniqSubstrInWraparoundStr467 implements Tracker{


    //FIXME: wrong on super long input:abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk...
    public int findSubstringInWraproundString(String p) {
        Set<String> dict = new HashSet<>();
        if(p==null || p.length()<1){
            return 0;
        }
        char prev = p.charAt(0);
        int prevIndex = 0;
        int sum=0;
        for(int ii=0; ii<p.length(); ii++){
            char cur = p.charAt(ii);
            if(isContinuous(cur, prev)){
                //keep prevIndex;
                String local = p.substring(prevIndex, ii+1);
                if(!dict.contains(local)){
                    sum += ii - prevIndex + 1;
                    dict.add(local);
                }
                if(!dict.contains(p.substring(ii,ii+1))){
                    dict.add(p.substring(ii,ii+1));
                }
                cout(String.format("ii=%d: <%s>, prev=<%s>, %s isContinuous, sum + %d", ii, cur, prev, local, ii-prevIndex));
            } else {
                prevIndex = ii;
                String local = p.substring(prevIndex, ii+1);
                if(!dict.contains(local)){
                    sum+=1;
                    dict.add(local);
                    cout(String.format("ii=%d: <%s>, prev=<%s>, %s is NOT Continuous, sum + 1", ii, cur, prev, local));
                } else {
                    cout(String.format("ii=%d: <%s>, prev=<%s>, %s is NOT Continuous", ii, cur, prev, local));
                }
            }
            prev= cur;
        }

        return sum;
        //if find the next is still continuous, delta = cur continuous length, i.e. reachable back
        //e.g. abc=x, abcd = x+len(abc) = x+4
    }

    private boolean isContinuous(char cur, char prev){
        if(cur=='a'){
            return prev=='z';
        } else {
            return cur==prev+1;
        }
    }

    public static void main(String [] args){
        UniqSubstrInWraparoundStr467 uu = new UniqSubstrInWraparoundStr467();
        uu.cout(uu.findSubstringInWraproundStringArray("zab"));
        uu.cout(uu.findSubstringInWraproundStringArray("cac"));
    }

    public int findSubstringInWraproundStringArray(String p) {
        if(p== null || p.length()<1){
            return 0;
        }
        int [] dp = new int[26];
        int i = 0; //current index
        int len = p.length();
        char [] cs = p.toCharArray();
        int sum = 1;
        while(i < len){
            char prev = cs[i];//mark current
            i++;//move to next

            //counting the current. Math.max rule out the duplicates
            dp[prev-'a']=Math.max(dp[prev-'a'], sum);

            //continuous
            while(i<len && cs[i]-'a'==(prev-'a'+1)%26){
                prev = cs[i];
                i++;
                sum++;
                dp[prev-'a']=Math.max(dp[prev-'a'], sum);
            }
            //find a non-continuous, prev is the last of continuous
            dp[prev-'a']=Math.max(dp[prev-'a'], sum);
            sum = 1;
        }
        return Arrays.stream(dp).sum();
    }
}
