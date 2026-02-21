package fr.paladium.palaforgeutils.lib.sidedaction.utils.server;

import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerComponent {
  SidedActionCache cache() default @SidedActionCache;
  
  boolean secure() default true;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\server\ServerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */