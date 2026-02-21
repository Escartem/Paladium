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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@PacketHandler
public class Handler implements IMessageHandler<CSSolarTestHandlePacket, IMessage> {
  public IMessage onMessage(CSSolarTestHandlePacket message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (!DistanceUtils.isNearTargetLocation((Entity)player, CSSolarTestHandlePacket.access$000(message), CSSolarTestHandlePacket.access$100(message), CSSolarTestHandlePacket.access$200(message)))
      return null; 
    if (!CSSolarTestHandlePacket.access$300(message)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi le test" });
      MonthlyUtils.playSound(player, "soft_fail");
      return null;
    } 
    World world = player.field_70170_p;
    if (!world.func_72899_e(CSSolarTestHandlePacket.access$000(message), CSSolarTestHandlePacket.access$100(message), CSSolarTestHandlePacket.access$200(message)))
      return null; 
    TileEntity tile = world.func_147438_o(CSSolarTestHandlePacket.access$000(message), CSSolarTestHandlePacket.access$100(message), CSSolarTestHandlePacket.access$200(message));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\cs\CSSolarTestHandlePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */