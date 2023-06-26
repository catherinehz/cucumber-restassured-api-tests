package com.cucumber.runner;

import com.cucumber.helpers.ScenarioContext;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"com.cucumber.cucumberglue"},
        plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberTestRunner.class);

    @Before
    public void setUp() {
        LOG.info("------------- TEST CONTEXT SETUP -------------");
    }

    @After
    public void tearDown() {
        LOG.info("------------- TEST CONTEXT TEAR DOWN -------------");
        ScenarioContext.CONTEXT.reset();
    }

}
