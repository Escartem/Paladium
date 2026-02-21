package fr.paladium.palamod.modules.spellsv2;

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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.spellsv2.blocks.ModBlocks;
import fr.paladium.palamod.modules.spellsv2.commands.SpellCommand;
import fr.paladium.palamod.modules.spellsv2.config.ConfigManager;
import fr.paladium.palamod.modules.spellsv2.entity.EntityEgg;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.events.EventsManager;
import fr.paladium.palamod.modules.spellsv2.init.Items;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFreeze;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFrozen;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientOpenFakeInventory;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientResetCooldown;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSetDelay;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientSpellTiming;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateClientManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateVelocity;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseAphone;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseMentalis;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUsePerception;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerCancelInertium;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerCancelMentalis;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerMoveEntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerUseSpell;
import fr.paladium.palamod.modules.spellsv2.tile.TileEntityInertiumBlock;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-spellsv2", description = "Paladium Spells", forced = true)
@DoNotRename
public class PSpellsV2 {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.spellsv2.ClientProxy", serverSide = "fr.paladium.palamod.modules.spellsv2.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-spellsv2")
  public static PSpellsV2 instance;
  
  public static SimpleNetworkWrapper network;
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    network = NetworkRegistry.INSTANCE.newSimpleChannel("pspellsv2");
    ConfigManager.init();
    System.out.println("##PalaSpells init config : " + (new File(
          ConfigManager.getFile())).getAbsolutePath());
    FMLCommonHandler.instance().bus().register(new EventsManager());
    System.out.println("##PalaSpells init FMLCommonHandler");
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    System.out.println("##PalaSpells init MinecraftForge");
    network.registerMessage(PacketServerUseSpell.Handler.class, PacketServerUseSpell.class, 0, Side.SERVER);
    network.registerMessage(PacketClientActiveSpell.Handler.class, PacketClientActiveSpell.class, 1, Side.CLIENT);
    network.registerMessage(PacketClientUpdateClientManager.Handler.class, PacketClientUpdateClientManager.class, 2, Side.CLIENT);
    network.registerMessage(PacketServerUpgradeSpell.Handler.class, PacketServerUpgradeSpell.class, 3, Side.SERVER);
    network.registerMessage(PacketClientUpgradeSpell.Handler.class, PacketClientUpgradeSpell.class, 4, Side.CLIENT);
    network.registerMessage(PacketClientSetDelay.Handler.class, PacketClientSetDelay.class, 5, Side.CLIENT);
    network.registerMessage(PacketClientOpenFakeInventory.Handler.class, PacketClientOpenFakeInventory.class, 6, Side.CLIENT);
    network.registerMessage(PacketClientUseAphone.Handler.class, PacketClientUseAphone.class, 7, Side.CLIENT);
    network.registerMessage(PacketServerMoveEntityGhost.Handler.class, PacketServerMoveEntityGhost.class, 8, Side.SERVER);
    network.registerMessage(PacketClientUseMentalis.Handler.class, PacketClientUseMentalis.class, 9, Side.CLIENT);
    network.registerMessage(PacketClientResetCooldown.Handler.class, PacketClientResetCooldown.class, 10, Side.CLIENT);
    network.registerMessage(PacketServerCancelInertium.Handler.class, PacketServerCancelInertium.class, 11, Side.SERVER);
    network.registerMessage(PacketClientFreeze.Handler.class, PacketClientFreeze.class, 12, Side.CLIENT);
    network.registerMessage(PacketClientSpellTiming.Handler.class, PacketClientSpellTiming.class, 13, Side.CLIENT);
    network.registerMessage(PacketServerCancelMentalis.Handler.class, PacketServerCancelMentalis.class, 14, Side.SERVER);
    network.registerMessage(PacketClientUpdateVelocity.Handler.class, PacketClientUpdateVelocity.class, 15, Side.CLIENT);
    network.registerMessage(PacketClientUsePerception.Handler.class, PacketClientUsePerception.class, 16, Side.CLIENT);
    network.registerMessage(PacketClientFrozen.Handler.class, PacketClientFrozen.class, 17, Side.CLIENT);
    System.out.println("##PalaSpells init Network");
    System.out.println("###OldSpell init");
    GameRegistry.registerTileEntity(TileEntityInertiumBlock.class, "inertiumBlock");
    ModBlocks.init();
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    Items.register();
    proxy.registerRenders();
    EntityRegistry.registerGlobalEntityID(EntityGhost.class, "entityGhost", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(0, 0, 0)).getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityGhost.class, "entityGhost", 421, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityEgg.class, "entityEgg", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 255, 255)).getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityEgg.class, "entityEgg", 422, PalaMod.instance, 40, 1, true);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) throws FontFormatException, IOException {
    if (e.getSide() != Side.CLIENT)
      return; 
    Font f = Font.createFont(0, getClass().getResource("/assets/palamod/font/BurbankBigCondensed-Bold.otf").openStream());
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(f);
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new SpellCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\PSpellsV2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */