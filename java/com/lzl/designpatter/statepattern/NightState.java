package com.lzl.designpatter.statepattern;

import java.util.Date;

/**
 * Created by Lizanle on 2017/9/24.
 */
public class NightState implements State {

    private NightState(){
    }

    public static State getInstance(){
        return InnerStateHolder.state;
    }

    /**
     * 静态内部类来实现的单例模式。通过类加载机制保证只有一个State实例。
     *
     */
    private static class InnerStateHolder{
        public static State state;
        static {
            state = new NightState();
        }
    }

    @Override
    public void setClock(TreasuryContext context, Date date) {

    }

    @Override
    public void doUse(TreasuryContext context) {

    }

    @Override
    public void doAlarm(TreasuryContext context) {

    }

    @Override
    public void doPhone(TreasuryContext context) {

    }
}
