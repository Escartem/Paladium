package fr.paladium.announcement.common.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class AnnouncementPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "announcement_AnnouncementPlayer";
  
  public static final String TAG_NAME = "AnnouncementPlayer";
  
  private static final String TAG_LAST_ANNOUNCEMENT = "lastAnnouncement";
  
  public void setLastAnnouncement(long lastAnnouncement) {
    this.lastAnnouncement = lastAnnouncement;
  }
  
  public long getLastAnnouncement() {
    return this.lastAnnouncement;
  }
  
  private long lastAnnouncement = 0L;
  
  public static AnnouncementPlayer get(EntityPlayer player) {
    return (AnnouncementPlayer)player.getExtendedProperties("announcement_AnnouncementPlayer");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74772_a("lastAnnouncement", this.lastAnnouncement);
    compound.func_74782_a("AnnouncementPlayer", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("AnnouncementPlayer"))
      return; 
    NBTTagCompound nbt = compound.func_74775_l("AnnouncementPlayer");
    this.lastAnnouncement = nbt.func_74763_f("lastAnnouncement");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\data\AnnouncementPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */