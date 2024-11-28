package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.operation.model.vo.GatewayDistributionDataVO;
import org.example.infrastructure.common.OperationRequest;
import org.example.infrastructure.po.GatewayDistribution;

import java.util.List;

/**
 * 网关分配
 */
@Mapper
public interface IGatewayDistributionDao {

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    String queryGatewayDistribution(String systemId);

    List<GatewayDistribution> queryGatewayDistributionList();

    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

}



