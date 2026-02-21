package fr.paladium.palaforgeutils.lib.command.annotated.annotation;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SubCommand {
  String command();
  
  String description() default "";
  
  String permission() default "";
  
  SenderType[] sender() default {SenderType.NONE};
  
  boolean help() default true;
  
  int priority() default 0;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\annotation\SubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */