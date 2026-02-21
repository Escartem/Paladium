package fr.paladium.palavoicechat.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palavoicechat.common.wgflag.PalaVoiceFlags;
import fr.paladium.worldguardbridge.common.dto.flag.WGFlag;
import fr.paladium.worldguardbridge.common.dto.flag.impl.EnumFlag;
import fr.paladium.worldguardbridge.server.event.FlagRegisteringEvent;

public class FlagRegisterListener {
  @SubscribeEvent
  @SideOnly(Side.SERVER)
  public void onPostFlagRegister(FlagRegisteringEvent.Post event) {
    PalaVoiceFlags.VOICE.register((WGFlag)new EnumFlag(PalaVoiceFlags.VOICE.getFlagName(), VoiceState.ENABLED, WGFlag.WGFlagGroup.ALL));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\listener\FlagRegisterListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */