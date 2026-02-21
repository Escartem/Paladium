package fr.paladium.palamod.modules.smeltery;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.client.manager.CraftManager;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.smeltery.betternei.handler.GrinderCraftHandler;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Blocks;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegisterer;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

@ObjectHolder("palamod")
@Pulse(id = "palamod-smeltery", description = "Grinder : multiblock structure.", forced = true)
public class PSmeltery {
  @Instance("palamod-smeltery")
  public static PSmeltery instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    UpgradeHelper.init();
    PSRegisterer.init();
    setupTabs();
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {}
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    PSRegisterer.postInit();
    if (event.getSide() == Side.CLIENT)
      try {
        CraftManager.getInstance().registerHandler((ACraftHandler)new GrinderCraftHandler());
      } catch (Exception e) {
        e.printStackTrace();
      }  
  }
  
  public void setupTabs() {
    Registry.SMELTERY_TAB.init(new ItemStack((Block)PSRegister_Blocks.GRINDER_BLOCK, 1, 0));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\PSmeltery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */