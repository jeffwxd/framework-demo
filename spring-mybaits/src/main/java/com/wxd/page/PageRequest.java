package com.wxd.page;

import lombok.Getter;
import lombok.Setter;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@Getter
public class PageRequest {
    public static final String DEFAULT_COUNT_KEY = "1";
    public static final String DEFAULT_COUNT_KEY_ID = "id";

    public static final String DEFAULT_COUNT_KEY_SUB = "-1";

    private final int page;
    private final int size;

    /**
     * count(*)语句中的主键,默认为1，传入主键可以提供效率，
     * 注意: 如果查询 列 里面还有子查询，必须传-1 否则报错
     */
    private final String countKey;
    @Setter
    private long totalElements = 0L;

    protected PageRequest(int page, int size, String countKey) {
        this.page = page;
        this.size = size;
        this.countKey = countKey;
    }

    public static PageRequest of(int page, int size) {
        return of(page, size, DEFAULT_COUNT_KEY);
    }

    public static PageRequest of(int page, int size, String countKey) {
        return new PageRequest(page, size, countKey);
    }
}
