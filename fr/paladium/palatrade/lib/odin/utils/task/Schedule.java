package fr.paladium.palatrade.lib.odin.utils.task;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Schedule {
  boolean async() default true;
  
  String every();
  
  String delay() default "0s";
  
  int repeat() default -1;
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odi\\utils\task\Schedule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */