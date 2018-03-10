package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/9/18.
 */
public class SumRootToLeafNumbers129 implements Tracker{

    public int sumNumbers(TreeNode root) {
        //FIXME: Fixed, all the below check is as same as helper, helper can handle it.
//        if(root==null){
//            return 0;
//        }
//        if(root.left==null && root.right==null){
//            return root.val;  //== prev*10 + root.val, when pre==0
//        }
//        return helper(root.left, root.val) + helper(root.right, root.val);
        return helper(root, 0);
    }

    private int helper(TreeNode root, int pre){
        cout("helper at root : " + root.val);
        if(root==null){
            return 0; //should return value at upper level
        }
        //pre should be root's root's value
        if(root.left==null && root.right==null){
            //leaf
            cout("heper at leaf: " + root.val);
            return pre*10 + root.val;
        }

        return helper(root.left, pre*10 + root.val) + helper(root.right, pre*10 + root.val);
    }

    public static  void  main(String [] args){
        TreeNode rt = new TreeNode(1);
        rt.left = new TreeNode(0);
        SumRootToLeafNumbers129 s = new SumRootToLeafNumbers129();
        s.cout(s.sumNumbers(rt));
    }

}
