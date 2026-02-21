package fr.paladium.palamod.modules.hunter.networks;

import fr.paladium.palamod.util.ItemStackSerializer;
import java.util.Base64;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerBackPackProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_BACKPACK";
  
  private EntityPlayer entity;
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  private ItemStack[] content = new ItemStack[81];
  
  public ItemStack[] getContent() {
    return this.content;
  }
  
  public void setContent(ItemStack[] content) {
    this.content = content;
  }
  
  public PlayerBackPackProperties(EntityPlayer player) {
    this.entity = player;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagList nbtlist = new NBTTagList();
    for (int i = 0; i < 81; i++) {
      if (this.content[i] != null) {
        NBTTagCompound comp1 = new NBTTagCompound();
        comp1.func_74768_a("slot", i);
        comp1.func_74778_a("item", Base64.getEncoder()
            .encodeToString(ItemStackSerializer.write(this.content[i]).getBytes()));
        nbtlist.func_74742_a((NBTBase)comp1);
      } 
    } 
    compound.func_74782_a("backpack", (NBTBase)nbtlist);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    this.content = new ItemStack[81];
    NBTTagList nbtlist = compound.func_150295_c("backpack", 10);
    for (int i = 0; i < nbtlist.func_74745_c(); i++) {
      NBTTagCompound comp1 = nbtlist.func_150305_b(i);
      int slot = comp1.func_74762_e("slot");
      byte[] decodedBytes = Base64.getDecoder().decode(comp1.func_74779_i("item"));
      String item = new String(decodedBytes);
      this.content[slot] = ItemStackSerializer.read(item);
    } 
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_BACKPACK", new PlayerBackPackProperties(player));
  }
  
  public static final PlayerBackPackProperties get(EntityPlayer player) {
    return (PlayerBackPackProperties)player.getExtendedProperties("palamod_BACKPACK");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\PlayerBackPackProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */