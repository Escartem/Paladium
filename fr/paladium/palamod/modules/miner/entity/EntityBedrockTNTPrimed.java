package fr.paladium.palamod.modules.miner.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityBedrockTNTPrimed extends EntityTNTPrimed implements IEntityAdditionalSpawnData {
  public EntityBedrockTNTPrimed(World world) {
    super(world);
  }
  
  public EntityBedrockTNTPrimed(World world, double x, double y, double z, EntityLivingBase placer) {
    super(world, x, y, z, placer);
  }
  
  public void func_70071_h_() {
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    this.field_70181_x -= 0.03999999910593033D;
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    this.field_70159_w *= 0.9800000190734863D;
    this.field_70181_x *= 0.9800000190734863D;
    this.field_70179_y *= 0.9800000190734863D;
    if (this.field_70122_E) {
      this.field_70159_w *= 0.699999988079071D;
      this.field_70179_y *= 0.699999988079071D;
      this.field_70181_x *= -0.5D;
    } 
    if (this.field_70516_a-- <= 0) {
      func_70106_y();
      if (!this.field_70170_p.field_72995_K)
        explode(); 
    } else {
      this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, 0.0D, 0.0D, 0.0D);
    } 
  }
  
  private void explode() {
    int offset = 3;
    if (func_94083_c() == null)
      return; 
    (new Thread(() -> {
          for (int oy = 0; oy > -3; oy--) {
            for (int oz = -3 - this.field_70170_p.field_73012_v.nextInt(2); oz < 3 + this.field_70170_p.field_73012_v.nextInt(2); oz++) {
              for (int ox = -3 - this.field_70170_p.field_73012_v.nextInt(2); ox < 3 + this.field_70170_p.field_73012_v.nextInt(2); ox++) {
                int x = (int)(this.field_70165_t + ox);
                int y = (int)(this.field_70163_u + oy) + this.field_70170_p.field_73012_v.nextInt(2);
                int z = (int)(this.field_70161_v + oz);
                BlockSnapshot snapshot = new BlockSnapshot(this.field_70170_p, x, y, z, Blocks.field_150347_e, 0);
                if (!ForgeEventFactory.onPlayerBlockPlace((EntityPlayer)func_94083_c(), snapshot, ForgeDirection.getOrientation(0)).isCanceled()) {
                  Block b = this.field_70170_p.func_147439_a(x, y, z);
                  float resistance = b.func_149638_a((Entity)func_94083_c());
                  if ((30.0F > resistance || b == Blocks.field_150357_h) && y > 0)
                    this.field_70170_p.func_147468_f(x, y, z); 
                } 
              } 
            } 
          } 
        })).start();
    this.field_70170_p.func_147468_f((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeByte(this.field_70516_a);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      this.field_70516_a = additionalData.readByte();
    } else {
      this.field_70516_a = 20;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\entity\EntityBedrockTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */