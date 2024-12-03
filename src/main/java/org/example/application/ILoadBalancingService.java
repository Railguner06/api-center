package org.example.application;

import org.example.domain.docker.model.aggregates.NginxConfig;

/**
 * 负载均衡配置服务
 */
public interface ILoadBalancingService {

    void updateNginxConfig(NginxConfig nginxConfig) throws Exception;

}

