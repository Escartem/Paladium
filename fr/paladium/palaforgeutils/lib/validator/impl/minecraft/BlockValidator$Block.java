package fr.paladium.palaforgeutils.lib.validator.impl.minecraft;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Block {
  Class<? extends net.minecraft.block.Block> value();
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\validator\impl\minecraft\BlockValidator$Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */