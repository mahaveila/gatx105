package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.Stack;

/**
 * Created by Erebus on 3/9/18.
 */
public class BSTIterator implements Tracker{


    public static void main(String [] args){
        TreeNode rt = new TreeNode(5);
        //left 1 2  3  4
        rt.left = new TreeNode(3);
        rt.left.left=new TreeNode(2);
        rt.left.left.left=new TreeNode(1);
        rt.left.right = new TreeNode(4);

        //right BST 6->7<-8
        rt.right = new TreeNode(7);
        rt.right.left = new TreeNode(6);
        rt.right.right = new TreeNode(8);

        BSTIterator i = new BSTIterator(rt);
        while (i.hasNext()){
            int j = i.next();
            i.cout(j);
        }
    }

    Stack<TreeNode> st;
    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        while(root!=null){
            cout("init: push " + root.val);
            st.push(root);
            cout("    st size = " + st.size());
            root=root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        cout("has next!");
        return !st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if(st.isEmpty()){
            return 0;
        }
        TreeNode root = st.pop();
        cout("poped : " + root.val);
        int val = root.val;
        TreeNode right=root.right;
        while(right!=null){
            cout("maintain: push " + right);
            st.push(right);
            right = right.left;
        }
        return val;
    }

}
