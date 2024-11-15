package org.example;


import com.alibaba.fastjson.JSON;
import org.example.application.IApiService;
import org.example.domain.model.ApiData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IApiService apiService;

    @Test
    public void test() {
        List<ApiData> apiDataList = apiService.queryHttpStatementList();
        logger.info("测试结果：{}", JSON.toJSONString(apiDataList));
    }

}

