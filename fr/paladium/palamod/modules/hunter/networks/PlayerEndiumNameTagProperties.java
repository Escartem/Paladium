package fr.paladium.palamod.modules.hunter.networks;

import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerEndiumNameTagProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_ENDIUM_NAMETAG";
  
  private EntityPlayer entity;
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  private List<String> entities = new ArrayList<>();
  
  public List<String> getEntities() {
    return this.entities;
  }
  
  public void setEntities(List<String> entities) {
    this.entities = entities;
  }
  
  public PlayerEndiumNameTagProperties(EntityPlayer player) {
    this.entity = player;
  }
  
  public void addEntity(Entity ent) {
    String entUuid = FastUUID.toString(ent);
    if (this.entities.contains(entUuid))
      return; 
    if (this.entities.size() >= 3) {
      int offset = this.entities.size() - 3;
      for (int i = 0; i < offset; i++) {
        for (Object obj : this.entity.field_70170_p.field_72996_f) {
          Entity e = (Entity)obj;
          if (FastUUID.toString(e).equalsIgnoreCase(this.entities.get(i)) && 
            e instanceof EntityLiving) {
            EntityLiving living = (EntityLiving)e;
            living.func_94058_c(living.getEntityData().func_74779_i("defaultName"));
          } 
        } 
        this.entities.remove(i);
      } 
    } 
    if (ent.getEntityData() != null)
      ent.getEntityData().func_74778_a("endium_nametag_owner", FastUUID.toString((Entity)this.entity)); 
    this.entities.add(entUuid);
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagList nbtlist = new NBTTagList();
    for (int i = 0; i < this.entities.size(); i++) {
      NBTTagCompound comp1 = new NBTTagCompound();
      comp1.func_74778_a("entity", this.entities.get(i));
      nbtlist.func_74742_a((NBTBase)comp1);
    } 
    compound.func_74782_a("entities", (NBTBase)nbtlist);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("entities")) {
      NBTTagList nbtlist = compound.func_150295_c("entities", 10);
      for (int i = 0; i < nbtlist.func_74745_c(); i++) {
        NBTTagCompound comp1 = nbtlist.func_150305_b(i);
        if (!this.entities.contains(comp1.func_74779_i("entity")))
          this.entities.add(comp1.func_74779_i("entity")); 
      } 
    } 
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_ENDIUM_NAMETAG", new PlayerEndiumNameTagProperties(player));
  }
  
  public static final PlayerEndiumNameTagProperties get(EntityPlayer player) {
    return (PlayerEndiumNameTagProperties)player.getExtendedProperties("palamod_ENDIUM_NAMETAG");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\PlayerEndiumNameTagProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */