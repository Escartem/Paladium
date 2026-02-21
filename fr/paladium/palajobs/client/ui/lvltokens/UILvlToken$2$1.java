package fr.paladium.palajobs.client.ui.lvltokens;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenCallback;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.packets.client.CSPacketUseLvlToken;

class null implements TweenCallback {
  public void onEvent(int arg0, BaseTween arg1) {
    PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketUseLvlToken(UILvlToken.access$000(this.this$1.this$0), lvlToken.uuid));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\UILvlToken$2$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */