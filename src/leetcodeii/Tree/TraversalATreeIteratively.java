package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.*;

/**
 * Created by Erebus on 3/9/18.
 */
public class TraversalATreeIteratively {

//    Here I summarize the iterative implementation for preorder, inorder, and postorder traverse.

//    Pre Order Traverse
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                result.add(p.val);  // Add before going to children
                p = p.left;
            } else {
                TreeNode node = stack.pop();
                p = node.right;
            }
        }
        return result;
    }
//    In Order Traverse
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                //but the maintenance takes O(n)
                stack.push(p);
                p = p.left;
            } else {
                //the pop looks like O(1)
                TreeNode node = stack.pop();
                result.add(node.val);  // Add after all left children
                p = node.right;
            }
        }
        return result;
    }
//    Post Order Traverse
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preorder
                p = p.right;             // Reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left;           // Reverse the process of preorder
            }
        }
        return result;
    }
}
