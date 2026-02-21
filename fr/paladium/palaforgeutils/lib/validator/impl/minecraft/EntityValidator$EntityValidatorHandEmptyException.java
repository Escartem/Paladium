package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

public class EntityValidatorHandEmptyException extends ValidatorException {
  public EntityValidatorHandEmptyException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
    super(validator, annotation, value, "the target \"{target}\" must not have an item in hand");
    if (validator == null)
      throw new NullPointerException("validator is marked non-null but is null"); 
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\EntityValidator$EntityValidatorHandEmptyException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */