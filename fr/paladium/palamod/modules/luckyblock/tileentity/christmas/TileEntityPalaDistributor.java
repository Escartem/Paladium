package fr.paladium.palamod.modules.luckyblock.tileentity.christmas;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPalaDistributor extends TileEntity {
  private long lastDrop;
  
  public void func_145839_a(NBTTagCompound compound) {
    this.lastDrop = compound.func_74763_f("lastDrop");
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74772_a("lastDrop", this.lastDrop);
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {
      long diff = System.currentTimeMillis() - this.lastDrop;
      if (diff > 60000L) {
        ItemStack itemStack = new ItemStack(ItemsRegister.PALADIUM_INGOT);
        EntityItem item = new EntityItem(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 2), this.field_145849_e, itemStack);
        PPalaDynamique.instance.addGenerated("PALA_DISTRIBUTOR", itemStack.field_77994_a);
        this.field_145850_b.func_72838_d((Entity)item);
        this.lastDrop = System.currentTimeMillis();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\christmas\TileEntityPalaDistributor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */