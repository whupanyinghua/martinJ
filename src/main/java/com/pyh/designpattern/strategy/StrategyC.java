package com.pyh.designpattern.strategy;

import org.apache.commons.lang3.StringUtils;

/**
 * 类StrategyC的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:05
 */
public class StrategyC extends DefaultStrategy {
    @Override
    public UserType parseUserType(String userType) {
        for(UserType.UserTypeC userTypeC: UserType.UserTypeC.values()) {
            if(StringUtils.equalsIgnoreCase(userTypeC.name(), userType)) {
                return userTypeC;
            }
        }
        return UserType.UserTypeC.NORMAL; //如果传递的参数不正常，默认返回普通用户
    }
}
