package org.example.domain.register.repository;

import org.example.domain.register.model.ApplicationInterfaceMethodVO;
import org.example.domain.register.model.ApplicationInterfaceVO;
import org.example.domain.register.model.ApplicationSystemVO;

/**
 * 接口注册仓储服务
 */
public interface IRegisterManageRepository {
    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
