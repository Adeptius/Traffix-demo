package ua.adeptius.traffix.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * My annotation that invoking method after Spring load complete
 * @see PostProxyInvokerContextListener
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterSpringLoadComplete {
}
