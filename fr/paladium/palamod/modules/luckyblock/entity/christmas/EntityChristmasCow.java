package fr.paladium.palamod.modules.luckyblock.entity.christmas;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityChristmasCow extends EntityCow {
  public EntityChristmasCow(World world) {
    super(world);
  }
  
  public EntityChristmasCow(World world, double x, double y, double z) {
    super(world);
    func_70634_a(x, y, z);
  }
  
  public boolean func_70085_c(EntityPlayer player) {
    ItemStack itemstack = player.field_71071_by.func_70448_g();
    if (itemstack != null && itemstack.func_77973_b() == Items.field_151133_ar && !player.field_71075_bZ.field_75098_d) {
      if (itemstack.field_77994_a-- == 1) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(ItemsRegister.CHOCOLATE_MILK));
      } else if (!player.field_71071_by.func_70441_a(new ItemStack(ItemsRegister.CHOCOLATE_MILK))) {
        player.func_71019_a(new ItemStack(ItemsRegister.CHOCOLATE_MILK, 1, 0), false);
      } 
      return true;
    } 
    return super.func_70085_c(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\christmas\EntityChristmasCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */