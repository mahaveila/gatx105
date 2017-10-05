package leetcodeii.FourSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Erebus on 9/17/17.
 */
public class StrongPasswordChecker420 {


    /**
     * TODO: summarize
     *
     * @param s
     * @return
     */
    public int strongPasswordChecker(String s) {

        if(s.length()<2) return 6-s.length();

        //Initialize the states, including current ending character(end), existence of lowercase letter(lower), uppercase letter(upper), digit(digit) and number of replicates for ending character(end_rep)
        char end = s.charAt(0);
        boolean upper = end>='A'&&end<='Z', lower = end>='a'&&end<='z', digit = end>='0'&&end<='9';

        //Also initialize the number of modification for repeated characters, total number needed for eliminate all consequnce 3 same character by replacement(change), and potential maximun operation of deleting characters(delete). Note delete[0] means maximum number of reduce 1 replacement operation by 1 deletion operation, delete[1] means maximun number of reduce 1 replacement by 2 deletion operation, delete[2] is no use here.
        int end_rep = 1, change = 0;
        int[] delete = new int[3];

        for(int i = 1;i<s.length();++i){
            if(s.charAt(i)==end) ++end_rep;
            else{
                change+=end_rep/3;
                if(end_rep/3>0) ++delete[end_rep%3];
                //updating the states
                end = s.charAt(i);
                upper = upper||end>='A'&&end<='Z';
                lower = lower||end>='a'&&end<='z';
                digit = digit||end>='0'&&end<='9';
                end_rep = 1;
            }
        }
        change+=end_rep/3;
        if(end_rep/3>0) ++delete[end_rep%3];

        //The number of replcement needed for missing of specific character(lower/upper/digit)
        int check_req = (upper?0:1)+(lower?0:1)+(digit?0:1);

        if(s.length()>20){
            int del = s.length()-20;

            //Reduce the number of replacement operation by deletion
            if(del<=delete[0]) change-=del;
            else if(del-delete[0]<=2*delete[1]) change-=delete[0]+(del-delete[0])/2;
            else change-=delete[0]+delete[1]+(del-delete[0]-2*delete[1])/3;

            return del+Math.max(check_req,change);
        }
        else return Math.max(6-s.length(), Math.max(check_req, change));
    }





    /**
     * 当密码串长度小于6时，情况一和情况二的操作步骤可以完全覆盖情况三，这个不难理解，因为这种情况下重复字符个数的范围为[3,5]，如果有三个重复字符，那么增加三个字符的操作可以同时解决重复字符问题("aaa" -> "a1BCaa"；如果有四个重复字符，那么增加二个字符的操作也可以解决重复问题("aaaa" -> "aa1Baa")；如果有五个重复字符，那么增加和置换操作也同时解决重复问题("aaaaa" -> "aa1aaB")。所以我们就专心看最少多少步能同时解决情况一和情况二，首先我们计算出当前密码串需要补几个字符才能到6，补充字符的方法只能用插入字符操作，而插入字符操作也可以解决情况二，所以当情况二的缺失种类个数小于等于diff时，我们不用再增加操作，当diff不能完全覆盖缺失种类个数时，我们还应加上二者的差值。
     *
     * 不能无脑删除字符，这样不一定能保证是最少步骤，所以在解决情况三的时候还要综合考虑到情况一，这里用到了一个trick (很膜拜大神能想的出来)，对于重复字符个数k大于等于3的情况，我们并不是直接将其删除到2个，而是先将其删除到最近的(3m+2)个，那么如果k正好被3整除，那么我们直接变为k-1，如果k除以3余1，那么变为k-2。这样做的好处是3m+2个重复字符可以最高效的用替换m个字符来去除重复。
     *
     * if more than 6,
     * condition 1: 首先我们算出超过20个的个数over，我们先把over加到结果res中，因为无论如何这over个删除操作都是要做的。如果没超过，over就是0，
     * condition 2: 用变量left表示解决重复字符最少需要替换的个数，初始化为0。
     * condition 3a: 然后我们遍历之前统计字符出现个数的数组，如果某个字符出现个数大于等于3，且此时over大于0，那么我们将个数减为最近的3m+2个，over也对应的减少，注意，一旦over小于等于0，不要再进行删除操作。
     * condition 3b: 如果所有重复个数都减为3m+2了，但是over仍大于0，那么我们还要进一步的进行删除操作，这回每次直接删除3m个，直到over小于等于0为止，剩下的如果还有重复个数大于3的字符，我们算出置换字符需要的个数直接加到left中即可，最后我们比较left和missing，取其中较大值加入结果res中即可
     */
    public int strongPasswordCheckerWrongImpl(String s) {
        int res = 0;
        int n = s.length();
        int lower = 1;
        int upper = 1;
        int digit = 1;
        List<Integer> threes = new ArrayList<>();
        char [] cs = s.toCharArray();
        for(int ii = 0; ii < cs.length; ii++) {
            char c= cs[ii];
            if(c-'0'>=0 && c-'0'<=9){
                //digit
                digit = 0;
            }
            if(c-'a'>=0 && c-'z'<=25){
                //lower
                lower = 0;
            }
            if(c-'A'>=0 && c-'A'<=25){
                //upper
                upper = 0;
            }
            int jj = ii;
            while(ii<cs.length && cs[jj]==cs[ii]){
                //skip the same chars
                ++ii;
            }
            threes.add(ii-jj);
        }
        int missing = upper + lower + digit;
        if(s.length()<6){
            int miss = 6-s.length();
            //if insertion cannot resolve both case 1 and 2, need to use replace as well
            //insertion&replace will solve 3 for sure.
            // e.g. aaaaa -> aaBaaa (missing = 2, miss = 1, insert resolve 3) -> aaBaa9, replacement resolve 3
            res += miss  + Math.max(0, missing - miss);
        } else {
            int over = Math.max(s.length()-20, 0);
            int left = 0;
            //gonna delete them anyway
            res+= over;
            List<Integer> updated = new ArrayList<>();
            updated.addAll(threes);
            //calculate 3m+2 deletions
            for(int k=1; k<3; ++k){
                //deleting until size = 20, smart deleting to 3m+2, so replacement m also resove repeats 3m case
                for(int ii = 0; ii<threes.size() && over > 0;ii++){
                    int val = threes.get(ii);
                    if(val < 3 && val%3!=k-1){
                        //skip non-repeats case
                        //and skip repeats but not current 3m+2 case, e.g. aaa(k=1)->aa, skip aaaa(k=2) case
                        continue;
                    }
                    updated.remove(ii);
                    updated.add(ii, val-k);
                    over -=k;
                }
            }
            threes = updated;
            updated = new ArrayList<>();
            updated.addAll(threes);
            //if size still > 20 after resolve all 3m+2 repeats, just delete 3m each time till size <= 20
            for(int ii =0; ii<threes.size(); ii++){
                int i = threes.get(ii);
                if(i>=3 && over>0){
                    int need = i - 2;
                    updated.remove(ii);
                    updated.add(ii, i-over);
                    over -= need;
                }
                if(updated.get(ii)>=3){
                    left += updated.get(ii)/3;
                }
            }
            res += Math.max(missing, left);
        }
        return res;
    }
}
