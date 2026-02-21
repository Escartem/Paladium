package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.scanners.TypeAnnotationsScanner;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ScannerTypeAnnotations {
  public static TypeAnnotationsScanner _a() {
    return new TypeAnnotationsScanner();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ScannerTypeAnnotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */