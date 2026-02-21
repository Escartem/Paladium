package fr.paladium.palamod.modules.luckyblock.gui.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.container.UISlot;
import fr.paladium.lib.apollon.container.interfaces.IApollonSlot;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.christmas.PacketChristmasSantaNoescroc;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;

public class UIChristmasSantaNoescrocTrade extends UIContainer {
  private final Color[] colors = new Color[] { new Color(239, 57, 38), new Color(255, 176, 116) };
  
  private final Color[] colors2 = new Color[] { new Color(242, 99, 85), new Color(255, 193, 146) };
  
  private float slotSize = 6.0F;
  
  private ItemStack selectedItem = null;
  
  private EntityPlayer player;
  
  public void setSelectedItem(ItemStack selectedItem) {
    this.selectedItem = selectedItem;
  }
  
  public UIChristmasSantaNoescrocTrade(EntityPlayer player, GuiScreen prev, String name, Container container) {
    super(prev, "palamod:christmas", container);
    this.player = player;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.selectedItem == null) {
      for (int j = 0; j < 2; j++) {
        for (int i = 0; i < 9; i++) {
          addSlot((IApollonSlot)(new UISlot(ContainerChristmasSantaNoescrocTrade.inventory, i + j * 9, width((this.slotSize + 1.0F) * i + 19.0F), height(2.0F * this.slotSize * j + 48.0F), width(this.slotSize)))
              .setTexture("palamod:textures/gui/christmas/slot")
              .setHoveredTexture("palamod:textures/gui/christmas/slot_hover")
              .setItemScale(0.7F));
          addNode((new ClickableNodeChristmasSantaNoescrocTrade(this, 0, i + j * 9, width((this.slotSize + 1.0F) * i + 19.0F), height(2.0F * this.slotSize * j + 48.0F), width(this.slotSize), width(this.slotSize)) {
              
              }).setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)this.instance)));
        } 
      } 
    } else {
      addNode((new ClickableNodeChristmasSantaNoescrocTrade(this, 1, -1, (width(50.0F) - width(14.0F) - width(1.0F)), height(65.0F), width(14.0F), height(5.0F)) {
            public void draw(Minecraft mc, int mouseX, int mouseY) {
              super.draw(mc, mouseX, mouseY);
              GuiUtils.drawRoundedRect(this.x, this.y, new Color(48 + (int)animationValue(207.0F), 217 + (int)animationValue(38.0F), 110 + (int)animationValue(145.0F)), this.width, this.height, width(2.0F));
              UIChristmasSantaNoescrocTrade.this.drawFullyCenteredString("oui".toUpperCase(), this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(207.0F), 255 - (int)animationValue(38.0F), 255 - (int)animationValue(145.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 70);
            }
          }).setCallback(node -> this.field_146297_k.func_147108_a(null)));
      addNode((new ClickableNodeChristmasSantaNoescrocTrade(this, 0, -1, (width(50.0F) + width(14.0F) / 2.0F - width(5.5F)), height(65.0F), width(14.0F), height(5.0F)) {
            public void draw(Minecraft mc, int mouseX, int mouseY) {
              super.draw(mc, mouseX, mouseY);
              GuiUtils.drawRoundedRect(this.x, this.y, new Color(162 + (int)animationValue(93.0F), 162 + (int)animationValue(93.0F), 162 + (int)animationValue(93.0F)), this.width, this.height, width(2.0F));
              UIChristmasSantaNoescrocTrade.this.drawFullyCenteredString("non".toUpperCase(), this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(93.0F), 255 - (int)animationValue(93.0F), 255 - (int)animationValue(93.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 70);
            }
          }).setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)this.instance)));
    } 
    addNode((new TexturedNodeButton(width(96.04F), height(3.61F), width(1.927F), width(1.927F), "palamod:textures/gui/christmas/close")).setCallback(n -> this.field_146297_k.func_147108_a(null)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    ChatComponentTranslation title = new ChatComponentTranslation("gui.entitySantaNoescroc.title", new Object[0]);
    ChatComponentTranslation sentence = new ChatComponentTranslation("gui.entitySantaNoescroc.sentence", new Object[0]);
    ChatComponentTranslation validation = new ChatComponentTranslation("gui.entitySantaNoescroc.validation", new Object[0]);
    GuiUtils.drawGradientRect(0.0D, 0.0D, this.colors, this.field_146294_l, this.field_146295_m, new Vector2d(0.0D, this.field_146295_m), new Vector2d(this.field_146294_l, 0.0D), this.field_146295_m);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, getBackground(), this.field_146294_l, this.field_146295_m, false);
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, height(16.0F), new Color(178, 53, 13));
    GuiUtils.drawGradientRoundedRect(width(17.0F), height(45.0F), this.colors2, width(66.0F), height(29.0F), new Vector2d(0.0D, this.field_146295_m), new Vector2d(this.field_146294_l, 0.0D), 20.0F, this.field_146295_m);
    drawFullyCenteredString(title.func_150254_d().toUpperCase(), (this.field_146294_l / 2), height(8.0F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 500);
    drawFullyCenteredString(sentence.func_150254_d(), width(50.0F), height(35.0F), Color.WHITE, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 300);
    if (this.selectedItem != null) {
      GuiUtils.drawRect(0.0D, height(35.0F), this.field_146294_l, height(75.0F), new Color(188, 0, 0));
      GuiUtils.drawImageTransparent(width(46.5F), height(38.0F), new ResourceLocation("palamod", "textures/gui/christmas/warning.png"), width(7.0F), width(7.0F), false);
      drawFullyCenteredString(validation.func_150254_d(), width(50.0F), height(52.0F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 250);
      drawFullyCenteredString(this.selectedItem.func_82833_r(), width(50.0F), height(59.0F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 400);
    } 
  }
  
  public void giveItem() {
    if (this.selectedItem != null && 
      this.player.field_71070_bA instanceof ContainerChristmasSantaNoescrocTrade) {
      ContainerChristmasSantaNoescrocTrade buffer = (ContainerChristmasSantaNoescrocTrade)this.player.field_71070_bA;
      PalaMod.getNetwork().sendToServer((IMessage)new PacketChristmasSantaNoescroc(this.selectedItem, buffer.getSantaNoescroc()));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\christmas\UIChristmasSantaNoescrocTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */