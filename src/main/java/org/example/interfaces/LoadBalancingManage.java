package org.example.interfaces;

import com.alibaba.fastjson.JSON;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.example.application.ILoadBalancingService;
import org.example.domain.docker.model.aggregates.NginxConfig;
import org.example.domain.docker.model.vo.LocationVO;
import org.example.domain.docker.model.vo.UpstreamVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wg/admin/load")
public class LoadBalancingManage {

    private Logger logger = LoggerFactory.getLogger(GatewayConfigManage.class);

    @Resource
    private ILoadBalancingService loadBalancingService;


    /**
     * http://localhost:8001/wg/admin/load/copy
     */
    @GetMapping(value = "copy", produces = "application/json;charset=utf-8")
    public void copy() throws IOException {
        // todo 修改地址
        ProcessBuilder pb = new ProcessBuilder(
                "docker",
                "cp",
                "/nginx.conf",
                "host:/Users/94461/Desktop/api/api-center/doc/data/nginx/nginx.conf"
        );
        pb.start();
    }

    private static void unTar(TarArchiveInputStream tis, File destFile)
            throws IOException {
        TarArchiveEntry tarEntry = null;
        while ((tarEntry = tis.getNextTarEntry()) != null) {
            if (tarEntry.isDirectory()) {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
            } else {
                FileOutputStream fos = new FileOutputStream(destFile);
                IOUtils.copy(tis, fos);
                fos.close();
            }
        }
        tis.close();
    }

    /**
     * http://localhost:8001/wg/admin/load/updateNginxConfig
     */
    @GetMapping(value = "updateNginxConfig", produces = "application/json;charset=utf-8")
    public void updateNginxConfig() {
        List<UpstreamVO> upstreamList = new ArrayList<>();
        // todo 修改地址
        upstreamList.add(new UpstreamVO("api01", "least_conn;", Arrays.asList("192.168.137.1:9001;", "192.168.137.1:9002;")));
        upstreamList.add(new UpstreamVO("api02", "least_conn;", Arrays.asList("192.168.137.1:9003;")));

        List<LocationVO> locationList = new ArrayList<>();
        locationList.add(new LocationVO("/api01/", "http://api01;"));
        locationList.add(new LocationVO("/api02/", "http://api02;"));
        NginxConfig nginxConfig = new NginxConfig(upstreamList, locationList);
        try {
            logger.info("刷新Nginx配置文件开始 nginxConfig：{}", JSON.toJSONString(nginxConfig));
            loadBalancingService.updateNginxConfig(nginxConfig);
            logger.info("刷新Nginx配置文件完成");
        } catch (Exception e) {
            logger.error("刷新Nginx配置文件失败", e);
        }
    }

}

