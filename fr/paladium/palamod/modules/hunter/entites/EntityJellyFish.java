package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityWaterMobStaffSound;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityJellyFish extends AEntityWaterMobStaffSound {
  private int type = 0;
  
  public int getType() {
    return this.type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public EntityJellyFish(World world) {
    super(world);
    func_70105_a(0.7F, 0.9F);
    this.type = world.field_73012_v.nextInt(3);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityPlayer.class, 3.0F, 0.2D, 0.6D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
  }
  
  public String getSoundName() {
    return "jellyfish";
  }
  
  public void func_70636_d() {
    int x = (int)this.field_70165_t;
    int y = (int)this.field_70163_u;
    int z = (int)this.field_70161_v;
    if (this.field_70170_p.field_73012_v.nextInt(50) == 0)
      for (int i = -2; i < 3; i++) {
        for (int j = -2; j < 3; j++) {
          for (int k = -2; k < 3; k++) {
            if (this.field_70170_p.func_147439_a(x + i, y + j, z + k) == Blocks.field_150355_j || this.field_70170_p
              .func_147439_a(x + i, y + j, z + k) == Blocks.field_150358_i)
              EventUtils.spawnParticle(this.field_70170_p, "cloud", (x + i), (y + j), (z + k), 2, 0.05D); 
          } 
        } 
      }  
    super.func_70636_d();
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4000000059604645D);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    if (compound.func_74764_b("type"))
      this.type = compound.func_74762_e("type"); 
    super.func_70020_e(compound);
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    compound.func_74768_a("type", this.type);
    super.func_70109_d(compound);
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(100) < 2)
      return ItemsRegister.MEDUSE_HOOK; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */