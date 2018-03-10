package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 3/9/18.
 */
public class TreeRightView199 {

    /**
     * we can get the right view as long as we know now just moves to the next level and it's the first from right to left
     * use depth, if depth == res.size(), i.e. at lvl 0, res.size()=0, add lvl 0's right first
     * move to next lvl 1, res.size()=1, holding ele of last lvl
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }

    private void rightView(TreeNode root, List<Integer> res, int depth){
        if(root==null){
            return;
        }
        if(depth==res.size()){
            res.add(root.val);
        }
        rightView(root.right, res, depth+1);
        rightView(root.left, res, depth+1);
    }
    

    public List<Integer> rightSideViewTooComplicated(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        List<TreeNode> lvl = new ArrayList<>();

        if(root!=null){
            lvl.add(root);
        }

        while(!lvl.isEmpty()){
            List<TreeNode> tmp = new ArrayList<>();
            for(int ii=0; ii<lvl.size(); ii++){
                TreeNode cur = lvl.get(ii);
                if(ii==lvl.size()-1){
                    res.add(cur.val);
                }
                if(cur.left!=null){
                    tmp.add(cur.left);
                }
                if(cur.right!=null){
                    tmp.add(cur.right);
                }
            }
            lvl = tmp;
        }
        return res;
    }

}
