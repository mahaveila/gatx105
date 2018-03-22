package leetcodeii.Graph;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/12/18.
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 X X X X
 X O O X
 X X O X
 X O X X

 =>

 X X X X
 X X X X
 X X X X
 X O X X
 */
public class SurroundedRegions implements Tracker{

    /**
     * For a very large board, this union find solution will be exceed time limit
     *
     * Alternative could do DFS from boarder for all connected 0
     *
     * if continue using union find, need to use weighted union find so it could be more faster.
     * FIXME: improve using weighted union find!
     * @param args
     */
    public static void main(String [] args){
        SurroundedRegions s = new SurroundedRegions();
        char[][] input = {{'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}};
        char[][] input2 =

        {{'X','O','X','X'},{'O','X','O','X'},{'X','O','X','O'},{'O','X','O','X'},{'X','O','X','O'},{'O','X','O','X'}};


        s.surroundedRegions(input2);
    }

    public void surroundedRegions(char[][] board) {
        // write your code here
        if(board==null || board.length<1 || board[0].length<1){
            return;
        }
        m = board.length;
        n = board[0].length;
        set = new int[m*n+1];
        //initialize to unique unit
        for(char[] cs : board){
            //cout(cs);
        }
        for(int i = 0; i<=m*n; i++){
            set[i]=i;
        }
        //cout(set);
        //all the 0 on the edge has been rooting to 0
        init(board);

        int[] x_vector = {0,0,1,-1};
        int[] y_vector = {1,-1,0,0};
        //next, go to board and connect each nodes
        for(int ii=0; ii<m;ii++){
            for(int jj=0; jj<n; jj++){
                for(int k=0;k<4;k++){
                    tryConnect(ii, jj, ii+x_vector[k], jj+y_vector[k], board);
                }
            }
        }
        //after connected
        //cout("after connected: \n" + stringfy(set));
        for(int ii=0; ii<m;ii++){
            for(int jj=0; jj<n; jj++){
                if(board[ii][jj]=='O'){
                    int idx = toIdx(ii, jj);
                    if(!query(idx, 0)){
                        //not connected to live cells
                        //cout(String.format("[%d][%d] is not connected to live cell, mark!", ii, jj));
                        board[ii][jj]='X';
                    } else {
                        //cout(String.format("[%d][%d] is connected to live cell, skip!", ii, jj));
                    }
                }

            }
        }
        for(char[] cs : board){
            //cout(cs);
        }
    }

    public boolean query(int i, int j){
        return root(i)==root(j);
    }

    public void tryConnect(int i, int j, int x, int y, char[][] board){
        if(x<0 || y<0 || x>=m || y>=n){
            return;
        }
        if(board[i][j]==board[x][y]){
            connect(toIdx(i,j), toIdx(x,y));
        }
    }

    public void init(char[][] board){
        for(int ii=0; ii<m; ii++){
            if(board[ii][0]=='O'){
                int idx=toIdx(ii, 0);
                connect(idx, 0);
            }
        }
        for(int ii=0; ii<n; ii++){
            if(board[0][ii]=='O'){
                int idx=toIdx(0, ii);
                connect(idx, 0);
            }
        }
        for(int ii=0; ii<m; ii++){
            if(board[ii][n-1]=='O'){
                int idx=toIdx(ii, n-1);
                connect(idx, 0);
            }
        }
        for(int ii=0; ii<n; ii++){
            if(board[m-1][ii]=='O'){
                int idx=toIdx(m-1, ii);
                connect(idx, 0);
            }
        }
    }

    public int root(int i){
        return set[i]==i?i:root(set[i]);
    }

    public void connect(int i, int j){
        int root_i = root(i);
        int root_j = root(j);
        if(root_i!=root_j){
            set[root_i]=root_j;
        }
    }

    int m;
    int n;
    int [] set;

    public int toIdx(int i, int j){
        return i*n+j+1;
    }
}
