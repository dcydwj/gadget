package com.github.wx.gadget.service.impl;

import com.github.wx.gadget.dbo.Tear;
import com.github.wx.gadget.service.TearService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService ")
public class TestTearServiceImpl implements TearService {
    @Override
    public List<Tear> allTears() {
        return null;
    }
}
