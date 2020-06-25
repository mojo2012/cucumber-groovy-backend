package io.cucumber.core.runner;

import java.util.Collection;

import io.cucumber.core.backend.Glue;
import io.cucumber.core.backend.StepDefinition;

public class CachingGlueUtil {

	public static Collection<StepDefinition> getStepDefinitions(Glue glue) {
		return ((CachingGlue) glue).getStepDefinitions();
	}
}
