package com.wzt.demo.example.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wangzitao
 * @create 2018-05-18 17:58
 **/

public class StudentQueryVO implements Serializable {

    @NotNull(message = "id不能为空")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
