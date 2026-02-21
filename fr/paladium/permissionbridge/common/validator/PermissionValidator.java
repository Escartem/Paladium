package fr.paladium.permissionbridge.common.validator;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PermissionValidator extends AValidator<EntityPlayer> {
  public PermissionValidator() {
    super(new Class[] { EntityPlayer.class });
  }
  
  protected void validate(EntityPlayer value) {
    get(Permission.class, permission -> except((), new PermissionValidatorException(this, permission, value)));
    get(NotPermission.class, notPermission -> except((), new NotPermissionValidatorException(this, notPermission, value)));
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Permission {
    String value();
    
    boolean exact() default false;
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface NotPermission {
    String value();
    
    boolean exact() default false;
  }
  
  public class PermissionValidatorException extends ValidatorException {
    public PermissionValidatorException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" doesn't have the \"{permission}\"{exact} permission.");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      PermissionValidator.Permission permission = (PermissionValidator.Permission)getAnnotation();
      return super.format(text).replace("{permission}", String.valueOf(permission.value())).replace("{exact}", permission.exact() ? " exact" : "");
    }
  }
  
  public class NotPermissionValidatorException extends ValidatorException {
    public NotPermissionValidatorException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\" have the \"{permission}\"{exact} permission.");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      PermissionValidator.Permission permission = (PermissionValidator.Permission)getAnnotation();
      return super.format(text).replace("{permission}", String.valueOf(permission.value())).replace("{exact}", permission.exact() ? " exact" : "");
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\validator\PermissionValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */