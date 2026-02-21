package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.back2future.core.utils.math.Vector3d;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.bukkit.util.Vector;

public class EntityGrappin extends EntityArrow implements IEntityAdditionalSpawnData {
  private int field_145791_d = -1;
  
  private int field_145792_e = -1;
  
  private int field_145789_f = -1;
  
  private boolean inGround;
  
  public EntityGrappin(World world) {
    super(world);
  }
  
  public EntityGrappin(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
    super(p_i1756_1_);
    this.field_70155_l = 10.0D;
    this.field_70250_c = (Entity)p_i1756_2_;
    if (p_i1756_2_ instanceof EntityPlayer)
      this.field_70251_a = 1; 
    func_70105_a(0.5F, 0.5F);
    func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, p_i1756_2_.field_70177_z, p_i1756_2_.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    this
      .field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
    this
      .field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
    this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5F, 1.0F);
  }
  
  public void func_70106_y() {
    if (this.field_70250_c instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)this.field_70250_c;
      player.field_70143_R = 0.0F;
      player.field_71075_bZ.field_75101_c = false;
      player.func_71016_p();
      if (player instanceof EntityPlayerMP)
        ServerManager.removeFreeze((EntityPlayerMP)player); 
    } 
    super.func_70106_y();
  }
  
  public void func_70071_h_() {
    if (!func_70089_S() && 
      this.field_70250_c instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)this.field_70250_c;
      player.field_70143_R = 0.0F;
      player.field_71075_bZ.field_75101_c = false;
      player.func_71016_p();
      if (player instanceof EntityPlayerMP)
        ServerManager.removeFreeze((EntityPlayerMP)player); 
    } 
    this.field_145791_d = (int)(this.field_70165_t + this.field_70159_w);
    this.field_145792_e = (int)(this.field_70163_u + this.field_70181_x);
    this.field_145789_f = (int)(this.field_70161_v + this.field_70179_y);
    func_70030_z();
    if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this
        .field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
    } 
    Block block = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
    if (block != Blocks.field_150350_a)
      this.inGround = true; 
    if (this.field_70249_b > 0)
      this.field_70249_b--; 
    if (this.inGround) {
      if (this.field_70250_c != null) {
        double distance = Math.sqrt((new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v)).distanceSq(this.field_70250_c.field_70165_t, this.field_70250_c.field_70163_u, this.field_70250_c.field_70161_v));
        if (!this.field_70170_p.field_72995_K) {
          Vector p1 = new Vector(this.field_70165_t, this.field_70163_u + 2.0D, this.field_70161_v);
          Vector p2 = new Vector(this.field_70250_c.field_70165_t, this.field_70250_c.field_70163_u, this.field_70250_c.field_70161_v);
          Vector vector = p1.clone().subtract(p2).normalize().multiply(0.1D);
          double length = 0.0D;
          for (; length < distance; p2.add(vector)) {
            this.field_70250_c
              
              .field_70159_w = (p2.getX() - this.field_70250_c.field_70165_t > 0.0D) ? ((p2.getX() - this.field_70250_c.field_70165_t > 0.5D) ? 0.5D : (p2.getX() - this.field_70250_c.field_70165_t)) : ((p2.getX() - this.field_70250_c.field_70165_t < -0.5D) ? -0.5D : (p2.getX() - this.field_70250_c.field_70165_t));
            this.field_70250_c
              
              .field_70181_x = (p2.getY() - this.field_70250_c.field_70163_u > 0.0D) ? ((p2.getY() - this.field_70250_c.field_70163_u > 0.5D) ? 0.5D : (p2.getY() - this.field_70250_c.field_70163_u)) : ((p2.getY() - this.field_70250_c.field_70163_u < -0.5D) ? -0.5D : (p2.getY() - this.field_70250_c.field_70163_u));
            this.field_70250_c
              
              .field_70179_y = (p2.getZ() - this.field_70250_c.field_70161_v > 0.0D) ? ((p2.getZ() - this.field_70250_c.field_70161_v > 0.5D) ? 0.5D : (p2.getZ() - this.field_70250_c.field_70161_v)) : ((p2.getZ() - this.field_70250_c.field_70161_v < -0.5D) ? -0.5D : (p2.getZ() - this.field_70250_c.field_70161_v));
            if (this.field_70250_c instanceof EntityPlayer) {
              EntityPlayer player = (EntityPlayer)this.field_70250_c;
              player.field_70143_R = 0.0F;
              player.field_71075_bZ.field_75101_c = true;
              player.func_71016_p();
              if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP)player).field_71135_a
                  .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
                ServerManager.addFreeze((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
              } 
            } 
            length += 2.0D;
          } 
          if (this.field_70250_c instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)this.field_70250_c;
            player.field_70143_R = 0.0F;
            player.field_71075_bZ.field_75101_c = false;
            player.func_71016_p();
            if (player instanceof EntityPlayerMP)
              ServerManager.removeFreeze((EntityPlayerMP)player); 
          } 
          if (distance < 2.0D || this.field_70173_aa > 200)
            func_70106_y(); 
        } else if (distance < 2.0D || this.field_70173_aa > 200) {
          func_70106_y();
        } 
      } else {
        func_70106_y();
      } 
    } else {
      this.field_70165_t += this.field_70159_w;
      this.field_70163_u += this.field_70181_x;
      this.field_70161_v += this.field_70179_y;
      float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
      this
        .field_70125_A = (float)(Math.atan2(this.field_70181_x, f2) * 180.0D / Math.PI);
      for (; this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
      while (this.field_70125_A - this.field_70127_C >= 180.0F)
        this.field_70127_C += 360.0F; 
      while (this.field_70177_z - this.field_70126_B < -180.0F)
        this.field_70126_B -= 360.0F; 
      while (this.field_70177_z - this.field_70126_B >= 180.0F)
        this.field_70126_B += 360.0F; 
      this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
      this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
      float f3 = 0.99F;
      float f1 = 0.05F;
      if (func_70090_H()) {
        for (int l = 0; l < 4; l++) {
          float f4 = 0.25F;
          this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * 0.25D, this.field_70163_u - this.field_70181_x * 0.25D, this.field_70161_v - this.field_70179_y * 0.25D, this.field_70159_w, this.field_70181_x, this.field_70179_y);
        } 
        f3 = 0.8F;
      } 
      if (func_70026_G())
        func_70066_B(); 
      this.field_70159_w *= f3;
      this.field_70181_x *= f3;
      this.field_70179_y *= f3;
      this.field_70181_x -= 0.05000000074505806D;
      func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      func_145775_I();
    } 
    if (this.field_70250_c != null) {
      double distance = Math.sqrt((new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v)).distanceSq(this.field_70250_c.field_70165_t, this.field_70250_c.field_70163_u, this.field_70250_c.field_70161_v));
      if (!this.field_70170_p.field_72995_K) {
        Vector p1 = new Vector(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        Vector p2 = new Vector(this.field_70250_c.field_70165_t, this.field_70250_c.field_70163_u, this.field_70250_c.field_70161_v);
        Vector vector = p2.clone().subtract(p1).normalize().multiply(1);
        double length = 0.0D;
        for (; length < distance; p1.add(vector)) {
          EventUtils.spawnParticle(this.field_70170_p, "flame", p1.getX(), p1.getY(), p1.getZ(), 1, 0.0D);
          length++;
        } 
      } else {
        Vector3d p1 = new Vector3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        Vector3d p2 = new Vector3d(this.field_70250_c.field_70165_t, this.field_70250_c.field_70163_u, this.field_70250_c.field_70161_v);
        Vector3d vector = p2.subtract(p1).normalize().multiply(1.0D, 1.0D, 1.0D);
        double length = 0.0D;
        for (; length < distance; p1.add(vector)) {
          EventUtils.spawnParticle(this.field_70170_p, "flame", p1.x, p1.y, p1.z, 1, 0.0D);
          length++;
        } 
      } 
    } 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.field_70250_c != null)
      ByteBufUtils.writeUTF8String(buffer, this.field_70250_c.func_70005_c_()); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      String name = ByteBufUtils.readUTF8String(additionalData);
      for (Object o : this.field_70170_p.field_73010_i) {
        if (o instanceof EntityPlayer) {
          EntityPlayer p = (EntityPlayer)o;
          if (p.func_70005_c_().equals(name))
            this.field_70250_c = (Entity)p; 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityGrappin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */