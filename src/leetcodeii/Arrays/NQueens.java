package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 3/20/18.
 */
public class NQueens implements Tracker{

    public List<List<String>> solveNQueens(int n) {
        /*
        guess we have to use dfs


        int[n+1] B;
        placing the board, for row:(1~n), try put [i]=row
        B[4]=3, means the 3rd row, 4th column (3,4).
        i=4, r=3,
        while(i and r in boundary){
            check diagonal:  i+1, r+1 -> i.e. [i+1]==r+1? [i+1]==r-1? [i-1]==r+1? [i-1]==r-1?
            for(k range from 1 to n){
                [i+k]==r+k? [i+k]==r-k? [i-k]==r+k? [i-k]==r-k?
            }
            same row: iterating all B, if other value [j] == [i] == row
            same column: vertical is checked by iteration, only put in columns that is not 0.
        }
        since we are using int[n+1], left bounary is >0, right is <n+1.
        */

        int[] B = new int[n+1];
        List<List<String>> res = new ArrayList<>();
        place(B, res, n);
        return res;
    }

    private void place(int[] B, List<List<String>> res, int n){
        cout("Placing: " + n);
        if(n==0){
            //no queens left
            List<String> board = generateBoard(B);
            res.add(board);
            return;
        }
        //else, not filled up yet
        for(int col = 1; col<B.length; col++){
            if(B[col]!=0){
                //already filled this col
                cout("already filled ["+col+"]="+B[col]);
                continue;
            }
            B[col]=n;
            cout("placing board:" +stringfy(B));
            if(checkBoard(B, col)){
                //valid placement, keep placing next one.
                place(B, res, n-1);
            }
            //reverse
            B[col]=0;
        }
    }

    private boolean checkBoard(int [] B, int col){

        //check row
        int row = B[col];
        for(int ii=1;ii<B.length;ii++){
            if(ii!=col && B[ii]==row){
                cout("same row queue! ["+ii+"]="+B[ii]);
                return false;
            }
        }
        for(int d = 1; d<B.length; d++){
            if(!checkDiagonalValid(B, row+d, col+d) ||
                    !checkDiagonalValid(B, row+d, col-d) ||
                    !checkDiagonalValid(B, row-d, col+d) ||
                    !checkDiagonalValid(B, row-d, col-d)){
                //invalid
                cout("Diagonal Invalid!");
                return false;
            }
        }
        cout("valid cboard: "+stringfy(B));
        return true;
    }

    private boolean checkDiagonalValid(int [] B, int i, int j){
        if(i<1 || j<1 || i>=B.length || j>=B.length){
            //out of boundary
            cout("out of boundary, valid: row="+i+", col="+j);
            return true;
        }
        //to be valid, need no col j is placed on that i
        cout("B["+j+"]="+B[j]+", v.s. row=" + i);
        return B[j]!=i;
    }

    /**
     input is a fully filled board
     */
    private List<String> generateBoard(int [] B){
        List<String> res = new ArrayList<>();
        for(int col=1; col<B.length; col++){
            //1~n
            int row = B[col];
            while(res.size()<row){
                res.add("");
            }
            String rowStr = generate(col, B.length);
            res.set(row-1, rowStr);
        }
        cout("board generated: " + stringfy(res));
        return res;
    }

    /**
     input is the col number, where to place the queen
     */
    private String generate(int i, int n){
        //n is boardsize+1
        cout("generate a board of size: 1 ~" + n);
        String res = "";
        for(int j=1;j<n;j++){
            if(i==j){
                res += "Q";
            } else {
                res += ".";
            }
        }
        return res;
    }

    public static void main(String [] args){
        NQueens n = new NQueens();
        n.cout(n.solveNQueens(4));
    }
}
