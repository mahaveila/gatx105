package leetcodeii.Graph;

import leetcodeii.Tracker;

import javax.sound.midi.Track;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Erebus on 4/20/18.
 */
public class PoliceDistance implements Tracker {


    public static void main(String [] args){
        PoliceDistance p = new PoliceDistance();
        int [][] in = {{0, -1, 0},{0,1,1},{0,0,0}};
        p.cout(p.policeDistance(in));
    }

    public int[][] policeDistance(int[][] matrix ) {
        // Write your code here
        if(matrix.length<1 || matrix[0].length<1){
            return matrix;
        }
        //now for each policeman, we do the BFS to each 0 locations
        //matrix bfs.

        for(int ii = 0; ii < matrix.length; ii ++){
            for(int jj = 0; jj < matrix[0].length; jj ++){
                if(matrix[ii][jj]!=-1 && matrix[ii][jj]!=1){
                    matrix[ii][jj] = Integer.MAX_VALUE-1;
                }
            }
        }
        for(int ii = 0; ii < matrix.length; ii ++){
            for(int jj = 0; jj < matrix[0].length; jj ++){
                if(matrix[ii][jj]%2==1){
                    cout("offering " + ii + ", " + jj);
                    Coordinate c = new Coordinate(ii, jj);
                    helper(matrix, c);
                }
            }
        }

        for(int ii = 0; ii < matrix.length; ii ++){
            for(int jj = 0; jj < matrix[0].length; jj ++){
                if(matrix[ii][jj]>0){
                    matrix[ii][jj]/=2;
                }
            }
        }
        return matrix;
    }

    int [] dx = {1, -1, 0, 0};
    int [] dy = {0, 0, 1, -1};

    private void helper(int [][] matrix, Coordinate st){
        boolean [][] visited = new boolean [matrix.length][matrix[0].length];

        Queue<Coordinate> q = new LinkedList<>();
        int dist = 0;
        q.offer(st);
        while(!q.isEmpty()){
            //starting from [1][1] police man,
            int size = q.size();
            for(int ii = 0; ii < size; ii ++){
                Coordinate c = q.poll();
                if(isValid(matrix, c.row, c.col, visited)){
                    visited[c.row][c.col] = true;
                    if(matrix[c.row][c.col]>dist){
                        matrix[c.row][c.col] = Math.min(matrix[c.row][c.col], dist);
                    }
                    for(int k = 0; k < dx.length; k ++){
                        Coordinate next = new Coordinate(c.row+dx[k], c.col+dy[k]);
                        q.offer(next);
                    }
                }
            }
            dist = dist + 2;
        }

    }

    public static class Coordinate{
        int row;
        int col;
        public Coordinate(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    //-1 means we cannot go through
    private boolean isValid(int [][] matrix, int i, int j, boolean[][] visited){
        if(i < 0 || j < 0 || i>= matrix.length || j >= matrix[0].length || visited[i][j] || matrix[i][j]<0){
            return false;
        }
        return true;
    }
}
