package leetcodeii.FourSeries;

/**
 * Created by Erebus on 9/4/17.
 */
public class AddString415 {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
            //!!!!!! -'0', and use validate index to assign int val to sum, regardless long or short
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }

    public String addStringsTooComplicated(String num1, String num2) {
        StringBuilder l = num1.length()>num2.length()?new StringBuilder(num1):new StringBuilder(num2);
        StringBuilder s = num1.length()>num2.length()?new StringBuilder(num2):new StringBuilder(num1);
        String longNum = l.reverse().toString();
        String shortNum = s.reverse().toString();
        StringBuilder sb = new StringBuilder();
        int carrier = 0;
        for(int ii = 0; ii<longNum.length(); ii++){
            int a = Integer.parseInt(""+longNum.charAt(ii));
            if(ii>=shortNum.length()){
                int sum = a + carrier;
                sb.append(sum%10);
                carrier=sum/10;
            } else {
                int b = Integer.parseInt(""+shortNum.charAt(ii));
                int sum = a + b + carrier;
                sb.append(sum%10);
                carrier=sum/10;
            }
        }
        if(carrier>0){
            sb.append(carrier);
        }
        return sb.reverse().toString();
    }
}
