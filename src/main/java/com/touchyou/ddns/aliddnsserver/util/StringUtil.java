package com.touchyou.ddns.aliddnsserver.util;

/**
 * Create by  Touchyou on 2021/6/18.
 * @author Touchyou
 */
public class StringUtil {

    /**
     * 获取域名的二级域名
     * @param host
     * @return
     */
    public static String getSecondaryDomain(String host) {
        int lastDelimiter = host.lastIndexOf(".", host.lastIndexOf(".") - 1);
        return host.substring(0, lastDelimiter);
    }

}
