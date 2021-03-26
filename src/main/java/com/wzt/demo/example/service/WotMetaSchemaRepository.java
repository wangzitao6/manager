package com.wzt.demo.example.service;

import com.wzt.demo.example.entity.WotMetaSchemaSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author wangzt29
 * @Date 2021/3/24 14:51
 * @Version 1.0.0
 */
@Repository
public interface WotMetaSchemaRepository extends MongoRepository<WotMetaSchemaSnapshot, String> {
}
