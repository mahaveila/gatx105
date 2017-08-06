package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 8/5/17.
 */
public class CountTheRepetitions466 implements Tracker<String>{

    public static void main (String [] args){
        CountTheRepetitions466 c = new CountTheRepetitions466();

        c.cout(c.getMaxRepetitions("nlhqgllunmelayl", 2, "lnl", 1));

        c.cout("----------------------------------------------------------");

        c.cout(c.getMaxRepetitions2("nlhqgllunmelayl", 2, "lnl", 1));
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (!ableToObtain(s1, s2)) return 0; // check if [s1. ∞] obtains s2
        int cnt=0, k=-1;
        String s=s1;
        StringBuilder remainBuilder; // record `remain string`
        ArrayList<String> stringList=new ArrayList<>(); // record all the `remain string`
        ArrayList<Integer> countList=new ArrayList<>(); // record matching count from start to the current remain string
        stringList.add(""); // record empty string
        countList.add(0);
        for (int i=0;i<=n1;i++) {

            remainBuilder=new StringBuilder();
            cnt+=getRemain(s, s2, remainBuilder); // get the next remain string, returns the count of matching
            String remain=remainBuilder.toString();
            cout(String.format("i=%d, s=%s, remain=%s",i,s,remain));
            if ((k=stringList.indexOf(remain))!=-1) break; // if there is a loop, break
            stringList.add(remain); // record the remain string into arraylist
            countList.add(cnt);
            s=remain+s1; // append s1 to make a new string
        }
        cout(stringList);
        // here, k is the beginning of the loop
        if (k==-1) return cnt/n2; // if there is no loop
        int countOfLoop=cnt-countList.get(k), loopLength=stringList.size()-k; // get matching count in the loop, and loop length
        cout(String.format("loopLen=%d, count/loop=%d", loopLength, countOfLoop));
        cout(countList.stream().map(intv->(""+intv.intValue())).collect(Collectors.toList()));
        cnt=countList.get(k);
        //n1 reduce begin of loop
        n1-=k;
        //calculate how many loops after begin of the loop that could complete, add them to total counts
        //cnt already has the counts before the begin of the loop
        cnt+=countOfLoop*(n1/loopLength);
        //now try to get what remains after loops, i.e. the tail
        n1%=loopLength;
        //check the loop history to find how many cnt remaining could have. n1 now has a value n1<loopLength
        cnt+=countList.get(k+n1)-countList.get(k);
        return cnt/n2;
    }

    // check if [s1. ∞] obtains s2
    private boolean ableToObtain(String s1, String s2) {
        boolean[] cnt=new boolean[26];
        for (char c: s1.toCharArray()) cnt[c-'a']=true;
        for (char c: s2.toCharArray()) {
            if (!cnt[c-'a']) return false;
        }
        return true;
    }

    // get remain string after s1 obtains s2, return the matching count
    // if nothing matched, remaining will be appended to prev remaining to extend s1
    // if sth matched, will only get the remaining after last matched.
    private int getRemain(String s1, String s2, StringBuilder remain) {
//        cout(String.format("  get remaining from %s -> %s", s1 , s2));
        int cnt=0, lastMatch=-1, p2=0;
        for (int p1=0;p1<s1.length();p1++) {
            if (s1.charAt(p1)==s2.charAt(p2)) {
                //check if s2 is completed.
                if (++p2==s2.length()) {
                    //if so, move p2 back to start, to count it one more time
                    p2=0;
                    cnt++;
                    //mark the end of one completely match.
                    lastMatch=p1;
                }
            }
        }
        //substring(begin index), get the remaining.
        remain.append(s1.substring(lastMatch+1));
//        cout("  getRemain()="+cnt);
        return cnt;
    }


    /**
     * Practice ////////////////////////////////////////////////////////////////////////////////////
     */
    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        if(!couldForm(s1, s2)){
            return 0;
        }
        ArrayList<String> remainings = new ArrayList<>();
        //FIXME : done, need to add empty string
        remainings.add("");
        //FIXME : done, count also add 0, corresponding to the beginning, for the case that no remaining left
        ArrayList<Integer> counts = new ArrayList<>();
        counts.add(0);
        String remainAndS1 = s1;
        StringBuilder newRemain;
        int cnt = 0;
        int l = -1; //beginning of the loop
        for(int i = 0; i<n1; i++){
            newRemain = new StringBuilder(); //the carrier of the remain from sub function, cuz String immutable
            cnt += count(remainAndS1, s2, newRemain);
            String remain = newRemain.toString();
            cout(String.format("i=%d, s=%s, remain=%s",i,remainAndS1,remain));
            if(remainings.indexOf(remain)!=-1){
                //found previous remaining, loop detected.
                l = remainings.indexOf(remain);
                break;
            }
            counts.add(cnt);//record current count at i
            remainings.add(remain);
            //FIXME: done, not remain + remainAndS1, just remain+s1, keep fresh everytime. case a->aaaa, rolling in getRemain(), not here.
            remainAndS1 = remain + s1;
        }
        cout(remainings);
        if(l==-1){
            //for running out all n1, and no loop found
            return cnt/n2;
        }
        //otherwise
        //1. cnt has the count before entering the loop


        //2. get counts from loop itself
        n1 -= l; //reduce ones before loop
        int loopLength = remainings.size() - l;
        int countPerLoop = cnt - counts.get(l);
        cout(String.format("loopLen=%d, count/loop=%d", loopLength, countPerLoop));
        cout(counts.stream().map(intv->(""+intv.intValue())).collect(Collectors.toList()));
        //FIXME: cnt should be at the before entering the loop before aggregation, after calculate param for loop
        cnt = counts.get(l);
        cnt += n1/loopLength*countPerLoop;
        //3. get counts after loops.
        n1 %= loopLength;
        cnt += counts.get(l+n1)-counts.get(l);
        return cnt/n2;
    }

    private boolean couldForm(String s1, String s2){
        boolean [] ary = new boolean[256];
        for(char c : s1.toCharArray()){
            ary[c-'a']=true;
        }
        for(char c : s2.toCharArray()){
            if(!ary[c-'a']){
                return false;
            }
        }
        return true;
    }

    private int count(String s1, String s2, StringBuilder sb){
//        cout(String.format("  get remaining from %s -> %s", s1 , s2));
        int p2 = 0;
        int count = 0;
        int lastMatch = -1;
        for(int i=0; i<s1.length();i++){
            if(s1.charAt(i)==s2.charAt(p2)){
                if(++p2==s2.length()){
                    //last one at s2, looping s2 for one more match
                    p2=0;
                    lastMatch=i;
                    count++;
                }
            }
        }
        sb.append(s1.substring(lastMatch+1));
//        cout("  getCount()="+count);
        return count;
    }
}
