package com.example.Flying_Tiger.Mapper;
import com.example.Flying_Tiger.Class.DBConn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExclusiveWriteLockManager {
    private static ExclusiveWriteLockManager instance;
    private Map<Long, Long> lockMapper = new ConcurrentHashMap<Long, Long>();
    public static final ExclusiveWriteLockManager getInstance() {
        if (instance == null) {
            try {
                instance = new ExclusiveWriteLockManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public boolean acquireLock(long lockable, long owner) {

        boolean result = true;
        if(lockMapper.containsKey(lockable)){
            result = false;
        }else{
            lockMapper.put(lockable, owner);
        }

        return result;
    }

    public void releaseLock(long lockable, long owner) {
        lockMapper.remove(lockable);
    }

}
