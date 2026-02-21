package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.scanners.FieldAnnotationsScanner;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ScannerFieldAnnotations {
  public static FieldAnnotationsScanner _a() {
    return new FieldAnnotationsScanner();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ScannerFieldAnnotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */