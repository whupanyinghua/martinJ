package com.pyh.designpattern.strategy;

import org.apache.commons.lang3.StringUtils;

/**
 * 类StrategyB的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:05
 */
public class StrategyB extends DefaultStrategy {
    @Override
    public UserType parseUserType(String userType) {
        for(UserType.UserTypeB userTypeB: UserType.UserTypeB.values()) {
            if(StringUtils.equalsIgnoreCase(userTypeB.name(), userType)) {
                return userTypeB;
            }
        }
        return UserType.UserTypeB.NORMAL; //如果传递的参数不正常，默认返回普通用户
    }
}
