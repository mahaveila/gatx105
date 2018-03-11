package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/11/18.
 */
public class HouseRob337 {

    /**
     * Assume now houses are in binary tree
     */

    /** FIXME: next availale child tree should be at left.left or left.right, not left
     * approach 1, naive and slow
     */
    public int robWrong(TreeNode root) {
        /**
         first, calculate sub local
         */
        //no house to rob
        if(root==null){
            return 0;
        }

        /**
         yet there is an additional rule, if cur robbed, cur.parent cannot be robbed
         */

        //choose the root, or its 2 children to rob, wrong
//        return Math.max(root.val, robWrong(root.left) + robWrong(root.right));
        //should choose its children's children,
        // null check skipped here

        return Math.max(root.val + robWrong(root.left.left) + robWrong(root.left.right)+robWrong(root.right.left) + robWrong(root.right.right)
        , robWrong(root.left) + robWrong(root.right));
    }

    /**
     * approach 2:
     * use hashMap store robbed house, so avoid re-calculate in other possible rob
     */

    /**
     * approach 3:
     * use an int[] to store the result, since the re-calculate only for left once, and right once, given upper level roots results also stored.
     *
     * there are two scenarios: it is robbed or is not. rob(root) does not distinguish between these two cases,
     * so “information is lost as the recursion goes deeper and deeper”, which results in repeated subproblems.
     *
     * with scenario stored, it populated up each level, no repeated subproblems.
     */
    public int rob(TreeNode root) {
        int[] res = robOrNotRob(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robOrNotRob(TreeNode root){
        if(root==null){
            //no house to rob, return empty array
            //rob[0] means do not rob current house situation
            //rob[1] means rob current house and it's grand children.
            return new int[2];
        }
        int[] left = robOrNotRob(root.left);
        int[] right = robOrNotRob(root.right);

        int rob = root.val + left[0] + right[0]; //cannot rob left and right
        int norob = Math.max(left[0], left[1]) + Math.max(right[0],right[1]); //can choose rob left/right or not, pick max
        int [] res = new int[2];
        res[0] = norob;
        res[1] = rob;
        return res;
    }
}

