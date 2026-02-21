package fr.paladium.palaforgeutils.lib.validator.impl.primitive.string;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.NonNull;

public class StringValidator extends AValidator<String> {
  public StringValidator() {
    super(new Class[] { String.class });
  }
  
  protected void validate(String value) {
    get(Length.class, length -> except((), new StringValidatorLengthException(this, length, value)));
    get(Regex.class, regex -> except((), new StringValidatorRegexException(this, regex, value)));
    get(StartsWith.class, startsWith -> except((), new StringValidatorRegexException(this, startsWith, value)));
    get(EndsWith.class, endsWith -> except((), new StringValidatorRegexException(this, endsWith, value)));
    get(Contains.class, contains -> except((), new StringValidatorRegexException(this, contains, value)));
    get(Empty.class, empty -> except((), new StringValidatorEmptyException(this, empty, value)));
    get(NotEmpty.class, notEmpty -> except((), new StringValidatorNotEmptyException(this, notEmpty, value)));
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Length {
    double min() default -1.7976931348623157E308D;
    
    double max() default 1.7976931348623157E308D;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Regex {
    String value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface StartsWith {
    String value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface EndsWith {
    String value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Contains {
    String value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Empty {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface NotEmpty {}
  
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
  
  public class StringValidatorEmptyException extends ValidatorException {
    public StringValidatorEmptyException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be empty but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class StringValidatorNotEmptyException extends ValidatorException {
    public StringValidatorNotEmptyException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must not be empty");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\string\StringValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */