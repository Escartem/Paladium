package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMineralRabbit extends EntityRabbit implements IEntityAdditionalSpawnData {
  public Item mineral;
  
  public int jump = 0;
  
  public EntityMineralRabbit(World world) {
    this(world, Blocks.field_150350_a);
  }
  
  public EntityMineralRabbit(World world, Item mineral) {
    super(world);
    this.mineral = mineral;
    func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 99999999, 2));
    func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 99999999, 2));
  }
  
  public EntityMineralRabbit(World world, Block mineral) {
    super(world);
    this.mineral = Item.func_150898_a(mineral);
    func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 99999999, 2));
    func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 99999999, 2));
  }
  
  protected void func_70664_aZ() {
    this.jump++;
    if (this.jump > 10) {
      func_70106_y();
    } else if (this.mineral != null && !this.field_70170_p.field_72995_K) {
      PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, new ItemStack(this.mineral, 1));
      if (this.mineral.equals(ItemsRegister.PALADIUM_INGOT))
        PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 1.0D); 
    } 
    super.func_70664_aZ();
  }
  
  public void func_70645_a(DamageSource source) {
    if (this.mineral != null)
      for (int i = 0; i < 10; i++) {
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, new ItemStack(this.mineral, 1));
        if (!this.field_70170_p.field_72995_K && this.mineral.equals(ItemsRegister.PALADIUM_INGOT))
          PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 1.0D); 
      }  
    super.func_70645_a(source);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    this.mineral = Item.func_150899_d(nbt.func_74762_e("mineral"));
    this.jump = nbt.func_74762_e("jump");
    super.func_70037_a(nbt);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74768_a("mineral", Item.func_150891_b(this.mineral));
    nbt.func_74768_a("jump", this.jump);
    super.func_70014_b(nbt);
  }
  
  public Item getMineral() {
    return this.mineral;
  }
  
  public int getJump() {
    return this.jump;
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(Item.func_150891_b(this.mineral));
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.mineral = Item.func_150899_d(additionalData.readInt());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityMineralRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */