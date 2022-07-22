package com.example.versioning.custom;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@VersionMapping(method = RequestMethod.GET)
public @interface VersionGetMapping {

    /**
     * Alias for {@link VersionMapping#name}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String name() default "";

    /**
     * Alias for {@link VersionMapping#value}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] value() default {};

    /**
     * Alias for {@link VersionMapping#path}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] path() default {};

    /**
     * Alias for {@link VersionMapping#params}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] params() default {};

    /**
     * Alias for {@link VersionMapping#headers}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] headers() default {};

    /**
     * Alias for {@link VersionMapping#consumes}.
     * @since 4.3.5
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] consumes() default {};

    /**
     * Alias for {@link VersionMapping#produces}.
     */
    @AliasFor(annotation = VersionMapping.class)
    String[] produces() default {};

}
