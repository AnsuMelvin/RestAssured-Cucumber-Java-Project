package com.fdmgroup.stepdefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.fdmgroup.stepdefinitions",
    plugin = {"pretty", 
    		"html:Report/cucumber.html",
    		"json:Report/cucumber.json",
    		"junit:Report/cucumber.xml"
    		},
    monochrome = true,
    publish = true
)
public class CucumberTestRunner {

}
