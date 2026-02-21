package fr.paladium.palaforgeutils.lib.validator;

import fr.paladium.palaforgeutils.lib.validator.annotation.NotNull;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.BlockValidator;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Validators {
  public static List<AValidator<?>> getValidatorList() {
    return validatorList;
  }
  
  private static final List<AValidator<?>> validatorList = new ArrayList<>();
  
  static {
    register((Class)NumberValidator.class);
    register((Class)StringValidator.class);
    register((Class)BlockValidator.class);
    register((Class)EntityValidator.class);
  }
  
  public static void register(@NotNull Class<? extends AValidator<?>> validator) {
    try {
      validatorList.add(validator.newInstance());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public static void register(@NotNull AValidator<?> validator) {
    validatorList.add(validator);
  }
  
  public static List<AValidator<?>> get(Object value) {
    Class<?> type = (value == null) ? null : value.getClass();
    List<AValidator<?>> validators = new ArrayList<>();
    for (AValidator<?> validator : validatorList) {
      if (!validator.isAssignableFrom(type))
        continue; 
      validators.add(validator);
    } 
    return validators;
  }
  
  public static ValidatorException validate(@NotNull Object instance) {
    return validate(instance, true);
  }
  
  public static ValidatorException validate(@NotNull Object instance, boolean throwException) {
    return validate(instance.getClass(), instance, throwException);
  }
  
  public static ValidatorException validate(Class<?> clazz, @NotNull Object instance, boolean throwException) {
    for (Field field : clazz.getDeclaredFields()) {
      if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
        try {
          field.setAccessible(true);
          ValidatorException exception = validate(field, field.get(instance));
          if (exception != null)
            return exception; 
        } catch (IllegalArgumentException|IllegalAccessException e) {
          e.printStackTrace();
        }  
    } 
    Class<?> superClass = clazz.getSuperclass();
    if (superClass != null)
      return validate(superClass, instance, throwException); 
    for (Class<?> interfaceClass : clazz.getInterfaces()) {
      ValidatorException exception = validate(interfaceClass, instance, throwException);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
  
  public static ValidatorException validate(@NotNull Field field, Object value) {
    return validate(field, value, true);
  }
  
  public static ValidatorException validate(@NotNull Field field, Object value, boolean throwException) {
    if ((field.getAnnotations()).length == 0)
      return null; 
    for (AValidator<?> validator : get(value)) {
      ValidatorException exception = validator.build(field, throwException).parse(value);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
  
  public static ValidatorException validate(@NotNull Parameter parameter, Object value) {
    return validate(parameter, value, true);
  }
  
  public static ValidatorException validate(@NotNull Parameter parameter, Object value, boolean throwException) {
    if ((parameter.getAnnotations()).length == 0)
      return null; 
    for (AValidator<?> validator : get(value)) {
      ValidatorException exception = validator.build(parameter, throwException).parse(value);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
  
  public static ValidatorException validate(@NotNull String target, @NotNull List<Annotation> annotations, Object value) {
    return validate(target, annotations, value, true);
  }
  
  public static ValidatorException validate(@NotNull String target, @NotNull List<Annotation> annotations, Object value, boolean throwException) {
    if (annotations.size() == 0)
      return null; 
    for (AValidator<?> validator : get(value)) {
      ValidatorException exception = validator.build(target, annotations, throwException).parse(value);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\Validators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */