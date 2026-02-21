package fr.paladium.palamod.modules.paladium.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Item {
  String name();
  
  String texture() default "";
  
  String type() default "item";
  
  String registry() default "paladium";
  
  int stackSize() default 64;
  
  boolean valid() default true;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */