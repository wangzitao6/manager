package com.wzt.demo.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体基础类.
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
@Getter
@Setter
public abstract class BaseEntity<T extends Model> extends Model<T> {
    /**
     * 统一配置的主键，采用统一序列赋值
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @Override
    protected Long pkVal() {
        return this.id;
    }
}
