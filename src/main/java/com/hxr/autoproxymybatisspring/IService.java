package com.hxr.autoproxymybatisspring;

import com.hxr.autoproxymybatisspring.mapper.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houxiurong
 * @date 2019-10-18
 */
@Service
public class IService {

    @Autowired
    private IDao iDao;

    public void list() {
        System.out.println(iDao.list());
    }
}
