package fr.paladium.palavoicechat.common.wgflag;

import fr.paladium.palavoicechat.common.listener.VoiceState;
import fr.paladium.worldguardbridge.common.dto.flag.impl.EnumFlag;
import fr.paladium.worldguardbridge.common.dto.flag.utils.FlagDefinition;

public class PalaVoiceFlags {
  public static FlagDefinition<EnumFlag<VoiceState>> VOICE = new FlagDefinition("voice", EnumFlag.class);
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\wgflag\PalaVoiceFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */