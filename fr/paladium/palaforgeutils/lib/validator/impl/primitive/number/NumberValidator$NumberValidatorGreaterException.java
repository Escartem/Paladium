package fr.paladium.palaforgeutils.lib.validator.impl.primitive.number;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class NumberValidatorGreaterException extends ValidatorException {
  public NumberValidatorGreaterException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\" must be greater than {greater} but found {value}");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    NumberValidator.Greater greater = (NumberValidator.Greater)getAnnotation();
    return super.format(text).replace("{greater}", String.valueOf(greater.value()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\number\NumberValidator$NumberValidatorGreaterException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */