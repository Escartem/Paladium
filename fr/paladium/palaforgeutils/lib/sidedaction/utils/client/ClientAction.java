package fr.paladium.palaforgeutils.lib.sidedaction.utils.client;

import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientAction {
  SidedActionCache cache() default @SidedActionCache;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedactio\\utils\client\ClientAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */