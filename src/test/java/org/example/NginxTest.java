package org.example;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.CopyExample.unTar;

public class NginxTest {

    public static void main(String[] args) throws Exception {
        // todo 修改地址
        String nginxConfigFile = "/etc/nginx/nginx.conf"; // Nginx配置文件路径
        String newServer = "\n" +
                "user  nginx;\n" +
                "worker_processes  auto;\n" +
                "\n" +
                "error_log  /var/log/nginx/error.log notice;\n" +
                "pid        /var/run/nginx.pid;\n" +
                "\n" +
                "\n" +
                "events {\n" +
                "    worker_connections  1024;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "http {\n" +
                "    include       /etc/nginx/mime.types;\n" +
                "    default_type  application/octet-stream;\n" +
                "\n" +
                "    log_format  main  '$remote_addr - $remote_user [$time_local] \"$request\" '\n" +
                "                      '$status $body_bytes_sent \"$http_referer\" '\n" +
                "                      '\"$http_user_agent\" \"$http_x_forwarded_for\"';\n" +
                "\n" +
                "    access_log  /var/log/nginx/access.log  main;\n" +
                "\n" +
                "    sendfile        on;\n" +
                "    #tcp_nopush     on;\n" +
                "\n" +
                "    keepalive_timeout  65;\n" +
                "\n" +
                "    #gzip  on;\n" +
                "\n" +
                "    include /etc/nginx/conf.d/*.conf;\n" +
                "\n" +
                "    # 设定负载均衡的服务器列表\n" +
                "    upstream api01 {\n" +
                "\n" +
                "        least_conn;\n" +
                "\n" +
                "        server 192.168.137.1:9001;\n" +
                "        server 192.168.137.1:9002;\n" +
                "    }\n" +
                "\n" +
                "    # 设定负载均衡的服务器列表\n" +
                "    upstream api02 {\n" +
                "        server 192.168.137.1:9003;\n" +
                "    }\n" +
                "\n" +
                "    # HTTP服务器\n" +
                "    server {\n" +
                "        # 监听80端口，用于HTTP协议\n" +
                "        listen  80;\n" +
                "\n" +
                "        # 定义使用IP/域名访问\n" +
                "        server_name 192.168.137.1;\n" +
                "\n" +
                "        # 首页\n" +
                "        index index.html;\n" +
                "\n" +
                "        # 反向代理的路径（upstream绑定），location 后面设置映射的路径\n" +
                "        location / {\n" +
                "            proxy_pass http://192.168.137.1:9001;\n" +
                "        }\n" +
                "\n" +
                "        location /api01/ {\n" +
                "            proxy_pass http://api01;\n" +
                "        }\n" +
                "\n" +
                "         location /api02/ {\n" +
                "            proxy_pass http://api02;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        // 连接到Docker守护程序
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();

        String containerId = dockerClient.listContainersCmd()
                .withNameFilter(new ArrayList<String>() {{
                    add("Nginx");
                }})
                .exec()
                .get(0)
                .getId();

        File containerConfigFile = new File(nginxConfigFile);

        dockerClient.copyArchiveToContainerCmd(containerId)
                .withHostResource(containerConfigFile.getAbsolutePath())
                .withRemotePath("/etc/nginx/")
                .exec();

        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                .execCreateCmd(containerId)
                .withCmd("nginx", "-s", "reload")
                .exec();
        ExecStartResultCallback responseCallback = new ExecStartResultCallback();
        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(responseCallback).awaitCompletion();
    }

    @Test
    public void test_nginx_reload() throws InterruptedException, IOException {
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();

        String containerId = dockerClient.listContainersCmd()
                .withNameFilter(new ArrayList<String>() {{
                    add("Nginx");
                }})
                .exec()
                .get(0)
                .getId();

        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                .execCreateCmd(containerId)
                .withCmd("nginx", "-s", "reload")
                .exec();

        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ResultCallback.Adapter<>()).awaitCompletion();

        dockerClient.close();
    }

    @Test
    public void xx() throws Exception{
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        String containerId = dockerClient.listContainersCmd()
                .withNameFilter(new ArrayList<String>() {{
                    add("api-gateway-center");
                }})
                .exec()
                .get(0)
                .getId();

        // todo 修改地址
        ExecCreateCmdResponse execCreateCmdResponse = dockerClient
                .execCreateCmd(containerId)
                .withCmd("docker", "cp", "/nginx.conf", "/Users/94461/Desktop/api/api-center/doc/data/nginx/")
                .exec();

        dockerClient.execStartCmd(execCreateCmdResponse.getId())
                .exec(new ResultCallback.Adapter<>()).awaitCompletion();

        dockerClient.close();
    }

    @Test
    public void test_nginx_file() throws InterruptedException, IOException {
        // Docker client
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock").build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        // Copy file from container
        // todo 修改地址
        try (TarArchiveInputStream tarStream = new TarArchiveInputStream(
                dockerClient.copyArchiveFromContainerCmd("api-gateway-center",
                        "/nginx.conf").exec())) {
            unTar(tarStream, new File("/Users/94461/Desktop/api/api-center/doc/data/nginx/nginx.conf"));
        }
        dockerClient.close();
    }

}

