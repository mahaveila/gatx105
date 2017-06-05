package leetcodeii.FourSeries;

import java.util.Arrays;

/**
 * Created by Erebus on 6/4/17.
 */
public class ValidIPAddress468 {

    public String validIPAddress(String IP) {
        String ipv4regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        String ipv6regex = "[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+:[a-zA-Z0-9]+";
        if(IP.matches(ipv4regex)){
            return ipv4(IP)?"IPv4":"Neither";
        }else if(IP.matches(ipv6regex)){
            return ipv6(IP)?"IPv6":"Neither";
        }else {
            return "Neither";
        }
    }

    //range from 0-255
    private boolean ipv4(String ip){
        String [] ips = ip.split("\\.");
        if(ips.length!=4){
            return false;
        }
        final boolean[] res = {true};
        Arrays.stream(ips).forEach(s->{
            if(s.length()>=4){
                res[0] = false;
            } else if(s.matches("\\d+") && Integer.parseInt(s)<256 && Integer.parseInt(s)>=0){
                //there could be leading 0 case
                if(s.matches("0\\d+")){
                    res[0] = false;
                }
            } else {
                res[0] = false;
            }
        });
        return res[0];
    }

    //range is 16 bits
    private boolean ipv6(String ip){
        String [] ips = ip.split(":");
        if(ips.length!=8){
            return false;
        }
        final boolean[] res = {true};
        Arrays.stream(ips).forEach(s->{
            if(!s.matches("[a-fA-F0-9]{1,4}|[a-fA-F1-9]{1,3}")){
                res[0] = false;
            }
        });
        return res[0];
    }

}
