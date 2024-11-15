package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.po.HttpStatement;

import java.util.List;

/**
 * 网关接口映射信息表DAO
 */
@Mapper
public interface IHttpStatementDao {
    List<HttpStatement> queryHttpStatementList();
}
