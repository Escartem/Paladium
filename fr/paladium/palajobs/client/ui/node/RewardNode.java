package fr.paladium.palajobs.client.ui.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RewardNode extends AClickableNode {
  private ItemStack is = null;
  
  public RewardNode(float x, float y, float width, float height, IReward reward) {
    super(x, y, width, height);
    setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    if (reward.getType() == RewardType.MONEY_GIVE) {
      this.is = (PalaJobs.getClient()).moneyIcon;
      if (this.is.func_77973_b() != null)
        addHover("§e" + reward.getMoneyAmount() + " $"); 
    } else if (reward.getType() == RewardType.GIVE) {
      this.is = reward.getRewardItem();
      if (this.is != null && this.is.func_77973_b() != null)
        addHover("§a" + this.is.field_77994_a + "x " + this.is.func_82833_r()); 
    } else if (reward.getType() == RewardType.CRAFT) {
      if (reward.getLogo() instanceof ItemStack) {
        this.is = (ItemStack)reward.getLogo();
        if (this.is.func_77973_b() != null)
          addHover("§6Craft " + this.is.func_82833_r()); 
      } 
    } else if (reward.getType() == RewardType.USAGE && 
      reward.getLogo() instanceof ItemStack) {
      this.is = (ItemStack)reward.getLogo();
      if (this.is.func_77973_b() != null)
        addHover("§bUtiliser " + this.is.func_82833_r()); 
    } 
    if (reward.getHover() != null && !reward.getHover().isEmpty()) {
      getHovers().clear();
      addHover(reward.getHover());
    } 
    if (reward.getLogo() instanceof ItemStack) {
      this.is = (ItemStack)reward.getLogo();
      List<String> tooltip = this.is.func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, false);
      if (tooltip.size() < 2)
        return; 
      tooltip.set(0, "");
      addHover(tooltip);
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (this.is != null && this.is.func_77973_b() != null) {
      if (this.is.field_77990_d != null && this.is.field_77990_d.func_74764_b("url")) {
        DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), Resource.of(this.is.field_77990_d.func_74779_i("url")).nearest());
        return;
      } 
      GuiUtils.renderScaledItemStackIntoGUI(this.is, getX(), getY(), width(7.0F));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\RewardNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */