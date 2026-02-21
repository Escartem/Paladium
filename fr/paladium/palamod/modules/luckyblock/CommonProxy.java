package fr.paladium.palamod.modules.luckyblock;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.ResourceLocation;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent e) {}
  
  public void init(FMLInitializationEvent e) {}
  
  public void registerRenders() {}
  
  public static final ResourceLocation CLIPBOARD_BLOCK = new ResourceLocation("bibliocraft", "textures/models/clipboard.png");
  
  public static final ResourceLocation CLIPBOARD_BOX_X = new ResourceLocation("bibliocraft", "textures/models/boxx.png");
  
  public static final ResourceLocation CLIPBOARD_BOX_CHECK = new ResourceLocation("bibliocraft", "textures/models/boxcheck.png");
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */