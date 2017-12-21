package com.waterhub.web.model;

import java.io.Serializable;

public class MyPage<T> implements Serializable {

    private int page;
    private int lastPage;
    private int pageSize;
    private int sort;
    private long totalElement;
    private T data;

    public MyPage() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
