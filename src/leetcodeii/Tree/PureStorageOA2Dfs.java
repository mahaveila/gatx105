package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/15/18.
 */
public class PureStorageOA2Dfs implements Tracker{

    TreeNode dfs(TreeNode root, int target){
        cout(root.val);
        root.visited=true;

        if(root.val == target){
            return root;
        }

        TreeNode[] nodes = {root.right, root.parent, root.left};
        for(int i=0; i<3; ++i){
            if(nodes[i]!=null && !nodes[i].visited){
                TreeNode node = dfs(nodes[i], target);
                if(node!=null){
                    return node;
                }
            }
        }

        return null;
    }
    public static void main(String [] args){
        PureStorageOA2Dfs p = new PureStorageOA2Dfs();

        TreeNode rt = new TreeNode(5);

        TreeNode r1 = new TreeNode(1);
        TreeNode l1 = new TreeNode(4);
        rt.right=r1;
        r1.parent=rt;
        rt.left=l1;
        l1.parent=rt;

        TreeNode r1l2 = new TreeNode(8);
        r1.left=r1l2; r1l2.parent=r1;
        TreeNode l1l2 = new TreeNode(7);
        TreeNode l1r2 = new TreeNode(3);
        l1l2.parent=l1;
        l1r2.parent=l1;
        l1.left=l1l2;
        l1.right=l1r2;

        TreeNode r1l2r3 = new TreeNode(5);
        TreeNode r1l2l3 = new TreeNode(11);
        r1l2.right=r1l2r3; r1l2r3.parent=r1l2;
        r1l2.left=r1l2l3; r1l2l3.parent=r1l2;
        TreeNode r1l2l3r4 = new TreeNode(5);
        TreeNode r1l2l3l4 = new TreeNode(4);
        r1l2l3l4.parent=r1l2l3;
        r1l2l3r4.parent=r1l2l3;
        r1l2l3.left=r1l2l3l4;
        r1l2l3.right=r1l2l3r4;

        TreeNode l1l2l3 = new TreeNode(8);
        TreeNode l1l2r3 = new TreeNode(1);
        l1l2.left=l1l2l3; l1l2l3.parent=l1l2;
        l1l2.right=l1l2r3; l1l2r3.parent=l1l2;

        TreeNode l1r2r3 = new TreeNode(9);
        l1r2r3.parent=l1r2; l1r2.right=l1r2r3;

        TreeNode last2 = new TreeNode(2);
        TreeNode t = new TreeNode(12);
        t.parent=last2;
        last2.right=t;
        last2.parent=l1r2r3;
        l1r2r3.left=last2;

        p.dfs(rt,12);
    }

}
