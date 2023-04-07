package com.sanapp.sms.controller;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class AnyQueueCondition extends AnyNestedCondition {

    public AnyQueueCondition(ConfigurationPhase configurationPhase) {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }
    @ConditionalOnProperty(name = "dependencies.aws.sqs-listener", havingValue = "config")
    static class testOne{

    }

    @ConditionalOnProperty(name = "dependencies.aws.sqs-listener", havingValue = "input")
    static class testTest{

    }

    @ConditionalOnProperty(name = "dependencies.aws.sqs-listener", havingValue = "real", matchIfMissing = true)
    static class testTest2{

    }
}
