package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityTaupiko;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSTaupikoPacket;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TaupikoUI extends UI {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod", "textures/gui/LuckyBlock/june/alarmclock_bg.png");
  
  private static final float BG_WIDTH = 46.666668F;
  
  private static final float BG_HEIGHT = 82.96296F;
  
  private UUID entityUniqueId;
  
  private int value;
  
  public TaupikoUI(Entity entity) {
    this.entityUniqueId = entity.func_110124_au();
    this.value = 0;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftCloseNode(width(68.177F), height(13.148F)));
    double posX = width(44.410414D);
    double posY = height(37.11111D);
    double maxWidth = width(11.25D);
    MinecraftTextCallToActionNode topButton = new MinecraftTextCallToActionNode(posX, posY, maxWidth, "+");
    posY = height(73.85111D);
    MinecraftTextCallToActionNode bottomButton = new MinecraftTextCallToActionNode(posX, posY, maxWidth, "-");
    topButton.setCallback(callback -> {
          this.value++;
          if (this.value > EntityTaupiko.JUMP_INTERVAL.getMax())
            this.value = 0; 
          SoundUtils.BUTTON_CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
        });
    bottomButton.setCallback(callback -> {
          this.value--;
          if (this.value < EntityTaupiko.JUMP_INTERVAL.getMin())
            this.value = 0; 
          SoundUtils.BUTTON_CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
        });
    addNode((ANode)topButton);
    addNode((ANode)bottomButton);
  }
  
  public void func_73869_a(char c, int keyCode) {
    if (keyCode == 1)
      sendPacket(); 
    super.func_73869_a(c, keyCode);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    String UI_TITLE = "Taupiko";
    Color COLOR_TITLE = Color.decode("#EF3926");
    Color COLOR_TITLE_SHADOW = Color.decode("#B91C0C");
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "Taupiko", 
        
        width(30.677F), height(14.259F), COLOR_TITLE, Fonts.MINECRAFT_DUNGEONS_REGULAR
        .getFont(), 150, true, COLOR_TITLE_SHADOW);
    double posX = width(44.010414D);
    double posY = height(41.851852D);
    double maxWidth = posX + width(12.03125D);
    double maxHeight = posY + height(31.296295D);
    GuiUtils.drawRect(posX, posY, maxWidth, maxHeight, new Color(0, 0, 0, 100));
    posX = width(50.0F);
    posY = height(45.851852D);
    drawCenteredString(String.valueOf(this.value), posX, posY, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 1400);
  }
  
  public void sendPacket() {
    PalaMod.getNetwork().sendToServer((IMessage)new CSTaupikoPacket(this.entityUniqueId, this.value));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\TaupikoUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */