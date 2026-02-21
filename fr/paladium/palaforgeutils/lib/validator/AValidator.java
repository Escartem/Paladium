package fr.paladium.palaforgeutils.lib.validator;

import fr.paladium.palaforgeutils.lib.validator.annotation.NotNull;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import fr.paladium.palaforgeutils.lib.validator.exception.impl.NotNullValidatorException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.NonNull;

public abstract class AValidator<T> {
  private final Class<?>[] types;
  
  private String target;
  
  private List<Annotation> annotations;
  
  private boolean throwException;
  
  public Class<?>[] getTypes() {
    return this.types;
  }
  
  public String getTarget() {
    return this.target;
  }
  
  public List<Annotation> getAnnotations() {
    return this.annotations;
  }
  
  public boolean isThrowException() {
    return this.throwException;
  }
  
  protected AValidator(@NonNull Class<?>... types) {
    if (types == null)
      throw new NullPointerException("types is marked non-null but is null"); 
    this.types = types;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull Field field) {
    if (field == null)
      throw new NullPointerException("field is marked non-null but is null"); 
    build(field.getName(), field, true);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull Field field, boolean throwException) {
    if (field == null)
      throw new NullPointerException("field is marked non-null but is null"); 
    build(field.getName(), field, throwException);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull Parameter parameter) {
    if (parameter == null)
      throw new NullPointerException("parameter is marked non-null but is null"); 
    build(parameter.getName(), parameter, true);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull Parameter parameter, boolean throwException) {
    if (parameter == null)
      throw new NullPointerException("parameter is marked non-null but is null"); 
    build(parameter.getName(), parameter, throwException);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull String target, @NonNull AnnotatedElement parameter) {
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
    if (parameter == null)
      throw new NullPointerException("parameter is marked non-null but is null"); 
    build(target, Arrays.asList(parameter.getAnnotations()), true);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull String target, @NonNull AnnotatedElement parameter, boolean throwException) {
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
    if (parameter == null)
      throw new NullPointerException("parameter is marked non-null but is null"); 
    build(target, Arrays.asList(parameter.getAnnotations()), throwException);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull String target, @NonNull List<Annotation> annotations) {
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
    if (annotations == null)
      throw new NullPointerException("annotations is marked non-null but is null"); 
    build(target, annotations, true);
    return this;
  }
  
  @NonNull
  public final AValidator<T> build(@NonNull String target, @NonNull List<Annotation> annotations, boolean throwException) {
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
    if (annotations == null)
      throw new NullPointerException("annotations is marked non-null but is null"); 
    this.target = target;
    this.annotations = annotations;
    this.throwException = throwException;
    return this;
  }
  
  public final boolean isAssignableFrom(Class<?> type) {
    if (type == null)
      return true; 
    for (Class<?> clazz : this.types) {
      if (clazz.isAssignableFrom(type))
        return true; 
    } 
    return false;
  }
  
  public final ValidatorException parse(Object value) throws ValidatorException {
    if (this.annotations == null)
      throw new IllegalStateException("The validator " + getClass().getSimpleName() + " is not initialized."); 
    if (this.annotations.isEmpty())
      return null; 
    if (this.types.length == 0)
      throw new IllegalStateException("The validator " + getClass().getSimpleName() + " does not have any type."); 
    if (value == null)
      try {
        get(NotNull.class, annotation -> {
              throw new NotNullValidatorException(this, annotation, value);
            });
        validate(null);
        return null;
      } catch (ValidatorException exception) {
        if (this.throwException)
          throw exception; 
        return exception;
      }  
    Class<?>[] arrayOfClass;
    int i;
    byte b;
    for (arrayOfClass = this.types, i = arrayOfClass.length, b = 0; b < i; ) {
      Class<?> type = arrayOfClass[b];
      if (!type.isAssignableFrom(value.getClass())) {
        b++;
        continue;
      } 
      T castedValue = (T)value;
      try {
        validate(castedValue);
        return null;
      } catch (ValidatorException exception) {
        if (this.throwException)
          throw exception; 
        return exception;
      } 
    } 
    throw new IllegalStateException("Unable to find a suitable type for the value \"" + value + "\" with the validator " + getClass().getSimpleName() + ".");
  }
  
  @NonNull
  public final AValidator<T> annotations(@NonNull Annotation... annotations) {
    if (annotations == null)
      throw new NullPointerException("annotations is marked non-null but is null"); 
    this.annotations = Arrays.asList(annotations);
    return this;
  }
  
  @NonNull
  public final AValidator<T> annotations(@NonNull List<Annotation> annotations) {
    if (annotations == null)
      throw new NullPointerException("annotations is marked non-null but is null"); 
    this.annotations = annotations;
    return this;
  }
  
  @NonNull
  public final AValidator<T> throwException(boolean throwException) {
    this.throwException = throwException;
    return this;
  }
  
  protected final ValidatorException except(@NonNull Supplier<Boolean> condition, @NonNull ValidatorException exception) throws ValidatorException {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    if (exception == null)
      throw new NullPointerException("exception is marked non-null but is null"); 
    if (!((Boolean)condition.get()).booleanValue())
      throw exception; 
    return null;
  }
  
  protected final boolean has(@NonNull Class<? extends Annotation> annotation) {
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
    return this.annotations.stream().anyMatch(annotation::isInstance);
  }
  
  protected final <S extends Annotation> S get(@NonNull Class<S> annotation) {
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
    return (S)this.annotations.stream().filter(annotation::isInstance).map(annotation::cast).findFirst().orElse(null);
  }
  
  protected final <S extends Annotation> void get(@NonNull Class<S> annotation, Consumer<S> consumer) {
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
    this.annotations.stream().filter(annotation::isInstance).map(annotation::cast).findFirst().ifPresent(consumer);
  }
  
  protected abstract void validate(T paramT);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\AValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */