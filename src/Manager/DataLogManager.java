package Manager;

import Model.DataSetBean;
import Util.ShowUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.List;

public class DataLogManager {
    static final String data_local_address="data/datalog.txt";
    private static List<DataSetBean> getData(){
        List<DataSetBean> dataSetBeans=new ArrayList<>();
        String local_str=FileManager.readFile(data_local_address);
        if(local_str==null){
            ShowUtils.errorMessage("本地文件读取失败！");
        }else {
            Gson gson=new Gson();
            dataSetBeans=gson.fromJson(local_str,new TypeToken<List<DataSetBean>>(){}.getType());
            for(DataSetBean i:dataSetBeans){
                System.out.println(i);
            }
        }
        return dataSetBeans;
    }
    /**
     * 保存文件
     * @param dataSetBeans
     * */
    public static void saveData(List<DataSetBean> dataSetBeans){
        Gson gson=new Gson();
        String json=gson.toJson(dataSetBeans);
        FileManager.saveDataIntoFile(json,data_local_address);
    }
    /**
     * 获取本地文件
     * */
    public static CategoryDataset getDataSet(){
        List<DataSetBean> dataSetBeans=getData();
        DefaultCategoryDataset mDataSet=new DefaultCategoryDataset();
        for(DataSetBean i:dataSetBeans){
            mDataSet.addValue(i.getValues(),i.getRowKey(),i.getColumnKey());
        }
        return mDataSet;
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
