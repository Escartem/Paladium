package fr.paladium.palamod.modules.luckyblock.entity.halloween;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityPumpkinGolem extends EntityGolem implements IRangedAttackMob, IShearable {
  private static final int HAS_PUMPKIN = 12;
  
  public EntityPumpkinGolem(World world) {
    super(world);
    func_70105_a(0.4F, 1.8F);
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, true, false, IMob.field_82192_a));
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d)
      .func_111128_a(0.20000000298023224D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(12, Byte.valueOf((byte)1));
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.field_70180_af.func_75692_b(12, Byte.valueOf(nbt.func_74767_n("HasPumpkin") ? 1 : 0));
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("HasPumpkin", (this.field_70180_af.func_75683_a(12) == 1));
  }
  
  public boolean isShearable(ItemStack stack, IBlockAccess world, int x, int y, int z) {
    return hasPumpkin();
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> list = new ArrayList<>();
    list.add(new ItemStack(Blocks.field_150423_aK));
    this.field_70180_af.func_75692_b(12, Byte.valueOf((byte)0));
    return list;
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70163_u);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    for (int l = 0; l < 4; l++) {
      i = MathHelper.func_76128_c(this.field_70165_t + ((l % 2 * 2 - 1) * 0.25F));
      j = MathHelper.func_76128_c(this.field_70163_u);
      k = MathHelper.func_76128_c(this.field_70161_v + ((l / 2 % 2 * 2 - 1) * 0.25F));
      if (this.field_70170_p.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a && BlocksRegister.PUMKINLAYER
        .func_149742_c(this.field_70170_p, i, j, k))
        this.field_70170_p.func_147449_b(i, j, k, BlocksRegister.PUMKINLAYER); 
    } 
  }
  
  protected Item func_146068_u() {
    return ItemsRegister.PUMPKINCRUSH;
  }
  
  protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    int j = this.field_70146_Z.nextInt(16);
    for (int k = 0; k < j; k++)
      func_145779_a(ItemsRegister.PUMPKINCRUSH, 1); 
  }
  
  public boolean hasPumpkin() {
    return (this.field_70180_af.func_75683_a(12) == 1);
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {
    EntitySnowball entitysnowball = new EntitySnowball(this.field_70170_p, (EntityLivingBase)this);
    double d0 = p_82196_1_.field_70165_t - this.field_70165_t;
    double d1 = p_82196_1_.field_70163_u + p_82196_1_.func_70047_e() - 1.100000023841858D - entitysnowball.field_70163_u;
    double d2 = p_82196_1_.field_70161_v - this.field_70161_v;
    float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2) * 0.2F;
    entitysnowball.func_70186_c(d0, d1 + f1, d2, 1.6F, 12.0F);
    func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
    this.field_70170_p.func_72838_d((Entity)entitysnowball);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\halloween\EntityPumpkinGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */