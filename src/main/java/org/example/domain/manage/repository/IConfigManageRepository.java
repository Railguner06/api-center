package org.example.domain.manage.repository;

import org.example.domain.manage.model.vo.GatewayServerDetailVO;
import org.example.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * 网关配置仓储服务
 */
public interface IConfigManageRepository {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

}
