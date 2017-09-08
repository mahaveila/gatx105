package leetcodeii.FourSeries;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 9/8/17.
 */
public class SumOfLeftLeaves403 implements Tracker{

    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null){
            return 0;
        }

        int res = 0;
        int left= sumOfLeftLeaves(root.left);
        int right= sumOfLeftLeaves(root.right);
        res +=left;
        res +=right;
        int cur =(root.left!=null&&root.left.right==null&&root.left.left==null)?root.left.val:0;
        res += cur;
        cout(String.format("root=%d, left=%d, right=%d, cur=%d", root.val, left, right, cur));
        return res;
    }

    public static void main(String [] args){
        TreeNode right = new TreeNode(20);
        right.left=new TreeNode(15);
        right.right=new TreeNode(7);
        TreeNode root = new TreeNode(3);
        root.right = right;
        root.left = new TreeNode(9);
        SumOfLeftLeaves403 s = new SumOfLeftLeaves403();
        s.cout(s.sumOfLeftLeaves(root));
    }
}
