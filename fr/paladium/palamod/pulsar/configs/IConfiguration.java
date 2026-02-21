package fr.paladium.palamod.pulsar.configs;

import fr.paladium.palamod.pulsar.pulse.PulseMeta;

public interface IConfiguration {
  void load();
  
  boolean isModuleEnabled(PulseMeta paramPulseMeta);
  
  void flush();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\configs\IConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */