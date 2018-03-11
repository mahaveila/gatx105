package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/10/18.
 */
public class KthSmallestElementInBST {

    int count;
    TreeNode res;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return res==null?0:res.val;
    }

    /**
     use a count to count down the k
     use a helper go through each node, and everytime count down by one

     goes to left first before count down current node
     goes to the right node after count down current node
     basically as same as recursively in order traversal
     */
    private void helper(TreeNode root){
        if(root==null){
            return;
        }
        if(root.left!=null){
            helper(root.left);
        }
        count --;
        if(count==0){
            res = root;
            return;
        }
        helper(root.right);
    }

}
