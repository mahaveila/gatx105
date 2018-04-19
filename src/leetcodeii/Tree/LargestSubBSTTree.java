package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 4/15/18.
 */
public class LargestSubBSTTree implements Tracker{

    public static void main(String [] args){
        LargestSubBSTTree l = new LargestSubBSTTree();
        TreeNode n = new TreeNode(4);
        n.left = new TreeNode(1);
        n.left.left = new TreeNode(2);
        n.left.left.left = new TreeNode(3);
        l.cout(l.largestBSTSubtree(n));
    }

    public static class Result{
        int min;
        int max;
        boolean isBST;
        int node;
        public Result(int min, int max, boolean isBST, int node){
            this.min = min;
            this.max = max;
            this.isBST = isBST;
            this.node = node;
        }

        public Result(){

        }
    }

    public int largestBSTSubtree(TreeNode root) {
        if(root == null){
            return 0;
        }
        Result res = find(root);
        return res.node;
    }

    private Result find(TreeNode root){
        if(root == null){
            Result res =  new Result(0, 0, true, 0);
            cout(String.format("Cur node = %s, Result: min=%d, max=%d, node=%d, isBST=" + " YES", "NULL", res.min, res.max, res.node));
            return res;
        }
        Result resL = find(root.left);
        Result resR = find(root.right);
        if(resL.isBST && resR.isBST){
            //min stands for lower boundary provided by child
            //max            upper
            //e.g. int leftMax = l.max
            if((resL.node>0 && root.val <= resL.max) || (resR.node>0 && root.val >= resR.min)){
                //not valid
                Result res = resL.node > resR.node? resL : resR;
                res.isBST = false;
                cout(String.format("Cur node = %d, Result: min=%d, max=%d, node=%d, isBST=" + res.isBST, root.val, res.min, res.max, res.node));
                return res;
            }
            Result res = new Result(root.val, root.val, true, 1);
            //now it could BST of null children
            if(resL.node>0){
                //left not null
                if(root.val > resL.max){
                    //satisfy BST
                    res.node += resL.node;
                    //expanding both sides of BST
                    res.min = resL.min;
                }
            }
            if(resR.node>0){
                if(root.val < resR.min){
                    res.node += resR.node;
                    res.max = resR.max;
                }
            }
            cout(String.format("Cur node = %d, Result: min=%d, max=%d, node=%d, isBST=" + res.isBST, root.val, res.min, res.max, res.node));
            return res;
        } else {
            //children are not BST, no need to validate cur node again.

            Result res = resL.node > resR.node? resL : resR;
            res.isBST = false;
            cout(String.format("Cur node = %d, Result: min=%d, max=%d, node=%d, isBST=" + res.isBST, root.val, res.min, res.max, res.node));
            return res;
        }
    }

    public int largestBSTSubtreeBUG(TreeNode root) {
        if(root == null){
            return 0;
        }
        int [] res = helperBUG(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return res[1];
    }

    /**
     * FIXME : the bug is that even 4's next is 1, 1 > than 3, so 341 is not a valid BST, however 41 is.
     * TODO: to validate either 341 or 41, we need not only the boundary populated from above.
     *
     * TODO: instead, we need to bubble boundary up.
     * @param root
     * @param min
     * @param max
     * @return
     */
    //returns an array, 1st element is 1/-1, means if it is a valid bst, 2nd element is the result of current node
    private int[] helperBUG(TreeNode root, long min, long max){
        int [] res = {1, 0};
        if(root==null){
            return res; //leaf's children, valid BST
        }
        /*
        case :    3
                      2
                    1
        root = 3, 3.left -> {1, 0}
                  3.right -> root = 2


        */

        if(root.val < max && root.val > min){
            //this children is a valid BST node, it may combine the solution of it's children
            int [] l = helperBUG(root.left, min, root.val);
            int [] r = helperBUG(root.right, root.val, max);
            if(l[0]==1 && r[0]==1){
                //both end BST
                res[0]=1;
                res[1]=l[1] + r[1] + 1;
            } else {
                //one of it's children is not valid BST, thus current is not a valid BST
                res[0]=-1;
                res[1]=Math.max(l[1], r[1]);
            }
            cout("cur root = "+root.val+", left is " + stringfy(l) + ", right is " + stringfy(r) + " => res = " + stringfy(res));
        } else {
            //this children is not a valid BST node. it's children can reset boundaries
            int [] l = helperBUG(root.left, Long.MIN_VALUE, root.val);
            int [] r = helperBUG(root.right, root.val, Long.MAX_VALUE);
            cout(" break at " + root.val + ", min = " + min + ", max = " + max);
            if(l[0]==1 && r[0]==1){
                //both end BST
                res[0]=-1;
                res[1]=l[1] + r[1] + 1;
            } else {
                //one of it's children is not valid BST, thus current is not a valid BST
                res[0]=-1;
                res[1]=Math.max(l[1], r[1]);
            }
            cout("cur root = "+root.val+", left is " + stringfy(l) + ", right is " + stringfy(r) + " => res = " + stringfy(res));
        }
        return res;
    }

}
