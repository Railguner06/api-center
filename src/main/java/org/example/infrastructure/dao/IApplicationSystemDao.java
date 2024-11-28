package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.operation.model.vo.ApplicationSystemDataVO;
import org.example.infrastructure.common.OperationRequest;
import org.example.infrastructure.po.ApplicationSystem;

import java.util.List;

/**
 * 应用系统
 */
@Mapper
public interface IApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

    List<ApplicationSystem> queryApplicationSystemList(List<String> list);

    List<ApplicationSystem> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

    int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);

}

