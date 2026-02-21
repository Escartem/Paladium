package fr.paladium.palamod.mixins.client.item;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemStack.class})
public abstract class IMixinItemStack {
  @Shadow
  public int field_77994_a;
  
  @Shadow
  public NBTTagCompound field_77990_d;
  
  @Shadow
  int field_77991_e;
  
  @Shadow
  public abstract void func_150996_a(Item paramItem);
  
  @Overwrite
  public void func_77963_c(NBTTagCompound p_77963_1_) {
    byte tagId = p_77963_1_.func_150299_b("id");
    if (tagId == 2 || tagId == 3 || tagId == 4 || tagId == 5 || tagId == 6) {
      func_150996_a(Item.func_150899_d(p_77963_1_.func_74765_d("id")));
    } else if (tagId == 8) {
      func_150996_a((Item)GameData.getItemRegistry().func_82594_a(p_77963_1_.func_74779_i("id")));
    } else {
      func_150996_a(Items.field_151055_y);
    } 
    this.field_77994_a = p_77963_1_.func_74771_c("Count");
    this.field_77991_e = p_77963_1_.func_74765_d("Damage");
    if (this.field_77991_e < 0)
      this.field_77991_e = 0; 
    if (p_77963_1_.func_150297_b("tag", 10))
      this.field_77990_d = p_77963_1_.func_74775_l("tag"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\item\IMixinItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */