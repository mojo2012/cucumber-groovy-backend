package io.spotnext.cucumber.groovy;
import java.util.function.Supplier;

import io.cucumber.core.backend.Backend;
import io.cucumber.core.backend.BackendProviderService;
import io.cucumber.core.backend.Container;
import io.cucumber.core.backend.Lookup;
import io.cucumber.java.GroovyBackend;

public final class GroovyBackendProviderService implements BackendProviderService {
    @Override
    public Backend create(Lookup lookup, Container container, Supplier<ClassLoader> classLoaderSupplier) {
        return new GroovyBackend(lookup, container, classLoaderSupplier);
    }
}