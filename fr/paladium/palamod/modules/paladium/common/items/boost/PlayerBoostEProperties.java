package fr.paladium.palamod.modules.paladium.common.items.boost;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerBoostEProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_BOOST";
  
  private EntityPlayer entity;
  
  private long boostDoubleXp;
  
  private long boostMinerFou;
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  public long getBoostDoubleXp() {
    return this.boostDoubleXp;
  }
  
  public long getBoostMinerFou() {
    return this.boostMinerFou;
  }
  
  public PlayerBoostEProperties(EntityPlayer player) {
    this.entity = player;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74772_a("boostDoubleXp", this.boostDoubleXp);
    compound.func_74772_a("boostMinerFou", this.boostMinerFou);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("boostDoubleXp"))
      this.boostDoubleXp = compound.func_74763_f("boostDoubleXp"); 
    if (compound.func_74764_b("boostMinerFou"))
      this.boostMinerFou = compound.func_74763_f("boostMinerFou"); 
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_BOOST", new PlayerBoostEProperties(player));
  }
  
  public static final PlayerBoostEProperties get(EntityPlayer player) {
    return (PlayerBoostEProperties)player.getExtendedProperties("palamod_BOOST");
  }
  
  public void setBoostDoubleXp(long boostDoubleXp) {
    this.boostDoubleXp = boostDoubleXp;
  }
  
  public void setBoostMinerFou(long boostMinerFou) {
    this.boostMinerFou = boostMinerFou;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\PlayerBoostEProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */