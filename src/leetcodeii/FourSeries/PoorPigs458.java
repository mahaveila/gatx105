package leetcodeii.FourSeries;

/**
 * Created by Erebus on 8/13/17.
 */
public class PoorPigs458 {
    public int poorPigs(int b, int md, int mt) {
        int round = mt/md;
        double res = Math.log(b)/Math.log(round);
        System.out.println(res);
        return (int) res;
    }

    public static void main(String [ ]args){
        PoorPigs458 p = new PoorPigs458();

        System.out.println(Math.log(1000)/Math.log(10));
        System.out.println(p.poorPigs(1000, 15, 60));
        System.out.println(p.poorPigs(1000, 12, 60));

    }
}
