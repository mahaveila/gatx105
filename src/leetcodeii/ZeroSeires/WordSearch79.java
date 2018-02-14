package leetcodeii.ZeroSeires;

/**
 * Created by Erebus on 2/9/18.
 * !! Ask the question, if reuse the char in board is allowed
 */
public class WordSearch79 {

    public boolean exist(char[][] board, String word) {
        if(board==null || board.length<1 || board[0].length<1){
            return false;
        }
        for(int ii = 0; ii<board.length; ii++){
            for(int jj=0; jj<board[0].length; jj++){
                if(dfs(board, word, 0, ii, jj)){
                    return true;
                }
            }
        }
        return false;
    }

    //check index i, and j.
    private boolean dfs(char[][] board, String word, int index, int i, int j){
        //return condition, previous indexes already matched
        if(index==word.length()){
            return true;
        }
        //else need to continue search
        if(i<0 || j<0){
            return false;
        }
        if(j>= board[0].length || i >= board.length){
            return false;
        }

        char c = word.charAt(index);
        if(c==board[i][j]){
            //find a match keeps to next one

            //!!!!!!!!! IMPORTANT: mark the visited char, and reset when done with the visit
            board[i][j]='*';
            boolean res = dfs(board, word, index+1, i+1, j)
                    || dfs(board, word, index+1, i-1, j)
                    || dfs(board, word, index+1, i, j+1)
                    || dfs(board, word, index+1, i, j-1);
            board[i][j]=c;
            return res;
        } else {
            return false;
        }
    }
}
