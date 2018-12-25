/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lanpang.dubboapiprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lanpang.dubboapisamples.service.DemoService;
import org.springframework.beans.factory.annotation.Value;


@Service
public class DefaultDemoService implements DemoService {

    @Value("${server.port}")
    private String port;

    public String sayHello1(String name) {
        return "Hello1, " + name + " "+port;
    }
    public String sayHello2(String name) {
        return "Hello2, " + name + " "+port;
    }

}