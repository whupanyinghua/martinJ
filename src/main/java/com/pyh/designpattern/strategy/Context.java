package com.pyh.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 类Context的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:32
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal calcAmount(String userType, BigDecimal originAmount) {
        return strategy.calPrice(userType, originAmount);
    }
}
