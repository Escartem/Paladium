package fr.paladium.pet.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.guihandler.CustomGuiHandler;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.pet.PalaPetMod;
import fr.paladium.pet.common.capture.CaptureManager;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.entity.EntityPetCage;
import fr.paladium.pet.common.handler.FeedPetGuiHandler;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.capture.BBPacketRequestCageData;
import fr.paladium.pet.common.network.packet.capture.CSPacketTrapInteraction;
import fr.paladium.pet.common.network.packet.capture.SCPacketTrapOpen;
import fr.paladium.pet.common.network.packet.pet.BBOpenEditPetUIPacket;
import fr.paladium.pet.common.network.packet.pet.BBOpenSkillRollUIPacket;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import fr.paladium.pet.common.network.packet.pet.BBUpdateClientConfigPacket;
import fr.paladium.pet.common.network.packet.pet.BBUpdateClientSkillValuesPacket;
import fr.paladium.pet.common.network.packet.pet.CSChangePetSkinPacket;
import fr.paladium.pet.common.network.packet.pet.CSChangeSkinVisibilityPacket;
import fr.paladium.pet.common.network.packet.pet.CSPOpenFeedContainerPacket;
import fr.paladium.pet.common.network.packet.pet.SCOpenDebugUIPacket;
import fr.paladium.pet.common.network.packet.skill.BBRequestSkillRollPacket;
import fr.paladium.pet.common.network.packet.skill.CSActiveSpellPacket;
import fr.paladium.pet.common.network.packet.skill.CSAssignRollPacket;
import fr.paladium.pet.common.network.packet.skill.CSAssignSkillPacket;
import fr.paladium.pet.common.network.packet.skill.CSRequestSkillList;
import fr.paladium.pet.common.network.packet.skill.breakspeed.SCGlobalBreakSpeedPacket;
import fr.paladium.pet.common.network.packet.skill.breakspeed.SCObsidianBreakSpeedPacket;
import fr.paladium.pet.common.pet.PetAdditionalData;
import fr.paladium.pet.common.profile.ModulePet;
import fr.paladium.pet.common.registry.IRegistry;
import fr.paladium.pet.common.registry.RegistryManager;
import fr.paladium.pet.common.skill.listener.passive.SpeedWalkCommonListener;
import fr.paladium.pet.common.store.PetSkinProviderModule;
import fr.paladium.profile.common.module.ProfileModules;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PetCommonProxy extends AModProxy {
  public static final List<String> DEFAULT_PETS = Arrays.asList(new String[] { "cat", "rabbit", "dog" });
  
  public static final boolean APRIL_FOOL;
  
  private static final String GEO_FOLDER = "/assets/palapet/geo/pets/";
  
  private static final String GEO_EXTENSION = ".geo.json";
  
  public static final String ADDITIONAL_DATA_FOLDER = "/assets/palapet/data/";
  
  public static final String ADDITIONAL_DATA_EXTENSION = ".json";
  
  private static PetCommonProxy instance;
  
  private final RegistryManager registryManager;
  
  private final CaptureManager captureManager;
  
  private CustomGuiHandler guiHandler;
  
  private int feedPetHandler;
  
  static {
    Calendar calendar = Calendar.getInstance();
    APRIL_FOOL = (calendar.get(2) == 3 && calendar.get(5) == 1);
  }
  
  public static PetCommonProxy getInstance() {
    return instance;
  }
  
  public RegistryManager getRegistryManager() {
    return this.registryManager;
  }
  
  public CaptureManager getCaptureManager() {
    return this.captureManager;
  }
  
  public CustomGuiHandler getGuiHandler() {
    return this.guiHandler;
  }
  
  public int getFeedPetHandler() {
    return this.feedPetHandler;
  }
  
  private final List<PetAdditionalData> pets = Arrays.asList(new PetAdditionalData[] { 
        PetAdditionalData.of("kapio_koi"), 
        PetAdditionalData.of("cat"), 
        PetAdditionalData.of("rabbit"), 
        PetAdditionalData.of("dog"), 
        PetAdditionalData.of("dragon"), 
        PetAdditionalData.of("feng_uang"), 
        PetAdditionalData.of("pet_mini_golem"), 
        PetAdditionalData.of("pet_ufo"), 
        PetAdditionalData.of("pet_blobfish"), 
        PetAdditionalData.of("pet_arty"), 
        PetAdditionalData.of("pet_zombie_hand"), 
        PetAdditionalData.of("pet_ender_dragon"), 
        PetAdditionalData.of("pet_reindeer"), 
        PetAdditionalData.of("pet_penguin"), 
        PetAdditionalData.of("pet_chameleon"), 
        PetAdditionalData.of("bubble_wither"), 
        PetAdditionalData.of("talikus_mosquito"), 
        PetAdditionalData.of("winter_fairy_pet") });
  
  public List<PetAdditionalData> getPets() {
    return this.pets;
  }
  
  public PetCommonProxy() {
    instance = this;
    this.registryManager = RegistryManager.getInstance();
    this.captureManager = new CaptureManager();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    ProviderDispatcher.register(new PetSkinProviderModule());
    this.registryManager.getRegistries().forEach(registry -> registry.onPreInit(event));
    ExtendedUtils.registerExtended(EntityPlayer.class, PetPlayer.class, "palapet_PetPlayer", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
    NetworkRegistry.INSTANCE.registerGuiHandler("palapet", (IGuiHandler)(this.guiHandler = new CustomGuiHandler()));
    this.feedPetHandler = this.guiHandler.registerHandler((GHandler)new FeedPetGuiHandler());
    initNetwork("palapet");
    SimpleNetworkWrapper network = getNetwork();
    PacketUtils.registerPacket(network, CSActiveSpellPacket.class);
    PacketUtils.registerPacket(network, CSAssignSkillPacket.class);
    PacketUtils.registerPacket(network, CSPacketTrapInteraction.class);
    PacketUtils.registerPacket(network, SCPacketTrapOpen.class);
    PacketUtils.registerPacket(network, SCObsidianBreakSpeedPacket.class);
    PacketUtils.registerPacket(network, SCGlobalBreakSpeedPacket.class);
    PacketUtils.registerPacket(network, BBOpenUIPacket.class);
    PacketUtils.registerPacket(network, CSPOpenFeedContainerPacket.class);
    PacketUtils.registerPacket(network, CSAssignRollPacket.class);
    PacketUtils.registerPacket(network, BBOpenSkillRollUIPacket.class);
    PacketUtils.registerPacket(network, BBOpenEditPetUIPacket.class);
    PacketUtils.registerPacket(network, CSChangePetSkinPacket.class);
    PacketUtils.registerPacket(network, BBRequestSkillRollPacket.class);
    PacketUtils.registerPacket(network, BBUpdateClientConfigPacket.class);
    PacketUtils.registerPacket(network, BBUpdateClientSkillValuesPacket.class);
    PacketUtils.registerPacket(network, SCOpenDebugUIPacket.class);
    PacketUtils.registerPacket(network, BBPacketRequestCageData.class);
    PacketUtils.registerPacket(network, CSChangeSkinVisibilityPacket.class);
    PacketUtils.registerPacket(network, CSRequestSkillList.class);
    addListener(new Class[] { SpeedWalkCommonListener.class });
    RegistryUtils.entity(EntityDummyPet.class, null, 40, PalaPetMod.getInstance());
    RegistryUtils.entity(EntityPetCage.class, null, 40, PalaPetMod.getInstance());
    ProfileModules.getInstance().registerModule(ModulePet.class);
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    this.registryManager.getRegistries().forEach(registry -> registry.onInit(event));
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    this.registryManager.getRegistries().forEach(registry -> registry.onPostInit(event));
  }
  
  public PetAdditionalData findPet(String name) {
    return this.pets.stream()
      .filter(pet -> pet.getName().equalsIgnoreCase(name))
      .findFirst()
      .orElse(null);
  }
  
  public String findRandomDefaultPet() {
    Random random = new Random();
    return DEFAULT_PETS.get(random.nextInt(DEFAULT_PETS.size()));
  }
  
  public List<File> getFiles(String folderPath, String extension) {
    List<File> files = new ArrayList<>();
    try {
      URL url = PetCommonProxy.class.getResource(folderPath);
      URI uri = url.toURI();
      if ("jar".equals(uri.getScheme())) {
        FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        Path path = fileSystem.getPath(folderPath, new String[0]);
        Files.walk(path, new java.nio.file.FileVisitOption[0]).forEach(p -> {
              if (p.toString().endsWith(extension))
                files.add(p.toFile()); 
            });
        fileSystem.close();
      } else if ("file".equals(uri.getScheme())) {
        File folder = new File(url.toURI());
        for (File listFile : folder.listFiles()) {
          String name = listFile.getName();
          if (name.endsWith(extension))
            files.add(listFile); 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } 
    return files;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\PetCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */