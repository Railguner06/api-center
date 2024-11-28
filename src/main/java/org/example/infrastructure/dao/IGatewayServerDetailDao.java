package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.operation.model.vo.GatewayServerDetailVO;
import org.example.infrastructure.common.OperationRequest;
import org.example.infrastructure.po.GatewayServerDetail;

import java.util.List;

@Mapper
public interface IGatewayServerDetailDao {

    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

    List<GatewayServerDetail> queryGatewayServerDetailList();

    List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetailVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetailVO> request);

}


