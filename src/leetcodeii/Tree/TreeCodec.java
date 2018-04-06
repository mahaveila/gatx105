package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Erebus on 4/2/18.
 */
public class TreeCodec implements Tracker{

    public static void main(String [] args){
        TreeCodec c = new TreeCodec();
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(3);
        n.right.left = new TreeNode(4);
        n.right.right = new TreeNode(5);
        c.cout(c.serialize(n));

        TreeNode dn = c.deserialize(c.serialize(n));
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        goThrough(root, sb);
        return sb.toString();
    }

    /** go through tree use pre-order traversal
     */
    private void goThrough(TreeNode root, StringBuilder sb){
        if(sb.length()>0){
            sb.append(",");
        }
        if(root==null){
            sb.append("X");
        } else {
            sb.append(""+root.val);
            goThrough(root.left, sb);
            goThrough(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String [] ss = data.split(",");
        Queue<String> q = new LinkedList<>();
        for(String s : ss){
            q.offer(s);
        }
        return buildTree(q);
    }

    private TreeNode buildTree(Queue<String> q){
        String s = q.poll();
        cout("buildTree: cur val is : " + s);
        if("X".equals(s)){
            //empty or leaf
            return null;
        } else {
            //do have a value
            int val = Integer.parseInt(s);
            TreeNode root = new TreeNode(val);
            root.left = buildTree(q);
            root.right = buildTree(q);
            return root;
        }
    }
}
