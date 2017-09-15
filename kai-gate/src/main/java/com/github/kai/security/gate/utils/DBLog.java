package com.github.kai.security.gate.utils;

import com.github.kai.security.api.vo.log.LogInfo;
import com.github.kai.security.gate.rpc.ILogService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 TODO: 数据库日志
 Author: kai
 CreateDate: 2017/9/5
 CreateTime: 14:24 */
@Slf4j             //Slf4j日志操作
public class DBLog extends Thread {
    private static DBLog dblog = null;
    private ILogService logService;
    //创建一个队列
    private static BlockingQueue<LogInfo> logInfoQueue = new LinkedBlockingQueue<LogInfo>(1024);

    public DBLog() {
        super("CLogOracleWriterThread");
    }

    /**
     TODO: 获取到LogService
     Author: kai
     CreateDate: 2017/9/5
     CreateTime: 14:32
     */
    public ILogService getLogService() {
        return logService;
    }

    /**
     TODO: 设置LogService层
     Author: kai
     CreateDate: 2017/9/5
     CreateTime: 14:32
     */
    public DBLog setLogService(ILogService logService) {
        if (this.logService == null) {
            this.logService = logService;
        }
        return this;
    }

    /**
     TODO: 加载DBLog实例
     Author: kai
     CreateDate: 2017/9/5
     CreateTime: 14:35
     */
    public static synchronized DBLog getInstance() {
        if (dblog == null) {
            dblog = new DBLog();
        }
        return dblog;
    }

    public void offerQueue(LogInfo logInfo) {
        try {
            logInfoQueue.offer(logInfo);
        } catch (Exception e) {
            log.error("日志写入失败", e);
        }
    }

    /**
     TODO: 多线程定义run方法
     Author: kai
     CreateDate: 2017/9/5
     CreateTime: 14:51
     */
    @Override
    public void run() {
        List<LogInfo> bufferedLogList = new ArrayList<LogInfo>();
        while (true) {
            try {
                //加入到array中logInfoQueue任务
                bufferedLogList.add(logInfoQueue.take());
                logInfoQueue.drainTo(bufferedLogList);
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    //写入日志
                    for (LogInfo log : bufferedLogList) {
                        logService.saveLog(log);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception eee) {
                }
            } finally {
                if (bufferedLogList != null && bufferedLogList.size() > 0) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
