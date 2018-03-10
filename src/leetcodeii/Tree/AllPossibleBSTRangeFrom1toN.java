package leetcodeii.Tree;

import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** TODO: review it again, recursion return a list of nodes, and use loop to add back.
 * Created by Erebus on 3/6/18.
 */
public class AllPossibleBSTRangeFrom1toN {

    public List<TreeNode> generateTrees(int n) {
        //BST!
        return generate(1, n);
    }

    public List<TreeNode> generate(int st, int ed){
        List<TreeNode> tmp = new ArrayList<>();
        //st is left boundary, ed is right boundary
        if(st==ed){
            tmp.add(new TreeNode(st));
            return tmp;
        }
        if(st>ed){
            tmp.add(null);
            return tmp;
        }
        for(int ii=st; ii<=ed; ii++){

            List<TreeNode> left=generate(st, ii-1);
            List<TreeNode> right = generate(ii+1, ed);

            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode rt = new TreeNode(ii);
                    rt.left=l;
                    rt.right=r;
                    tmp.add(rt);
                }
            }
        }
        return tmp;
    }

}
