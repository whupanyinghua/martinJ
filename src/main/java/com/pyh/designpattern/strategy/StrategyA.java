package com.pyh.designpattern.strategy;

import org.apache.commons.lang3.StringUtils;

/**
 * 类StrategyA的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:05
 */
public class StrategyA extends DefaultStrategy {

    @Override
    public UserType parseUserType(String userType) {
        for(UserType.UserTypeA userTypeA: UserType.UserTypeA.values()) {
            if(StringUtils.equalsIgnoreCase(userTypeA.name(), userType)) {
                return userTypeA;
            }
        }
        return UserType.UserTypeA.NORMAL; //如果传递的参数不正常，默认返回普通用户
    }
}
