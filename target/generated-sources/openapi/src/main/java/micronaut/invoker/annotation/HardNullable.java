package micronaut.invoker.annotation;

import io.micronaut.core.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * An annotation specifying that a parameter is nullable, that is also inherited.
 * <p>The annotation is used in generated operations to make implementations consistent with
 * the specification.</p>
 */
@Nullable
@Inherited
@Target({ElementType.PARAMETER, ElementType.TYPE_PARAMETER, ElementType.FIELD})
public @interface HardNullable {

}
