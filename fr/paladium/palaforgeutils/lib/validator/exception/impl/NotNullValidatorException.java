package fr.paladium.palaforgeutils.lib.validator.exception.impl;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class NotNullValidatorException extends ValidatorException {
  public NotNullValidatorException(@NonNull AValidator<?> validator, @NonNull Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\" must not be null");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    return text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\exception\impl\NotNullValidatorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */