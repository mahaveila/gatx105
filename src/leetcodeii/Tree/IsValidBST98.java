package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/11/18.
 */
public class IsValidBST98 {

    /**
     * In case of node.val = Integer.MIN_VALUE or max value
     * use Long to set the boundary
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    private boolean isValidBST(TreeNode root, long min, long max){
        if(root==null){
            return true;
        }
        return root.val>min && root.val<max &&
                isValidBST(root.left, min, root.val) &&
                isValidBST(root.right, root.val, max);
    }


    /**
     * FIXME: WRONG
     * because it doesn't check root left child agains root's parent
     * e.g.  root.parent=10, 10.right=root=15, root.left=6
     *     10
     *    8   15
     *  1    6  20
     *  is not valid
     * @param root
     * @return
     */
    public boolean isValidBSTWrong(TreeNode root) {
        if(root==null){
            return true;
        }
        boolean sub = true;
        if(root.right!=null){
            sub = sub && root.right.val>root.val && isValidBST(root.right);
        }
        if(root.left!=null){
            sub = sub && root.left.val < root.val && isValidBST(root.left);
        }
        return sub;
    }

}
