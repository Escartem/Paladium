package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.client.manager.AncientHammerEffectClientCamManager;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import net.minecraft.client.settings.KeyBinding;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketAncientHammerEffectCam, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketAncientHammerEffectCam message, MessageContext ctx) {
    KeyBinding.func_74506_a();
    AncientHammerEffectClientCamManager.inst().setCam(SCPacketAncientHammerEffectCam.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\network\SCPacketAncientHammerEffectCam$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */