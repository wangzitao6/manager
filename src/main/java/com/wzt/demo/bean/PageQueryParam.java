package com.wzt.demo.bean;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * 分页查询参数
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
public class PageQueryParam implements Serializable {

    @Min(value = 1, message = "页码最小值为1")
    private Integer pageNumber = 1;
    @Min(value = 1, message = "页大小最小值为1")
    private Integer pageSize = 10;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber != null) {
            this.pageNumber = pageNumber;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    public Pagination toPagination() {
        return new Pagination(pageNumber, pageSize);
    }

    public <T> com.baomidou.mybatisplus.plugins.Page<T> getMybatisPage(Class<T> c) {
        return new com.baomidou.mybatisplus.plugins.Page<T>(pageNumber, pageSize);
    }
}
