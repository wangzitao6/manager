package com.wzt.demo.bean;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wzt.demo.utils.LambdaUtil;
import com.wzt.demo.utils.LambdaUtil.LambdaFun;


/**
 * Wrapper的装饰器,增加此类的原因是因为Wrapper拼接sql很不友好<br/>
 * 本质上是装饰模式，在这里增加 wrapper的功能，有需要就拓展，争取全面替代Wrapper
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
public class EntityWrapperDecorator<T> extends EntityWrapper<T> {

    public EntityWrapperDecorator() {
        super();
    }

    /**
     * 这个方法是简化了eq，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> eqNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.eq(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> eq(LambdaFun<T, ?> column, Object param) {
        this.eq(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> eqNotNull(LambdaFun<T, ?> column, Object params) {
        return eqNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了ne，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> neNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.ne(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> ne(LambdaFun<T, ?> column, Object param) {
        this.ne(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> neNotNull(LambdaFun<T, ?> column, Object params) {
        return neNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了lt，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> ltNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.lt(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> lt(LambdaFun<T, ?> column, Object param) {
        this.lt(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> ltNotNull(LambdaFun<T, ?> column, Object params) {
        return ltNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了le，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> leNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.le(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> le(LambdaFun<T, ?> column, Object param) {
        this.le(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> leNotNull(LambdaFun<T, ?> column, Object params) {
        return leNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了gt，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> gtNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.gt(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> gt(LambdaFun<T, ?> column, Object param) {
        this.gt(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> gtNotNull(LambdaFun<T, ?> column, Object params) {
        return gtNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了gt，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> geNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            this.ge(column.toString(), params);
        }
        return this;
    }

    public EntityWrapperDecorator<T> ge(LambdaFun<T, ?> column, Object param) {
        this.ge(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> geNotNull(LambdaFun<T, ?> column, Object params) {
        return geNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    /**
     * 这个方法是简化了like，因为大部分都是判断非空就增加条件<br>
     *
     * @return
     */
    public EntityWrapperDecorator<T> likeNotNull(Object column, Object params) {
        if (!StrUtil.isBlankIfStr(params)) {
            like(column.toString(), params.toString());
        }
        return this;
    }

    public EntityWrapperDecorator<T> like(LambdaFun<T, ?> column, String param) {
        like(LambdaUtil.fieldToCol2(column), param);
        return this;
    }

    public EntityWrapperDecorator<T> likeNotNull(LambdaFun<T, ?> column, Object params) {
        return likeNotNull(LambdaUtil.fieldToCol2(column), params);
    }

    public EntityWrapperDecorator<T> orderByAsc(String... cloumns) {
        if (cloumns != null) {
            return (EntityWrapperDecorator<T>) orderAsc(CollectionUtil.newArrayList(cloumns));
        }
        return this;
    }

    public EntityWrapperDecorator<T> orderByAsc(LambdaFun<T, ?>... cloumns) {

        return orderByAsc(funcToStr(cloumns));
    }

    public EntityWrapperDecorator<T> orderByDesc(String... cloumns) {
        if (cloumns != null) {
            return (EntityWrapperDecorator<T>) orderDesc(CollectionUtil.newArrayList(cloumns));
        }
        return this;
    }

    public EntityWrapperDecorator<T> orderByDesc(LambdaFun<T, ?>... cloumns) {
        return orderByDesc(funcToStr(cloumns));
    }

    public EntityWrapperDecorator<T> orderBy(LambdaFun<T, ?> column) {
        super.orderBy(LambdaUtil.fieldToCol2(column));
        return this;
    }

    public EntityWrapperDecorator<T> orderBy(LambdaFun<T, ?> column, boolean isAsc) {
        super.orderBy(LambdaUtil.fieldToCol2(column), isAsc);
        return this;
    }

    private String[] funcToStr(LambdaFun<T, ?>[] columns) {
        String[] columnStr = new String[columns.length];
        for (int i = 0; i < columnStr.length; i++) {
            columnStr[i] = LambdaUtil.fieldToCol2(columns[i]);
        }
        return columnStr;
    }

}
