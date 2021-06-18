package com.touchyou.ddns.aliddnsserver.controller;

import com.touchyou.ddns.aliddnsserver.common.Result;
import com.touchyou.ddns.aliddnsserver.util.DnsUtil;
import com.touchyou.ddns.aliddnsserver.util.IpUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by  Touchyou on 2021/6/18.
 * @author Touchyou
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@RestController
@RequestMapping("/d-dns")
@EnableScheduling
@Slf4j
public class DDNSController {

    @Autowired
    private DnsUtil dnsUtil;


    //域名
    @Value("${dnsValue}")
    private String dnsValue;

    /**
     * 获取自己的公网IP
     * @param request
     * @return
     */
    @GetMapping("/ip/my/string")
    public String getMyIpString(HttpServletRequest request) {
        IpUtil ipUtil = new IpUtil();
        String remoteHost = ipUtil.getPublicIP();
        if (StrUtil.isBlank(remoteHost)) {
            return "error";
        }
        return remoteHost;
    }


    /**
     * 获取域名当前绑定的IP
     * @param host
     * @return
     */
    @GetMapping("/dns/bind/current")
    public Result getCurrentBinding(@RequestParam("host") String host) {
        String ip = dnsUtil.getIpByHost(host);
        if (StrUtil.hasBlank(ip)) {
            return Result.error("查询出错或域名不存在,请检查您的域名");
        }
        return Result.success(ip);
    }


    @Scheduled(cron ="*/59 * * * * ?")
    public void updatePubIp2Host() {
        IpUtil ipUtil = new IpUtil();
        String ipPubNew = ipUtil.getPublicIP();
        log.info("获取到公网ip:" + ipPubNew);

        if (StrUtil.hasBlank(ipPubNew)) {
            log.error("获取到公网ip为空！");
            return;
        }
        String ip_ali_old = dnsUtil.getIpByHost(dnsValue);
        if (ip_ali_old.equals(ipPubNew)) {
            log.info("获取到公网ip:" + ip_ali_old + "和 "+ dnsValue + "域名解析中的一致，故不修改。");
            return ;
        }

        String result = dnsUtil.updateBindIp(dnsValue, ipPubNew);
        if ("success".equals(result)) {
            log.info("成功把域名：" + dnsValue + "的 ip 修改成 :"  + ipPubNew);
            return ;
        }

    }

}
