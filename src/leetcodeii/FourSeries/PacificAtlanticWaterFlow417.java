package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 9/4/17.
 */
public class PacificAtlanticWaterFlow417 implements Tracker{

    public List<int[]> pacificAtlantic(int[][] matrix) {

        if(matrix==null){
            return null;
        }
        //input check
        List<int[]> res = new ArrayList<>();
        int h = matrix.length;
        if(h<1){
            return res;
        }
        int w = matrix[0].length;
        if(w<1){
            return res;
        }
        if(h==1){
            for(int jj=0; jj<w;jj++){
                int[] tmp = new int[2];
                tmp[1]=jj;
                res.add(tmp);
            }
            return res;
        }
        if(w==1){
            for(int ii=0; ii<h;ii++){
                int[] tmp = new int[2];
                tmp[0]=ii;
                res.add(tmp);
            }
            return res;
        }

        //pacific
        Queue<int[]> pacific = new LinkedList<>();
        for(int ii =0; ii<h;ii++){
            cout(matrix[ii]);
            int [] west = new int[2];
            west[0]=ii;
            pacific.add(west);
        }
        for(int jj=0;jj<w;jj++){
            int [] north = new int[2];
            north[1]=jj;
            pacific.add(north);
        }
        cout("---------------------------------------");

        Set<String> pacificFlooded = flood(pacific, matrix);
        cout(pacificFlooded);
        Queue<int[]> atlantic = new LinkedList<>();
        for(int ii=h-1; ii>=0; ii--){
            int [] east = new int[2];
            east[0]=ii;
            east[1]=w-1;
            atlantic.add(east);
        }
        for(int jj=w-1;jj>=0;jj--){
            int [] south = new int[2];
            south[1]=jj;
            south[0]=h-1;
            atlantic.add(south);
        }

        Set<String> atlanticFlooded = flood(atlantic, matrix);
        cout(atlanticFlooded);
        List<int[]> bothFlooded = pacificFlooded.stream()
                .filter(ground -> atlanticFlooded.contains(ground))
                .map(s->{
                    String[] grounds = s.split(",");
                    int[] tmp = new int[2];
                    tmp[0]=Integer.parseInt(grounds[0]);
                    tmp[1]=Integer.parseInt(grounds[1]);
                    return tmp;
                })
                .sorted((s1,s2)->s1[0]==s2[0]?s1[1]-s2[1]:s1[0]-s2[0])
                .collect(Collectors.toList());
        return bothFlooded;
    }

    private Set<String> flood(Queue<int[]> q, int[][] matrix){
        cout("begin flood, q size = " + q.size());
        int h = matrix.length;
        int w = matrix[0].length;
        Set<String> flooded = new HashSet<>();
        while(!q.isEmpty()){
            int[] cur = q.poll();
            cout(String.format("flooding [%d, %d], to be flooded = %d", cur[0],cur[1],q.size()));
            if(flooded.contains(toKey(cur))){
                //already flooded
                continue;
            } else {
                flooded.add(toKey(cur));
                cout(String.format(" -- mark [%d, %d] flooded", cur[0],cur[1]));
                //add surroundings to be flooded if could
                if(cur[0]>0){
                    int[] north = new int[2];
                    north[0]=cur[0]-1;
                    north[1]=cur[1];
                    if(!flooded.contains(toKey(north)) && matrix[cur[0]][cur[1]]<=matrix[north[0]][north[1]]){
                        q.add(north);
                    }
                }
                if(cur[1]>0){
                    int[] west = new int[2];
                    west[0]=cur[0];
                    west[1]=cur[1]-1;
                    if(!flooded.contains(toKey(west)) && matrix[cur[0]][cur[1]]<=matrix[west[0]][west[1]]){
                        q.add(west);
                    }
                }
                if(cur[0]<h-1){
                    int[] south = new int[2];
                    south[0]=cur[0]+1;
                    south[1]=cur[1];
                    if(!flooded.contains(toKey(south)) && matrix[cur[0]][cur[1]]<=matrix[south[0]][south[1]]){
                        q.add(south);
                    }
                }
                if(cur[1]<w-1){
                    int[] east = new int[2];
                    east[0] = cur[0];
                    east[1] = cur[1]+1;
                    if(!flooded.contains(toKey(east)) && matrix[cur[0]][cur[1]]<=matrix[east[0]][east[1]]){
                        q.add(east);
                    }
                }
            }
        }
        return flooded;
    }

    private String toKey(int[] g){
        return "" + g[0]+","+g[1];
    }

    public static void main(String [] args){
        int [][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        PacificAtlanticWaterFlow417 p = new PacificAtlanticWaterFlow417();
        p.pacificAtlantic(matrix).stream().forEach(s->p.cout(s));
    }
}
