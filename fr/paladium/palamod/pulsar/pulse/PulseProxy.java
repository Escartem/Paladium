package fr.paladium.palamod.pulsar.pulse;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Deprecated
public @interface PulseProxy {
  String clientSide();
  
  String serverSide();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\pulse\PulseProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */