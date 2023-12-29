package dilib;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;

import java.util.HashMap;
import java.util.Map;

public class DiEnvironment implements Environment {
    @Override
    public Container configure(Configuration configuration) {
        Map<Class<?>, Class<?>> classesMap = new HashMap<>();
        Map<Class<?>, Object> instancesMap = new HashMap<>();
        Binder binder = new DiBinder(classesMap, instancesMap);
        Container container = new DiContainer(classesMap, instancesMap);
        configuration.configure(binder);
        return container;
    }
}
