package fr.paladium.palaforgeutils.lib.validator.impl.primitive.number;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import lombok.NonNull;

public class NumberValidator extends AValidator<Number> {
  public NumberValidator() {
    super(new Class[] { Number.class, int.class, long.class, float.class, double.class, short.class, byte.class });
  }
  
  protected void validate(Number value) {
    get(Range.class, range -> except((), new NumberValidatorRangeException(this, range, value)));
    get(Equals.class, equals -> except((), new NumberValidatorEqualsException(this, equals, value)));
    get(Greater.class, greater -> except((), new NumberValidatorGreaterException(this, greater, value)));
    get(GreaterOrEquals.class, greater -> except((), new NumberValidatorGreaterOrEqualsException(this, greater, value)));
    get(Lower.class, lower -> except((), new NumberValidatorLowerException(this, lower, value)));
    get(LowerOrEquals.class, lower -> except((), new NumberValidatorLowerOrEqualsException(this, lower, value)));
    get(Modulo.class, modulo -> except((), new NumberValidatorModuloException(this, modulo, value)));
    get(Negative.class, negative -> except((), new NumberValidatorNegativeException(this, negative, value)));
    get(Positive.class, positive -> except((), new NumberValidatorPositiveException(this, positive, value)));
    get(Zero.class, zero -> except((), new NumberValidatorZeroException(this, zero, value)));
    get(NotZero.class, notZero -> except((), new NumberValidatorNotZeroException(this, notZero, value)));
    get(Odd.class, odd -> except((), new NumberValidatorOddException(this, odd, value)));
    get(Even.class, even -> except((), new NumberValidatorEvenException(this, even, value)));
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Range {
    double min() default -1.7976931348623157E308D;
    
    double max() default 1.7976931348623157E308D;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Equals {
    double[] value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Greater {
    double value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface GreaterOrEquals {
    double value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Lower {
    double value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface LowerOrEquals {
    double value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Modulo {
    double value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Negative {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Positive {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Zero {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface NotZero {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Odd {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Even {}
  
  public class NumberValidatorRangeException extends ValidatorException {
    public NumberValidatorRangeException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be between {min} and {max} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.Range range = (NumberValidator.Range)getAnnotation();
      return super.format(text).replace("{min}", String.valueOf(range.min())).replace("{max}", String.valueOf(range.max()));
    }
  }
  
  public class NumberValidatorEqualsException extends ValidatorException {
    public NumberValidatorEqualsException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be equals to {values} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.Equals equals = (NumberValidator.Equals)getAnnotation();
      return super.format(text).replace("{values}", Arrays.toString(equals.value()));
    }
  }
  
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
  
  public class NumberValidatorGreaterOrEqualsException extends ValidatorException {
    public NumberValidatorGreaterOrEqualsException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be greater or equals to {greater} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.GreaterOrEquals greater = (NumberValidator.GreaterOrEquals)getAnnotation();
      return super.format(text).replace("{greater}", String.valueOf(greater.value()));
    }
  }
  
  public class NumberValidatorLowerException extends ValidatorException {
    public NumberValidatorLowerException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be lower than {lower} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.Lower lower = (NumberValidator.Lower)getAnnotation();
      return super.format(text).replace("{lower}", String.valueOf(lower.value()));
    }
  }
  
  public class NumberValidatorLowerOrEqualsException extends ValidatorException {
    public NumberValidatorLowerOrEqualsException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be lower or equals to {lower} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.LowerOrEquals lower = (NumberValidator.LowerOrEquals)getAnnotation();
      return super.format(text).replace("{lower}", String.valueOf(lower.value()));
    }
  }
  
  public class NumberValidatorModuloException extends ValidatorException {
    public NumberValidatorModuloException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be a multiple of {modulo} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      NumberValidator.Modulo modulo = (NumberValidator.Modulo)getAnnotation();
      return super.format(text).replace("{modulo}", String.valueOf(modulo.value()));
    }
  }
  
  public class NumberValidatorNegativeException extends ValidatorException {
    public NumberValidatorNegativeException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be negative but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class NumberValidatorPositiveException extends ValidatorException {
    public NumberValidatorPositiveException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be positive but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class NumberValidatorZeroException extends ValidatorException {
    public NumberValidatorZeroException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be zero but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class NumberValidatorNotZeroException extends ValidatorException {
    public NumberValidatorNotZeroException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must not be zero but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class NumberValidatorOddException extends ValidatorException {
    public NumberValidatorOddException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be odd but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class NumberValidatorEvenException extends ValidatorException {
    public NumberValidatorEvenException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be even but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\primitive\number\NumberValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */