package fr.paladium.palawither.common.wither.base.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WitherProperty {
  String value();
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\property\WitherProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */