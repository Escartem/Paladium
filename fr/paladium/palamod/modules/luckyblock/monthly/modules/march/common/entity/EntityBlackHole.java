package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.world.World;

public class EntityBlackHole extends Entity {
  private EntityPlayerMP player;
  
  private int tickCpt = 0;
  
  public EntityBlackHole(World world) {
    super(world);
  }
  
  public EntityBlackHole(World world, EntityPlayerMP player) {
    super(world);
    this.player = player;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70170_p.field_72995_K) {
      this.tickCpt++;
      if (this.player == null || this.player.field_70128_L) {
        func_70106_y();
        return;
      } 
      if (this.tickCpt / 25 > 10) {
        func_70106_y();
        return;
      } 
      if (this.tickCpt >= 100) {
        double distX = this.field_70165_t - this.player.field_70165_t;
        double distY = this.field_70163_u - this.player.field_70163_u;
        double distZ = this.field_70161_v - this.player.field_70161_v;
        double distance = Math.sqrt(distX * distX + distY * distY + distZ * distZ);
        if (distance < 0.5D) {
          for (int i = 1; i <= 20; i++) {
            int slotA = this.field_70146_Z.nextInt(36);
            int slotB = this.field_70146_Z.nextInt(36);
            ItemStack itemA = this.player.field_71071_by.func_70301_a(slotA);
            ItemStack itemB = this.player.field_71071_by.func_70301_a(slotB);
            this.player.field_71071_by.func_70299_a(slotA, itemB);
            this.player.field_71071_by.func_70299_a(slotB, itemA);
          } 
          func_70106_y();
          DoubleLocation spawnLocation = new DoubleLocation((this.player.field_70170_p.func_72861_E()).field_71574_a, (this.player.field_70170_p.func_72861_E()).field_71572_b, (this.player.field_70170_p.func_72861_E()).field_71573_c);
          spawnLocation.teleportServer((EntityPlayer)this.player);
        } else {
          double strength = 0.15D / distance;
          this.player.field_70159_w += strength * distX;
          this.player.field_70181_x += strength * distY;
          this.player.field_70179_y += strength * distZ;
          this.player.field_70160_al = true;
          this.player.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)this.player));
        } 
      } 
    } 
  }
  
  protected void func_70088_a() {}
  
  protected void func_70037_a(NBTTagCompound p_70037_1_) {}
  
  protected void func_70014_b(NBTTagCompound p_70014_1_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\entity\EntityBlackHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */