package fr.paladium.palavoicechat.common.wgflag;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palavoicechat.common.eep.VoiceChatExtendedEntityProperties;
import fr.paladium.palavoicechat.common.listener.VoiceState;
import fr.paladium.palavoicechat.server.manager.MuteCacheManager;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import fr.paladium.worldguardbridge.common.dto.flag.impl.EnumFlag;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import fr.paladium.worldguardbridge.common.manager.utils.WGRegionList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class VoiceChatFlagsUtils {
  public static boolean canUserSpeak(EntityPlayer player) {
    boolean canSpeak;
    if (player == null || (FMLCommonHandler.instance().getSide() == Side.CLIENT && VoiceChatExtendedEntityProperties.me() != null && VoiceChatExtendedEntityProperties.me().isMuted()))
      return false; 
    if (FMLCommonHandler.instance().getSide() == Side.SERVER && MuteCacheManager.getInstance().isPlayerMuted(player))
      return false; 
    WGRegionList regionList = WGManager.inst().getRegionListAt(player);
    EnumFlag<VoiceState> voiceFlag = regionList.getFlag(PalaVoiceFlags.VOICE).orElse(null);
    VoiceState state = (voiceFlag != null) ? VoiceState.fromOrdinal(((Enum)voiceFlag.getValue()).ordinal()) : VoiceState.ENABLED;
    if (state == VoiceState.ENABLED) {
      canSpeak = true;
    } else if (state == VoiceState.ONLYSTAFF) {
      canSpeak = PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "palavoicechat.bypass");
    } else if (state == VoiceState.DISABLED) {
      canSpeak = false;
    } else {
      canSpeak = true;
    } 
    return canSpeak;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\wgflag\VoiceChatFlagsUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */