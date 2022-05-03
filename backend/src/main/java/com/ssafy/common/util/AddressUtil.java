package com.ssafy.common.util;

import java.util.StringTokenizer;

public class AddressUtil {

    public static String[] get3DepthAddress(String address) {
        String[] splitAddress = new String[3];

        StringTokenizer st = new StringTokenizer(address);
        splitAddress[0] = st.nextToken(); // 시도
        splitAddress[1] = st.nextToken(); // 구군
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens())
            sb.append(st.nextToken()).append(" ");
        sb.setLength(sb.length() - 1);
        splitAddress[2] = sb.toString();

        return splitAddress;
    }

}
