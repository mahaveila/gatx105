/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii.FourSeries;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erebus
 */
public class DiagonalTrav498 {
    private int[] diagTravFunc(int[][] M){
        int m = M.length;
        int n = M[0].length;
        int seq_len = m+n-1;
        int[] res = new int[m*n];
        List<int[]> seqs = new ArrayList<>();
        for(int ii = 0; ii<seq_len; ii++){
            List<int[]> next = seq(ii, m, n);
            seqs.addAll(next);
        }
        for(int ii=0;ii<m*n;ii++){
            int[] rc = seqs.get(ii);
            res[ii] = M[rc[0]][rc[1]];
        }
        return res;
    }

    private List<int[]> seq(int ii, int m, int n) {
        List<int[]> res = new ArrayList<>();
        if(ii%2==0){
            for(int r = ii, c=0; r>=0; r--,c++){
                if(validseq(r,c,m,n)){
                    int [] next = {r,c};
                    res.add(next);
                }
            }
        }else{
            for(int c=ii,r=0;c>=0;r++,c--){
                if(validseq(r,c,m,n)){
                    int [] next = {r,c};
                    res.add(next);
                }
            }
        }
        return res;
    }

    private boolean validseq(int r, int c, int m, int n) {
        return r<m&&c<n;
    }
}
