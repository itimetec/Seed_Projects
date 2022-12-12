package com.itt.base;

import com.itt.configurations.test.TestConfiguration;
import com.itt.reporting.TestReport;
import com.itt.testExecution.AnnotationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@Listeners({TestReport.class, AnnotationTransformer.class})
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class TestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    protected TestConfiguration configuration;


}
