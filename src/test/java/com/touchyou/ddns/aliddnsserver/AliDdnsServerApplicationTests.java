package com.touchyou.ddns.aliddnsserver;

import com.touchyou.ddns.aliddnsserver.util.DnsUtil;
import com.touchyou.ddns.aliddnsserver.util.IpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AliDdnsServerApplicationTests {

    @Autowired
    private DnsUtil dnsUtil;

    @Test
    void contextLoads() {
        System.out.println(dnsUtil.getIpByHost("xxx.baidu.com"));

    }

    @Test
    void getPubIp() {
        IpUtil ipUtil = new IpUtil();
        System.out.println(ipUtil.getPublicIP());
    }

}
