package fr.paladium.palamod.modules.mailbox.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;

public class Handler implements IMessageHandler<MailboxClientGetEmailsPacket, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(MailboxClientGetEmailsPacket message, MessageContext ctx) {
    if (ZUI.isOpen(ZUIMailBox.class)) {
      ZUIMailBox ui = (ZUIMailBox)ZUI.getUI(ZUIMailBox.class);
      ui.setMails(message.getMailList());
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\client\MailboxClientGetEmailsPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */