package leetcodeii.FourSeries;

/** TODO: test
 * Created by Youming on 4/19/2017.
 */
public class LicenseKeyFomatting482 {

    String formatLicenseKey(String input, int k){
        StringBuilder sb = new StringBuilder();
        String processed = input.replaceAll("-","").toUpperCase();
        for(int ii = 0; ii < processed.length();ii++){
            char c= input.charAt(input.length()-1-ii);
            if(ii % k ==0 && sb.length() > 0){
                sb.append("-");
            }
            sb.append(c);
        }
        return sb.reverse().toString();
    }

}
