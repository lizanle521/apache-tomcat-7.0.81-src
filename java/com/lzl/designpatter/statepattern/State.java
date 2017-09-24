package com.lzl.designpatter.statepattern;

import java.util.Date;

/**
 * 状态接口
 * 这里我们要模拟一个金库警报系统
 *
 * 白天状态{
 *     使用金库{
 *         向警报中心报告使用行为
 *     }
 *
 *     警铃响起{
 *         向警报中心报告紧急事态
 *     }
 *
 *     正常通话{
 *         呼叫警报中心
 *     }
 * }
 *
 * 晚上的状态{
 *      使用金库{
 *         向警报中心报告紧急事态
 *     }
 *
 *     警铃响起{
 *         向警报中心报告紧急事态
 *     }
 *
 *     正常通话{
 *         呼叫警报中心的留言电话
 *     }
 * }
 *
 * 状态里边的方法接收的参数应该是包含所有的
 * Created by Lizanle on 2017/9/24.
 */
public interface State {
    /**
     * 设置时间
     * @param context
     */
    void setClock(TreasuryContext context, Date date);

    /**
     * 使用金库
     * @param context
     */
    void doUse(TreasuryContext context);

    /**
     * 使用
     * @param context
     */
    void doAlarm(TreasuryContext context);

    /**
     * 打电话
     * @param context
     */
    void doPhone(TreasuryContext context);
}
