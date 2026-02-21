package fr.paladium.palamod.modules.end.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.worldguardbridge.common.dto.flag.WGFlag;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.server.event.FlagRegisteringEvent;

public class EventEndWorldGuardListener {
  @SubscribeEvent
  public void onRegistering(FlagRegisteringEvent.Post event) {
    PEnd.EVENT_END_FLAG.register((WGFlag)new StateFlag(PEnd.EVENT_END_FLAG.getFlagName(), StateFlag.EnumStateFlag.DENY, WGFlag.WGFlagGroup.ALL));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\event\EventEndWorldGuardListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */