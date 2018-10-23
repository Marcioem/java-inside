package fr.umlv.javainside.labFive;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface JSONProperty {
    String defaultPropertyValue() default "";
}
