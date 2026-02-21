package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import io.netty.buffer.ByteBuf;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTippedArrow extends EntityArrow implements IEntityAdditionalSpawnData {
  private PotionEffect effect;
  
  public EntityTippedArrow(World world) {
    super(world);
  }
  
  public EntityTippedArrow(World world, EntityLivingBase entity, float f0) {
    super(world, entity, f0);
  }
  
  public EntityTippedArrow(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public EntityTippedArrow(World world, EntityLivingBase entity, EntityLivingBase target, float f0, float f1) {
    super(world, entity, target, f0, f1);
  }
  
  public void setEffect(PotionEffect effect) {
    this.effect = effect;
  }
  
  public PotionEffect getEffect() {
    return this.effect;
  }
  
  private boolean isEffectValid() {
    return (this.effect != null && Potion.field_76425_a[this.effect.func_76456_a()] != null);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (isEffectValid()) {
      Color colour = new Color(Potion.field_76425_a[this.effect.func_76456_a()].func_76401_j());
      this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t, this.field_70163_u, this.field_70161_v, (colour.getRed() / 255.0F), (colour
          .getGreen() / 255.0F), (colour.getBlue() / 255.0F));
    } 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    if (this.effect != null) {
      NBTTagCompound effectNBT = new NBTTagCompound();
      this.effect.func_82719_a(effectNBT);
      nbt.func_74782_a("Effect", (NBTBase)effectNBT);
    } 
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    if (nbt.func_74764_b("Effect"))
      this.effect = PotionEffect.func_82722_b((NBTTagCompound)nbt.func_74781_a("Effect")); 
  }
  
  public void func_70100_b_(EntityPlayer player) {
    boolean inGround = false;
    try {
      inGround = ((Boolean)ReflectionHelper.getPrivateValue(EntityArrow.class, this, new String[] { "inGround", "field_70254_i" })).booleanValue();
    } catch (Exception exception) {}
    if (!this.field_70170_p.field_72995_K && inGround && this.field_70249_b <= 0 && isEffectValid()) {
      boolean flag = (this.field_70251_a == 1 || (this.field_70251_a == 2 && player.field_71075_bZ.field_75098_d));
      ItemStack stack = new ItemStack(ModItems.tipped_arrow);
      TippedArrow.setEffect(stack, Potion.field_76425_a[this.effect.func_76456_a()], this.effect.func_76459_b(), this.effect
          .func_76458_c());
      if (this.field_70251_a == 1 && !player.field_71071_by.func_70441_a(stack))
        flag = false; 
      if (flag) {
        func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        player.func_71001_a((Entity)this, 1);
        func_70106_y();
      } 
    } 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeFloat(this.field_70177_z);
    int id = (this.field_70250_c == null) ? func_145782_y() : this.field_70250_c.func_145782_y();
    buffer.writeInt(id);
    buffer.writeDouble(this.field_70159_w);
    buffer.writeDouble(this.field_70181_x);
    buffer.writeDouble(this.field_70179_y);
    if (this.effect != null) {
      buffer.writeInt(this.effect.func_76456_a());
      buffer.writeInt(this.effect.func_76459_b());
      buffer.writeInt(this.effect.func_76458_c());
    } else {
      buffer.writeInt(Potion.field_76432_h.field_76415_H);
      buffer.writeInt(1);
      buffer.writeInt(0);
    } 
  }
  
  public void readSpawnData(ByteBuf buffer) {
    this.field_70177_z = buffer.readFloat();
    this.field_70250_c = this.field_70170_p.func_73045_a(buffer.readInt());
    this.field_70159_w = buffer.readDouble();
    this.field_70181_x = buffer.readDouble();
    this.field_70179_y = buffer.readDouble();
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.effect = new PotionEffect(buffer.readInt(), buffer.readInt(), buffer.readInt());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */