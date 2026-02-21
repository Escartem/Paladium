package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<PacketRemoveMoney, IMessage> {
  private double withdraw;
  
  public IMessage onMessage(PacketRemoveMoney message, MessageContext ctx) {
    try {
      PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get((EntityPlayer)(ctx.getServerHandler()).field_147369_b);
      if (ctx.side == Side.SERVER && properties.isHasRemoveMoneyNow()) {
        properties.setHasRemoveMoneyNow(false);
        removeMoney((EntityPlayer)(ctx.getServerHandler()).field_147369_b);
      } else if (ctx.side == Side.CLIENT) {
        properties.setHasRemoveMoneyNow(false);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  private void removeMoney(EntityPlayer player) {
    try {
      double money = CresusManager.getInstance().getBalance(player.func_110124_au());
      if (money >= 5000.0D) {
        this.withdraw = 5000.0D;
      } else {
        this.withdraw = money + 1.0D;
      } 
      CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), this.withdraw, "LuckyBlock -> PacketRemoveMoney (" + this.withdraw + ")", new CresusCallback<CresusResponse>() {
            public void onSuccess(CresusResponse response) {}
            
            public void onFail(CresusResponse response, Throwable error) {}
          });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketRemoveMoney$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */