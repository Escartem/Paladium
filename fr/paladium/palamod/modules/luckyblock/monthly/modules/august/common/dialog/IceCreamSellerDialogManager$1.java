package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.IceCreamSellerData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse cresusResponse) {
    if (data.getSellAmount() >= 5)
      return; 
    World world = player.field_70170_p;
    EntityNPC npc = (EntityNPC)entity;
    ItemUtils.spawnItemAtEntity((Entity)npc, IceCreamSellerDialogManager.access$000(IceCreamSellerDialogManager.this, data));
    data.sell();
    if (data.getSellAmount() >= 5)
      npc.func_70106_y(); 
  }
  
  public void onFail(CresusResponse cresusResponse, Throwable throwable) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour acheter une glace !" });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\dialog\IceCreamSellerDialogManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */