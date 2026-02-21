package fr.paladium.permissionbridge.common.validator;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class NotPermissionValidatorException extends ValidatorException {
  public NotPermissionValidatorException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\" have the \"{permission}\"{exact} permission.");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    PermissionValidator.Permission permission = (PermissionValidator.Permission)getAnnotation();
    return super.format(text).replace("{permission}", String.valueOf(permission.value())).replace("{exact}", permission.exact() ? " exact" : "");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\validator\PermissionValidator$NotPermissionValidatorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */