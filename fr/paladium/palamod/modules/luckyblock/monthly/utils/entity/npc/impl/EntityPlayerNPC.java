package fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.AEntityNPC;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPlayerNPC extends AEntityNPC {
  public static final String ENTITY_ID = "entityPlayerNPC";
  
  public static final int NAME_WATCHER_ID = 12;
  
  public static final int SKIN_WATCHER_ID = 13;
  
  private static final String DEFAULT_NAME = "Inconnu";
  
  private static final String DEFAULT_SKIN_PATH = "textures/entity/steve.png";
  
  public static final String TAG_SKIN_PATH = "skinPath";
  
  public static final String TAG_DISPLAY_NAME = "displayName";
  
  protected String skinPath;
  
  public EntityPlayerNPC(World world) {
    super(world);
  }
  
  public EntityPlayerNPC(World world, String displayName, double x, double y, double z, long durationMillis, boolean focus) {
    this(world, displayName, "textures/entity/steve.png", x, y, z, durationMillis, focus);
  }
  
  public EntityPlayerNPC(World world, String displayName, String skinPath, double x, double y, double z, long durationMillis, boolean focus) {
    super(world, displayName, x, y, z, durationMillis, focus);
    this.displayName = displayName;
    this.skinPath = skinPath;
  }
  
  public void spawn(World world) {
    super.spawn(world);
    setDisplayName(this.displayName);
    setSkinPath(this.skinPath);
  }
  
  protected void onSpawn(World world, double x, double y, double z) {}
  
  protected void onExpired(World world, double x, double y, double z) {}
  
  protected void func_70088_a() {
    super.func_70088_a();
    if (this.displayName != null) {
      this.field_70180_af.func_75682_a(12, this.displayName);
    } else {
      this.displayName = "Inconnu";
      this.field_70180_af.func_75682_a(12, "Inconnu");
    } 
    if (this.skinPath != null) {
      this.field_70180_af.func_75682_a(13, this.skinPath);
    } else {
      this.skinPath = "textures/entity/steve.png";
      this.field_70180_af.func_75682_a(13, "textures/entity/steve.png");
    } 
  }
  
  public String func_70005_c_() {
    String displayName = getDisplayName();
    if (displayName == null || displayName.isEmpty())
      displayName = "Unknown"; 
    return displayName;
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
    compound.func_74778_a("skinPath", this.skinPath);
    compound.func_74778_a("displayName", this.displayName);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    this.skinPath = compound.func_74779_i("skinPath");
    this.displayName = compound.func_74779_i("displayName");
  }
  
  public ResourceLocation getPlayerSkin() {
    String skinPath = getSkinPath();
    if (skinPath == null || skinPath.isEmpty())
      skinPath = "textures/entity/steve.png"; 
    return new ResourceLocation(skinPath);
  }
  
  public String getSkinPath() {
    return this.field_70180_af.func_75681_e(13);
  }
  
  public void setSkinPath(String skinPath) {
    this.field_70180_af.func_75692_b(13, skinPath);
  }
  
  public String getDisplayName() {
    return this.field_70180_af.func_75681_e(12);
  }
  
  public void setDisplayName(String displayName) {
    this.field_70180_af.func_75692_b(12, displayName);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\entity\npc\impl\EntityPlayerNPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */