package Frame;

import Util.DateUtils;

import javax.swing.*;
import java.awt.*;

public class TimeShowPanel extends Thread {
    JTextField jTextField_time;
    public TimeShowPanel(){
        jTextField_time=new JTextField();
        jTextField_time.setEditable(false);
        Font font=new Font("宋体",Font.BOLD,18);
        jTextField_time.setFont(font);
        jTextField_time.setBackground(Color.WHITE);
    }
    @Override
    public void run() {
        while (true){
            jTextField_time.setText(DateUtils.getCurTime());
        }
    }
    public JTextField getTimeField(){
        return jTextField_time;
    }
}
