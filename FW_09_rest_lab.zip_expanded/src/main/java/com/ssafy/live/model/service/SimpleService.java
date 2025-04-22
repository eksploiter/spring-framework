package com.ssafy.live.model.service;

import java.util.Map;

public interface SimpleService {
    Map<String, Integer> getGugu(int dan);

    void required(int mod);

    void requiresNew(int mod);

    void nested(int mod);

}
