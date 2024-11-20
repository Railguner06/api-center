package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.GatewayServer;

import java.util.List;

@Mapper
public interface IGatewayServerDao {

    List<GatewayServer> queryGatewayServerList();

}

