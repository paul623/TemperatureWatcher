package Frame;

import Manager.DataLogManager;
import Model.DataSetBean;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TableViewFrame extends JFrame {
    JTable jTable;
    List<DataSetBean> dataSetBeans;
    public TableViewFrame(){
        setTitle("日志查看");
        Container container=getContentPane();
        container.setLayout(new BorderLayout());
        setLocation(200,300);
        dataSetBeans=DataLogManager.getData();
        initJtable();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setViewportView(jTable);
        container.add(jScrollPane,BorderLayout.CENTER);
        container.setBackground(Color.white);
        createMenu();
        pack();
        int windowWidth = getWidth(); //获得窗口宽
        int windowHeight = getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setVisible(true);
    }
    private void initJtable() {
        jTable = new JTable();

        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn("传感器名称");
        tableModel.addColumn("温度");
        tableModel.addColumn("时间");
        jTable.setRowHeight(50);

        for (DataSetBean i : dataSetBeans) {
            Object[] o = {i.getColumnKey(), i.getValues(), i.getAddDate()};
            tableModel.addRow(o);
        }
        jTable.setBackground(Color.white);
    }
    private void createMenu(){
        JMenu jm=new JMenu("操作") ;     //创建JMenu菜单对象
        JMenuItem t1=new JMenuItem("导出日志") ;  //菜单项
        jm.add(t1);
        JMenuBar br=new JMenuBar();
        br.add(jm);
        setJMenuBar(br);
    }

}
