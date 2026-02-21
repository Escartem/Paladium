package fr.paladium.palamod.modules.luckyblock.entity.easter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

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
      if (this.player == null || (this.tickCpt % 100 == 0 && this.field_70170_p.func_152378_a(this.player.func_110124_au()) == null)) {
        func_70106_y();
        return;
      } 
      if (this.tickCpt >= 100) {
        double distX = this.field_70165_t - this.player.field_70165_t;
        double distY = this.field_70163_u - this.player.field_70163_u;
        double distZ = this.field_70161_v - this.player.field_70161_v;
        double distance = Math.sqrt(distX * distX + distY * distY + distZ * distZ);
        if (distance < 0.5D) {
          func_70106_y();
          Player pl = Bukkit.getPlayer(this.player.func_110124_au());
          World bukkitWorld = pl.getWorld();
          Location spawnLocation = bukkitWorld.getSpawnLocation();
          pl.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\easter\EntityBlackHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */