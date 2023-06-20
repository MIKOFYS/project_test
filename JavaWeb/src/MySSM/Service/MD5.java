package MySSM.Service;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    private final static String salt = "sdaf6546y65l;uq234o;i";

    public static String getMd5(String message){
        String base = message + "/" + salt;
        String md5 = DigestUtils.md2Hex(base.getBytes());
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(MD5.getMd5("SID99"));
    }
}