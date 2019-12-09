import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    public test(){
        Container container=getContentPane();
        setTitle("测试一下");
        setLocation(100,200);
        container.setLayout(new FlowLayout());
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        CategoryDataset mDataset = GetDataset();
        JFreeChart mChart = ChartFactory.createLineChart(
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
        container.add(new ChartPanel(mChart));
        JPanel jPanel_main=new JPanel(new GridLayout(2,1));
        JPanel jPanel=new JPanel(new FlowLayout());
        jPanel.add(new JLabel("目前温度为：32度"));
        jPanel_main.add(jPanel);

        JPanel jPanel1=new JPanel(new GridLayout(3,1,10,50));
        String aa[]={"导出日志","查看日志","退出系统"};
        for(int i=0;i<3;i++){
            jPanel1.add(new JButton(aa[i]));
        }
        jPanel_main.add(jPanel1);
        container.add(jPanel_main);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {

        new test();

    }
    public static CategoryDataset GetDataset()
    {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        mDataset.addValue(10.5, "First", "20:30");
        mDataset.addValue(37.5, "First", "21:00");
        mDataset.addValue(26.8, "First", "21:30");
        mDataset.addValue(20.0, "First", "22:00");
        mDataset.addValue(30.5, "First", "22:30");
        mDataset.addValue(12.8, "First", "23:00");
        mDataset.addValue(14.9, "Second", "20:30");
        mDataset.addValue(13.8, "Second", "21:00");
        mDataset.addValue(12.4, "Second", "21:30");
        mDataset.addValue(9.8, "Second", "22:00");
        mDataset.addValue(5.9, "Second", "22:30");
        mDataset.addValue(7.7, "Second", "23:00");
        mDataset.addValue(10.9, "Third", "20:30");
        mDataset.addValue(12.4, "Third", "21:00");
        mDataset.addValue(1.5, "Third", "21:30");
        mDataset.addValue(9.8, "Third", "22:00");
        mDataset.addValue(7.1, "Third", "22:30");
        mDataset.addValue(2.5, "Third", "23:00");
        return mDataset;
    }
}

