package fr.paladium.pet.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.pet.PetLogger;
import fr.paladium.pet.client.listener.ClientBreakSpeedListener;
import fr.paladium.pet.client.listener.PetRenderListener;
import fr.paladium.pet.client.listener.SkillRollListener;
import fr.paladium.pet.client.renderer.CageGeoRenderer;
import fr.paladium.pet.client.renderer.PetCageRenderInventory;
import fr.paladium.pet.client.renderer.PetGeoRenderer;
import fr.paladium.pet.client.renderer.TileEntityPetCageRenderer;
import fr.paladium.pet.client.ui.utils.PetUIUtils;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.entity.EntityPetCage;
import fr.paladium.pet.common.pet.PetAdditionalData;
import fr.paladium.pet.common.tile.cage.TileEntityPetCage;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

public class PetClientProxy extends PetCommonProxy {
  public static int cageRenderId;
  
  private static PetClientProxy instance;
  
  private List<SkillRollSlotData> skillRollSlots;
  
  private HashMap<String, Double> skillValues;
  
  private KeyBinding keySkillRoll;
  
  public void setSkillRollSlots(List<SkillRollSlotData> skillRollSlots) {
    this.skillRollSlots = skillRollSlots;
  }
  
  public void setSkillValues(HashMap<String, Double> skillValues) {
    this.skillValues = skillValues;
  }
  
  public void setKeySkillRoll(KeyBinding keySkillRoll) {
    this.keySkillRoll = keySkillRoll;
  }
  
  public static PetClientProxy getInstance() {
    return instance;
  }
  
  public List<SkillRollSlotData> getSkillRollSlots() {
    return this.skillRollSlots;
  }
  
  public HashMap<String, Double> getSkillValues() {
    return this.skillValues;
  }
  
  public KeyBinding getKeySkillRoll() {
    return this.keySkillRoll;
  }
  
  public PetClientProxy() {
    instance = this;
    this.skillValues = new HashMap<>();
    this.skillRollSlots = new ArrayList<>();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PetRenderListener.class, ClientBreakSpeedListener.class, SkillRollListener.class });
    RenderingRegistry.registerEntityRenderingHandler(EntityDummyPet.class, (Render)new PetGeoRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityPetCage.class, (Render)new CageGeoRenderer());
    cageRenderId = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetCage.class, (TileEntitySpecialRenderer)new TileEntityPetCageRenderer());
    RenderingRegistry.registerBlockHandler(cageRenderId, (ISimpleBlockRenderingHandler)new PetCageRenderInventory());
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    this.keySkillRoll = new KeyBinding("pet.key.skill_roll", 46, "key.categories.pet");
    ClientRegistry.registerKeyBinding(this.keySkillRoll);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    loadAdditionalData();
  }
  
  public void loadAdditionalData() {
    IResourceManager manager = Minecraft.func_71410_x().func_110442_L();
    List<PetAdditionalData> pets = PetCommonProxy.getInstance().getPets();
    for (PetAdditionalData pet : pets) {
      ResourceLocation location = PetUIUtils.getAdditionalDataFileLocation(pet.getName());
      try {
        IResource resource = manager.func_110536_a(location);
        pet.read(resource.func_110527_b());
        PetLogger.info("Loaded additional data for pet " + pet.getName());
      } catch (Exception e) {
        PetLogger.error("Failed to load additional data for pet " + pet.getName());
      } 
    } 
  }
  
  public double getSkillValue(PassiveSkillEnum skill) {
    return getSkillValue(skill.getId());
  }
  
  public double getSkillValue(String skill) {
    return ((Double)this.skillValues.getOrDefault(skill, Double.valueOf(0.0D))).doubleValue();
  }
  
  public SkillRollSlotData findSkillRollData(int slot) {
    for (SkillRollSlotData data : this.skillRollSlots) {
      if (data.getSlot() == slot)
        return data; 
    } 
    return SkillRollSlotData.none(slot);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\PetClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */