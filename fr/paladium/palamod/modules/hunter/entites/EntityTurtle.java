package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.utils.AEntityWaterMobStaffSound;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityTurtle extends AEntityWaterMobStaffSound {
  public EntityTurtle(World world) {
    super(world);
    func_70105_a(1.1F, 1.1F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityPlayer.class, 3.0F, 0.2D, 0.6D));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.0D));
  }
  
  public String getSoundName() {
    return "turtle";
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(10000) < 45)
      return ItemsRegister.TURTLE_SCALES; 
    return Item.func_150899_d(0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */