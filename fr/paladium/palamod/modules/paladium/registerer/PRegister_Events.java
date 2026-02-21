package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.paladium.common.events.EventHandlerAnvil;
import fr.paladium.palamod.modules.paladium.common.events.EventHandlerArmor;
import fr.paladium.palamod.modules.paladium.common.events.EventHandlerArrow;
import fr.paladium.palamod.modules.paladium.common.events.EventHandlerBucket;
import fr.paladium.palamod.modules.paladium.common.events.PotionHandler;
import net.minecraftforge.common.MinecraftForge;

public class PRegister_Events {
  public static void init() {
    MinecraftForge.EVENT_BUS.register(new EventHandlerArmor());
    MinecraftForge.EVENT_BUS.register(new EventHandlerBucket());
    MinecraftForge.EVENT_BUS.register(new PotionHandler());
    MinecraftForge.EVENT_BUS.register(new EventHandlerArrow());
    MinecraftForge.EVENT_BUS.register(new EventHandlerAnvil());
    FMLCommonHandler.instance().bus().register(new EventHandlerArmor());
    FMLCommonHandler.instance().bus().register(new EventHandlerBucket());
    FMLCommonHandler.instance().bus().register(new PotionHandler());
    FMLCommonHandler.instance().bus().register(new EventHandlerArrow());
    FMLCommonHandler.instance().bus().register(new EventHandlerAnvil());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Events.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */