package fr.paladium.palaforgeutils.lib.validator.impl.primitive.string;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class StringValidatorRegexException extends ValidatorException {
  public StringValidatorRegexException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\" must match the regex pattern \"{regex}\" but found {value}");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    StringValidator.Regex regex = (StringValidator.Regex)getAnnotation();
    return super.format(text).replace("{regex}", regex.value());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\string\StringValidator$StringValidatorRegexException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */