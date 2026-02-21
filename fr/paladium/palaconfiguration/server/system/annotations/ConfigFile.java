package fr.paladium.palaconfiguration.server.system.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigFile {
  String path();
  
  boolean blocking() default false;
  
  boolean reloadable() default true;
  
  boolean local() default false;
  
  boolean hidden() default false;
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\system\annotations\ConfigFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */