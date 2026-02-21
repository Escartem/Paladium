package fr.paladium.palavote.common.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PalaVotePlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palavote_DATA";
  
  public Map<String, String> getVotedMap() {
    return this.votedMap;
  }
  
  private final Map<String, String> votedMap = new HashMap<>();
  
  public static PalaVotePlayer get(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (PalaVotePlayer)entity.getExtendedProperties("palavote_DATA");
  }
  
  @NonNull
  public EntityPlayer getPlayer() {
    return (EntityPlayer)getEntity();
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("palavote_DATA"))
      return; 
    NBTTagCompound nbt = compound.func_74775_l("palavote_DATA");
    NBTTagList list = nbt.func_150295_c("votes", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound entry = list.func_150305_b(i);
      this.votedMap.put(entry.func_74779_i("id"), entry.func_74779_i("choice"));
    } 
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagList list = new NBTTagList();
    this.votedMap.forEach((id, choice) -> {
          NBTTagCompound entry = new NBTTagCompound();
          entry.func_74778_a("id", id);
          entry.func_74778_a("choice", choice);
          list.func_74742_a((NBTBase)entry);
        });
    nbt.func_74782_a("votes", (NBTBase)list);
    compound.func_74782_a("palavote_DATA", (NBTBase)nbt);
  }
  
  public boolean hasVoted(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.votedMap.containsKey(id);
  }
  
  public String getVote(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.votedMap.get(id);
  }
  
  public void vote(@NonNull String id, @NonNull String choice) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (choice == null)
      throw new NullPointerException("choice is marked non-null but is null"); 
    this.votedMap.put(id, choice);
    sync();
  }
  
  public void unvote(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    this.votedMap.remove(id);
    sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\common\data\PalaVotePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */