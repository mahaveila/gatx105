package leetcodeii.oa;

import leetcodeii.Tracker;

import java.util.*;

/**
 * Created by Erebus on 3/29/18.
 */
public class Pure implements Tracker {

    public boolean validSquare(int[][] input) {
        Map<String, Integer> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();
        list.addAll(Arrays.asList(input));
        int count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (!check(list.get(i), list.get(j))) continue;
                String str = genStr(list.get(i), list.get(j));
                //point 0,0 is going to put 2
                map.put(str, 1 + map.getOrDefault(str, 0));
            }
        }

        cout("After first iteration: ");
        for(String key : map.keySet()){
            cout(key+"->"+map.get(key));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (!check(list.get(i), list.get(j))) continue;
                String diag = createDiag(list.get(i), list.get(j));
                if (diag.length() == 0) continue;
                //d1 found d2, d2 0->1
                //d2 found d1, d1 0->1
                map.put(diag, map.getOrDefault(diag, 0)+1);
            }
        }
        count += map.values().stream().map(i->i>1?1:0).reduce(0, (a,b)->a+b);
        count >>= 1;
        cout(count);
        return count > 0;
    }

    private boolean check(int[] p1, int[] p2) {
        if (p1[0] == p2[0] && p1[1] == p2[1]) return false;
        return true;
    }

    private String genStr(int[] p1, int[] p2) {
        if (p1[0] < p2[0] || (p1[0] == p2[0] && p1[1] < p2[1])) {
            return String.format("%d,%d;%d,%d", p1[0]*2, p1[1]*2, p2[0]*2, p2[1]*2);
        } else {
            return String.format("%d,%d;%d,%d", p2[0]*2, p2[1]*2, p1[0]*2, p1[1]*2);
        }

    }


    /**
     * (x2,y2)  = (xm+ym-y0, ym-xm+x0), (x3,y3) = (xm-ym+y0,  ym+xm-x0);
     * 2x2, 2y2 = 2xm + 2ym - 2y0, 2ym - 2xm + 2x0,
     * 2xm = x1-x0+2x0 = x1+x0, 2ym = y1-y0+2y0 = y1+y0
     * TODO: 2x2 = x1+x0+y1+y0-2y0 = x1+x0+y1-y0
     * TODO: 2y2 = y1+y0-x1-x0+2x0 = y1+y0-x1+x0
     * when this could be odd?
     * @param a
     * @param c
     * @return
     */
    private String createDiag(int[] a, int[] c) {
        int midX = a[0] + c[0];
        int midY = a[1] + c[1];
        int Ax = 2*a[0] - midX;
        int Ay = 2*a[1] - midY;
        int bX = midX - Ay;
        int bY = midY + Ax;

        int cX = 2*c[0] - midX;
        int cY = 2*c[1] - midY;
        int dX = midX - cY;
        int dY = midY + cX;

        String diag = null;
        if (bX < dX || (bX == dX && bY < dY)) {
            diag= String.format("%d,%d;%d,%d", bX, bY, dX, dY);
        } else {
            diag= String.format("%d,%d;%d,%d", dX, dY, bX, bY);
        }

        return diag;
    }

    public static void main(String [] args){
        Pure p = new Pure();
        int[][] input = {{0,0},{1,7},{-3,4},{4,3},{1,0},{0,1},{1,1}};
        int[][] input2 = {{0,0},{1,7},{-3,4},{1,1}};
        p.cout(input);
        p.cout(p.validSquare(input));
        p.cout(p.validSquare(input2));
    }

}
