package com.design.chain.impl;

import com.design.chain.Ratify;
import com.design.chain.Ratify.Chain;
import com.design.chain.Request;
import com.design.chain.Result;

import java.util.List;

/**
 * @Description: 实现Chain的真正的包装Request和转发功能
 * @Time: 2018-12-09 17:31
 * @Version: 1.0
 */
public class RealChain implements Chain {

    public Request request;

    public List<Ratify> ratifyList;

    public int index;


    public RealChain() {
    }

    public RealChain(Request request, List<Ratify> ratifyList, int index) {
        this.request = request;
        this.ratifyList = ratifyList;
        this.index = index;
    }


    @Override
    public Request request() {
        return request;
    }

    @Override
    public Result proceed(Request request) {

        Result proceed = null;
        if (ratifyList.size() > index) {
            RealChain realChain = new RealChain(request, ratifyList, index + 1);
            Ratify ratify = ratifyList.get(index);
            proceed = ratify.deal(request);
        }
        return proceed;
    }



}
