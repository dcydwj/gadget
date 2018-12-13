package com.design.chain.filter.impl;

import com.design.chain.filter.Filter;
import com.design.chain.filter.Req;
import com.design.chain.filter.Resp;

/**
 * @Description:
 * @Time: 2018-12-11 22:43
 * @Version: 1.0
 */
public class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Req req, Resp resp, FilterChain chain) {

        req.setReqStr(req.getReqStr().replace("被就业","就业")+"---sensitiveFilter()");

        chain.doFilter(req, resp, chain);

        resp.setRespStr(resp.getRespStr() + "---sensitiveFilter()");
    }

}
