package fr.paladium.palawither.common.network.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.item.target.ItemWitherTargetPoint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class CSPacketWitherTargetLaserSystem extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  public CSPacketWitherTargetLaserSystem() {}
  
  public CSPacketWitherTargetLaserSystem(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (this.y < 1 || this.y > 300)
      return; 
    double distance = player.func_70011_f(this.x, this.y, this.z);
    if (distance > 1500.0D || distance < 1.0D)
      return; 
    ItemStack stack = player.func_70694_bm();
    if (stack == null || stack.func_77973_b() != ItemsRegister.WITHER_TARGET_LASER_SYSTEM)
      return; 
    ItemStack item = ItemWitherTargetPoint.create(new DoubleLocation(this.x, Math.min(this.y, 256), this.z));
    if (!player.field_71071_by.func_70441_a(item))
      player.func_71019_a(item, false); 
    if (!player.field_71075_bZ.field_75098_d) {
      stack.func_77972_a(1, (EntityLivingBase)player);
      if (stack.field_77994_a <= 0)
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
    } 
    player.field_71069_bz.func_75142_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\network\item\CSPacketWitherTargetLaserSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */