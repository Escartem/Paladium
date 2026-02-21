package fr.paladium.palaforgeutils.lib.command.annotated.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameter {
  String description() default "";
  
  String error() default "";
  
  boolean infinite() default true;
  
  CommandParameterTabComplete tabComplete() default @CommandParameterTabComplete;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\annotation\CommandParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */