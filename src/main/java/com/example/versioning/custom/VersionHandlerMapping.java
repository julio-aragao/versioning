package com.example.versioning.custom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.CompositeRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
public class VersionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    public int getOrder() {
        return 0; // needed so this mapping happens before the default one from Spring
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        VersionMapping versionMapping = handlerType.getAnnotation(VersionMapping.class);

        if ( versionMapping == null ) {
            return null;
        }

        ApplicationContext context = obtainApplicationContext();
        VersionRequestCondition versionRequestCondition =
                context.getBean(
                    VersionRequestCondition.class,
                    versionMapping.version(),
                    versionMapping.value()[0]
                );

        return versionRequestCondition;

//        RequestMappingInfo requestMappingInfo =
//                RequestMappingInfo.paths(versionMapping.path())
//                    .mappingName(versionMapping.name())
//                    .methods(versionMapping.method())
//                    .params(versionMapping.params())
//                    .headers(versionMapping.headers())
//                    .consumes(versionMapping.consumes())
//                    .produces(versionMapping.produces())
//                    .build();
//
//        return new CompositeRequestCondition(
//            versionRequestCondition,
//            requestMappingInfo
//        );
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        method.setAccessible(true);
        VersionMapping versionMapping = method.getDeclaringClass().getAnnotation(VersionMapping.class);

        VersionGetMapping getMapping = method.getAnnotation(VersionGetMapping.class);
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);


        if( getMapping == null ) {
            return null;
        }

        VersionRequestCondition versionRequestCondition =
                obtainApplicationContext().getBean(
                VersionRequestCondition.class,
                versionMapping.version(),
                getMapping.value()[0]
        );

        return versionRequestCondition;

//        RequestMappingInfo requestMappingInfo =
//                RequestMappingInfo.paths(getMapping.path())
//                        .mappingName(getMapping.name())
//                        .methods(RequestMethod.GET)
//                        .params(getMapping.params())
//                        .headers(getMapping.headers())
//                        .consumes(getMapping.consumes())
//                        .produces(getMapping.produces())
//                        .build();
//
//        return new CompositeRequestCondition(
//            versionRequestCondition,
//            requestMappingInfo
//        );
    }
}
