package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 9/7/17.
 */
public class ConvertNumToHexval405 implements Tracker{

    public String toHex(int num) {
        if(num==0){
            return "0";
        }
        String mpStr = "0123456789abcdef";
        char[] map = mpStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        while(num!=0){
            //get hex by (num AND last 4 digits) => value of the value of a mod of 16.
            sb.append(map[num&15]);
            num = num>>>4; //shift by 16
        }
        return sb.reverse().toString();
    }

    public String toHexWrong(int num) {

        int max=1;
        for(int ii=0;ii<7;ii++){
            max += 15*(int)Math.pow(16,ii);
            cout(String.format("      ii=%d, forming max:%d",ii, max));
        }
        // input = num;
        // while(input<0){
        //     input += max;
        // }

        int input=num<0?(max+num):num;
        cout(String.format("num=%d, input=%d, max=%d", num, input, max));
        StringBuilder sb = new StringBuilder();
        boolean st =false;
        int f=(int)Math.pow(16,7);
        if(num<0){
            sb.append('f');
        }
        while(f>0){
            int n = input/f;
            cout(String.format("  loop input=%d, f=%d, n=%d -- original input=%d, f=%d", input, f, n, num<0?(max+num):num, (int)Math.pow(16,7)));
            input = input%f;
            char c = intToHex(n);
            f/=16;
            if(c=='0' && !st){
                //not start yet
                continue;
            } else {
                sb.append(""+c);
            }
            if(c!='0'){
                st=true;
            }
        }

        return st?sb.toString():"0";
    }

    private char intToHex(int n){
        if(n<10&&n>0){
            return (char)('0'+n);
        } else if(n>9 && n<16){
            return (char)('a'+n-10);
        }
        return '0';
    }

    public static void main (String [] args){
        ConvertNumToHexval405 c = new ConvertNumToHexval405();
        c.cout(c.toHex(-1));
        c.cout(c.toHex(26));
        c.cout(c.toHex(16));
        c.cout(c.toHex(0));
    }
}
