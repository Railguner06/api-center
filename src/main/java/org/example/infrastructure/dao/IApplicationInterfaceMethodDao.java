package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import org.example.infrastructure.common.OperationRequest;
import org.example.infrastructure.po.ApplicationInterfaceMethod;

import java.util.List;

/**
 * 应用接口方法
 */
@Mapper
public interface IApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodList(ApplicationInterfaceMethod req);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

}
