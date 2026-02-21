package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySuperBeacon extends TileEntity implements IEntityAdditionalSpawnData {
  private String owner;
  
  private int food;
  
  private int tick;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public int getFood() {
    return this.food;
  }
  
  public void setFood(int food) {
    this.food = food;
  }
  
  public TileEntitySuperBeacon(String owner, int food) {
    this.owner = owner;
    this.food = food;
  }
  
  public TileEntitySuperBeacon() {}
  
  public void func_145845_h() {
    if (this.owner != null && !this.owner.isEmpty() && !this.field_145850_b.field_72995_K) {
      EntityPlayer player = this.field_145850_b.func_72924_a(this.owner);
      if (player != null)
        if (isValid() && player.func_70011_f(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 16.0D) {
          player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 400, 2));
          EventUtils.spawnParticle(this.field_145850_b, "witchMagic", this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 1, 0.05D);
        } else if (this.food > 0 && player.func_70011_f(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 16.0D) {
          player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 400, 2));
          player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 400, 2));
          EventUtils.spawnParticle(this.field_145850_b, "witchMagic", this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 1, 0.05D);
          this.tick++;
          if (this.tick % 200 == 0)
            this.food--; 
        }  
    } 
    this.tick++;
    super.func_145845_h();
  }
  
  public boolean isValid() {
    int ox;
    for (ox = -3; ox < 4; ox++) {
      for (int oz = -3; oz < 4; oz++) {
        if (!isValidBlock(this.field_145851_c + ox, this.field_145848_d - 3, this.field_145849_e + oz))
          return false; 
      } 
    } 
    for (ox = -2; ox < 3; ox++) {
      for (int oz = -2; oz < 3; oz++) {
        if (!isValidBlock(this.field_145851_c + ox, this.field_145848_d - 2, this.field_145849_e + oz))
          return false; 
      } 
    } 
    for (ox = -1; ox < 2; ox++) {
      for (int oz = -1; oz < 2; oz++) {
        if (!isValidBlock(this.field_145851_c + ox, this.field_145848_d - 1, this.field_145849_e + oz))
          return false; 
      } 
    } 
    return true;
  }
  
  private boolean isValidBlock(int x, int y, int z) {
    Block b = this.field_145850_b.func_147439_a(x, y, z);
    return (b == Blocks.field_150340_R || b == Blocks.field_150339_S || b == Blocks.field_150484_ah);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.owner != null && !this.owner.isEmpty())
      compound.func_74778_a("owner", this.owner); 
    super.func_145841_b(compound);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null && !this.owner.isEmpty())
      ByteBufUtils.writeUTF8String(buffer, this.owner); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.owner = ByteBufUtils.readUTF8String(additionalData); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntitySuperBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */