package fr.paladium.palamod.modules.mailbox.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.ui.container.MailDisplayContainer;
import fr.paladium.palamod.modules.mailbox.client.ui.container.MailListContainer;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerGetEmailsPacket;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.text.SimpleDateFormat;
import java.util.List;
import net.minecraft.client.Minecraft;

@UIData(backgroundColor = "#3D6CE7")
public class ZUIMailBox extends UI {
  public static final Resource BACKGROUND = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/fixed_background.png"));
  
  public static final Resource MESSAGE_BADGE = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/message_badge.png"));
  
  public static final Resource WHITE_ENVELOPE_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/white_envelope_icon.png"));
  
  public static final Resource TRASH_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/trash_icon.png"));
  
  public static final Resource PLUS_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/plus_icon.png"));
  
  public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm");
  
  private final ListSignal<Mail> mailSignal = new ListSignal();
  
  public ListSignal<Mail> getMailSignal() {
    return this.mailSignal;
  }
  
  private final Signal<Mail> selectedMail = new Signal(null);
  
  public Signal<Mail> getSelectedMail() {
    return this.selectedMail;
  }
  
  private final Signal<UIAction> typeSignal = new Signal(UIAction.NONE);
  
  public Signal<UIAction> getTypeSignal() {
    return this.typeSignal;
  }
  
  public ZUIMailBox() {
    PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerGetEmailsPacket((Minecraft.func_71410_x()).field_71439_g.func_110124_au().toString(), ""));
  }
  
  public void init() {
    ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .resource(BACKGROUND)
      .body(body -> {
          TextNode.create(68.0D, 42.0D).text(Text.create("mailbox", PaladiumText.HEADER.copy().color(Color.WHITE).shadow(Color.LIGHTGRAY))).attach(body);
          CloseButtonNode.create(1843.0D, 42.0D).onClick(()).attach(body);
          MailListContainer.create(68.0D, 193.0D, 621.0D, 820.0D).color(Color.WHITE.copyAlpha(0.15F)).attach(body);
          MailDisplayContainer.create(778.0D, 193.0D, 1100.0D, 820.0D).color(Color.WHITE.copyAlpha(0.15F)).watch(this.selectedMail).watch(this.typeSignal).attach(body);
        }).attach(this);
  }
  
  public void setMails(List<Mail> mails) {
    this.mailSignal.set(mails);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\ZUIMailBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */