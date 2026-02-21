package fr.paladium.palamod.modules.shop;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.shop.api.ShopManager;
import fr.paladium.palamod.modules.shop.commands.ShopCommand;
import fr.paladium.palamod.modules.shop.events.EventsManager;
import fr.paladium.palamod.modules.shop.network.CSPacketBuyShopItem;
import fr.paladium.palamod.modules.shop.network.CSPacketSellShopItem;
import fr.paladium.palamod.modules.shop.network.CSPacketShopItemListRequest;
import fr.paladium.palamod.modules.shop.network.SCPacketShopItemListReply;
import fr.paladium.palamod.modules.shop.network.SCPacketUpdateInventory;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.io.IOException;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-shop", description = "Shop", forced = true)
@DoNotRename
public class PShop {
  @Instance("palamod-shop")
  public static PShop instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.shop.ClientProxy", serverSide = "fr.paladium.palamod.modules.shop.CommonProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper network;
  
  public static SimpleNetworkWrapper getNetwork() {
    return network;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    Constants.logger.info("[Shop] PreInit");
    registerPackets();
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    if (event.getSide().isServer())
      new ShopManager(); 
  }
  
  private void registerPackets() {
    network = NetworkRegistry.INSTANCE.newSimpleChannel("adminshop");
    network.registerMessage(CSPacketShopItemListRequest.Handler.class, CSPacketShopItemListRequest.class, 0, Side.SERVER);
    network.registerMessage(SCPacketShopItemListReply.Handler.class, SCPacketShopItemListReply.class, 1, Side.CLIENT);
    network.registerMessage(CSPacketBuyShopItem.Handler.class, CSPacketBuyShopItem.class, 2, Side.SERVER);
    network.registerMessage(CSPacketSellShopItem.Handler.class, CSPacketSellShopItem.class, 3, Side.SERVER);
    network.registerMessage(SCPacketUpdateInventory.Handler.class, SCPacketUpdateInventory.class, 4, Side.CLIENT);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.register();
  }
  
  @Handler
  @SideOnly(Side.CLIENT)
  public void postInit(FMLPostInitializationEvent event) throws IOException {}
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    Constants.logger.info("[Shop] Registering commands.");
    event.registerServerCommand((ICommand)new ShopCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\PShop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */