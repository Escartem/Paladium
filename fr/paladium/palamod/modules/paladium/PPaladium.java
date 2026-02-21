package fr.paladium.palamod.modules.paladium;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.betternei.api.BNEIAPI;
import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.factory.ICraftFactory;
import fr.paladium.betternei.client.manager.CraftManager;
import fr.paladium.lindworm.Lindworm;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltip;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.cheat.client.ClientNoClipListener;
import fr.paladium.palamod.modules.paladium.cheat.commands.NoClipCommand;
import fr.paladium.palamod.modules.paladium.client.ImagePositionData;
import fr.paladium.palamod.modules.paladium.client.betternei.craft.factory.EndiumGauntletRecipeFactory;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.AlchemyCreatorArrowCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.AlchemyCreatorPotionCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.BowMachineCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.CobbleBreakerCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.FlowerFarmCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.GolemBoxCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.PalaForgeCraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.PalaMachineCraftHandler;
import fr.paladium.palamod.modules.paladium.client.events.ClientEventHandler;
import fr.paladium.palamod.modules.paladium.client.events.ClientItemAncientArmorRenderListener;
import fr.paladium.palamod.modules.paladium.client.manager.AncientHammerEffectClientCamManager;
import fr.paladium.palamod.modules.paladium.commands.AskCommand;
import fr.paladium.palamod.modules.paladium.commands.DumpDataIds;
import fr.paladium.palamod.modules.paladium.commands.GiftCommand;
import fr.paladium.palamod.modules.paladium.commands.OpenQuestCommand;
import fr.paladium.palamod.modules.paladium.commands.SkinCommand;
import fr.paladium.palamod.modules.paladium.commands.StatueCommand;
import fr.paladium.palamod.modules.paladium.commands.UnlootableChestGenerate;
import fr.paladium.palamod.modules.paladium.commands.UnlootableChestReport;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockFlowerFarm;
import fr.paladium.palamod.modules.paladium.common.eep.LegendaryStoneEEP;
import fr.paladium.palamod.modules.paladium.common.eep.WelcomeEEP;
import fr.paladium.palamod.modules.paladium.common.events.ChunkHandler;
import fr.paladium.palamod.modules.paladium.common.events.PlayerHandler;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.data.ItemAncientArmorPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network.SCPacketItemAncientArmorInvisibilityEffect;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network.SCPacketItemAncientArmorTeleportationEffect;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network.CSPacketAncientHammerValidateFortuneUI;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network.SCPacketAncientHammerEffectCam;
import fr.paladium.palamod.modules.paladium.common.legendarystone.ModuleLegendaryStone;
import fr.paladium.palamod.modules.paladium.common.logics.TileFlowerFarm;
import fr.paladium.palamod.modules.paladium.common.network.ServerPacketEntriesHandler;
import fr.paladium.palamod.modules.paladium.common.potion.PotionExtender;
import fr.paladium.palamod.modules.paladium.guardiangolem.GuardianGolemBridge;
import fr.paladium.palamod.modules.paladium.helios.HeliosController;
import fr.paladium.palamod.modules.paladium.listener.KnockbackListener;
import fr.paladium.palamod.modules.paladium.listener.WelcomeKitListener;
import fr.paladium.palamod.modules.paladium.network.SCPacketSyncEvents;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import fr.paladium.palamod.modules.paladium.network.data.SCPacketPaladiumPlayerDataSync;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Craft;
import fr.paladium.palamod.modules.paladium.registerer.PRegisterer;
import fr.paladium.palamod.modules.paladium.utils.PacketAnnotationProcessor;
import fr.paladium.palamod.modules.paladium.utils.usebugs.unlootable.ConfigurationManager;
import fr.paladium.palamod.modules.paladium.wiki.WikiRegister;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.profile.common.module.ProfileModules;
import java.io.File;
import java.io.FileWriter;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.block.BlockModelPropsBlock;
import software.bernie.geckolib3.block.data.property.PropsHitboxProperty;
import software.bernie.geckolib3.block.data.property.PropsInventoryProperty;
import software.bernie.geckolib3.block.data.property.PropsOffsetProperty;
import software.bernie.geckolib3.block.data.property.PropsRotateProperty;
import software.bernie.geckolib3.block.data.property.PropsSizeProperty;
import software.bernie.geckolib3.block.data.property.impl.PropsBlockProperty;
import software.bernie.geckolib3.block.tile.TileEntityBlockModelProps;

