package com.github.wx.gadget.service.impl;

import com.github.wx.gadget.dbo.Tear;
import com.github.wx.gadget.mybatis.mapper.TearMapper;
import com.github.wx.gadget.service.TearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 2016/11/29.
 */
@Service("testService2")
public class TearServiceImpl implements TearService {

    @Autowired
    private TearMapper tearMapper;

    @Override
    public List<Tear> allTears() {
        return tearMapper.all();
    }

}
