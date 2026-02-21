package fr.paladium.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.common.combat.Combat;
import fr.paladium.common.combat.ModuleCombatTag;
import fr.paladium.common.combat.network.SCPacketCombatTag;
import fr.paladium.common.config.MConfigs;
import fr.paladium.common.network.SCPacketCommonConfig;
import fr.paladium.common.network.SCPacketServerStatus;
import fr.paladium.common.tooltip.ModuleItemTooltip;
import fr.paladium.helios.Helios;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.temp.CommonRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class CommonModule extends AModProxy {
  private static CommonModule instance;
  
  public static SimpleNetworkWrapper networkWrapper;
  
  private MConfigs configFile;
  
  private CommonConfig config;
  
  private Combat combatTag;
  
  public static CommonModule getInstance() {
    return instance;
  }
  
  public MConfigs getConfigFile() {
    return this.configFile;
  }
  
  public CommonConfig getConfig() {
    return this.config;
  }
  
  public Combat getCombatTag() {
    return this.combatTag;
  }
  
  private static final String PALADIUM_MODS_PATH = System.getenv("APPDATA") + File.separator + ".paladium" + File.separator + "mods";
  
  public static boolean illegal = false;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
    CommonRegistry.register();
    File palamodFile = new File(PALADIUM_MODS_PATH, "palamod-prod.pala");
    if (palamodFile.exists())
      try (InputStream is = Files.newInputStream(Paths.get(palamodFile.getAbsolutePath(), new String[0]), new java.nio.file.OpenOption[0])) {
        String md5 = DigestUtils.md5Hex(is);
        illegal = (md5.equals("572d134567f49f35b739e15ca46fef90") || md5.equals("0e3d31f91644557b0a1a7df62c1a8e98"));
      } catch (IOException e) {
        e.printStackTrace();
      }  
    this.configFile = new MConfigs("common", "Common paladium configuration");
    this.configFile.load();
    this.config = new CommonConfig();
    this.combatTag = new Combat(-1L, -1L, new ArrayList());
    networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("common-COMMON");
    networkWrapper.registerMessage(SCPacketCommonConfig.Handler.class, SCPacketCommonConfig.class, 0, Side.CLIENT);
    networkWrapper.registerMessage(SCPacketCombatTag.Handler.class, SCPacketCombatTag.class, 1, Side.CLIENT);
    networkWrapper.registerMessage(SCPacketServerStatus.Handler.class, SCPacketServerStatus.class, 2, Side.CLIENT);
    FMLCommonHandler.instance().bus().register(new CommonEventHandler());
    MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    if (event.getSide() == Side.CLIENT) {
      FMLCommonHandler.instance().bus().register(new ClientEventHandler());
      MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
      Helios.getClient().addModule(ModuleCombatTag.class);
      Helios.getClient().addModule(ModuleItemTooltip.class);
    } 
    System.out.println("[Paladium] Common is loaded");
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\CommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */