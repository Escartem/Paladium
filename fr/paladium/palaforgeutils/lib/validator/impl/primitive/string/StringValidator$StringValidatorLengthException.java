package fr.paladium.palaforgeutils.lib.validator.impl.primitive.string;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class StringValidatorLengthException extends ValidatorException {
  public StringValidatorLengthException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\"'s length must be between {min} and {max} but found {value}");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    StringValidator.Length length = (StringValidator.Length)getAnnotation();
    return super.format(text).replace("{min}", String.valueOf(length.min())).replace("{max}", String.valueOf(length.max()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\string\StringValidator$StringValidatorLengthException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */