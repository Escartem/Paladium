package fr.paladium.palarpg.common.extended.playerdata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlayerData {
  String value() default "";
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\extended\playerdata\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */