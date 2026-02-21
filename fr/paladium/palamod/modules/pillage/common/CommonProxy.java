package fr.paladium.palamod.modules.pillage.common;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.ranking.RankingEvents;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
  public static final Map<UUID, Byte> SPONGE_SHEEP = new ConcurrentHashMap<>();
  
  public void preInit() {
    MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    FMLCommonHandler.instance().bus().register(new CommonEventHandler());
    FMLCommonHandler.instance().bus().register(new RankingEvents());
    MinecraftForge.EVENT_BUS.register(new RankingEvents());
  }
  
  public void init() {}
  
  public void registerRenders() {}
  
  public ResourceLocation bindSkin(String playerUUID) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */