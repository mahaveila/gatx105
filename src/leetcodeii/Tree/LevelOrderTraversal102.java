package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 3/11/18.
 */
public class LevelOrderTraversal102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        /** would be the same idea of use int for lvl, get(lvl) for the list,
         * and add cur node to list, recursive to children with lvl+1,
         * and create new list if found lvl requires new list
         * */
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> ls, TreeNode rt, int lvl){
        if(rt==null){
            return;
        }
        if(ls.size()==lvl){
            ls.add(new ArrayList<>());
        }
        ls.get(lvl).add(rt.val);
        helper(ls, rt.left, lvl+1);
        helper(ls, rt.right, lvl+1);
    }
}
