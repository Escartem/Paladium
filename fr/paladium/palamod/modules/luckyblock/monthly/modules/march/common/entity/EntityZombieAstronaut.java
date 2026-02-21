package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.ChanceObject;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RandomObjectPicker;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityZombieAstronaut extends EntityZombie {
  private RandomObjectPicker<Item> itemPicker;
  
  public EntityZombieAstronaut(World world) {
    super(world);
  }
  
  public EntityZombieAstronaut(World world, double x, double y, double z) {
    super(world);
    func_70107_b(x, y, z);
    func_82227_f(true);
  }
  
  protected Item func_146068_u() {
    return (Item)getItemPicker().pickRandomObject();
  }
  
  protected void func_70600_l(int i) {}
  
  public RandomObjectPicker<Item> getItemPicker() {
    if (this.itemPicker == null)
      this.itemPicker = new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(10.0D, ItemsRegister.GALACTIC_PILE), 
              ChanceObject.of(20.0D, ItemsRegister.PALADIUM_GREEN_INGOT), 
              ChanceObject.of(3.0D, Item.func_150898_a(BlocksRegister.BLOCK_GPALADIUM)), 
              ChanceObject.of(32.0D, Items.field_151123_aH), 
              ChanceObject.of(15.0D, ItemsRegister.TRAVEL_SLIMYHELMET), 
              ChanceObject.of(20.0D, null) })); 
    return this.itemPicker;
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    super.func_70014_b(compound);
    compound.func_74778_a("id", UUID.randomUUID().toString());
  }
  
  public String func_94057_bL() {
    return "[NS]" + func_70005_c_();
  }
  
  public String func_70005_c_() {
    return "§bZombie Astronaute";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\entity\EntityZombieAstronaut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */