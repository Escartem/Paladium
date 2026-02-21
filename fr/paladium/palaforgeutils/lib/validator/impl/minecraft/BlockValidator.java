package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.NonNull;

public class BlockValidator extends AValidator<BlockLocation> {
  public BlockValidator() {
    super(new Class[] { BlockLocation.class });
  }
  
  protected void validate(BlockLocation value) {
    get(TileEntity.class, tileEntity -> except((), new BlockValidatorTileEntityException(this, tileEntity, value)));
    get(Block.class, block -> except((), new BlockValidatorBlockException(this, block, value)));
    get(At.class, at -> except((), new BlockValidatorAtException(this, at, value)));
    get(Exists.class, exists -> except((), new BlockValidatorExistsException(this, exists, value)));
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface TileEntity {
    Class<? extends net.minecraft.tileentity.TileEntity> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Block {
    Class<? extends net.minecraft.block.Block> value();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface At {
    int x();
    
    int y();
    
    int z();
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  public static @interface Exists {}
  
  public class BlockValidatorTileEntityException extends ValidatorException {
    public BlockValidatorTileEntityException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\"'s tileentity must be an instance of {tileentity} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      BlockValidator.TileEntity tileEntity = (BlockValidator.TileEntity)getAnnotation();
      return super.format(text).replace("{tileentity}", String.valueOf(tileEntity.value()));
    }
  }
  
  public class BlockValidatorBlockException extends ValidatorException {
    public BlockValidatorBlockException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\"'s block must be an instance of {block} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      BlockValidator.Block block = (BlockValidator.Block)getAnnotation();
      return super.format(text).replace("{block}", String.valueOf(block.value()));
    }
  }
  
  public class BlockValidatorAtException extends ValidatorException {
    public BlockValidatorAtException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\"'s block must be at {x}, {y}, {z} but found {value}");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
    
    public String format(@NonNull String text) {
      if (text == null)
        throw new NullPointerException("text is marked non-null but is null"); 
      BlockValidator.At at = (BlockValidator.At)getAnnotation();
      return super.format(text).replace("{x}", String.valueOf(at.x())).replace("{y}", String.valueOf(at.y())).replace("{z}", String.valueOf(at.z()));
    }
  }
  
  public class BlockValidatorExistsException extends ValidatorException {
    public BlockValidatorExistsException(@NonNull AValidator<?> validator, Annotation annotation, Object value) {
      super(validator, annotation, value, "the target \"{target}\"'s block must exist in the world");
      if (validator == null)
        throw new NullPointerException("validator is marked non-null but is null"); 
      if (annotation == null)
        throw new NullPointerException("annotation is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\BlockValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */