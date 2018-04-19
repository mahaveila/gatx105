package leetcodeii.Tree;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Erebus on 4/7/18.
 */
public class SerializeAndDeserializeBST  implements Tracker{


    // Encodes a tree to a single string.
    /*
    case 1 2 3-45 sb.append root 2 first, then left then right  213 then 3's children -> 21345
    */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        zip(root, sb);
        cout(sb.toString());
        return sb.toString();
    }

    private void zip(TreeNode root, StringBuilder sb){
        if(root == null){
            return;
        }
        sb.append(root.val).append(",");
        zip(root.left, sb);
        zip(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String [] ss = data.split(",");
        Queue<Integer> q = new LinkedList<>();
        for(String s : ss){
            if(s!=null && s.length()>0){
                q.offer(Integer.parseInt(s));
            }
        }
        //now we have 21345 in the queue
        return unzip(q);
    }

    private TreeNode unzip(Queue<Integer> q){
        if(q.isEmpty()){
            return null;
        }
        //polling the 2 as root.
        TreeNode root = new TreeNode(q.poll());
        cout(root.val);
        Queue<Integer> left = new LinkedList<>();
        //for any peek < than 2, move it to left. i.e. 1
        while(!q.isEmpty() && root.val > q.peek()){
            left.offer(q.poll());//move to left queue
        }
        //1
        root.left = unzip(left);
        //345
        root.right = unzip(q);
        return root;
    }

    public static void main(String [] args){
        SerializeAndDeserializeBST s = new SerializeAndDeserializeBST();
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(1);
        t.right = new TreeNode(3);
        t.right.left = new TreeNode(4);
        t.right.right = new TreeNode(5);
        s.deserialize(s.serialize(t));
    }
}
