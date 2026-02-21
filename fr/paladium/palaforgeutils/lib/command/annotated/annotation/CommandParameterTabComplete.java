package fr.paladium.palaforgeutils.lib.command.annotated.annotation;

import cpw.mods.fml.relauncher.Side;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameterTabComplete {
  String[] value() default {};
  
  String method() default "";
  
  Side side() default Side.SERVER;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\annotation\CommandParameterTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */