package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import fr.paladium.palaforgeutils.lib.validator.AValidator;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.annotation.Annotation;
import lombok.NonNull;

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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\BlockValidator$BlockValidatorBlockException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */