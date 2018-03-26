package android_debugdata_webtool.tool.itgowo.com.webtoollibrary;

import java.util.List;

/**
 * Created by hnvfh on 2017/8/22.
 */

public class Request {
    /**
     * 操作动作
     */
    private String action;
    /**
     * 数据库名
     */
    private String database;
    private String spFileName;

    /**
     * 数据表明
     */
    private String tableName;
    /**
     * 携带的数据
     */
    private String data;
    /**
     *
     */
    private List<RowDataRequest> RowDataRequests;
    /**
     * 非必须
     */
    private Integer pageIndex;
    /**
     * 非必须
     */
    private Integer pageSize;

    public String getSpFileName() {
        return spFileName;
    }

    public Request setSpFileName(String mSpFileName) {
        spFileName = mSpFileName;
        return this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Request setPageIndex(Integer mPageIndex) {
        pageIndex = mPageIndex;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Request setPageSize(Integer mPageSize) {
        pageSize = mPageSize;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public Request setTableName(String mTableName) {
        tableName = mTableName;
        return this;
    }

    public List<RowDataRequest> getRowDataRequests() {
        return RowDataRequests;
    }

    public Request setRowDataRequests(List<RowDataRequest> mRowDataRequests) {
        RowDataRequests = mRowDataRequests;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Request setAction(String mAction) {
        action = mAction;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public Request setDatabase(String mDatabase) {
        database = mDatabase;
        return this;
    }

    public String getData() {
        return data;
    }

    public Request setData(String mData) {
        data = mData;
        return this;
    }

    public static class RowDataRequest {
        public String title;
        public boolean isPrimary;
        public String dataType;
        public String value;

        public String getTitle() {
            return title;
        }

        public RowDataRequest setTitle(String mTitle) {
            title = mTitle;
            return this;
        }

        public boolean isPrimary() {
            return isPrimary;
        }

        public RowDataRequest setPrimary(boolean mPrimary) {
            isPrimary = mPrimary;
            return this;
        }

        public String getDataType() {
            return dataType;
        }

        public RowDataRequest setDataType(String mDataType) {
            dataType = mDataType;
            return this;
        }

        public String getValue() {
            return value;
        }

        public RowDataRequest setValue(String mValue) {
            value = mValue;
            return this;
        }
    }
}
