package com.pyh.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 类Strategy的实现描述：策略接口
 *
 * @author panyinghua 2020-7-29 21:04
 */
public interface Strategy {

    public BigDecimal calPrice(String userType, BigDecimal oriAmount);

    public UserType parseUserType(String userType);
}
