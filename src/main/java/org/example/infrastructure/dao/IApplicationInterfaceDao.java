package org.example.infrastructure.dao;

import org.example.infrastructure.po.ApplicationInterface;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应用接口
 */
@Mapper
public interface IApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

}

