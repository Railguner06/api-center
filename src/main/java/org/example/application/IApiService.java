package org.example.application;

import org.example.domain.model.ApiData;

import java.util.List;

/**
 * API服务
 */
public interface IApiService {
    List<ApiData> queryHttpStatementList();
}
