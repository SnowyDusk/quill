package com.quill.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowFlake {
    private static final long dataCenterId = 1;
    private static final long workerId = 1;

    public static long nextId() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
    }
}