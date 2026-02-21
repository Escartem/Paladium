package fr.paladium.palaforgeutils.lib.command.annotated.annotation;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Command {
  String[] command();
  
  String description() default "";
  
  String permission() default "";
  
  SenderType[] sender() default {SenderType.ALL};
  
  boolean help() default true;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\annotation\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */