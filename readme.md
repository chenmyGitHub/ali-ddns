### 项目简述:
通过获取本机公有IP（访问公有ip站点查询网站，正则解析）,结合阿里云域名API，通过定时器实现IP与域名的绑定。
方便动态IP的小伙伴把家里闲置的服务暴露到外网.


### 安装过程
  步骤1：下载代码   
  git clone https://github.com/chenmyGitHub/ali-ddns.git

  步骤2：修改application.properties
    ali.profile.regionId=cn-hangzhou
    ali.profile.accessKeyId=阿里云账号accessKeyId
    ali.profile.secret=阿里云账号的secret
    dnsValue=目标域名(www.touchyou.com)

   步骤3：打包
   mvn clean package
 
   步骤4：运行
   java -jar  ali-ddns.jar


### HTTP 接口:
- 获取本机IP字符串  
  _返回值只包含字符串,不需要解析JSON,但是不好判断服务器请求是否成功(无状态码)_   
请求URL: /d-dns/ip/my/string  
请求方式: GET  
参数: 无  
响应结果示例: 
``` json
111.111.111.111
```

- 获取域名解析的IP  
请求URL: /d-dns/dns/bind/current  
请求方式: GET  
参数:   

参数名|类型|示例|说明
---|---|---|---
host|String|www.baidu.com|要查询的域名地址
响应结果示例: 
``` json
{"code":200,"msg":"222.222.222.222"}
```

- 定时更新ip与dns
 updatePubIp2Host
