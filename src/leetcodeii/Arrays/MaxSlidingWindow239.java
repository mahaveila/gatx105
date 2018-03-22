package leetcodeii.Arrays;

/**
 * Created by Erebus on 3/14/18.
 */
public class MaxSlidingWindow239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length<1||k<=0){
            return new int[0];
        }
        /*
        the key here is divide the numbers into blocks

        {    A    }{    B    }{    C    }{ D }, with width = k
        assume we have a window
                        [  a  | b  ], it's divided into two parts, sitting in different blocks
                        i     k    j
                        assume ma = max in section a, mb = max in section b
                the max of the window = max(ma, mb)


                and ma = scanning from block boundary, i.e. the middle of a and b, AKA boundary of block B and block C

                why block boundary B-C? because A-B will possibly carry out of window element, if that value is larger.

                    that said, ma = max scanning from right to left
                    for(int x=k; x>=i; x--){
                        ma = Math.max(ma, section_a[x])
                    }
                similarly, mb = scanning from k to j, i.e. from left to right, reducing to Math.max(mb, section_b[x])
            Now we have [ma <---|---> mb]

            pre-compute the ma and mb for each boundary
            [ma<------][ma<------][ma<------][ma<--] = int[] leftward
            [------>mb][------>mb][------>mb][-->mb] = int[] rightward
            for a given window [      ] , max = Math.max(ma, mb) = Math.max(leftward[i], rightward[j])
                                i    j
            look back, choose block size=k, is because that's the far-est we want to carry a value, otherwise, there
            could be out of window value, which is not valid
        */
        int[] leftward= new int[nums.length];
        int[] rightward= new int[nums.length];
        int mb = nums[0];
        for(int ii=0; ii<nums.length;ii++){
            if(ii%k==0){
                mb=nums[ii];
            } else {
                mb=Math.max(mb, nums[ii]);
            }
            rightward[ii] = mb;
        }
        int ma = nums[nums.length-1];
        for(int ii=nums.length-1; ii>=0; ii--){
            if(ii%k==0){
                ma=nums[ii];
            } else {
                ma=Math.max(ma, nums[ii]);
            }
            leftward[ii] = ma;
        }
        int[] res = new int[nums.length-k+1];//[0,1,2,3],4 k=4, size=5, res_size=5-4+1=2
        for(int ii=0; ii<res.length; ii++){
            res[ii] = Math.max(leftward[ii], rightward[ii+k-1]); //i.e. cur=0, mb_idx=0+4-1=3.
        }
        return res;
    }
}
