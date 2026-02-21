package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.factions.core.utils.ControlResult;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

class null extends ControlResult<Boolean> {
  public void onCallback() {
    MonthlyUtils.sendMessage((EntityPlayer)forgePlayer, new String[] { "&cJ’en avais besoin pour piller le bastion de Salazar ! (2500$)", "&6Je ne suis pas sûr que ton chef de faction apprécie que tu voles dans son coffre ! " });
    SoundUtils.SKELETON_HURT.playSound(forgePlayer, x, y, z, 1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\FelonEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */