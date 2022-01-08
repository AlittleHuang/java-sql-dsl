package github.sql.dsl.query.suport.jdbc.meta;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectionInformation implements Iterable<ProjectionAttribute> {

    private static final Map<Class<?>, Map<Class<?>, ProjectionInformation>> cache = new ConcurrentHashMap<>();

    private final Map<String, ProjectionAttribute> nameMap = new ConcurrentHashMap<>();

    protected ProjectionInformation() {
    }

    public static ProjectionInformation get(Class<?> entityType, Class<?> projectionType) {
        return cache.computeIfAbsent(entityType, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(projectionType, k -> {
                    ProjectionInformation information = new ProjectionInformation();
                    try {
                        EntityInformation<?> instance = EntityInformation.getInstance(entityType);
                        BeanInfo info = Introspector.getBeanInfo(projectionType);
                        for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
                            String name = descriptor.getName();
                            Attribute a = instance.getAttribute(name);
                            if (a == null || !a.isBasic() || a.getJavaType() != descriptor.getPropertyType()) {
                                continue;
                            }
                            Field field = EntityInformation.getDeclaredField(projectionType, name);
                            Method getter = descriptor.getReadMethod();
                            Method setter = descriptor.getWriteMethod();
                            ProjectionAttribute attribute = new ProjectionAttribute(name, field, getter, setter);
                            information.nameMap.put(name, attribute);
                        }
                    } catch (Exception e) {
                        //noinspection ConstantConditions
                        throw (RuntimeException) e;
                    }
                    return information;
                });
    }

    public ProjectionAttribute get(String name) {
        return nameMap.get(name);
    }

    public static void main(String[] args) {
        ProjectionInformation attributes = get(User.class, Test.class);
        System.out.println(attributes);
        ProjectionAttribute attribute = attributes.get("id");
        System.out.println(attribute);
    }

    @NotNull
    @Override
    public Iterator<ProjectionAttribute> iterator() {
        return nameMap.values().iterator();
    }

    interface Test {
        Integer getId();
    }

    @Data
    static class User {
        Integer id;
    }


}
