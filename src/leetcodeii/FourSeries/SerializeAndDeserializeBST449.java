package leetcodeii.FourSeries;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Erebus on 8/30/17.
 */
public class SerializeAndDeserializeBST449 implements Tracker{

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> q = new LinkedBlockingQueue<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            sb.append(cur.val);
            if(cur.left!=null){
                q.add(cur.left);
            }
            if(cur.right!=null){
                q.add(cur.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //return condition
        if(data==null||data.length()<1){
            return null;
        }
        TreeNode head = new TreeNode(0);
        head.val = Integer.parseInt(data.substring(0,1));
        if(data.length()<2){
            return head;
        }
        int right = 0;
        for(int ii = 1; ii<data.length();ii++){
            int cur = Integer.parseInt(""+data.charAt(ii));
            if(cur>=head.val){
                right = ii;
                break;
            }
        }

        if(right>0){
            //there is right node
            head.right=deserialize(data.substring(right));
        }
        if(right>1){
            //there is left node before right node
            head.left=deserialize(data.substring(1,right));
        } else if(data.length()>1 && right==0){
            //there are only left nodes
            head.left=deserialize(data.substring(1));
        }

        return head;
    }

    public static void main(String [] args){
        SerializeAndDeserializeBST449 s = new SerializeAndDeserializeBST449();
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(1);
        head.left.right = new TreeNode(2);
        head.right = new TreeNode(4);
        s.deserialize(s.serialize(head));
        s.cout(s.serialize(head));
    }

}
