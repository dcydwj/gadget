package com.design.chain.filter.impl;

import com.design.chain.filter.Filter;
import com.design.chain.filter.Req;
import com.design.chain.filter.Resp;

/**
 * @Description:
 * @Time: 2018-12-11 22:33
 * @Version: 1.0
 */
public class FaceFilter implements Filter {
    @Override
    public void doFilter(Req req, Resp resp, FilterChain chain) {
        String reqStr = req.getReqStr().replace(":):", "^V^") + "----FaceFilter()";
        req.setReqStr(reqStr);
        chain.doFilter(req, resp, chain);
        resp.setRespStr(resp.getRespStr() + "----FaceFilter()");
    }



}
