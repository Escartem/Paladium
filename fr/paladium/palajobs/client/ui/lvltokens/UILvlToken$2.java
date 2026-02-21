package fr.paladium.palajobs.client.ui.lvltokens;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.Timeline;
import fr.paladium.lib.aurelienribon.tweenengine.Tween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenCallback;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardHiddenReward;
import fr.paladium.palajobs.core.packets.client.CSPacketUseLvlToken;
import fr.paladium.palajobs.core.tokens.LvlToken;

class null implements TweenCallback {
  public void onEvent(int arg0, BaseTween arg1) {
    hiddenReward1.setVisible(false);
    hiddenReward3.setVisible(false);
    Timeline timeline2 = ((Timeline)Timeline.createSequence().beginSequence().beginParallel().push(Tween.to(hiddenReward2, 3, 400.0F).target(0.0F)).setCallback(new TweenCallback() {
          public void onEvent(int arg0, BaseTween arg1) {
            PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketUseLvlToken(UILvlToken.access$000(UILvlToken.this), lvlToken.uuid));
          }
        })).end();
    timeline2.start(hiddenReward1.getTweenManager());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\UILvlToken$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */