package fr.paladium.palamod.asm;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {
  public byte[] transform(String name, String transformedName, byte[] basicClass) {
    return basicClass;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\asm\ClassTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */