package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/15/18.
 */
public class ValidateTicTacToe implements Tracker{

    public static void main(String [] args){
        ValidateTicTacToe v = new ValidateTicTacToe();
        String [] t = {"OXX","XOX","OXO"};
        v.cout(v.validTicTacToe(t));
    }
    public boolean validTicTacToe(String[] board) {
        int turn = 0; //1 is X, 0 is O

        int diagonal = 0;
        int anti_diagonal = 0;
        int [] row = new int[board.length];
        int [] col = new int[board.length];

        for(int ii = 0; ii < board.length; ii ++){
            for(int jj = 0; jj < board[ii].length(); jj ++){
                char c = board[ii].charAt(jj);
                if(c == 'X'){
                    turn ++;
                    row[ii]++;
                    col[jj]++;
                    if(ii == jj){
                        diagonal ++;
                    }
                    if(ii+jj == board.length-1){
                        anti_diagonal ++;
                    }
                } else if(c == 'O'){
                    turn --;
                    row[ii]--;
                    col[jj]--;
                    if(ii==jj){
                        diagonal --;
                    }
                    if(ii+jj == board.length-1){
                        anti_diagonal --;
                    }
                }
            }
        }
        cout(col);
        cout(row);
        cout(diagonal);
        cout(anti_diagonal);
        boolean xwin = diagonal == 3 || anti_diagonal == 3;
        boolean owin = diagonal == -3 || anti_diagonal == -3;
        for(int ii = 0; ii < row.length; ii ++){
            xwin |= row[ii] == 3 || col[ii] == 3;
            owin |= row[ii] == -3 || col[ii] == -3;
        }
        cout("turn = " + turn + ", winner = " + (owin?"O":xwin?"X":"Nobody"));
        if((xwin&&turn==0) || (owin&&turn==1)){
            return false;
        }
        return (turn == 0 || turn == 1) && (!xwin || !owin);
    }
}
