package com.lzl.designpatter.statepattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/** 界面上下文
 * 个人觉得界面是不是应该独立描述
 * Created by Lizanle on 2017/9/24.
 */
public class FrameContext implements TreasuryContext ,ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void setDate(Date date) {

    }

    @Override
    public void changeState(State state) {

    }

    @Override
    public void callSecurityCenter(String msg) {

    }

    @Override
    public void recordLog(String msg) {

    }
}
