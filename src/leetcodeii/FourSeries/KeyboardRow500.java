package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Erebus on 2/3/17.
 */
public class KeyboardRow500 implements Tracker{

    public String[] findWords(String[] words) {
        if(words==null || words.length==0){
            return words;
        }
        Set<Character> firstRow = new HashSet<>();
        firstRow.add('Q');
        firstRow.add('W');
        firstRow.add('E');
        firstRow.add('R');
        firstRow.add('T');
        firstRow.add('Y');
        firstRow.add('U');
        firstRow.add('I');
        firstRow.add('O');
        firstRow.add('P');
        Set<Character> secondRow = new HashSet<>();
        secondRow.add('A');
        secondRow.add('S');
        secondRow.add('D');
        secondRow.add('F');
        secondRow.add('G');
        secondRow.add('H');
        secondRow.add('J');
        secondRow.add('K');
        secondRow.add('L');
        String [] res = Arrays.stream(words).filter(s -> sameRow(s, firstRow, secondRow)==true).toArray(str -> new String[str]);
        log(res);
        return res;
    }

    public boolean sameRow(String s, Set<Character> firstRow, Set<Character> secondRow){

        if(s==null || s.length()<=1){
            return true;
        }
        s = s.toUpperCase();
        int fi = 0;
        int se = 0;
        int th = 0;
        for(int ii = 0; ii<s.length(); ii++){
            if(firstRow.contains(s.charAt(ii))){
                LOG.info("first row contains " + s.charAt(ii));
                fi = 1;
            } else if (secondRow.contains(s.charAt(ii))){
                LOG.info("second row contains " + s.charAt(ii));
                se = 1;
            } else {
                LOG.info("third row contains " + s.charAt(ii));
                th = 1;
            }
        }
        LOG.info(s + " | " + fi + " | " + se + " | " + th);
        return (fi + se + th) == 1;
    }

    public static void main(String [] args){
        KeyboardRow500 k = new KeyboardRow500();
        String [] strs = {"Hello","Alaska","Dad","Peace"};
        String [] res = k.findWords(strs);
    }

    //cleaner, but slower
    public String[] findWordsSolution(String[] words) {
        return Arrays.stream(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
    }

    //another cool solution!
    /**
     * 1.  private static final int ROW1 = 0b00000001010110111100000100010000;
     * 2. (wordLetterIndexes & ROW1) == wordLetterIndexes,    the word is a valid subset of one of ROW indexes
     */
}
