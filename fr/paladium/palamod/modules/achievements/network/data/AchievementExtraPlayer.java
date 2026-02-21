package fr.paladium.palamod.modules.achievements.network.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class AchievementExtraPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_AchievementExtra";
  
  public static final String TAG_NAME = "AchievementExtra";
  
  private static final String TAG_BALACLAVA_HELMET_TIME = "balaclavaHelmetTime";
  
  private long balaclavaHelmetTime = 0L;
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74772_a("balaclavaHelmetTime", this.balaclavaHelmetTime);
    compound.func_74782_a("AchievementExtra", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("AchievementExtra"))
      return; 
    this.balaclavaHelmetTime = compound.func_74763_f("balaclavaHelmetTime");
  }
  
  public boolean updateAchievement(long incrementationMillis) {
    this.balaclavaHelmetTime += incrementationMillis;
    if (this.balaclavaHelmetTime >= TimeUnit.MINUTES.toMillis(1L)) {
      this.balaclavaHelmetTime = 0L;
      return true;
    } 
    return false;
  }
  
  public static AchievementExtraPlayer get(EntityPlayer player) {
    return (AchievementExtraPlayer)player.getExtendedProperties("palamod_AchievementExtra");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\network\data\AchievementExtraPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */