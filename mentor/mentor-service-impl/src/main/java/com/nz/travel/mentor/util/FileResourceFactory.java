package com.nz.travel.mentor.util;

import static java.text.MessageFormat.format;
import static com.nz.travel.mentor.model.Constants.BLANK;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


/**
 * Prefixes the location with file: and calls the ResourceLoader to find the resource. Uses ResourceLoaderAware as @Resource
 * does not seem to work as this factory is called early in the startup cycle.
 * @see FileResourceFactoryUnitTest
 */
public class FileResourceFactory extends FareGateObject implements ResourceLoaderAware {

    private String location;
    private ResourceLoader resourceLoader;

    public Resource findResource() {
        if (logger.isDebugEnabled()) logger.debug(format("finding resource for <{0}>", location));
        String prefix = "file:";
        if (location.startsWith(prefix)) {
            prefix = BLANK;
        }
        return resourceLoader.getResource(prefix + location);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
