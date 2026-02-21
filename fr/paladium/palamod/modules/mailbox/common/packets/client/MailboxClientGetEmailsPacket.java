package fr.paladium.palamod.modules.mailbox.common.packets.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class MailboxClientGetEmailsPacket implements IMessage {
  public void setMailList(List<Mail> mailList) {
    this.mailList = mailList;
  }
  
  public void setFilter(String filter) {
    this.filter = filter;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxClientGetEmailsPacket))
      return false; 
    MailboxClientGetEmailsPacket other = (MailboxClientGetEmailsPacket)o;
    if (!other.canEqual(this))
      return false; 
    Object<Mail> this$mailList = (Object<Mail>)getMailList(), other$mailList = (Object<Mail>)other.getMailList();
    if ((this$mailList == null) ? (other$mailList != null) : !this$mailList.equals(other$mailList))
      return false; 
    Object this$filter = getFilter(), other$filter = other.getFilter();
    return !((this$filter == null) ? (other$filter != null) : !this$filter.equals(other$filter));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxClientGetEmailsPacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<Mail> $mailList = (Object<Mail>)getMailList();
    result = result * 59 + (($mailList == null) ? 43 : $mailList.hashCode());
    Object $filter = getFilter();
    return result * 59 + (($filter == null) ? 43 : $filter.hashCode());
  }
  
  public String toString() {
    return "MailboxClientGetEmailsPacket(mailList=" + getMailList() + ", filter=" + getFilter() + ")";
  }
  
  public MailboxClientGetEmailsPacket(List<Mail> mailList, String filter) {
    this.mailList = mailList;
    this.filter = filter;
  }
  
  private List<Mail> mailList = new ArrayList<>();
  
  public List<Mail> getMailList() {
    return this.mailList;
  }
  
  private String filter = "";
  
  public String getFilter() {
    return this.filter;
  }
  
  public void fromBytes(ByteBuf buf) {
    int numberOfItem = buf.readInt();
    for (int i = 0; i < numberOfItem; i++) {
      int size = buf.readInt();
      ByteBuf buff = buf.readBytes(size);
      byte[] bytes = buff.array();
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
      InflaterInputStream iis = new InflaterInputStream(bais);
      Reader reader = new InputStreamReader(iis, Charset.forName("UTF-8"));
      String result = "";
      char[] buf1 = new char[5];
      int rlen = -1;
      try {
        while ((rlen = reader.read(buf1)) != -1)
          result = result + new String(Arrays.copyOf(buf1, rlen)); 
        this.mailList.add(Mail.deserialize(result));
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    this.filter = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.mailList.size());
    for (int i = 0; i < this.mailList.size(); i++) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DeflaterOutputStream dos = new DeflaterOutputStream(baos);
      Writer writer = new OutputStreamWriter(dos, Charset.forName("UTF-8"));
      try {
        writer.write(Mail.serialize(this.mailList.get(i)));
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
      byte[] bytes = baos.toByteArray();
      buf.writeInt(bytes.length);
      buf.writeBytes(bytes);
    } 
    ByteBufUtils.writeUTF8String(buf, this.filter);
  }
  
  public MailboxClientGetEmailsPacket() {}
  
  public static class Handler implements IMessageHandler<MailboxClientGetEmailsPacket, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MailboxClientGetEmailsPacket message, MessageContext ctx) {
      if (ZUI.isOpen(ZUIMailBox.class)) {
        ZUIMailBox ui = (ZUIMailBox)ZUI.getUI(ZUIMailBox.class);
        ui.setMails(message.getMailList());
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\client\MailboxClientGetEmailsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */