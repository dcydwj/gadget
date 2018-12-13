package com.design.chain.filter;

import com.design.chain.filter.impl.FilterChain;

/**
 * @Description:
 * @Time: 2018-12-11 22:26
 * @Version: 1.0
 */
public interface Filter {
    void doFilter(Req req, Resp resp, FilterChain chain);
}
