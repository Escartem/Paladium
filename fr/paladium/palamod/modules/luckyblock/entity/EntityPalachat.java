package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPalachat extends EntityOcelot {
  public EntityPalachat(World world) {
    super(world);
  }
  
  public EntityPalachat(World world, String uuid) {
    super(world);
    func_70903_f(true);
    func_70912_b(0);
    func_152115_b(uuid);
  }
  
  protected void func_70088_a() {
    for (List obj : (this.field_70170_p.func_72964_e(this.field_70176_ah, this.field_70164_aj)).field_76645_j) {
      if (obj instanceof EntityPalachat) {
        EntityPalachat ent = (EntityPalachat)obj;
        if (ent != this)
          ent.func_70106_y(); 
      } 
    } 
    super.func_70088_a();
  }
  
  public void func_70071_h_() {
    this.field_70714_bg.func_85156_a((EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0D, 0.0F, 0.0F));
    super.func_70071_h_();
    if (this.field_70173_aa % 120000 == 0)
      if ((new Random()).nextInt(2) == 0) {
        func_70099_a(new ItemStack(Blocks.field_150346_d), 5.0F);
      } else {
        ItemStack itemStack = new ItemStack(ItemsRegister.PALADIUM_INGOT);
        func_70099_a(itemStack, 5.0F);
        PPalaDynamique.instance.addGenerated("PALA_CHAT", 1.0D);
      }  
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityPalachat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */