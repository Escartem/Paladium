package fr.paladium.palapass.common.network.packet.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SCPacketPalapassCopyText extends ForgePacket {
  @PacketData
  private String message;
  
  public SCPacketPalapassCopyText() {}
  
  public SCPacketPalapassCopyText(String message) {
    this.message = message;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    String translatedText = PalapassTranslateEnum.TEXT_SAVED_CLIPBOARD.textOrDefault("§8[§6Paladium§8] §eText sauvegardé dans votre presse papier:");
    (Minecraft.func_71410_x()).field_71439_g.func_146105_b((IChatComponent)new ChatComponentText(translatedText));
    (Minecraft.func_71410_x()).field_71439_g.func_146105_b((IChatComponent)new ChatComponentText(this.message));
    Toolkit.getDefaultToolkit()
      .getSystemClipboard()
      .setContents(new StringSelection(this.message), null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\client\SCPacketPalapassCopyText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */