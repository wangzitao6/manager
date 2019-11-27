package com.wzt.demo.example.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzt.demo.bean.EntityWrapperDecorator;
import com.wzt.demo.bean.Page;
import com.wzt.demo.example.dao.StudentMapper;
import com.wzt.demo.example.dto.StudentDTO;
import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.service.StudentService;
import com.wzt.demo.example.vo.NotifyJsonVO;
import com.wzt.demo.example.vo.NotifyVO;
import com.wzt.demo.utils.BeanCopyUtil;
import com.wzt.demo.example.vo.StudentQueryPageVO;
import com.wzt.demo.example.vo.StudentQueryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzitao
 * @create 2018-05-17 10:23
 **/
@Service("studentServiceImpl")
@CacheConfig(cacheNames = "student")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentMapper studentMapper;

    @Override
    public void addUser(Student student) {
        studentMapper.insert(student);
    }

    @Override
    @Cacheable(keyGenerator = "keyGenerator")
    public Student selectStudentById(StudentQueryVO vo) {
        return studentMapper.selectById(vo.getId());
    }

    @Override
    @Cacheable(keyGenerator = "keyGenerator")
    public List<Student> findAllUser() {
        Wrapper<Student> wrapper = new EntityWrapper<Student>();

        return studentMapper.selectList(wrapper);
    }

    @Override
    public Page<StudentDTO> pageUser(StudentQueryPageVO vo) {
        EntityWrapperDecorator<Student> wrapper = new EntityWrapperDecorator<>();
        wrapper.eqNotNull("name",vo.getName());
        wrapper.eqNotNull("age",vo.getAge());
        wrapper.eqNotNull("school",vo.getSchool());
        Pagination pagination = vo.toPagination();
        List<Student> entities = studentMapper.selectPage(pagination,wrapper);
        List<StudentDTO> studentDTOS= BeanCopyUtil.copyList(entities,StudentDTO.class);
        return new Page<>(studentDTOS,pagination);
    }

    @Override
    public String CallBack(String json) {
        LOG.info("json: "+json);
        NotifyVO vo = JSONUtil.toBean(json, NotifyVO.class);
        String notifyJson = vo.getNotifyJson();
        NotifyJsonVO notifyJsonVO = JSONUtil.toBean(notifyJson, NotifyJsonVO.class);
        LOG.info("notifyJson: "+notifyJsonVO.toString());

        return "call";
    }

    @Override
    public String payCallBack(String json) {
        LOG.info("notifyJson: "+json);
        return "hei hei！ ";
    }
}
