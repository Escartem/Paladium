package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.api.ItemsRegister;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityInvisible extends EntityLiving {
  private int ticks = 0;
  
  private long sec = 30L;
  
  public EntityInvisible(World world) {
    super(world);
    this.field_70131_O = 1.0F;
  }
  
  public EntityInvisible(World world, Entity player, double x, double y, double z) {
    super(world);
    this.field_70131_O = 1.0F;
  }
  
  private List<EntityItem> getNearDrops() {
    AxisAlignedBB axis = AxisAlignedBB.func_72330_a(this.field_70165_t - 10.0D, this.field_70163_u - 10.0D, this.field_70161_v - 10.0D, this.field_70165_t + 10.0D, this.field_70163_u + 10.0D, this.field_70161_v + 10.0D);
    return this.field_70170_p.func_72872_a(EntityItem.class, axis);
  }
  
  private int getDropCount() {
    int drops = 0;
    for (EntityItem item : getNearDrops()) {
      ItemStack stack = item.func_92059_d();
      if (stack != null && stack.func_77973_b().func_77658_a().toLowerCase().contains("seeds"))
        drops += stack.field_77994_a; 
    } 
    return drops;
  }
  
  public boolean func_85032_ar() {
    return true;
  }
  
  public void func_70071_h_() {
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    func_94061_f(true);
    if (this.ticks >= 20) {
      this.sec--;
      this.ticks = 0;
    } 
    if (this.sec < 0L || getDropCount() >= 32) {
      func_70106_y();
      return;
    } 
    func_94058_c("§bJetez vos graines ici - " + this.sec + " secondes restantes");
    this.ticks++;
    super.func_70071_h_();
  }
  
  public void func_70106_y() {
    List<EntityItem> nearDrops = getNearDrops();
    int dropTransformed = 0;
    for (EntityItem entityItem : nearDrops) {
      ItemStack stack = entityItem.func_92059_d();
      if (dropTransformed >= 32)
        continue; 
      if (stack == null || stack.func_77973_b() == null)
        continue; 
      Item item = stack.func_77973_b();
      if (!item.func_77658_a().toLowerCase().contains("seeds"))
        continue; 
      if (item == ItemsRegister.SEED_ORANGEBLUE)
        continue; 
      int itemSize = stack.field_77994_a;
      if (itemSize + dropTransformed > 32) {
        int sizeToRemove = itemSize + dropTransformed - 32;
        dropTransformed += itemSize;
        if (sizeToRemove < 0) {
          entityItem.func_70106_y();
          break;
        } 
        stack.field_77994_a -= sizeToRemove;
        ItemStack nStack = new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE, sizeToRemove);
        EntityItem ent = new EntityItem(this.field_70170_p, entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, nStack);
        this.field_70170_p.func_72838_d((Entity)ent);
        break;
      } 
      entityItem.func_92058_a(new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE, stack.field_77994_a));
    } 
    super.func_70106_y();
  }
  
  public void func_70037_a(NBTTagCompound compound) {}
  
  public void func_70014_b(NBTTagCompound compound) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityInvisible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */