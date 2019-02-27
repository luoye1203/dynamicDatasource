package com.xzst.modi.app.gCommon;

import com.google.common.base.Function;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by huqingqing on 2017/8/10.
 */
public class PageModel<T> {
    /**
     * 总记录数
     */
    private long count;
    /**
     * 页码
     */
    private int pageNO;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 单页记录
     */
    private List<T> records = new ArrayList<>();
    /**
     * 起始index
     */
    private int firstIndex;
    /**
     * 其余参数
     */
    private Map<String, String> params;

    public long getCount() {
        return count;
    }

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /**
     * constructor
     *
     * @param pageNO
     * @param pageSize
     */
    public PageModel(final int pageNO, final int pageSize) throws Exception {
        if (!(pageNO > 0 && pageSize >= 0)) {
            throw new Exception();
        }
        this.pageNO = pageNO;
        this.pageSize = pageSize;
        this.records = Collections.emptyList();
        this.firstIndex = (pageNO - 1) * pageSize;
    }

    /**
     * 设置总记录数
     *
     * @param count
     */
    public void setCount(final long count) {
        this.count = count;
        this.totalPage = this.pageSize > 0 ? (int) ((count + this.pageSize - 1) / this.pageSize) : 1;
        this.hasNextPage = this.pageNO < this.totalPage;
    }

    /**
     * deep copy
     *
     * @param function
     * @param <K>
     * @return
     */
    public <K> PageModel<K> copy(final Function<T, K> function) throws Exception {
        final PageModel<K> pageModel = new PageModel<>(this.pageNO, this.pageSize);
        pageModel.setCount(this.count);
        pageModel.setParams(this.params);
        pageModel.setRecords(this.records.stream().map(function::apply).collect(Collectors.toList()));
        return pageModel;
    }

    public PageModel<T> addParam(final String key, final String value) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(key, value);
        return this;
    }
}
