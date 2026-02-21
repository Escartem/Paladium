package fr.paladium.palashop.common.shop.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ShopPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palashop_DATA";
  
  public Set<String> getNotifyExpiredSubscriptions() {
    return this.notifyExpiredSubscriptions;
  }
  
  public Set<String> getNotifyReminderSubscriptions() {
    return this.notifyReminderSubscriptions;
  }
  
  private final Set<String> notifyExpiredSubscriptions = new HashSet<>();
  
  private final Set<String> notifyReminderSubscriptions = new HashSet<>();
  
  public static ShopPlayer get(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (ShopPlayer)entity.getExtendedProperties("palashop_DATA");
  }
  
  @NonNull
  public EntityPlayer getPlayer() {
    return (EntityPlayer)getEntity();
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("notifyExpiredSubscriptions")) {
      this.notifyExpiredSubscriptions.clear();
      NBTTagList list = compound.func_150295_c("notifyExpiredSubscriptions", 8);
      for (int i = 0; i < list.func_74745_c(); i++)
        this.notifyExpiredSubscriptions.add(list.func_150307_f(i)); 
    } 
    if (compound.func_74764_b("notifyReminderSubscriptions")) {
      this.notifyReminderSubscriptions.clear();
      NBTTagList list = compound.func_150295_c("notifyReminderSubscriptions", 8);
      for (int i = 0; i < list.func_74745_c(); i++)
        this.notifyReminderSubscriptions.add(list.func_150307_f(i)); 
    } 
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagList notifyExpiredSubscriptions = new NBTTagList();
    for (String subscription : this.notifyExpiredSubscriptions)
      notifyExpiredSubscriptions.func_74742_a((NBTBase)new NBTTagString(subscription)); 
    compound.func_74782_a("notifyExpiredSubscriptions", (NBTBase)notifyExpiredSubscriptions);
    NBTTagList notifyReminderSubscriptions = new NBTTagList();
    for (String subscription : this.notifyReminderSubscriptions)
      notifyReminderSubscriptions.func_74742_a((NBTBase)new NBTTagString(subscription)); 
    compound.func_74782_a("notifyReminderSubscriptions", (NBTBase)notifyReminderSubscriptions);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\data\ShopPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */