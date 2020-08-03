package com.pyh.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 类Client的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:34
 */
public class Client {
    public static void main(String[] args) {
        // 选择策略对象
        Strategy strategy = new StrategyA();
        Context context = new Context(strategy);
        BigDecimal amount = context.calcAmount("SVIP", new BigDecimal(100));
        System.out.println("amount:"+amount);
    }
}
