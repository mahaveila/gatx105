package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/7/18.
 */
public class MatrixSetZeros73 implements Tracker{

    public void setZeroes(int[][] matrix) {
        cout(matrix);
        //cache result for 1st row & col
        int hrzn=1, vert=1;
        for(int ii=0; ii<matrix.length; ii++){
            if(matrix[ii][0]==0){
                vert=0;
                break;
            }
        }
        for(int ii=0; ii<matrix[0].length; ii++){
            if(matrix[0][ii]==0){
                hrzn=0;
                break;
            }
        }
        //detecting zeros
        for(int ii=1; ii<matrix.length; ii++){
            for(int jj=1; jj<matrix[0].length; jj++){
                if(matrix[ii][jj]==0){
                    matrix[ii][0]=0;
                    matrix[0][jj]=0;
                }
            }
        }
        cout(matrix);
        //setting zeros
        for(int ii=1; ii<matrix.length; ii++){
            if(matrix[ii][0]==0){
                cout("setting row " + ii + " to 0");
                for(int jj=1; jj<matrix[ii].length; jj++){
                    matrix[ii][jj]=0;
                }
            }
        }

        for(int ii=1; ii<matrix[0].length; ii++){
            if(matrix[0][ii]==0){
                cout("setting column " + ii + " to 0");
                for(int jj=1; jj<matrix.length; jj++){
                    matrix[jj][ii]=0;
                }
            }
        }

        if(vert==0){
            for(int ii=0; ii<matrix.length; ii++){
                matrix[ii][0]=0;
            }
        }
        if(hrzn==0){
            for(int ii=0; ii<matrix[0].length; ii++){
                matrix[0][ii]=0;
            }
        }

    }

    /**
     * One column is enough to store the head information. No need to store for the row.
     * And setting zeros from the end.
     * @param matrix
     */
    void setZeroesMuchBetter(int [][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }

    public static void main(String [] args){
        MatrixSetZeros73 msz = new MatrixSetZeros73();
        int [][] m = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        msz.setZeroes(m);
        msz.cout(m);
    }
}
