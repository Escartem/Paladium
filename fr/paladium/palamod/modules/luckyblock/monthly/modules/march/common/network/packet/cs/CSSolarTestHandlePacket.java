package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityTelescope;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DistanceUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CSSolarTestHandlePacket implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private boolean win;
  
  public CSSolarTestHandlePacket() {}
  
  public CSSolarTestHandlePacket(int x, int y, int z, boolean win) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.win = win;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.win = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeBoolean(this.win);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSSolarTestHandlePacket, IMessage> {
    public IMessage onMessage(CSSolarTestHandlePacket message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (!DistanceUtils.isNearTargetLocation((Entity)player, message.x, message.y, message.z))
        return null; 
      if (!message.win) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi le test" });
        MonthlyUtils.playSound(player, "soft_fail");
        return null;
      } 
      World world = player.field_70170_p;
      if (!world.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntity tile = world.func_147438_o(message.x, message.y, message.z);
      if (!(tile instanceof TileEntityTelescope))
        return null; 
      TileEntityTelescope telescope = (TileEntityTelescope)tile;
      if (telescope.isRewardTaken()) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as réussi le test !" });
      } else {
        telescope.setRewardTaken(true);
        telescope.func_70296_d();
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as réussi le test et tu obtiens 1 amulette de feu" });
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.MAGMA_AMULET, 1));
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\cs\CSSolarTestHandlePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */