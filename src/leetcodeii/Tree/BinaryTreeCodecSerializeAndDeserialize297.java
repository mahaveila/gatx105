package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Erebus on 3/11/18.
 */
public class BinaryTreeCodecSerializeAndDeserialize297 {

    /** approach 1 for lvl traversal, doesn't work
     if right sub tree is 2 or more levels deeper than the left, then the left bottom will be messed up
     1
     2   3
     # # 4  5
     6  # #
     7 8
     # ## #    hard to figure out where to create # node when deserialize.
     List<Character> nodes is used for store those lvl nodes;
     */

    /**
     approach 2, use pre-order traversal. Put into a queue.  mark leaf as #, --> 123##45...etc
     -> when dequeue, pop a node at a time. e.g. 1, 2, 3, when equals to #, return null in the function
     i.e. 2.left and 2.right are null, because there are 2 # after 3.
     notice when creating 3, we calling function on 3.left and 3.right, which are not # -> because previous 2 #s poped,
     now it's poping 6 and #, so 3.left=6, 3.right=#
     ---> no children after left subtree 2, so no pop will be called. So 6, 7, 8 will keep calling pop, and put them in right position
     */

    /**  Character won't work, because there could be value >9, which needs more than 1 characters */

    String splitter = ",";
    String end = "X";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root==null){
            sb.append(end).append(splitter);
        } else if(root!=null){
            sb.append(root.val).append(splitter);
            sb.append(serialize(root.left));
            sb.append(serialize(root.right));
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null){
            return null;
        }
        String [] strs = data.split(splitter);
        Queue<String> q = new LinkedList<>();
        for(String s : strs){
            q.offer(s);
        }
        return buildTree(q);
    }

    private TreeNode buildTree(Queue<String> q){
        if(q.isEmpty()){
            return null;
        }
        String root = q.poll();
        if("".equals(root) || end.equals(root)){
            //the last one is after the last "," sign, would be empty
            return null;
        }
        TreeNode res = new TreeNode(Integer.parseInt(root));

        //else can keep dequeue to get next node, current node can have deeper nodes
        //get cur value and assign new node
        res.left = buildTree(q);
        res.right = buildTree(q);

        return res;
    }

}
