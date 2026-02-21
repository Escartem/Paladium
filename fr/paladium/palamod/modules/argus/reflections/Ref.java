package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class Ref {
  public static Reflections ref(ConfigurationBuilder b) {
    return new Reflections((Configuration)b);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\Ref.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */