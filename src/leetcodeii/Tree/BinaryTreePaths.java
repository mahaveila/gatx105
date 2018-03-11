package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erebus on 3/10/18.
 */
public class BinaryTreePaths {

    /**
     * Top down
     */
    public List<String> binaryTreePathsTopDown(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(root, res, "");
        return res;
    }

    private void helper(TreeNode root, List<String> res, String cur){
        if(root==null){
            return;
        }
        if(cur.length()<1){
            cur += root.val;
        } else {
            cur += ("->" + root.val);
        }

        if(root.left==null && root.right==null){
            //a leaf
            res.add(cur);
        }
        if(root.right!=null){
            //left has more
            helper(root.right, res, cur);
        }
        if(root.left!=null) {
            //right has more
            helper(root.left, res, cur);
        }
    }

    /**
     * Bottom up, Optimized
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> paths = new LinkedList<>();

        if(root == null) return paths;

        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

        for (String path : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + path);
        }

        for (String path : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + path);
        }

        return paths;

    }
}
