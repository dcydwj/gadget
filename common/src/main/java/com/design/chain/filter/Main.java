package com.design.chain.filter;

import com.design.chain.filter.impl.FaceFilter;
import com.design.chain.filter.impl.FilterChain;
import com.design.chain.filter.impl.SensitiveFilter;

/**
 * @Description:
 * @Time: 2018-12-11 22:47
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        String msg = ":):,<script>,敏感,被就业,网络授课";

        Req req = new Req(msg);
        Resp resp = new Resp("response: ");

        FilterChain chain = new FilterChain();

        chain.addFilter(new FaceFilter())
                .addFilter(new SensitiveFilter());

        chain.doFilter(req, resp, chain);

        System.out.println(req.getReqStr());
        System.out.println("----------------");
        System.out.println(resp.getRespStr());


    }
}
