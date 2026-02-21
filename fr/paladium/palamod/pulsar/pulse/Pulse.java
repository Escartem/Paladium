package fr.paladium.palamod.pulsar.pulse;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Pulse {
  String id();
  
  String description() default "";
  
  String pulsesRequired() default "";
  
  boolean forced() default false;
  
  boolean defaultEnable() default true;
  
  boolean clientOnly() default false;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\pulse\Pulse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */