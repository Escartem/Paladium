package fr.paladium.palamod.modules.apet.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.apet.common.network.packet.SCLightningBoltPacket;
import fr.paladium.pet.common.registry.impl.PetBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public abstract class PetCommonProxy extends AModProxy {
  private static final String PACKET_CHANNEL_NAME = "palamod_pet";
  
  private static PetCommonProxy instance;
  
  public static PetCommonProxy getInstance() {
    return instance;
  }
  
  public PetCommonProxy() {
    instance = this;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    initNetwork("palamod_pet");
    SimpleNetworkWrapper network = getNetwork();
    network.registerMessage(SCLightningBoltPacket.Handler.class, SCLightningBoltPacket.class, 0, Side.CLIENT);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    GameRegistry.addRecipe(new ItemStack((Block)PetBlockRegistry.PET_CAGE), new Object[] { "STS", "TAT", "STS", 
          
          Character.valueOf('S'), ItemsRegister.STICK_TITANE, 
          Character.valueOf('T'), ItemsRegister.TITANE_INGOT, 
          Character.valueOf('A'), ItemsRegister.COMPRESSED_AMETHYST });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\common\PetCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */