package com.xiang.api.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageHelper<T> implements Serializable{

    private int pageNumber = 1;// 当前第几页(默认第一页),---主要用于传递到前台显示
    private int totalPage;// 总页数
    private int totalCount;// 总记录数
    private int pageSize = 5;// 每页显示的记录条数(默认5条)

    private List<T> entitys = new ArrayList<T>();// 记录当前页中的数据条目

    private T total;

    /** 排序字段 */
    @JsonIgnore
    private String orderBy = null;
    /** 排序方向，升序还是降序 */
    @JsonIgnore
    private String order = null;

    @JsonIgnore
    private Boolean isAll = false;

    // 所有参数都进行修改

    public T getTotal() {
        return total;
    }

    public void setTotal(T total) {
        this.total = total;
    }

    public PageHelper(int pageNumber, int totalCount, int pageSize, List entitys) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalCount % pageSize == 0 ? totalCount
                / pageSize : totalCount / pageSize + 1;
        this.entitys = entitys;
        this.pageNumber = pageNumber<1?1:(pageNumber>totalPage?totalPage:pageNumber);//如果当前页小于第一页，则停留在第一页
    }
    public PageHelper(int pageNumber, int pageSize){
        if(pageNumber== -1 && pageSize == -1){
            this.isAll = true;
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    // 使用默认的当前页和每页显示记录条数
    public PageHelper(int totalCount, List entitys) {
        this.totalCount = totalCount;
        this.totalPage = totalCount % pageSize == 0 ? totalCount
                / pageSize : totalCount / pageSize + 1;
        this.entitys = entitys;
        this.pageNumber = pageNumber<1?1:(pageNumber>totalPage?totalPage:pageNumber);//如果当前页小于第一页，则停留在第一页
    }



    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalCount % pageSize == 0 ? totalCount
                / pageSize : totalCount / pageSize + 1;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (totalCount - 1)/pageSize + 1;
        this.pageNumber = pageNumber <1?1:(pageNumber>totalPage?totalPage:pageNumber);//如果当前页小于第一页，则停留在第一页
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber<1?1:(pageNumber>totalPage?totalPage:pageNumber);//如果当前页小于第一页，则停留在第一页
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<T> entitys) {
        this.entitys = entitys;
    }

    @Override
    public String toString() {
        return "PageUtil [pageNumber=" + pageNumber + ", totalPage="
                + totalPage + ", totalCount=" + totalCount
                + ", pageSize=" + pageSize + ", entitys=" + entitys + "]";
    }


    @JsonIgnore
    public int getLimit() {
        return pageSize;
    }

    @JsonIgnore
    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Boolean getIsAll() {
        return isAll;
    }

    public void setIsAll(Boolean all) {
        isAll = all;
    }
}

