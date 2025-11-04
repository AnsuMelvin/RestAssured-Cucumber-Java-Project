package com.fdmgroup.stepdefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.fdmgroup.stepdefinitions",
    plugin = {"pretty", 
    		"html:target/cucumber-report.html",
    		"json:target/cucumber-reports/cucumber.json",
    		"junit:target/cucumber-reports/cucumber.xml"
    		},
    monochrome = true,
    publish = true
)
public class CucumberTestRunner {

}
