package fr.paladium.palaforgeutils.lib.subcommand.base;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SubCommandInfo {
  String name();
  
  String permission() default "";
  
  String description() default "Cette commande n'a pas de description";
  
  boolean executable() default false;
  
  double min() default 0.0D;
  
  double max() default 1.7976931348623157E308D;
  
  Class<? extends Enum<?>> enumClass() default DefaultEnum.class;
  
  SubCommandType type();
  
  SenderType[] senderTypes() default {SenderType.CONSOLE, SenderType.PLAYER};
  
  String[] freeArgs() default {};
  
  public enum DefaultEnum {
  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\SubCommandInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */