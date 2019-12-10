package Model;

import Util.DateUtils;

import java.util.Objects;

public class DataSetBean {
    Double values;
    String rowKey;
    String columnKey;
    String addDate;

    public DataSetBean(Double values, String rowKey, String columnKey) {
        this.values = values;
        this.rowKey = rowKey;
        this.columnKey = columnKey;
        addDate= DateUtils.getCurTime();
    }

    public Double getValues() {
        return values;
    }

    public void setValues(Double values) {
        this.values = values;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSetBean that = (DataSetBean) o;
        return Objects.equals(values, that.values) &&
                Objects.equals(rowKey, that.rowKey) &&
                Objects.equals(columnKey, that.columnKey);
    }

    @Override
    public String toString() {
        return "DataSetBean{" +
                "values=" + values +
                ", rowKey='" + rowKey + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", addDate='" + addDate + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, rowKey, columnKey);
    }
}
