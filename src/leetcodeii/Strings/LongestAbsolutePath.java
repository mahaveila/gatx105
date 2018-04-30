package leetcodeii.Strings;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 4/26/18.
 */
public class LongestAbsolutePath implements Tracker{

    public static void main(String [] args){
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String input2 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input3 = "dir\n\t        file.txt\n\tfile2.txt";
        String input4 = "a\n\taa\n\t\taaa\n\t\t\tfile1.txt\naaaaaaaaaaaaaaaaaaaaa\n\tsth.png";
        LongestAbsolutePath l = new LongestAbsolutePath();
        l.cout(l.lengthLongestPath(input4));
    }

    public int lengthLongestPath(String input) {
        //SWEET: the split of the line end is using the \n
        String[] inputs = input.split("\n");
        cout("inputs length = " + inputs.length);

        /**
         the basic idea would be store only the upper level directory, and comparing current

         i.e. bucket[i] = latest directory with depth i
         when cur contains ., which is a file,
         1. we have the tab length, which is \t, if cur len > prev file len, then update the global
         e.g.
         dir 1
         file 1
         dir 12
         file 12 -> file 12 has longer path, update
         case:
         dir 1
         dir 12
         file 12 -> res len =2
         file 1 ---> in this case we have previous len = 2, already, to get the correct lenght of the current file, we need to use map.get(curFile.depth -1), so we can skip the dir12 and file 12 and get the dir1
         1) i.e. use map's bucket for back tracking

         when cur doesn't contains ., that means we now at a folder, then we update the bucket with current depth.
         i.e. dir 1
         dir 2
         dir 3
         -> bucket 2 stores the dir3, instead of dir2, cur we will never iterate back to dir2
         */
        Map<Integer, String> map = new HashMap<>();
        int res = 0;
        int max = 0;
        //we got each lines
        for(String s : inputs){
            //iterating each lines

            int d = getDepth(s);
            cout("current line: " + s +", the depth is " + d);
            if(s.contains(".")){
                cout("      find a file = " + s);
                //is a file
//                if(d >= max){
//                    cout("max = " + max + ", d = " + d);
                    //doesn't store this path in map, cuz it's a file
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < d; i ++){
                        if(sb.length()>0){
                            sb.append("/");
                        }
                        sb.append(map.get(i));
                    }
                    if(sb.length()>0){
                        sb.append("/");
                    }
                    sb.append(s.replaceAll("\t", ""));
                    cout(" longest file path is : " + sb.toString() + ", len = " + sb.toString().length());
                    res = Math.max(sb.toString().length(), res);
//                    max = d;
//                }
            } else {
                //is a directory
                map.put(d, s.replaceAll("\t", ""));
            }
        }

        return res;
    }

    private int getDepth(String s){
        //given a string, return the depth that this string represents
        //e.g. given \tdir1, return 1
        //given \t\t dir2, return 2
        int count = 0;
        while(s!=null && s.contains("\t")){
            s = s.substring(1);
//            cout("  reduce depth by 1, s = " + s);
            count ++;
        }
        return count;
    }
}
