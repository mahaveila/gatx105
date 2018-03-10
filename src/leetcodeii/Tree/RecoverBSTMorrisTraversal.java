package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

/**
 * Created by Erebus on 3/6/18.
 */
public class RecoverBSTMorrisTraversal implements Tracker{

    public static void main(String [] args){
        RecoverBSTMorrisTraversal r = new RecoverBSTMorrisTraversal();
        TreeNode root = new TreeNode(2);
        root.left = null;
        root.right = new TreeNode(3);
        root.right.left=new TreeNode(1);
        r.recoverTree(root);
    }


    public void recoverTree(TreeNode root) {


        TreeNode first = null;
        TreeNode second = null;
        //O(n) space? traversal and swap value
        //but O(1) space? use morris traversal, connecting root's last ancestor with root using threading, then root, then root's right. use 2 vairables to store the 2 nodes.

        //FIXME: the threadNode is not always the previous Node.
        /**
         * for threading in right children, the thread moves along right.left nodes, all the way till root's successor
         * ---> its prev node is still at root (successor has no left children any more)
         *
         * when iterated the successor, it has no left child, moves to right child
         * NOW update prev=successor, (i.e. another root), and cur=cur.right, i.e. root.right
         *
         * thus, we only set prev when there's no left children, and moves to right children
         * OR, when cur's predecessor.right == cur, already iterated predecessor, now is iterating cur,
         * then update prev=cur, cur=cur.right, & cut threading.
         *
         */
        TreeNode prev = null;
        TreeNode threadNode = null;
        TreeNode cur = root;


        while(cur!=null){

            if(cur.left==null){
                cout("node " + cur.val+ " has no left child");
                //has no left child, in-order: cur's turn
                if(prev!=null && prev.val > cur.val){
                    cout("[X] find a invalid: prev= " + prev.val + ", cur= " + cur.val);
                    if(first==null){
                        first=prev;
                        second=cur;
                    } else {
                        second=cur;
                    }
                }
                //keeps going
                prev=cur;
                cur=cur.right;
                cout(String.format("Move to right:     prev now at %d, cur at %d, threadNode at %d", prev==null?-1:prev.val, cur.val, threadNode==null?-1:threadNode.val));
            } else {
                //has left child
                threadNode = cur.left;
                //case 1, predecessor      case 2, threaded already
                while(threadNode.right !=null && threadNode.right!=cur){
                    threadNode = threadNode.right;
                }

                cout("predecessor found at: " + threadNode.val);
                if(threadNode.right==null){
                    cout("threading prev " + threadNode.val +" to " + cur.val);
                    //find the predecessor, threading to cur
                    threadNode.right = cur;
                    //moving cur to cur's left child, whose rightest node has been threaded
                    cur = cur.left;
                    cout(String.format("threading:     prev now at %d, cur at %d, threadNode at %d", prev==null?-1:prev.val, cur.val, threadNode==null?-1:threadNode.val));
                } else {
                    //the prev has been threaded, we've been here already
                    //i.e. prev.right = cur, cutting the thread to recover structure
                    cout("cutting prev "+threadNode.val+" 's thread to cur： " + cur.val);
                    threadNode.right = null;
                    //handle cur and move to cur.right
                    if(prev!=null && prev.val>cur.val){
                        cout("[X] find a invalid: prev= " + threadNode.val + ", cur= " + cur.val);
                        if(first==null){
                            first = prev;
                            second = cur;
                        } else {
                            second = cur;
                        }
                    }
                    prev = cur;
                    cur = cur.right;
                    cout(String.format("Un-threading:     prev now at %d, cur at %d, threadNode at %d", prev==null?-1:prev.val, cur.val, threadNode==null?-1:threadNode.val));
                }
            }
        }
        //above all the way to the end, cur=cur.right -> == null
        //swap the wrong nodes
        int tmp = first.val;
        first.val=second.val;
        second.val = tmp;

    }

}
