package io.cucumber.java;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;
import java.util.List;

import io.cucumber.core.backend.Lookup;
import io.cucumber.core.backend.ParameterInfo;
import io.cucumber.core.backend.StepDefinition;

final class GroovyStepDefinition extends AbstractGlueDefinition implements StepDefinition {
	private final String expression;

	private final List<ParameterInfo> parameterInfos;

	GroovyStepDefinition(Method method, String expression, Lookup lookup) {
		super(method, lookup);
		this.parameterInfos = JavaParameterInfo.fromMethod(method);

		if (expression != null && !"".intern().equals(expression)) {
			this.expression = requireNonNull(expression, "cucumber-expression may not be null");
		} else {
			// groovy methods can be strings, so in case the expression is empty, we just use the method name
			this.expression = method.getName();
		}
	}

	@Override
	public void execute(Object[] args) {
		Invoker.invoke(this, lookup.getInstance(method.getDeclaringClass()), method, args);
	}

	@Override
	public String getPattern() {
		return expression;
	}

	@Override
	public List<ParameterInfo> parameterInfos() {
		return parameterInfos;
	}

}
