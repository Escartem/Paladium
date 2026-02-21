package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\BlockValidator$BlockValidatorTileEntityException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */