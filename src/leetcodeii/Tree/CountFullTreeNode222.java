package leetcodeii.Tree;

import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/10/18.
 */
public class CountFullTreeNode222 {
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }

        //left children is always full, use right to decide if full or not
        int h = getHeight(root);
        return h<0? 0: (h-1)==getHeight(root.right)?
                /** FULL TREE, Nodes # = 2^h-1. (h=1, n=1, h=3, n=7)
                 If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.

                 If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
                 */
                //right has same height as left, left side must be a full tree, count all left nodes as h level
                ((1<<h) + countNodes(root.right)):
                //right h != root.h-1, means right side not full, but also means left is full at h-1 level, count one less level (full tree on right) and plus counting the left side.
                ((1 << (h-1)) + countNodes(root.left));
    }

    //left is always full
    private int getHeight(TreeNode node){
        if(node==null){
            return -1;
        }
        //-1 is because the +1 in recursion, and root level =1 need height=0, so 1 << 0 =1, represents  1 node
        //also because count= 2^h-1. node=null, return -1, node not null but a leaf, should be 2^0-1.
        return 1+getHeight(node.left);
    }
}
