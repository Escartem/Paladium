package fr.paladium.palaforgeutils.lib.validator.exception;

import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.validator.AValidator;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public abstract class ValidatorException extends RuntimeException {
  private final AValidator<?> validator;
  
  private final Annotation annotation;
  
  private final Object value;
  
  private final String rawMessage;
  
  public AValidator<?> getValidator() {
    return this.validator;
  }
  
  public Annotation getAnnotation() {
    return this.annotation;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public String getRawMessage() {
    return this.rawMessage;
  }
  
  public ValidatorException(@NonNull AValidator<?> validator, @NonNull Annotation annotation, Object value, @NonNull String message) {
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    this.validator = validator;
    this.annotation = annotation;
    this.value = value;
    this.rawMessage = message.replace("{target}", validator.getTarget()).replace("{value}", (value == null) ? "null" : value.toString());
  }
  
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    return text.replace("{target}", this.validator.getTarget()).replace("{value}", (this.value == null) ? "null" : this.value.toString());
  }
  
  public String getMessage() {
    return "[" + ClassHelper.of(this.validator.getClass()).getSimpleName() + "][" + this.annotation.annotationType().getSimpleName() + "] " + format(this.rawMessage);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\exception\ValidatorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */