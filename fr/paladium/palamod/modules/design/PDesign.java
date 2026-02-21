package fr.paladium.palamod.modules.design;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@ObjectHolder("palamod")
@Pulse(id = "palamod-design", description = "Paladium Design", forced = true)
@DoNotRename
public class PDesign {
  @Instance("palamod-design")
  public static PDesign instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.design.ClientProxy", serverSide = "fr.paladium.palamod.modules.design.CommonProxy")
  public static CommonProxy proxy;
  
  @Handler
  @SideOnly(Side.CLIENT)
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
  }
  
  @Handler
  @SideOnly(Side.CLIENT)
  public void init(FMLInitializationEvent e) {
    proxy.registerRenders(e.getSide());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\PDesign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */