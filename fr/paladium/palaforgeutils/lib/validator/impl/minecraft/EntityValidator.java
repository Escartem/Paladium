package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class EntityValidator extends AValidator<Entity> {
  public EntityValidator() {
    super(new Class[] { Entity.class });
  }
  
  protected void validate(Entity value) {
    get(Entity.class, entity -> except((), new EntityValidatorEntityException(this, entity, value)));
    get(Online.class, online -> except((), new EntityValidatorOnlineException(this, online, value)));
    get(HandEmpty.class, handEmpty -> except((), new EntityValidatorHandEmptyException(this, handEmpty, value)));
    get(HandNotEmpty.class, handNotEmpty -> except((), new EntityValidatorHandNotEmptyException(this, handNotEmpty, value)));
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Entity {
    Class<? extends Entity> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface HandEmpty {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface HandNotEmpty {}
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Online {}
  
  public class EntityValidatorEntityException extends ValidatorException {
    public EntityValidatorEntityException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be an instance of {entity} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      EntityValidator.Entity entity = (EntityValidator.Entity)getAnnotation();
      return super.format(text).replace("{entity}", String.valueOf(entity.value()));
    }
  }
  
  public class EntityValidatorOnlineException extends ValidatorException {
    public EntityValidatorOnlineException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must be online");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class EntityValidatorHandEmptyException extends ValidatorException {
    public EntityValidatorHandEmptyException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must not have an item in hand");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
  
  public class EntityValidatorHandNotEmptyException extends ValidatorException {
    public EntityValidatorHandNotEmptyException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" must have an item in hand");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\EntityValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */