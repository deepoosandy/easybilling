package com.sanapp.sms.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
//@Conditional(AnyQueueCondition.class)
public class TestService {
    public String name="done!";
}
