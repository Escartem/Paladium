package fr.paladium.palamod.util;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class PrintingTransformer implements ClassFileTransformer {
  public byte[] transform(ClassLoader loader, String fullyQualifiedClassName, Class classBeingRedefined, ProtectionDomain protectionDomain, byte[] classofileBuffer) throws IllegalClassFormatException {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\PrintingTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */