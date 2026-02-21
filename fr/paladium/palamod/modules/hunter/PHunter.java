package fr.paladium.palamod.modules.hunter;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.hunter.command.BackpackCommand;
import fr.paladium.palamod.modules.hunter.events.EventsManager;
import fr.paladium.palamod.modules.hunter.events.EventsManagerServer;
import fr.paladium.palamod.modules.hunter.init.Blocks;
import fr.paladium.palamod.modules.hunter.init.Crafts;
import fr.paladium.palamod.modules.hunter.init.Entities;
import fr.paladium.palamod.modules.hunter.init.Items;
import fr.paladium.palamod.modules.hunter.items.ItemCaptureStone;
import fr.paladium.palamod.modules.hunter.networks.HunterCompassNetwork;
import fr.paladium.palamod.modules.hunter.networks.PacketRituel;
import fr.paladium.palamod.modules.hunter.networks.PacketUpdateHunterCompass;
import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityBamboo;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityEndiumTotem;
import fr.paladium.palamod.modules.hunter.world.BambooWorldGenerator;
import fr.paladium.palamod.modules.luckyblock.potions.CustomPotion;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.awt.Color;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-hunter", description = "Paladium Hunter", forced = true)
@DoNotRename
public class PHunter {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.hunter.proxies.ClientProxy", serverSide = "fr.paladium.palamod.modules.hunter.proxies.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-hunter")
  public static PHunter instance;
  
  public static EnchantmentBase xp_stealer;
  
  public static EnchantmentBase overwhelmed;
  
  public static EnchantmentBase recuperation;
  
  public static EnchantmentBase recuperation_dig;
  
  public static CustomPotion CONTRE_BUFF;
  
  public static CustomPotion[] potions;
  
  public static SimpleNetworkWrapper network;
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    network = NetworkRegistry.INSTANCE.newSimpleChannel("palahunter");
    CONTRE_BUFF = new CustomPotion(60, true, Color.WHITE.getRGB(), "contre_buff");
    potions = new CustomPotion[] { CONTRE_BUFF };
    for (CustomPotion potion : potions)
      potion.setIconIndex(0, 0); 
    for (CustomPotion potion : potions) {
      potion.loadEffects();
      potion.register();
    } 
    Blocks.register();
    Items.register();
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    if (e.getSide() == Side.SERVER) {
      FMLCommonHandler.instance().bus().register(new EventsManagerServer());
      MinecraftForge.EVENT_BUS.register(new EventsManagerServer());
    } 
    network.registerMessage(PacketRituel.Handler.class, PacketRituel.class, 901, Side.CLIENT);
    network.registerMessage(PacketUpdateHunterCompass.Handler.class, PacketUpdateHunterCompass.class, 902, Side.SERVER);
    GameRegistry.registerTileEntity(TileEntityEndiumTotem.class, "tileEntityEndiumTotem");
    GameRegistry.registerTileEntity(TileEntityBamboo.class, "tileEntityBamboo");
    recuperation_dig = (new EnchantmentBase(148, 0, EnumEnchantmentType.digger, "recuperation_dig", 0, 0, 0, 0)).setMaxLevel(1);
    recuperation = (new EnchantmentBase(149, 0, EnumEnchantmentType.weapon, "recuperation", 0, 0, 0, 0)).setMaxLevel(1);
    xp_stealer = (new EnchantmentBase(152, 0, EnumEnchantmentType.weapon, "xp_stealer", 11, 0, 0, 0)).setMaxLevel(4);
    EnchantMod.registerEnchantment(xp_stealer, "§6Permet de voler l’XP de l’adversaire respectivement par niveau 2/4/7/10%.<br>§6La quantité volée d’XP est de 40/50/70/80 xp.");
    EnchantMod.registerEnchantment(recuperation, "§6Récupère dans la limite<br>§6de votre inventaire les ressources<br>§6qui drop en tuant un joueur.");
    EnchantMod.registerEnchantment(recuperation_dig, "§6Récupère dans la limite<br>§6de votre inventaire les ressources<br>§6qui drop en cassant un bloc.");
    overwhelmed = (new EnchantmentBase(158, 0, EnumEnchantmentType.armor_torso, "overwhelmed", 19, 0, 0, 0)).setMaxLevel(3);
    EnchantMod.registerEnchantment(overwhelmed, "§6A chaque niveau, cet enchantement permet<br>§6de rajouter des slots d’amulette<br>§6à son armure.<br>§6Par défaut, une armure<br>§6ne peut contenir qu’une seule amulette.");
    System.out.println("##Hunter preInit");
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    FMLEventChannel event1 = NetworkRegistry.INSTANCE.newEventDrivenChannel("huntercompass");
    event1.register(new HunterCompassNetwork());
    Entities.register();
    ItemCaptureStone.initMapping();
    Registry.HUNTER_TAB.init(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 0));
    proxy.registerRenders();
    GameRegistry.registerWorldGenerator((IWorldGenerator)new BambooWorldGenerator(), 3);
    System.out.println("##Hunter Init");
    if (e.getSide() == Side.SERVER)
      CommandRegistry.register(new Class[] { BackpackCommand.class }); 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    Crafts.register();
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\PHunter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */