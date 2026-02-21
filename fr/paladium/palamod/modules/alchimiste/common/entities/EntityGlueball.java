package fr.paladium.palamod.modules.alchimiste.common.entities;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumGlueball;
import fr.paladium.spellflag.SpellFlag;
import io.netty.buffer.ByteBuf;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGlueball extends EntityThrowable implements IEntityAdditionalSpawnData {
  public EnumGlueball g;
  
  public EntityGlueball(World world) {
    super(world);
  }
  
  public EntityGlueball(World world, EntityLivingBase entity) {
    super(world, entity);
  }
  
  public EntityGlueball(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public void func_70109_d(NBTTagCompound tag) {
    tag.func_74768_a("glue", this.g.ordinal());
    super.func_70109_d(tag);
  }
  
  public void func_70020_e(NBTTagCompound tag) {
    if (tag.func_74764_b("glue"))
      this.g = EnumGlueball.values()[tag.func_74762_e("glue")]; 
    super.func_70020_e(tag);
  }
  
  public EntityGlueball setG(EnumGlueball g) {
    this.g = g;
    return this;
  }
  
  public Item getItem() {
    if (this.g == null)
      return (Item)ItemsRegister.GREEN_GLUEBALL; 
    switch (this.g.getName().toLowerCase()) {
      case "green":
        return (Item)ItemsRegister.GREEN_GLUEBALL;
      case "blue":
        return (Item)ItemsRegister.BLUE_GLUEBALL;
      case "yellow":
        return (Item)ItemsRegister.YELLOW_GLUEBALL;
      case "red":
        return (Item)ItemsRegister.RED_GLUEBALL;
      case "orange":
        return (Item)ItemsRegister.ORANGE_GLUEBALL;
      case "gray":
        return (Item)ItemsRegister.GRAY_GLUEBALL;
      case "purple":
        return (Item)ItemsRegister.PURPLE_GLUEBALL;
      case "green_flash":
        return (Item)ItemsRegister.GREEN_FLASH_GLUEBALL;
      case "green_dark":
        return (Item)ItemsRegister.GREEN_DARK_GLUEBALL;
      case "cyan":
        return (Item)ItemsRegister.CYAN_GLUEBALL;
    } 
    return null;
  }
  
  protected void func_70184_a(MovingObjectPosition mop) {
    func_70106_y();
    if (!this.field_70170_p.field_72995_K) {
      BlockAlchemist blockAlchemist;
      Block toset = null;
      switch (this.g.getName().toLowerCase()) {
        case "green":
          blockAlchemist = BlocksRegister.GREEN_GLUEBALL_BLOCK;
          break;
        case "blue":
          blockAlchemist = BlocksRegister.BLUE_GLUEBALL_BLOCK;
          break;
        case "yellow":
          blockAlchemist = BlocksRegister.YELLOW_GLUEBALL_BLOCK;
          break;
        case "red":
          blockAlchemist = BlocksRegister.RED_GLUEBALL_BLOCK;
          break;
        case "orange":
          blockAlchemist = BlocksRegister.ORANGE_GLUEBALL_BLOCK;
          break;
        case "gray":
          blockAlchemist = BlocksRegister.GRAY_GLUEBALL_BLOCK;
          break;
        case "purple":
          blockAlchemist = BlocksRegister.PURPLE_GLUEBALL_BLOCK;
          break;
        case "green_flash":
          blockAlchemist = BlocksRegister.GREEN_FLASH_GLUEBALL_BLOCK;
          break;
        case "green_dark":
          blockAlchemist = BlocksRegister.GREEN_DARK_GLUEBALL_BLOCK;
          break;
        case "cyan":
          blockAlchemist = BlocksRegister.CYAN_GLUEBALL_BLOCK;
          break;
      } 
      int fx = MathHelper.func_76128_c(this.field_70165_t);
      int fy = MathHelper.func_76128_c(this.field_70163_u);
      int fz = MathHelper.func_76128_c(this.field_70161_v);
      Block downBlock = this.field_70170_p.func_147439_a(fx, fy - 1, fz);
      if (blockAlchemist != null && downBlock != null && !downBlock.func_149688_o().func_76224_d() && !(downBlock instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.BlockGlueball))
        if (func_85052_h() instanceof EntityPlayer) {
          this.field_70170_p.func_72900_e((Entity)this);
          boolean spawn = false;
          try {
            if (SpellFlag.flagIsLoaded) {
              Set<? extends Integer> flag = SpellFlag.getFlag((EntityPlayer)func_85052_h());
              if (flag != null)
                for (Integer spellId : flag) {
                  if (spellId.intValue() == 5 || spellId.intValue() == -1)
                    spawn = true; 
                }  
            } 
          } catch (NoClassDefFoundError noClassDefFoundError) {}
          if (this.field_70170_p.func_147439_a(fx, fy, fz).equals(Blocks.field_150350_a) && !spawn)
            this.field_70170_p.func_147465_d(fx, fy, fz, (Block)blockAlchemist, 1, 3); 
        }  
    } 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(this.g.ordinal());
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.g = EnumGlueball.values()[additionalData.readInt()]; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\entities\EntityGlueball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */