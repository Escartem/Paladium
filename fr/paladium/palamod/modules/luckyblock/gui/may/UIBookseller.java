package fr.paladium.palamod.modules.luckyblock.gui.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VieilleHistoire;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketBooksellerChoice;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIBookseller extends UI {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod", "textures/gui/LuckyBlock/may/bookseller_bg.png");
  
  private static final float BG_WIDTH = 46.666668F;
  
  private static final float BG_HEIGHT = 53.7037F;
  
  private ItemStack[] rewards = new ItemStack[4];
  
  public UIBookseller() {
    super(null, "palamod:ui_bookseller");
    for (int i = 0; i < this.rewards.length; i++)
      this.rewards[i] = VieilleHistoire.getReward(i); 
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    float bgLeft = 26.666666F;
    float rewardNodeSize = 1.0F;
    float bgSplit = 46.666668F / this.rewards.length;
    float nodeWidth = UIBooksellerRewardNode.SLOT_WIDTH * rewardNodeSize;
    float nodeHeight = UIBooksellerRewardNode.SLOT_HEIGHT * rewardNodeSize;
    float margin = bgSplit - nodeWidth - 0.8F;
    float nodeWidthConverted = width(nodeWidth);
    float nodeHeightConverted = height(nodeHeight);
    float rewardNodeMargin = margin;
    for (int i = 0; i < this.rewards.length; i++) {
      int rewardId = i;
      addNode((new UIBooksellerRewardNode(this.field_146297_k.field_71439_g, this.rewards[i], width(bgLeft + rewardNodeMargin), height(50.0F), nodeWidthConverted, nodeHeightConverted)).setCallback(node -> {
              PalaMod.getNetwork().sendToServer((IMessage)new PacketBooksellerChoice(rewardId));
              this.field_146297_k.field_71439_g.func_71053_j();
            }));
      rewardNodeMargin += margin + nodeWidth;
    } 
    addNode((ANode)new MinecraftCloseNode(width(69.333336F), height(27.64815F), width(1.5F), height(2.8F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    float bgLeft = 26.666666F;
    GuiUtils.drawImageTransparent(width(bgLeft), height(23.14815F), BACKGROUND, width(46.666668F), height(53.7037F), false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\may\UIBookseller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */