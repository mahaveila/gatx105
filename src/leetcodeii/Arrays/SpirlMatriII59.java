package leetcodeii.Arrays;

/**
 * Created by Erebus on 2/4/18.
 */
public class SpirlMatriII59 {

    public int[][] generateMatrix(int n) {

        int[][] m = new int[n][n];

        /** given i from 1:n^2
         row = i/n
         column = i%n
         */
        int st = 0;
        int ed = n-1;

        int c = 0;
        int count = 1;
        while(c<n/2){
            for(int i = st+c; i<ed-c; i++){
                m[st+c][i] = count++;
            }

            for(int j = st+c; j<ed-c; j++){
                m[j][ed-c] = count++;
            }

            for(int p = ed-c; p>st+c; p--){
                m[ed-c][p] = count++;
            }

            for(int q = ed-c; q>st+c; q--){
                m[q][st+c] = count++;
            }
            c++;
        }
        if(n%2!=0){
            m[n/2][n/2]=count;
        }
        return m;
    }

}
