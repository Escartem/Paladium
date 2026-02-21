package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.gui.may.UICustomDialog;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class PacketOpenCustomDialogGui implements IMessage {
  private CustomDialog dialog;
  
  public PacketOpenCustomDialogGui() {}
  
  public PacketOpenCustomDialogGui(CustomDialog dialog) {
    this.dialog = dialog;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.dialog.getTitle());
    ByteBufUtils.writeUTF8String(buf, this.dialog.getText());
    buf.writeBoolean(this.dialog.isCloseGui());
    if (this.dialog.hasOptions()) {
      buf.writeInt(1);
      int optionNb = this.dialog.getAnswerOptions().size();
      buf.writeInt(optionNb);
      for (int i = 0; i < optionNb; i++)
        ByteBufUtils.writeUTF8String(buf, this.dialog.getAnswerOptions().get(i)); 
    } 
    if (this.dialog.hasExpression()) {
      buf.writeInt(2);
      ByteBufUtils.writeUTF8String(buf, this.dialog.getExpressionTexture());
      buf.writeFloat(this.dialog.getExpressionX().floatValue());
      buf.writeFloat(this.dialog.getExpressionY().floatValue());
      buf.writeFloat(this.dialog.getExpressionSizeX().floatValue());
      buf.writeFloat(this.dialog.getExpressionSizeY().floatValue());
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    this.dialog = new CustomDialog(ByteBufUtils.readUTF8String(buf), ByteBufUtils.readUTF8String(buf));
    boolean closeGui = buf.readBoolean();
    if (closeGui)
      this.dialog.setCloseGui(); 
    while (buf.isReadable()) {
      int index = buf.readInt();
      if (index == 1) {
        int optionNb = buf.readInt();
        for (int i = 0; i < optionNb; i++)
          this.dialog.addOption(ByteBufUtils.readUTF8String(buf)); 
        continue;
      } 
      if (index == 2)
        this.dialog.setExpression(ByteBufUtils.readUTF8String(buf), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat()); 
    } 
  }
  
  public static class Handler implements IMessageHandler<PacketOpenCustomDialogGui, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketOpenCustomDialogGui message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UICustomDialog(message.dialog));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\PacketOpenCustomDialogGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */