package com.pyh.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 类DefaultStrategy的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:26
 */
public abstract class DefaultStrategy implements Strategy {
    @Override
    public BigDecimal calPrice(String userType, BigDecimal oriAmount) {
        return oriAmount.multiply(parseUserType(userType).getRate());
    }

    /**
     * 由子类保证返回不为空
     * @param userType
     * @return
     */
    public abstract UserType parseUserType(String userType);


}
