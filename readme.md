# java web demo 

可运行地址 http://localhost:9999/?number=0

CI 服务器上执行单测单测的覆盖率文件，各 JVM 进程下执行以下步骤

steps:
- 1: pom.xml文件引入jacoco 插件，sonar 插件
- 2: 构建jacoco-build 文件 详细参考 注释
- 3：后端java进程服务启动时记得加上参数
    ```shell
         java -Xmx2560m -Xmx2560m -javaagent:/root/jacoco/jacocoagent.jar=includes=com.sxf.*,output=tcpserver,address=*,port=6302 -jar javaweb.jar 
    ```
   address=* 默认是和当前java同个Ip 对应jacoco.build 里面的 ${server_ip}
   port=6302 对应jacoco.build 里面的 ${server_port}
   
- 4：服务器上配置ant 的路径（实先安装好 apache-ant-1.9.15）,执行的方式如下
     ```shell
        /opt/apache-ant-1.9.15/bin/ant -file jacoco-build.xml report
     ```
     
     log 如下：
     ```
     02:35:34 dump:
     02:35:34    [echo] jacoco 从远程服务器dump executiondata
     02:35:34    [delete] Deleting: /var/lib/jenkins/jobs/Sonar-Coverage/workspace/javaweb/jacoco-unit.exec
     02:35:34 [jacoco:dump] Connecting to /172.24.153.45:6301
     02:35:34 [jacoco:dump] Dumping execution data to /var/lib/jenkins/jobs/Sonar-Coverage/workspace/agilean-rbac-server/rbac-jacoco.exec
     02:35:34 
     02:35:34 xml_report:
     02:35:34 [jacoco:report] Loading execution data file /var/lib/jenkins/jobs/Sonar-Coverage/workspace/agilean-rbac-server/rbac-jacoco.exec
     02:35:35 [jacoco:report] Writing bundle 'JaCoCo Ant Example' with 89 classes
     02:35:35 
     02:35:35 report:
     ```
- 5：sonar 集成
可以直接sonar，如果和目前团队方式不一样的话，如果可行，直接采用当前方式即可，没有硬性要求
    ```
    mvn sonar:sonar -Dsonar.host.url=$sonaradd -Dsonar.login=$sonarUser -Dsonar.password=$sonarPwd  -Dsonar.coverage.jacoco.xmlReportPaths=report.xml

  ```
  $sonaradd  : sonar 地址 
  $sonarUser : sonar 账号
  $sonarPwd  : sonar 密码