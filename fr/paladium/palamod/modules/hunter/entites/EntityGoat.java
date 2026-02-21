package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntitySheepStaffSound;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGoat extends AEntitySheepStaffSound {
  public EntityGoat(World world) {
    super(world);
    func_70105_a(1.0F, 1.0F);
  }
  
  public String getSoundName() {
    return "goat";
  }
  
  protected void func_70628_a(boolean flag, int p_70628_2_) {
    PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(
          func_146068_u()));
    super.func_70628_a(flag, p_70628_2_);
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(1000) < 8)
      return ItemsRegister.GOAT_SHOE; 
    return Item.func_150899_d(0);
  }
  
  public EntitySheep func_90011_a(EntityAgeable older) {
    EntityGoat goat1 = new EntityGoat(this.field_70170_p);
    goat1.func_70891_b(this.field_70170_p.field_73012_v.nextInt(16));
    return (EntitySheep)goat1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */