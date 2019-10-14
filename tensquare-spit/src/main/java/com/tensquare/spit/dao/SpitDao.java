package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-10-14 15:56
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    public Page<Spit> findByParentid(String parentId, Pageable pageable);
}
