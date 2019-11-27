package com.wzt.demo.bean;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象，因为mybatis插件返回的是一个零件.
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
public class Page<T> implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
    private Integer totalPage;
    private List<T> list;

    public Page(Integer pageNumber, Integer pageSize, Integer total, Integer totalPage, List<T> list) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }

    public Page(List<T> list, Pagination page) {
        this(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), list);
    }


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
