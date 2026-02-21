package fr.paladium.palamod.modules.back2future.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ArmourStandInteractMessage implements IMessage {
  public int dimID;
  
  public int standID;
  
  public Vec3 hitPos;
  
  public ArmourStandInteractMessage() {}
  
  public ArmourStandInteractMessage(int dimID, EntityArmourStand stand, EntityPlayer player) {
    this.dimID = dimID;
    this.standID = stand.func_145782_y();
    MovingObjectPosition hit = (Minecraft.func_71410_x()).field_71476_x;
    this.hitPos = Vec3.func_72443_a(hit.field_72307_f.field_72450_a - stand.field_70165_t, hit.field_72307_f.field_72448_b - stand.field_70163_u, hit.field_72307_f.field_72449_c - stand.field_70161_v);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.dimID = buf.readInt();
    this.standID = buf.readInt();
    this.hitPos = Vec3.func_72443_a(buf.readDouble(), buf.readDouble(), buf.readDouble());
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.dimID);
    buf.writeInt(this.standID);
    buf.writeDouble(this.hitPos.field_72450_a);
    buf.writeDouble(this.hitPos.field_72448_b);
    buf.writeDouble(this.hitPos.field_72449_c);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\network\ArmourStandInteractMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */