package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erebus on 4/7/18.
 */
public class TreeZigZagTraversal implements Tracker{

    public static void main(String [] args){
        TreeZigZagTraversal z = new TreeZigZagTraversal();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(12);
        z.zigzagLevelOrder(root);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /*
        the idea is maintain a next list, and use level to determine insert in front or from back
        KEY: we have multiple list here, we can use the lvl trick.
        But how to do it without the lvl trick?

        */


        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        if(root==null){
            return res;
        }

        deque.offer(root);
        int lvl = 0;
        while(!deque.isEmpty()){
            int size = deque.size();
            cout("lvl: " + lvl + ", size = " + size);
            List<Integer> tmp = new ArrayList<>();
            for(int ii = 0; ii < size; ii ++){
                TreeNode node = deque.pollLast();
                tmp.add(node.val);
                if(lvl%2==0){
                    if(node.right!=null){
                        deque.addFirst(node.right);
                    }
                    if(node.left!=null){
                        deque.addFirst(node.left);
                    }
                } else {
                    if(node.left!=null){
                        deque.addLast(node.left);
                    }
                    if(node.right!=null){
                        deque.addLast(node.right);
                    }
                }
            }
            res.add(tmp);
            lvl ++;
        }
        return res;
    }


}
