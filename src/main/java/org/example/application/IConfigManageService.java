package org.example.application;

import org.example.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * 网关配置服务
 */
public interface IConfigManageService {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

}


