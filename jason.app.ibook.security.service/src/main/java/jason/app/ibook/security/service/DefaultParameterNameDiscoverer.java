package jason.app.ibook.security.service;

import jason.app.ibook.security.api.annotation.ParamName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;

public class DefaultParameterNameDiscoverer implements ParameterNameDiscoverer {

    @Override
    public String[] getParameterNames(Method method) {
        Annotation[][]  annotations = method.getParameterAnnotations();
        ParamName annotation = AnnotationUtils.getAnnotation(method,ParamName.class);
        return null;
    }

    @Override
    public String[] getParameterNames(Constructor<?> ctor) {
        // TODO Auto-generated method stub
        return null;
    }

}
