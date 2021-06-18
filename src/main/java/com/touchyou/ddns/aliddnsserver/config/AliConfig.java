package com.touchyou.ddns.aliddnsserver.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by  Touchyou on 2021/6/18.
 * @author Touchyou
 */
@Configuration
public class AliConfig {

    @Value("${ali.profile.regionId}")
    private String regionId;

    @Value("${ali.profile.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.profile.secret}")
    private String secret;



    @Bean
    public DefaultProfile getDefaultProfile() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        return profile;
    }

    @Bean
    @Autowired
    public IAcsClient getIAcsClient(DefaultProfile defaultProfile) {
        return new DefaultAcsClient(defaultProfile);
    }

}
