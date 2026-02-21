package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.scanners.SubTypesScanner;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ScannerSubTypes {
  public static SubTypesScanner _a() {
    return new SubTypesScanner(false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ScannerSubTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */