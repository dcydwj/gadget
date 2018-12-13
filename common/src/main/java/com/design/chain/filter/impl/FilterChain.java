package com.design.chain.filter.impl;

import com.design.chain.filter.Filter;
import com.design.chain.filter.Req;
import com.design.chain.filter.Resp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time: 2018-12-11 22:27
 * @Version: 1.0
 */
public class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<>();

    private int index;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public void doFilter(Req req, Resp resp, FilterChain chain) {

        if (filters.size() == index) {
            return;
        }
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(req, resp, chain);
    }
}
