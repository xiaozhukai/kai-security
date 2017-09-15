package com.github.kai.security.gate.rpc;

import com.github.kai.security.api.vo.log.LogInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO: LogService AOP层
 *
 * Author: kai
 * CreateDate: 2017/9/5
 * CreateTime: 11:53
 */
@FeignClient("admin-back")
public interface ILogService {
    @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
    public void saveLog(LogInfo info);
}