@ObjectHolder("palamod")
@Pulse(id = "palamod-paladium", description = "All the bases of Paladium, spikes, sticks of god, ..", forced = true)
public class PPaladium {
  @Instance("palamod-paladium")
  public static PPaladium instance;
  
  public static PlayerHandler playerTracker;
  
  public static FMLEventChannel ch_Type;
  
  public static FMLEventChannel ch_TypeFlag;
  
  public static FMLEventChannel ch_TypeDelete;
  
  public static FMLEventChannel ch_TypeUpdate;
  
  public static ServerPacketEntriesHandler serverPacketEntriesHandler;
  
  public static FMLEventChannel staffalertNetwork;
  
  public static List<ImagePositionData> images = new ArrayList<>();
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    PaladiumPlayer.register();
    try {
      TrustManager trm = new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }
          
          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
          
          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        };
      SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, new TrustManager[] { trm }, null);
      HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    } catch (Exception e) {
      e.printStackTrace();
    } 
    PotionExtender.preInit();
    PRegisterer.init();
    playerTracker = new PlayerHandler();
    FMLCommonHandler.instance().bus().register(playerTracker);
    MinecraftForge.EVENT_BUS.register(playerTracker);
    PropsBlockProperty property = PropsBlockProperty.builder().hitbox(new PropsHitboxProperty(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D, false, false)).inventory(new PropsInventoryProperty(new PropsRotateProperty(0.0D, 0.0D, 0.0D), new PropsSizeProperty(0.7D, 0.7D, 0.7D), new PropsOffsetProperty(0.0D, -0.3D, 0.0D))).build();
    BlocksRegister.FLOWER_FARM = Lindworm.registerGeckolibBlock(event, "flower_farm", (BlockModelPropsBlock)new BlockFlowerFarm(), (TileEntityBlockModelProps)new TileFlowerFarm(), property);
    PacketAnnotationProcessor.registerPacket(SCPacketSyncEvents.class);
    PacketAnnotationProcessor.registerPacket(SCPacketPaladiumPlayerDataSync.class);
    ExtendedUtils.registerExtended(EntityPlayer.class, LegendaryStoneEEP.class, "legendarystone_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
    ExtendedUtils.registerExtended(EntityPlayer.class, WelcomeEEP.class, "welcome_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
    ProfileModules.getInstance().registerModule(ModuleLegendaryStone.class);
    PacketAnnotationProcessor.registerPacket(SCPacketItemAncientArmorInvisibilityEffect.class);
    PacketAnnotationProcessor.registerPacket(SCPacketItemAncientArmorTeleportationEffect.class);
    PacketAnnotationProcessor.registerPacket(SCPacketAncientHammerEffectCam.class);
    PacketAnnotationProcessor.registerPacket(CSPacketAncientHammerValidateFortuneUI.class);
    ExtendedUtils.registerExtended(EntityPlayer.class, ItemAncientArmorPlayerData.class, "palamod_ANCIENT_ARMOR", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
    ExtendedUtils.registerExtended(EntityPlayer.class, ItemAncientHammerPlayerData.class, "palamod_ANCIENT_HAMMER", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
    if (Side.CLIENT.equals(event.getSide())) {
      AncientHammerEffectClientCamManager.inst().init();
      Object listener = new ChunkHandler();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
      listener = new ClientEventHandler();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
      listener = new ClientNoClipListener();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
      listener = new ClientItemAncientArmorRenderListener();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
      CustomTooltip.register(item -> item.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor, fr.paladium.palamod.modules.paladium.client.render.ItemAncientArmorTooltipNode::new);
      CustomTooltip.register(item -> item.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer, fr.paladium.palamod.modules.paladium.client.render.ItemAncientHammerTooltipNode::new);
      FMLEventChannel paladiumImages = NetworkRegistry.INSTANCE.newEventDrivenChannel("paladium_images");
      paladiumImages.register(new ClientEventHandler());
      BNEIAPI.registerCraftFatory((ICraftFactory)new EndiumGauntletRecipeFactory());
      PRegisterer.initClientOnly();
      String filePath = "optionsof.txt";
      try {
        File file = new File("optionsof.txt");
        if (!file.exists()) {
          System.out.println("Unable to find optionsof.txt");
          return;
        } 
        Scanner sc = new Scanner(file);
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine())
          buffer.append(sc.nextLine() + System.lineSeparator()); 
        String fileContents = buffer.toString();
        sc.close();
        fileContents = fileContents.replace("ofChunkLoading:1", "ofChunkLoading:0");
        fileContents = fileContents.replace("ofChunkLoading:2", "ofChunkLoading:0");
        fileContents = fileContents.replace("ofChunkLoading:3", "ofChunkLoading:0");
        fileContents = fileContents.replace("ofShowFps:true", "ofShowFps:false");
        FileWriter writer = new FileWriter("optionsof.txt");
        writer.append(fileContents);
        writer.flush();
        writer.close();
        System.out.println("Patching Optifine");
      } catch (Exception e1) {
        System.out.println("Unable to load optionsof.txt");
        e1.printStackTrace();
      } 
    } 
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    GuardianGolemBridge.init();
    HeliosController.register(event.getSide());
    serverPacketEntriesHandler = new ServerPacketEntriesHandler();
    ch_Type = NetworkRegistry.INSTANCE.newEventDrivenChannel("DecodiumType");
    ch_TypeFlag = NetworkRegistry.INSTANCE.newEventDrivenChannel("DecodiumTypeFlag");
    ch_TypeDelete = NetworkRegistry.INSTANCE.newEventDrivenChannel("DecodiumTypeDelete");
    ch_TypeUpdate = NetworkRegistry.INSTANCE.newEventDrivenChannel("DecodiumTypeUpdate");
    ch_TypeDelete.register(serverPacketEntriesHandler);
    ch_TypeFlag.register(serverPacketEntriesHandler);
    ch_Type.register(serverPacketEntriesHandler);
    staffalertNetwork = NetworkRegistry.INSTANCE.newEventDrivenChannel("staffalert");
    setupTabs();
    ConfigurationManager.init();
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    PRegister_Craft.init();
    if (event.getSide() == Side.CLIENT) {
      WikiRegister.register();
      try {
        CraftManager.getInstance().registerHandler((ACraftHandler)new PalaMachineCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new BowMachineCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new AlchemyCreatorArrowCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new AlchemyCreatorPotionCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new CobbleBreakerCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new PalaForgeCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new FlowerFarmCraftHandler());
        CraftManager.getInstance().registerHandler((ACraftHandler)new GolemBoxCraftHandler());
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new SkinCommand());
    e.registerServerCommand((ICommand)new GiftCommand());
    e.registerServerCommand((ICommand)new DumpDataIds());
    e.registerServerCommand((ICommand)new UnlootableChestReport());
    e.registerServerCommand((ICommand)new UnlootableChestGenerate());
    e.registerServerCommand((ICommand)new StatueCommand());
    e.registerServerCommand((ICommand)new OpenQuestCommand());
    e.registerServerCommand((ICommand)new NoClipCommand());
    e.registerServerCommand((ICommand)new AskCommand());
    WelcomeKitListener welcomeListener = new WelcomeKitListener();
    FMLCommonHandler.instance().bus().register(welcomeListener);
    MinecraftForge.EVENT_BUS.register(welcomeListener);
    KnockbackListener knockbackListener = new KnockbackListener();
    FMLCommonHandler.instance().bus().register(knockbackListener);
    MinecraftForge.EVENT_BUS.register(knockbackListener);
  }
  
  private void setupTabs() {
    Registry.PALADIUM_TAB.init(new ItemStack(ItemsRegister.PALADIUM_INGOT, 1, 0));
    Registry.PLANTS_TAB.init(new ItemStack(BlocksRegister.FLOWER_HARPAGOPHYTUM, 1, 0));
    Registry.FOOD_TAB.init(new ItemStack((Item)ItemsRegister.FOOD_HAM, 1, 0));
    Registry.DECORATIVE_TAB.init(new ItemStack((Block)BlocksRegister.FINDIUM_STAIRS, 1, 0));
    Registry.POTION_TAB.init(new ItemStack((Item)ItemsRegister.POTION_FIRE, 1, 0));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\PPaladium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */