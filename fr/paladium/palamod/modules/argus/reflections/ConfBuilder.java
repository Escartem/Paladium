package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.scanners.Scanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ConfBuilder {
  public static ConfigurationBuilder _a() {
    return (new ConfigurationBuilder()).setScanners(new Scanner[] { (Scanner)ScannerSubTypes._a(), (Scanner)ScannerTypeAnnotations._a(), (Scanner)ScannerMethodAnnotation._a(), (Scanner)ScannerFieldAnnotations._a() }).setUrls(ClasspathHelper.forClassLoader());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ConfBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */