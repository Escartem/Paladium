package fr.paladium.palamod.modules.luckyblock.gui.may;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.util.PaladiumColorCode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIBooksellerRewardNode extends AClickableNode {
  private static final ResourceLocation SLOT = new ResourceLocation("palamod", "textures/gui/LuckyBlock/may/slot.png");
  
  public static float SLOT_WIDTH = 7.8125F;
  
  public static float SLOT_HEIGHT = 13.888889F;
  
  private final ItemStack stack;
  
  public UIBooksellerRewardNode(EntityClientPlayerMP player, ItemStack stack, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.stack = stack;
    addHover(stack.func_82840_a((EntityPlayer)player, false));
    setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    setHoverColor(PaladiumColorCode.INFO_COLOR);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, SLOT, this.width, this.height, false);
    GuiUtils.renderScaledItemStackIntoGUI(this.stack, this.x + width(13.0F), this.y + width(9.0F), height(5.0F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\may\UIBooksellerRewardNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */