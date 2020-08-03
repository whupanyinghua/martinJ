package com.pyh.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 类UserType的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 21:11
 */
public interface UserType {
    public BigDecimal getRate();


    public static enum UserTypeA implements UserType {
        SVIP("0.7"),
        VIP("0.9"),
        NORMAL("1"),
        ;


        UserTypeA(String s) {
            rate = new BigDecimal(s);
        }

        BigDecimal rate;

        @Override
        public BigDecimal getRate() {
            return rate;
        }
    }

    public static enum UserTypeB implements UserType {
        GOLD_VIP("0.65"),
        SILVER_VIP("0.75"),
        COPPER_VIP("0.85"),
        NORMAL("1"),
        ;


        UserTypeB(String s) {
            rate = new BigDecimal(s);
        }

        BigDecimal rate;

        @Override
        public BigDecimal getRate() {
            return rate;
        }
    }

    public static enum UserTypeC implements UserType {
        CROWN_SVIP("0.8"),
        NORMAL("1"),
        ;


        UserTypeC(String s) {
            rate = new BigDecimal(s);
        }

        BigDecimal rate;

        @Override
        public BigDecimal getRate() {
            return rate;
        }
    }
}
