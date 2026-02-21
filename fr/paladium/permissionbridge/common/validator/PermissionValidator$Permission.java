package fr.paladium.permissionbridge.common.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Permission {
  String value();
  
  boolean exact() default false;
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\validator\PermissionValidator$Permission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */