package org.example.domain.repository;

import org.example.domain.model.ApiData;

import java.util.List;

/**
 * API仓储
 */
public interface IApiRepository {
    List<ApiData> queryHttpStatementList();
}
