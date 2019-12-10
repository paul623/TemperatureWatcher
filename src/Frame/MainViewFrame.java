package Frame;

import Manager.DataLogManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;


import javax.swing.*;
import java.awt.*;


public class MainViewFrame extends JFrame {
    JFreeChart mChart;
    JTextField jTextField_temperature;
    TimeShowPanel timeShowPanel;
    public MainViewFrame(){
        setTitle("温度检测软件");
        setLocation(200,300);
        Container container=getContentPane();
        container.setLayout(new BorderLayout());
        setResizable(false);
        timeShowPanel=new TimeShowPanel();
        JPanel jPanel_center=new JPanel(new BorderLayout());
        jPanel_center.add("West",getChart());
        jPanel_center.add("Center",getShowPanel());

        container.add("Center",jPanel_center);

        JPanel jPanel_South=new JPanel(new GridLayout(1,2));
        jPanel_South.add(getSettingPanel());
        jPanel_South.add(getButtonPanel());
        container.add("South",jPanel_South);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    private ChartPanel getChart(){
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = DataLogManager.GetDataset();
        mChart = ChartFactory.createLineChart(
                "温度实时监控系统V1.0",//图名字
                "时间",//横坐标
                "温度",//纵坐标
                mDataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器
                true);// 是否生成超链接

        CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);//边界线
        return new ChartPanel(mChart);
    }
    private JPanel getShowPanel(){
        JPanel jPanel=new JPanel(new BorderLayout());
        jTextField_temperature=new JTextField();
        jTextField_temperature.setEditable(false);

        Font font=new Font("宋体",Font.BOLD,28);
        jTextField_temperature.setFont(font);
        jTextField_temperature.setText("目前温度为:29摄氏度");
        jTextField_temperature.setBackground(Color.WHITE);
        jPanel.add("Center",jTextField_temperature);
        JPanel jPanel1=new JPanel(new FlowLayout());
        jPanel1.add(timeShowPanel.getTimeField());
        jPanel1.setBackground(Color.WHITE);
        jPanel.add("South",jPanel1);
        timeShowPanel.start();
        return jPanel;
    }
    private JPanel getButtonPanel(){
        String []strs={"开始检测","停止检测","查看日志","退出系统"};
        JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel.setBorder(BorderFactory.createTitledBorder("操作"));
        jPanel.setBackground(Color.white);
        for(String i:strs){
            JButton jButton=new JButton(i);
            jButton.setBackground(Color.WHITE);
            jButton.setPreferredSize(new Dimension(100,50));
            jPanel.add(jButton);
        }
        return jPanel;
    }
    private JPanel getSettingPanel(){
        String []ports={"COM1","COM2","COM3","COM4","COM5","COM6"};
        String []baudrates={"9600","19200","38400","57600","115200"};
        JPanel jPanel=new JPanel(new GridLayout(2,1));
        jPanel.setBorder(BorderFactory.createTitledBorder("设置"));
        jPanel.setBackground(Color.white);

        JPanel jPanel_1=new JPanel(new FlowLayout());
        jPanel_1.setBackground(Color.white);
        jPanel_1.add(new JLabel("串口     "));
        JComboBox jComboBox_ports=new JComboBox();
        jComboBox_ports.setBackground(Color.WHITE);
        for(String i:ports){
            jComboBox_ports.addItem(i);
        }
        jComboBox_ports.setPreferredSize(new Dimension(100,40));
        jPanel_1.add(jComboBox_ports);

        JPanel jPanel_2=new JPanel(new FlowLayout());
        jPanel_2.setBackground(Color.white);
        jPanel_2.add(new JLabel("波特率"));
        JComboBox jComboBox_baudrates=new JComboBox();
        jComboBox_baudrates.setBackground(Color.WHITE);
        for(String i:baudrates){
            jComboBox_baudrates.addItem(i);
        }
        jComboBox_baudrates.setPreferredSize(new Dimension(100,40));
        jPanel_2.add(jComboBox_baudrates);

        jPanel.add(jPanel_1);
        jPanel.add(jPanel_2);
        return jPanel;
    }

    public static void main(String[] args) {
        new MainViewFrame();
    }
}
