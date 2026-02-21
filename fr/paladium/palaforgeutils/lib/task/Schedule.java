package fr.paladium.palaforgeutils.lib.task;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Schedule {
  boolean async() default true;
  
  long everyMillis() default -1L;
  
  String every() default "0s";
  
  long delayMillis() default -1L;
  
  String delay() default "0s";
  
  int repeat() default -1;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\task\Schedule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */