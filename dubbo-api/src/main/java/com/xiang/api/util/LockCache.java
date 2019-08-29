package com.xiang.api.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Component
public class LockCache {

    private static ConcurrentHashMap<String, Lock> lockMap = new ConcurrentHashMap<String, Lock>();

    /**
     * 申请锁定对某指定字符串操作
     * @param str
     * @return
     */
    public static void reqOrderLock(String str){
        Lock lock = lockMap.get(str);
        if (lock == null) {
            lock = new ReentrantLock();
            Lock tmp = lockMap.putIfAbsent(str, lock);
            if(tmp != null){
                lock = tmp;
            }
        }
        lock.lock();
    }

    /**
     * 申请解锁对某指定订单操作
     * @param str
     */
    public static void releaseOrderLock(String str){
        if(lockMap.containsKey(str)){
            Lock lock = lockMap.get(str);
            if(lock != null){
                lock.unlock();
            }
        }
    }
}
