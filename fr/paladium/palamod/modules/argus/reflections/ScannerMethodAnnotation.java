package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.scanners.MethodAnnotationsScanner;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ScannerMethodAnnotation {
  public static MethodAnnotationsScanner _a() {
    return new MethodAnnotationsScanner();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ScannerMethodAnnotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */