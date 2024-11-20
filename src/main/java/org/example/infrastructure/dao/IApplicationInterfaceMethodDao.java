package org.example.infrastructure.dao;

import org.example.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应用接口方法
 */
@Mapper
public interface IApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

}
