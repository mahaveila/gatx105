package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/10/18.
 */
public class FIndLowestCommonAncestor {

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestorBST(root.right, p, q);
        } else if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestorBST(root.left, p, q);
        } else {
            //if one of them equals to root, then it must contains
            return root;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q){
            //root is null, leaf, of course return null
            //or probably root is one of them
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        /**
         if left and right both are not null, means find one of them in each side.
         */
        if(left!=null&&right!=null){
            return root;
        }
        return left!=null?left:right;
    }

}
