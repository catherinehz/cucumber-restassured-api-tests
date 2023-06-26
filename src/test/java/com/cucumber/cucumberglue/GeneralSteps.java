package com.cucumber.cucumberglue;

import com.cucumber.helpers.ScenarioContext;

import static com.cucumber.helpers.ScenarioContext.CONTEXT;

public abstract class GeneralSteps {

  public ScenarioContext scenarioContext() {
    return CONTEXT;
  }

}
