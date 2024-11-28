package org.example.domain.register.repository;

import org.example.domain.register.model.vo.ApplicationInterfaceMethodVO;
import org.example.domain.register.model.vo.ApplicationInterfaceVO;
import org.example.domain.register.model.vo.ApplicationSystemVO;

/**
 * 接口注册仓储服务
 */
public interface IRegisterManageRepository {
    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
