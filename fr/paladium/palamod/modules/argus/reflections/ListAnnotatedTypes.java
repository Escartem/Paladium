package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.reflections.Reflections;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ListAnnotatedTypes {
  public static MyHashSet<String> nh(Reflections rf, MyHashSet<String> a) {
    Arrays.<Class<? extends Annotation>>asList(TypesRef._a).forEach(q -> a.p((Collection)rf.getTypesAnnotatedWith(q).stream().map(Class::getName).collect(Collectors.toList())));
    return a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ListAnnotatedTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */