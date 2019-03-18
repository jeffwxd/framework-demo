/*
 *  Copyright (c) 2015.  meicanyun.com Corporation Limited.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  meicanyun Company. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with meicanyun.com.
 */

package com.wxd.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author freeway
 * @date 15/12/4
 */
@Getter
@Setter
@Accessors(chain=true)
@ToString
public class Pagination<T> implements Serializable {

    /**
     * 当前页返回数据列表
     */
    private List<T> results;

    private long totalCount;

    @SuppressWarnings("unchecked")
    public static <T> Pagination<T> build(long totalCount) {
        return new Pagination<T>().setResults(Collections.<T>emptyList()).setTotalCount(totalCount);
    }

    public static <T> Pagination<T> build(List<T> results, long totalCount) {
        return new Pagination<T>().setResults(results).setTotalCount(totalCount);
    }

}
