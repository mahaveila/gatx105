package leetcodeii.FourSeries;

import leetcodeii.Tracker;
import leetcodeii.TreeNode;

import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Erebus on 8/30/17.
 */
public class SerializeAndDeserializeBST449 implements Tracker{

    private String SEP = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();

        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){

            TreeNode cur = st.pop();
            if(sb.length()>0){
                sb.append(SEP);
            }
            sb.append(""+cur.val);
            if(cur.right!=null){
                st.push(cur.right);
            }
            if(cur.left!=null){
                st.push(cur.left);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] in = data.split(SEP);
        cout(in);
        return helper(in);
    }

    private TreeNode helper(String [] in){
        //return condition
        if(in==null||in.length<1){
            return null;
        }
        TreeNode head = new TreeNode(0);
        head.val = Integer.parseInt(in[0]);
        cout("root : " + head.val);
        if(in.length<2){
            return head;
        }

        int right = 0;
        for(int ii = 1; ii<in.length;ii++){
            int cur = Integer.parseInt(in[ii]);
            if(cur>=head.val){
                right = ii;
                break;
            }
        }
        String[] leftAry =new String[0];
        String[] rightAry = new String[0];

        if(right==0){
            leftAry = Arrays.copyOfRange(in, 1, in.length);
        } else {
            rightAry = Arrays.copyOfRange(in, right, in.length);
            if(right>1){
                leftAry=Arrays.copyOfRange(in, 1, right);
            }
        }

        cout("  left child " + stringfy(leftAry));
        cout("  right child " + stringfy(rightAry));

        head.left=helper(leftAry);
        head.right=helper(rightAry);

        return head;
    }

    public static void main(String [] args){
        SerializeAndDeserializeBST449 s = new SerializeAndDeserializeBST449();
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(1);
        head.left.right = new TreeNode(2);
        head.right = new TreeNode(4);
        TreeNode res = s.deserialize(s.serialize(head));
        s.cout(s.serialize(head));
        s.cout(s.serialize(res));

        TreeNode h2 = new TreeNode(2);
        h2.left=new TreeNode(1);

        s.cout(s.serialize(h2));
        s.deserialize(s.serialize(h2));
    }

}
