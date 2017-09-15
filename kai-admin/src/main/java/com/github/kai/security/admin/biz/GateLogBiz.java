package com.github.kai.security.admin.biz;

import com.github.kai.security.admin.entity.GateLog;
import com.github.kai.security.admin.mapper.GateLogMapper;
import com.github.kai.security.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

@Service
public class GateLogBiz extends BaseBiz<GateLogMapper,GateLog>{

    @Override
    public void insert(GateLog entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(GateLog entity) {
        mapper.insertSelective(entity);
    }
}
