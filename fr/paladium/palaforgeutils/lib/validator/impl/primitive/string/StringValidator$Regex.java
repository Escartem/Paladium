package fr.paladium.palaforgeutils.lib.validator.impl.primitive.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Regex {
  String value();
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\string\StringValidator$Regex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */