package com.wxd.page;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@Getter
public class PageRequestParam extends PageRequest{
    /**
     * 参数
     */
    private final Map<String, Object> params = new HashMap();

    protected PageRequestParam(int page, int size, String countKey) {
        super(page, size, countKey);
    }

    public static PageRequestParam of(int page, int size) {
        return of(page, size, DEFAULT_COUNT_KEY);
    }

    public static PageRequestParam of(int page, int size, String countKey) {
        return new PageRequestParam(page, size, countKey);
    }

    public PageRequestParam put(String key, Object value){
        this.params.put(key,value);
        return this;
    }
}
