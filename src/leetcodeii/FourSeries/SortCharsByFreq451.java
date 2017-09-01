package leetcodeii.FourSeries;

/**
 * Created by Erebus on 8/16/17.
 */
public class SortCharsByFreq451 {
    public String frequencySort(String s) {
        if(s==null || s.length()<3){
            return s;
        }
        //1 build the freq map
        int[] map = new int[256];
        int max = 0;
        for(char c : s.toCharArray()){
            map[c]++;
            max = Math.max(max, map[c]);
        }

        //2 build/combine chars in buckets, e.g. char map, index=freq
        String [] buckets = new String[max+1]; //0, 1, 2. size =3 to include max=2, [2]
        for(int f = 0; f<map.length; f++){
            String prev = buckets[map[f]];
            if(map[f]>0){ //char f occurs
                //put additional distinct char to bucket
                buckets[map[f]] = prev==null?(""+(char)f):(prev+(char)f);
            }
        }
        //3 divide chars in each buckets, order by freq desc, append to result
        StringBuilder sb = new StringBuilder();
        for(int i = max; i>0; i--){
            String cur = buckets[i];
            if(cur!=null && cur.length()>0){
                for(char c : cur.toCharArray()){
                    for(int ii = 0; ii<i; ii++){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
