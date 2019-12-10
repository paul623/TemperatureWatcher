package Frame;

import Manager.DataLogManager;
import Manager.SerialPortManager;
import Model.DataSetBean;
import Util.DateUtils;
import Util.ShowUtils;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainViewFrame extends JFrame implements ActionListener {
    JFreeChart mChart;
    JTextField jTextField_temperature,jTextField_working_condition;
    TimeShowPanel timeShowPanel;
    JComboBox jComboBox_ports, jComboBox_baudrates;
    SerialPort serialPort;

    public MainViewFrame() {
        setTitle("温度检测软件");
        setLocation(200, 300);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        setResizable(false);
        timeShowPanel = new TimeShowPanel();
        JPanel jPanel_center = new JPanel(new BorderLayout());
        jPanel_center.add("West", getChart());
        jPanel_center.add("Center", getShowPanel());

        container.add("Center", jPanel_center);

        JPanel jPanel_South = new JPanel(new GridLayout(1, 2));
        jPanel_South.add(getSettingPanel());
        jPanel_South.add(getButtonPanel());
        container.add("South", jPanel_South);
        pack();
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private ChartPanel getChart() {
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = DataLogManager.getDataSet();
        mChart = ChartFactory.createLineChart(
                "温度实时监控系统V1.0",//图名字
                "时间",//横坐标
                "温度",//纵坐标
                mDataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器
                true);// 是否生成超链接
        CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
        mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);//边界线

        return new ChartPanel(mChart);
    }

    private JPanel getShowPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        jTextField_temperature = new JTextField();
        jTextField_temperature.setEditable(false);

        Font font = new Font("宋体", Font.BOLD, 28);
        jTextField_temperature.setFont(font);
        jTextField_temperature.setText("目前温度为:29摄氏度");
        jTextField_temperature.setBackground(Color.WHITE);
        jPanel.add("Center", jTextField_temperature);
        JPanel jPanel1 = new JPanel(new FlowLayout());
        jPanel1.add(timeShowPanel.getTimeField());
        jPanel1.setBackground(Color.WHITE);
        jPanel.add("South", jPanel1);
        timeShowPanel.start();
        return jPanel;
    }

    private JPanel getButtonPanel() {
        Font font=new Font("宋体",Font.BOLD,15);
        jTextField_working_condition=new JTextField("准备就绪");
        jTextField_working_condition.setFont(font);
        jTextField_working_condition.setEditable(false);

        jTextField_working_condition.setBackground(Color.white);
        String[] strs = {"刷新","开始检测", "停止检测", "查看日志", "退出系统"};
        JPanel jPanel_main=new JPanel(new GridLayout(2,1,5,5));
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel_main.setBorder(BorderFactory.createTitledBorder("操作"));
        jPanel.setBackground(Color.white);
        for (String i : strs) {
            JButton jButton = new JButton(i);
            jButton.setBackground(Color.WHITE);
            jButton.setPreferredSize(new Dimension(100, 50));
            jButton.addActionListener(this);
            jPanel.add(jButton);
        }
        jPanel_main.add(jPanel);
        JPanel jPanel_text=new JPanel(new FlowLayout());
        jPanel_text.setBackground(Color.white);
        jPanel_text.add(jTextField_working_condition);
        jPanel_main.add(jPanel_text);
        jPanel_main.setBackground(Color.white);

        return jPanel_main;
    }

    private JPanel getSettingPanel() {
        //String[] ports = {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6"};
        List<String> ports=SerialPortManager.findPorts();
        if(ports.size()==0){
            ShowUtils.warningMessage("未找到有效端口，请检查连接后单击刷新按钮重试！");
        }
        String[] baudrates = {"9600", "19200", "38400", "57600", "115200"};
        JPanel jPanel = new JPanel(new GridLayout(2, 1));
        jPanel.setBorder(BorderFactory.createTitledBorder("设置"));
        jPanel.setBackground(Color.white);

        JPanel jPanel_1 = new JPanel(new FlowLayout());
        jPanel_1.setBackground(Color.white);
        jPanel_1.add(new JLabel("串口     "));
        jComboBox_ports = new JComboBox();
        jComboBox_ports.setBackground(Color.WHITE);
        for (String i : ports) {
            jComboBox_ports.addItem(i);
        }
        jComboBox_ports.setPreferredSize(new Dimension(100, 40));
        jComboBox_ports.addActionListener(this);
        jPanel_1.add(jComboBox_ports);

        JPanel jPanel_2 = new JPanel(new FlowLayout());
        jPanel_2.setBackground(Color.white);
        jPanel_2.add(new JLabel("波特率"));
        jComboBox_baudrates = new JComboBox();
        jComboBox_baudrates.setBackground(Color.WHITE);
        for (String i : baudrates) {
            jComboBox_baudrates.addItem(i);
        }
        jComboBox_baudrates.setPreferredSize(new Dimension(100, 40));
        jComboBox_baudrates.addActionListener(this);
        jPanel_2.add(jComboBox_baudrates);

        jPanel.add(jPanel_1);
        jPanel.add(jPanel_2);
        return jPanel;
    }

    public static void main(String[] args) {
        new MainViewFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "开始检测":
                openSerialPort();
                break;
            case "停止检测":
                closeSerialPort();
                break;
            case "查看日志":
                new TableViewFrame();
                break;
            case "退出系统":
                ShowUtils.message("感谢使用，bye~");
                System.exit(0);
                break;
            case "刷新":
                updatePortName();
                break;
        }

    }
    /**
     * 打开串口通信~
     * */
    private void openSerialPort(){
        //获取端口号
        String commName=(String)jComboBox_ports.getSelectedItem();
        //获取波特率，默认为9600
        int baudrate=9600;
        String btl=(String)jComboBox_baudrates.getSelectedItem();
        baudrate=Integer.parseInt(btl);

        //检查串口名称是否正确
        if(commName==null||commName.equals("")){
            ShowUtils.warningMessage("没有搜索到有效的串口！");
        }else {
            try {
                serialPort= SerialPortManager.openPort(commName,baudrate);
                if(serialPort!=null){
                    jTextField_working_condition.setText("正在监测");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("串口已经被占用！");
            }
        }
        SerialPortManager.addListener(serialPort, new SerialPortManager.DataAvailableListener() {
            @Override
            public void dataAvailable() {
                byte[] data=null;
                if(serialPort==null){
                    ShowUtils.errorMessage("串口对象为空，监听失败！");
                }else {
                    //读取串口数据
                    data=SerialPortManager.readFromPort(serialPort);
                    //以字符串形式接收数据
                    String str=new String(data);
                    DataSetBean dataSetBean=new DataSetBean(Double.parseDouble(str),commName, DateUtils.getCurTime());
                    DataLogManager.saveIntoLocal(dataSetBean);
                }
            }
        });
    }
    /**
     * 关闭串口通信
     * */
    private void closeSerialPort() {
        SerialPortManager.closePort(serialPort);
        jTextField_working_condition.setText("准备就绪");
        serialPort = null;
    }

    private void updatePortName(){
        List<String> ports=SerialPortManager.findPorts();
        if(ports.size()==0){
            ShowUtils.warningMessage("未找到有效端口，请检查连接后单击刷新按钮重试！");
        }
        jComboBox_ports.removeAllItems();
        for (String i : ports) {
            jComboBox_ports.addItem(i);
        }
    }

}
