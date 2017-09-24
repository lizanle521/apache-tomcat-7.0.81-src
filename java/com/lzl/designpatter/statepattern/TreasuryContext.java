package com.lzl.designpatter.statepattern;

import java.util.Date;

/**
 * 上下文接口
 * 状态方法实际调用的是具体Context里边的内容进行实现
 * Created by Lizanle on 2017/9/24.
 */
public interface TreasuryContext {
    /**
     * 上下文保存当前时间
     * 当前时间是不是应该有一个线程在跑，根据当前时间和时间策略，动态的改变上下文里边的状态
     * @param date
     */
    void setDate(Date date);

    /**
     * 时间更改以后判断当前时间进行更改当前的状态
     * 这应该有一个时间策略模式，根据对应的策略，设置对应的状态
     * @param state
     */
    void changeState(State state);

    /**
     * 呼叫警报中心
     * @param msg
     */
    void callSecurityCenter(String msg);

    /**
     * 警报中心记录行为
     * @param msg
     */
    void recordLog(String msg);
}
