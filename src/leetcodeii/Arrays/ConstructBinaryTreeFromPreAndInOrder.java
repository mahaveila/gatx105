package leetcodeii.Arrays;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 2/23/18.
 */
public class ConstructBinaryTreeFromPreAndInOrder implements Tracker{

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        cout(preorder);
        cout(inorder);
        return buildTree(preorder, inorder, 0, 0, inorder.length-1);
    }

    private TreeNode buildTree(int [] preorder, int [] inorder, int preIdx, int left, int right){
        if(left>right ||left<0 || right>=inorder.length){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preIdx]);

        int rt = left;
        while(rt<right){ //find the root in the inorder array
            if(inorder[rt]==preorder[preIdx]){
                break;
            }
            rt++;
        }
        //[rt, left eles, right eles], use cur root's left children length to calculate next left root index and right index,
        node.left = buildTree(preorder, inorder, preIdx+1, left, rt-1);
        node.right = buildTree(preorder, inorder, preIdx+rt-left +1, rt+1, right);
        return node;
    }

    public static void main(String [] args){
        ConstructBinaryTreeFromPreAndInOrder c = new ConstructBinaryTreeFromPreAndInOrder();

        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        c.buildTree(pre, in);
    }

}
