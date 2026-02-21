package fr.paladium.palamod.modules.paladium.network.data;

import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;

public class PaladiumPlayer implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_COSMETIQUES";
  
  private Entity entity;
  
  private int paladiumArmorType;
  
  private int currentSpell;
  
  private int spellPoints;
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public void setEntity(Entity entity) {
    this.entity = entity;
  }
  
  public int getPaladiumArmorType() {
    return this.paladiumArmorType;
  }
  
  public int getCurrentSpell() {
    return this.currentSpell;
  }
  
  public int getSpellPoints() {
    return this.spellPoints;
  }
  
  private int skinID = 0;
  
  public int getSkinID() {
    return this.skinID;
  }
  
  public void setSkinID(int skinID) {
    this.skinID = skinID;
  }
  
  private long skinLast = 0L;
  
  private boolean needUpdateSkin;
  
  public long getSkinLast() {
    return this.skinLast;
  }
  
  public void setSkinLast(long skinLast) {
    this.skinLast = skinLast;
  }
  
  public boolean isNeedUpdateSkin() {
    return this.needUpdateSkin;
  }
  
  public void setNeedUpdateSkin(boolean needUpdateSkin) {
    this.needUpdateSkin = needUpdateSkin;
  }
  
  private boolean compensation = false;
  
  public boolean isCompensation() {
    return this.compensation;
  }
  
  public void setCompensation(boolean compensation) {
    this.compensation = compensation;
  }
  
  private boolean showHoodHelmet = false;
  
  public boolean isShowHoodHelmet() {
    return this.showHoodHelmet;
  }
  
  public void setShowHoodHelmet(boolean showHoodHelmet) {
    this.showHoodHelmet = showHoodHelmet;
  }
  
  private boolean trixiumRank = false;
  
  public boolean isTrixiumRank() {
    return this.trixiumRank;
  }
  
  public void setTrixiumRank(boolean trixiumRank) {
    this.trixiumRank = trixiumRank;
  }
  
  private int[] adventCalendar = new int[24];
  
  private boolean hasPlayed;
  
  private boolean aprilCape;
  
  private long aprilCapeDate;
  
  public int[] getAdventCalendar() {
    return this.adventCalendar;
  }
  
  public void setAdventCalendar(int[] adventCalendar) {
    this.adventCalendar = adventCalendar;
  }
  
  public boolean isHasPlayed() {
    return this.hasPlayed;
  }
  
  public void setHasPlayed(boolean hasPlayed) {
    this.hasPlayed = hasPlayed;
  }
  
  public boolean isAprilCape() {
    return this.aprilCape;
  }
  
  public void setAprilCape(boolean aprilCape) {
    this.aprilCape = aprilCape;
  }
  
  public long getAprilCapeDate() {
    return this.aprilCapeDate;
  }
  
  public void setAprilCapeDate(long aprilCapeDate) {
    this.aprilCapeDate = aprilCapeDate;
  }
  
  public static void register() {
    MinecraftForge.EVENT_BUS.register(new PaladiumPlayerHandler());
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("skinID", this.skinID);
    nbt.func_74772_a("skinLast", this.skinLast);
    nbt.func_74768_a("skinID", this.skinID);
    nbt.func_74768_a("paladiumArmorType", this.paladiumArmorType);
    nbt.func_74757_a("compensation", this.compensation);
    nbt.func_74757_a("showHoodHelmet", this.showHoodHelmet);
    nbt.func_74783_a("adventCalendar", this.adventCalendar);
    nbt.func_74757_a("hasPlayed", this.hasPlayed);
    nbt.func_74757_a("aprilCape", this.aprilCape);
    nbt.func_74772_a("aprilCapeDate", this.aprilCapeDate);
    compound.func_74782_a("cosmetics", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("cosmetics")) {
      NBTTagCompound nbt = (NBTTagCompound)compound.func_74781_a("cosmetics");
      if (nbt.func_74764_b("skinID"))
        this.skinID = nbt.func_74762_e("skinID"); 
      if (nbt.func_74764_b("skinLast"))
        this.skinLast = nbt.func_74763_f("skinLast"); 
      if (nbt.func_74764_b("paladiumArmorType"))
        this.paladiumArmorType = nbt.func_74762_e("paladiumArmorType"); 
      if (nbt.func_74764_b("compensation"))
        this.compensation = nbt.func_74767_n("compensation"); 
      if (nbt.func_74764_b("showHoodHelmet"))
        this.showHoodHelmet = nbt.func_74767_n("showHoodHelmet"); 
      if (nbt.func_74764_b("adventCalendar"))
        this.adventCalendar = nbt.func_74759_k("adventCalendar"); 
      if (nbt.func_74764_b("hasPlayed"))
        this.hasPlayed = nbt.func_74767_n("hasPlayed"); 
      if (nbt.func_74764_b("aprilCape"))
        this.aprilCape = nbt.func_74767_n("aprilCape"); 
      if (nbt.func_74764_b("aprilCapeDate"))
        this.aprilCapeDate = nbt.func_74763_f("aprilCapeDate"); 
    } 
  }
  
  public void init(Entity entity, World world) {
    this.entity = entity;
  }
  
  public void dataChanged(Entity e) {
    if (e instanceof EntityPlayer)
      dataChanged((EntityPlayer)e); 
  }
  
  private void dataChanged() {
    dataChanged(this.entity);
  }
  
  public void setPaladiumArmorType(int paladiumArmorType) {
    this.paladiumArmorType = paladiumArmorType;
    dataChanged();
  }
  
  public void dataChanged(EntityPlayer player) {
    if (!player.field_70170_p.field_72995_K) {
      EntityTracker tracker = ((WorldServer)player.field_70170_p).func_73039_n();
      SCPacketPaladiumPlayerDataSync message = new SCPacketPaladiumPlayerDataSync(this, FastUUID.toString((Entity)player));
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)player))
        PalaMod.getNetHandler().sendTo(message, (EntityPlayerMP)entityPlayer); 
      PalaMod.getNetHandler().sendTo(message, (EntityPlayerMP)player);
    } 
  }
  
  public void playerStartedTracking(EntityPlayer player) {
    SCPacketPaladiumPlayerDataSync message = new SCPacketPaladiumPlayerDataSync(this, FastUUID.toString(this.entity));
    PalaMod.getNetHandler().sendTo(message, (EntityPlayerMP)player);
  }
  
  public static PaladiumPlayer get(Entity p) {
    return (PaladiumPlayer)p.getExtendedProperties("palamod_COSMETIQUES");
  }
  
  public boolean isShowAura() {
    return !isShowHoodHelmet();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\data\PaladiumPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */