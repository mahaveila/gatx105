package leetcodeii.Strings;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/29/18.
 */
public class SentenceScreenFitting implements Tracker{

    public static void main(String [] args){
        SentenceScreenFitting s = new SentenceScreenFitting();
        String [] input = {"f","p","a"};
        s.cout(s.wordsTyping(input, 8, 7));
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        //ERROR previous count row method is not right, need to consider that '-' also takes the place.
        /**
         okay, the basic idea is, to find the start for each row
         say we have a loooong repeating string
         a bcd ef g a bcd ef g a ... etc,  and each col is a window [6] with len = 6
         then it looks like

         a bcd ef g a bcd ef g a ...
         [     ] --> the start + col ends up at index 6, [6] is not empty space, so we move back until start = space,
         then we start next row
         [     ]  --> the start + col ends at " ", just fill up the entire row, start ++ for starting next row.
         [     ]
         [     ]

         in the end, repeated time = start/origStrLen

         DEBUG:
         master = "",
         rows*cols = 3*6 = 18
         sentece[idx]=   :   a   bcd     e       ...     a
         master =        :   a_  a_bdc_  a_bcd_e_    X2  X2a_
         master len=     :   2   6       8           16  18
         idx             :   0   1       2           5   6%3=0
         origLen         :   2   6       8
         012345678901234567890123
         [a bcd e a bcd e a ]
         start:       0                      init
         6                start = min(ML, start) = 6, c = 'e', start --> 5, start++>6
         12
         12,     'd',          9           10
         16                             16      'a',          15          16
         22                       17      ' ',                      18
         now we seems formed:
         a bcd   6
         e a     10
         bcd e   16
         a       18

         18/8=2

         DEBUG 2:
         1     2     3     4     5     6     7     8     9
         master = [f p a f p a f p a f p a f p a f p a f p a f p a f p a f ], origLen = 6
         start =   0       8       16      24      32      40      48      56,  Not right
         should be 0      7
         8 this skipped one space.
         //BUG: when using master's len, we don't really skip the space, thus we could miss rows.
         ROW:      0       1       2       3       4       5       6       7      8, previous missed one row
         res = 56/6=9.
         row = 8,
         cols = 7,
         should not be
         f_p_a_f_ 8
         should be:
         f_p_a_f,


         */
        int origLen = 0;
        String master = "";
        int idx = 0;

        // int skipSpace = 0; should count move how many ahead, and thus extend the master correspondingly
        //circularly adding string to master string
        //SWEET: while loop, remember to increase the index to break the loop! CONDITION!
        // while(master.length() < rows*cols){
        //     master += sentence[idx%sentence.length] + " "; //remember to append the space, or -, either works
        //     if(idx++ < sentence.length){
        //         origLen = master.length();
        //     }
        // }
        // master += master; //for the case with space skipped, extend the master.
        //now we can only rely on the rows to count, hmmm?

        //SWEET: use single row only, otherwise if row*col is huge, will TIMED OUT.
        String s = String.join(" ", sentence) + " ";
        int l = s.length();
        int start = 0;
        //SWEET: use rows for counting now.
        for(int r = 0; r < rows; r ++){
            start += cols;
            if(s.charAt(start%l)==' '){
                //moving forward for next row
                start ++;
            } else {
                //SWEET use start-1 % l, the char before current to decide. case p_f_a_p_, [0+7]=" ", start->8, directly.
                //                                                               f_a_p_f_, [8+7]=[15]=" ", keep increase
                //hmmm, similar to !=" " start-- then start++, always find previous " ", and step one ahead.
                while(start > 0 && s.charAt((start)%l)!=' '){
                    //moving backward for next row
                    start --;
                }
                //now start @ ' '
                start ++; //step ahead for next row -> just goes to the next row then, let the next row step ahead
            }
        }
        // while(row < rows){
        //     start += cols;
        //     // //now try to move next row
        //     // start = Math.min(start, master.length()-1);
        //     if(master.charAt(start)==' '){
        //         //moving forward for next row
        //         start ++;
        //     } else {
        //         while(start >= 0 && master.charAt(start)!=' '){
        //             //moving backward for next row
        //             start --;
        //         }
        //         //now start @ ' '
        //         start ++; //step ahead for next row
        //     }
        //     row ++;
        // }
        return s.length()==0?-1:start/s.length();
    }

}
