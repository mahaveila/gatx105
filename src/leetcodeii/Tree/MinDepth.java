package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/8/18.
 */
public class MinDepth implements Tracker{

    public int minDepth(TreeNode root) {
        return min(root, 0);
    }

    private int min(TreeNode root, int depth){
        if(root==null){
            return depth;
        }
        return Math.min(min(root.left, depth+1), min(root.right, depth+1));
    }

}
