package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.nodes.AnnounceButtonNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSJobOfferPacket;
import net.minecraft.util.ResourceLocation;

public class AnnouncesUI extends UI {
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/september/announces/";
  
  private static final String UI_TITLE = "LES ANNONCES DE PALADIUM";
  
  private static final ResourceLocation FIRST = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/1.png");
  
  private static final ResourceLocation SECOND = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/2.png");
  
  private static final ResourceLocation THIRD = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/3.png");
  
  private static final ResourceLocation FOURTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/4.png");
  
  private static final ResourceLocation FIFTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/5.png");
  
  private static final ResourceLocation SIXTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/announces/6.png");
  
  private static final Color COLOR_TITLE = Color.decode("#EF3926");
  
  private static final Color COLOR_TITLE_SHADOW = Color.decode("#B91C0C");
  
  public void func_73866_w_() {
    super.func_73866_w_();
    AnnounceButtonNode first = new AnnounceButtonNode(0, FIRST, width(30.677084D), height(23.148148D), width(36.927082D), height(9.444445D));
    AnnounceButtonNode second = new AnnounceButtonNode(1, SECOND, width(30.677084D), height(32.59259D), width(36.927082D), height(16.018518D));
    AnnounceButtonNode third = new AnnounceButtonNode(2, THIRD, width(30.677084D), height(49.444443D), width(36.927082D), height(9.444445D));
    AnnounceButtonNode fourth = new AnnounceButtonNode(3, FOURTH, width(30.677084D), height(60.27778D), width(36.927082D), height(9.444445D));
    AnnounceButtonNode fifth = new AnnounceButtonNode(4, FIFTH, width(30.677084D), height(69.72222D), width(36.927082D), height(9.444445D));
    AnnounceButtonNode sixth = new AnnounceButtonNode(5, SIXTH, width(30.677084D), height(80.64815D), width(36.927082D), height(9.444445D));
    AnnounceButtonNode[] newNodes = { first, second, third, fourth, fifth, sixth };
    for (AnnounceButtonNode node : newNodes) {
      node.setCallback(callback -> sendPacketAndClose(node.getId()));
      addNode((ANode)node);
    } 
  }
  
  public void sendPacketAndClose(int id) {
    this.field_146297_k.func_147108_a(null);
    this.field_146297_k.func_71381_h();
    SeptemberCommonModule.INSTANCE.getNetwork().sendToServer((IMessage)new CSJobOfferPacket(id));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_73866_w_();
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "LES ANNONCES DE PALADIUM", 
        
        width(30.677F), height(14.259F), COLOR_TITLE, Fonts.MINECRAFT_DUNGEONS_REGULAR
        .getFont(), 150, true, COLOR_TITLE_SHADOW);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void func_73869_a(char c, int keyCode) {
    super.func_73869_a(c, keyCode);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\AnnouncesUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */