package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/9/18.
 */
public class MathPathSumAnyStartEnd124 {

    int global;

    public int maxPathSum(TreeNode root) {
        global = Integer.MIN_VALUE;
        maxDownSum(root);
        return global;
    }

    private int maxDownSum(TreeNode root){
        if(root==null){
            return 0;
        }
        //use 0 to cut out negative branch/leaves
        //single left, can go up
        int left = Math.max(0, maxDownSum(root.left));
        //single right, can also go up
        int right = Math.max(0, maxDownSum(root.right));

        //assume current root is the lowest common parent for left and right ranch (of the max path), calculate global
        global = Math.max(global, left+right+root.val);
        //choose the bigger side to go up, since only one side is allowed to go up
        return Math.max(left, right) + root.val;
    }

}

