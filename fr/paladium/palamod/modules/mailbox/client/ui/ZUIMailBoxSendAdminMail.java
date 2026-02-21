package fr.paladium.palamod.modules.mailbox.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.betternei.api.data.BNEIOption;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.ui.container.AdminNewMessageContainer;
import fr.paladium.palamod.modules.mailbox.client.ui.container.AdminSettingsContainer;
import fr.paladium.palamod.modules.mailbox.common.containers.MailboxSendAdminMailContainer;
import fr.paladium.palamod.modules.mailbox.common.containers.MailboxSendAdminMailInventory;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerAdminMessagePacket;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

@BNEIOption(active = false)
@UIData(backgroundColor = "#3D6CE7", container = true)
public class ZUIMailBoxSendAdminMail extends UI {
  private final StringSignal objectSignal = new StringSignal("");
  
  public StringSignal getObjectSignal() {
    return this.objectSignal;
  }
  
  private final StringSignal recipientSignal = new StringSignal("");
  
  public StringSignal getRecipientSignal() {
    return this.recipientSignal;
  }
  
  private final StringSignal contentSignal = new StringSignal("");
  
  public StringSignal getContentSignal() {
    return this.contentSignal;
  }
  
  private final IntegerSignal expirationDateSignal = new IntegerSignal(-1);
  
  public IntegerSignal getExpirationDateSignal() {
    return this.expirationDateSignal;
  }
  
  private final Signal<EnumMailType> selectedType = new Signal(EnumMailType.MESSAGE);
  
  public Signal<EnumMailType> getSelectedType() {
    return this.selectedType;
  }
  
  private final BooleanSignal everyoneSignal = new BooleanSignal(false);
  
  public BooleanSignal getEveryoneSignal() {
    return this.everyoneSignal;
  }
  
  private final BooleanSignal paladiumSignal = new BooleanSignal(false);
  
  public BooleanSignal getPaladiumSignal() {
    return this.paladiumSignal;
  }
  
  private final BooleanSignal containItems = new BooleanSignal(false);
  
  private MailboxSendAdminMailInventory inventory;
  
  private AdminNewMessageContainer adminNewMessageContainer;
  
  public BooleanSignal getContainItems() {
    return this.containItems;
  }
  
  public MailboxSendAdminMailInventory getInventory() {
    return this.inventory;
  }
  
  public AdminNewMessageContainer getAdminNewMessageContainer() {
    return this.adminNewMessageContainer;
  }
  
  public ZUIMailBoxSendAdminMail(MailboxSendAdminMailInventory inventory) {
    super((Container)new MailboxSendAdminMailContainer((IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by, (IInventory)inventory));
    this.inventory = inventory;
    this.containItems.subscribe(value -> {
          if (EnumMailType.MESSAGE.equals(this.selectedType.getOrDefault()) && value.booleanValue()) {
            this.selectedType.set(EnumMailType.GIFT);
          } else if (!EnumMailType.MESSAGE.equals(this.selectedType.getOrDefault()) && !value.booleanValue()) {
            this.selectedType.set(EnumMailType.MESSAGE);
          } 
          return true;
        });
  }
  
  public void init() {
    containerBounds(0.0D, 0.0D, 1920.0D, 1080.0D);
    List<Slot> slots = (getContainer()).field_75151_b;
    ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .resource(ZUIMailBox.BACKGROUND)
      .body(body -> {
          TextNode.create(68.0D, 42.0D).text(Text.create("mailbox", PaladiumText.HEADER.copy().color(Color.WHITE).shadow(Color.LIGHTGRAY))).attach(body);
          CloseButtonNode.create(1843.0D, 42.0D).onClick(()).attach(body);
          AdminSettingsContainer.create(68.0D, 193.0D, 621.0D, 820.0D).color(Color.WHITE.copyAlpha(0.15F)).attach(body);
          this.adminNewMessageContainer = (AdminNewMessageContainer)AdminNewMessageContainer.create(778.0D, 193.0D, 1100.0D, 820.0D).color(Color.WHITE.copyAlpha(0.15F)).attach(body);
          slots.forEach(());
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Boolean)this.containItems.getOrDefault()).booleanValue() != this.inventory.containsItem())
      this.containItems.set(Boolean.valueOf(this.inventory.containsItem())); 
  }
  
  public void sendMessage() {
    String object = ((String)this.objectSignal.getOrDefault()).trim();
    String recipient = ((String)this.recipientSignal.getOrDefault()).trim();
    String content = ((String)this.contentSignal.getOrDefault()).trim();
    String type = ((EnumMailType)this.selectedType.getOrDefault()).toString();
    boolean everyone = ((Boolean)this.everyoneSignal.getOrDefault()).booleanValue();
    boolean paladium = ((Boolean)this.paladiumSignal.getOrDefault()).booleanValue();
    int expiration = ((Integer)this.expirationDateSignal.getOrDefault()).intValue();
    if (object.length() < 3 || recipient.length() < 3 || content.length() < 3)
      return; 
    PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerAdminMessagePacket(object, content, recipient, paladium, everyone, expiration, type));
  }
  
  public TextFieldNode getRecipientField() {
    return this.adminNewMessageContainer.getRecipientNode();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\ZUIMailBoxSendAdminMail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */