package io.cucumber.java;

import static io.cucumber.core.resource.ClasspathSupport.CLASSPATH_SCHEME;
import static io.cucumber.java.MethodScanner.scan;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import io.cucumber.core.backend.Backend;
import io.cucumber.core.backend.Container;
import io.cucumber.core.backend.Glue;
import io.cucumber.core.backend.Lookup;
import io.cucumber.core.backend.Snippet;
import io.cucumber.core.resource.ClasspathScanner;
import io.cucumber.core.resource.ClasspathSupport;

public class GroovyBackend implements Backend {

	private final Lookup lookup;
	private final Container container;
	private final ClasspathScanner classFinder;

	public GroovyBackend(Lookup lookup, Container container, Supplier<ClassLoader> classLoaderSupplier) {
		this.lookup = lookup;
		this.container = container;
		this.classFinder = new ClasspathScanner(classLoaderSupplier);
	}

	@Override
	public void loadGlue(Glue glue, List<URI> gluePaths) {
		GroovyGlueAdaptor glueAdaptor = new GroovyGlueAdaptor(lookup, glue);

		gluePaths.stream()
				.filter(gluePath -> CLASSPATH_SCHEME.equals(gluePath.getScheme()))
				.map(ClasspathSupport::packageName)
				.map(classFinder::scanForClassesInPackage)
				.flatMap(Collection::stream)
				.forEach(aGlueClass -> scan(aGlueClass, (method, annotation) -> {
					container.addClass(method.getDeclaringClass());
					glueAdaptor.addDefinition(method, annotation);
				}));
	}

//	private static void scan(Class<?> aClass, BiConsumer<Method, Annotation> consumer) {
//        if (aClass.getInterfaces(Groovy))
//    }

	@Override
	public void buildWorld() {
//
	}

	@Override
	public void disposeWorld() {
		//
	}

	@Override
	public Snippet getSnippet() {
		return new GroovySnippet();
	}



}