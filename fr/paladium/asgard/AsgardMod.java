package fr.paladium.asgard;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@Pulse(id = "palamod-asgard", description = "Paladium asgard.", forced = true)
public class AsgardMod {
  @Handler
  public void preInit(FMLPreInitializationEvent event) {}
  
  @Handler
  public void modPost(FMLPostInitializationEvent evt) {
    (new AsgardCommon()).modPost(evt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\AsgardMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */