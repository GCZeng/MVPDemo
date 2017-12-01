package zgc.mvpdemo.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Nick on 2017/1/7
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivityScope {
}
