package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.Stack;

/**
 * Created by Erebus on 4/12/18.
 */
public class SymmetricBT implements Tracker{

    public static void main(String [] args){
        SymmetricBT s = new SymmetricBT();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        s.isSymmetric(root);
    }

    public boolean isSymmetric(TreeNode root) {


        /* iteration is just to use stack, push the symmetric node together */
        Stack<TreeNode> st = new Stack<>();
        if(root==null){
            return true;
        }
        if(root.left==null && root.right==null){
            return true;
        }
        if(root.left==null || root.right==null){
            return false;
        }
        st.push(root.left);
        st.push(root.right);
        /**
         case   3,2,1,2,3

         push 2 2 ->  bottom[  2, 2] top
         pop get  2,2, 2.l=3, 2.r=null, 2.l=null, 2.r=3
         2 == 2,
         2.l!=null, 2.r!=null, push 3, 3
         */
        while(!st.isEmpty()){
            //need to push 2 nodes each time
            TreeNode r = st.pop();
            TreeNode l = st.pop();

            cout("poped " + l.val +" v.s. " + r.val);
            if(l.val != r.val){
                return false;
            }

            if(l.left!=null){
                if(r.right==null){
                    return false;
                }
                st.push(l.left);
                st.push(r.right);
                cout("pushing " +l.left.val + " v.s. " + r.right.val);
            } else if(r.right!=null){
                return false;
            }

            if(l.right!=null){
                if(r.left==null){
                    return false;
                }
                st.push(l.right);
                st.push(r.left);
                cout("pushing " +l.right.val + " v.s. " + r.left.val);
            } else if(r.left!=null){
                return false;
            }
        }
        return true;
    }
}
