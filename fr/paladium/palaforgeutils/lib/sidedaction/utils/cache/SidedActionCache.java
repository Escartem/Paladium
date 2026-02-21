package fr.paladium.palaforgeutils.lib.sidedaction.utils.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SidedActionCache {
  String client() default "";
  
  String server() default "";
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\cache\SidedActionCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */