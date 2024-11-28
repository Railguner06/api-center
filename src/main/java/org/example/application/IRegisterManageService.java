package org.example.application;

import org.example.domain.register.model.vo.ApplicationInterfaceMethodVO;
import org.example.domain.register.model.vo.ApplicationInterfaceVO;
import org.example.domain.register.model.vo.ApplicationSystemVO;

/**
 * 接口注册服务
 */
public interface IRegisterManageService {
    /**
     * 注册应用
     * @param applicationSystemVO
     */
    void registerApplication(ApplicationSystemVO applicationSystemVO);

    /**
     * 注册应用接口
     * @param applicationInterfaceVO
     */
    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    /**
     * 注册应用接口方法
     * @param applicationInterfaceMethodVO
     */
    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}

