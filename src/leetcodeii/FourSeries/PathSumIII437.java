package leetcodeii.FourSeries;

import leetcodeii.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 8/30/17.
 */
public class PathSumIII437 {
    public int pathSum(TreeNode root, int sum) {
        return helper(root, sum, new ArrayList<Integer>());
    }

    private int helper(TreeNode root, int sum, List<Integer> hist){
        //return condition
        if(root == null){
            return 0;
        }
        int count = 0;
        List<Integer> next = new ArrayList<>();
        for(Integer i : hist){
            int now = i.intValue() + root.val;
            if(now==sum){
                count++;
            }
            next.add(now);
        }
        if(root.val==sum){
            count++;
        }
        next.add(root.val);
        return count + helper(root.left, sum, next) + helper(root.right, sum, next);
    }

}
