package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.config.ClientHalloweenTradeConfig;
import fr.paladium.palamod.modules.luckyblock.gui.halloween.node.HalloweenTradeRewardNode;
import fr.paladium.palamod.modules.luckyblock.network.CSPacketHalloweenBuyCosmetic;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.vecmath.Vector2d;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

public class UIHalloweenTrade extends UIContainer {
  private final Color[] colors = new Color[] { new Color(239, 57, 38), new Color(255, 176, 116) };
  
  private final EntityPlayer player;
  
  private ClientHalloweenTradeConfig config;
  
  private SlotNode slot;
  
  public void setConfig(ClientHalloweenTradeConfig config) {
    this.config = config;
  }
  
  public UIHalloweenTrade(EntityPlayer player, World world, int x, int y, int z, ClientHalloweenTradeConfig configHalloween) {
    super(null, "palamod:halloween", new ContainerHalloweenTrade(player, world, x, y, z, configHalloween.getTrade(), configHalloween.getTradeAmount()));
    this.player = player;
    this.config = configHalloween;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    this.config = ClientProxy.configHalloween;
    if (this.config == null) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    ContainerHalloweenTrade container = (ContainerHalloweenTrade)getContainer();
    addNode((ANode)(new SlotNode(container
          .getInventories()[0], 0, width(3.59F), height(22.13F), width(3.96F), width(3.96F)))
        .setTexture("palamod:textures/gui/halloween/slot")
        .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
        .setItemScale(0.7F)
        .setPlaceholder(new ItemStack(ItemsRegister.CANDY_PIECE, 64)));
    addNode((ANode)(new SlotNode(container
          .getInventories()[0], 1, width(41.51F), height(22.13F), width(3.96F), width(3.96F)))
        .setTexture("palamod:textures/gui/halloween/slot")
        .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
        .setItemScale(0.7F)
        .setPlaceholder(ItemsRegister.CANDY));
    addNode((ANode)(new SlotNode(container
          .getInventories()[1], 0, width(3.59F), height(31.11F), width(3.96F), width(3.96F)))
        .setTexture("palamod:textures/gui/halloween/slot")
        .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
        .setItemScale(0.7F)
        .setPlaceholder(new ItemStack(ItemsRegister.CANDY, 10)));
    addNode((ANode)(new SlotNode(container
          .getInventories()[1], 1, width(41.51F), height(31.11F), width(3.96F), width(3.96F)))
        .setTexture("palamod:textures/gui/halloween/slot")
        .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
        .setItemScale(0.7F)
        .setPlaceholder(ItemsRegister.CANDY_BAG));
    if (this.config.getTrade() != null) {
      addNode(
          
          (ANode)(this.slot = (new SlotNode(container.getInventories()[2], 0, width(3.59F), height(48.05F), width(3.96F), width(3.96F))).setTexture("palamod:textures/gui/halloween/slot").setHoveredTexture("palamod:textures/gui/halloween/slot_hover").setItemScale(0.7F).setPlaceholder(new ItemStack(Item.func_150899_d(this.config.getTrade().getInput().getId()), this.config.getTrade().getInput().getAmount(), this.config.getTrade().getInput().getMeta()))));
      addNode((ANode)(new SlotNode(container
            .getInventories()[2], 1, width(41.51F), height(48.05F), width(3.96F), width(3.96F)))
          .setTexture("palamod:textures/gui/halloween/slot")
          .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
          .setItemScale(0.7F)
          .setPlaceholder(new ItemStack(ItemsRegister.CANDY_PIECE, 2)));
    } 
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)(new SlotNode((IInventory)this.player.field_71071_by, j + i * 9 + 9, (
              width(3.59F) + width(4.74F) * j), (height(61.018F) + height(8.42F) * i), width(3.96D), width(3.96D)))
            .setTexture("palamod:textures/gui/halloween/slot")
            .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
            .setItemScale(0.7F)); 
    } 
    for (i = 0; i < 9; i++)
      addNode((ANode)(new SlotNode((IInventory)this.player.field_71071_by, i, (
            width(3.59F) + width(4.74F) * i), height(87.87F), width(3.96D), width(3.96D)))
          .setTexture("palamod:textures/gui/halloween/slot")
          .setHoveredTexture("palamod:textures/gui/halloween/slot_hover")
          .setItemScale(0.7F)); 
    addNode((new HalloweenTradeRewardNode(width(53.8F), height(23.33F), width(15.05F), height(26.76F), this.config.getCosmeticImage()))
        .addHover("Les morceaux, les sacs et les bonbons seront disponibles à l'échange contre")
        .addHover("une multitude de récompenses à la fin de l’événement via des NPCs.")
        .addHover("")
        .addHover("§pLe cosmétique est disponible uniquement pendant l'event.")
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new AClickableNode(width(85.88F), height(45.09F), width(11.19F), height(4.63F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            Color primaryColor = (UIHalloweenTrade.this.config.getRemainingCosmetics() > 0) ? new Color(48, 217, 110) : new Color(84, 93, 87);
            if (UIHalloweenTrade.this.config.getRemainingCosmetics() <= 0) {
              this.animationValue = 0.0F;
              getHovers().clear();
              addHover("Plus aucun cosmétique en vente.");
              setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
            } 
            GuiUtils.drawRoundedRect(this.x, this.y, new Color(primaryColor.getRed() + (int)animationValue(255.0F - primaryColor.getRed()), primaryColor.getGreen() + (int)animationValue(255.0F - primaryColor.getGreen()), primaryColor.getBlue() + (int)animationValue(255.0F - primaryColor.getBlue())), this.width, this.height, this.ui.width(0.3F));
            UIHalloweenTrade.this.drawFullyCenteredString("Acheter", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(255.0F - primaryColor.getRed()), 255 - (int)animationValue(255.0F - primaryColor.getGreen()), 255 - (int)animationValue(255.0F - primaryColor.getBlue())), Fonts.MONTSERRAT_BOLD.getFont(), 30);
          }
        }).setCallback(n -> {
            if (this.config.getRemainingCosmetics() > 0) {
              PalaMod.getNetHandler().sendToServer((IMessage)new CSPacketHalloweenBuyCosmetic());
              this.field_146297_k.func_147108_a(null);
            } 
          }));
    addNode((new TexturedNodeButton(width(96.04F), height(3.61F), width(1.927F), width(1.927F), "palamod:textures/gui/halloween/close"))
        .setCallback(n -> this.field_146297_k.func_147108_a(null)));
  }
  
  public void fixedUpdate() {
    if (this.config.getTrade() != null && this.config.getTrade().getInput() != null && this.config.getTrade().getInput().getMeta() == -1 && this.field_146297_k.field_71439_g.field_70173_aa % 20 == 0) {
      ItemStack item = this.slot.getPlaceholder();
      Block b = Block.func_149634_a(item.func_77973_b());
      if (b == null)
        return; 
      List<?> subBlocks = new ArrayList();
      b.func_149666_a(item.func_77973_b(), CreativeTabs.field_78027_g, subBlocks);
      int meta = item.func_77960_j();
      item.func_77964_b((meta + 1) % subBlocks.size());
      this.slot.setPlaceholder(item);
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawGradientRect(0.0D, 0.0D, this.colors, this.field_146294_l, this.field_146295_m, new Vector2d(0.0D, this.field_146295_m), new Vector2d(this.field_146294_l, 0.0D), this.field_146295_m);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, getBackground(), this.field_146294_l, this.field_146295_m, false);
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, height(10.65F), new Color(178, 53, 13));
    drawVerticalCenteredString("halloween".toUpperCase(), width(4.68F), height(5.37F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 220);
    GuiUtils.drawRoundedRect(width(2.03F), height(15.27F), new Color(178, 53, 13), width(45.0F), height(4.44F), width(0.3F));
    drawVerticalCenteredString("trade".toUpperCase(), width(3.03F), (height(15.27F) + height(2.22F)), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40);
    GuiUtils.drawRoundedRect(width(2.03F), height(41.11F), new Color(178, 53, 13), width(45.0F), height(4.44F), width(0.3F));
    if (this.config.getTrade() != null) {
      drawVerticalCenteredString(("trade journalier (" + (((ContainerHalloweenTrade)getContainer()).getInventories()[2]).tradeAmount + "/" + ((this.config.getTrade() != null) ? this.config.getTrade().getMaxTrade() : 0) + ")").toUpperCase(), width(3.03F), (height(41.11F) + height(2.22F)), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40);
      long now = System.currentTimeMillis();
      long remaining = this.config.getTrade().getEnd() - now;
      String time = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(remaining)), 
            Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(remaining) - TimeUnit.HOURS
              .toMinutes(TimeUnit.MILLISECONDS.toHours(remaining))), 
            Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(remaining) - TimeUnit.MINUTES
              .toSeconds(TimeUnit.MILLISECONDS.toMinutes(remaining))) });
      drawVerticalCenteredString(time, (width(46.03F) - GuiUtils.getStringWidth(this.field_146297_k, time, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40)), (height(41.11F) + height(2.22F)), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40);
    } else {
      drawVerticalCenteredString("pas de trade aujourd'hui".toUpperCase(), width(3.03F), (height(41.11F) + height(2.22F)), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40);
    } 
    GuiUtils.drawRoundedRect(width(52.97F), height(15.27F), new Color(178, 53, 13), width(45.0F), height(4.44F), width(0.3F));
    drawVerticalCenteredString("récompense".toUpperCase(), width(53.97F), (height(15.27F) + height(2.22F)), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40);
    GuiUtils.drawImageTransparent(width(21.98F), height(23.52F), new ResourceLocation("palamod", "textures/gui/halloween/arrow.png"), width(4.27F), height(4.26F));
    GuiUtils.drawImageTransparent(width(21.98F), height(32.5F), new ResourceLocation("palamod", "textures/gui/halloween/arrow.png"), width(4.27F), height(4.26F));
    if (this.config.getTrade() != null) {
      boolean locked = ((ContainerHalloweenTrade)getContainer()).getInventories()[2].isLocked();
      GuiUtils.drawImageTransparent(width(21.98F), height(49.44F), 0.0D, 0.0D, width(4.27F), height(4.26F), width(4.27F), height(4.26F), new ResourceLocation("palamod", "textures/gui/halloween/arrow.png"), locked ? new Color(196, 78, 51) : Color.WHITE, locked ? 0.4F : 1.0F);
    } 
    GuiUtils.drawRoundedRect(width(2.03F), height(58.7F), new Color(255, 99, 75), width(45.0F), height(38.14F), width(0.6F));
    GuiUtils.drawRoundedRect(width(52.97F), height(21.76F), new Color(255, 195, 141), width(44.89F), height(29.81F), width(0.6F));
    drawString(StringUtils.capitalize(this.config.getCosmeticName()), width(70.0F), height(30.0F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 120);
    GuiUtils.drawRoundedRect(width(70.0F), height(23.33F), new Color(210, 81, 33), width(7.34F), height(3.98F), width(0.4F));
    drawFullyCenteredString("PALARARE", width(73.67F), height(25.32F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 20);
    drawString(this.config.getRemainingCosmetics() + "/10 RESTANT" + ((this.config.getRemainingCosmetics() > 1) ? "S" : ""), width(70.0F), height(48.0F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 40);
    GuiUtils.drawRoundedRect(width(88.84F), height(23.33F), new Color(212, 152, 98), width(8.23F), height(6.57F), height(3.29F));
    drawSplittedString(String.valueOf(this.config.getCosmeticCost()), width(92.954994F), height(25.5F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 90, this.field_146294_l, TextAlign.RIGHT);
    UIContainer.drawItemStack(new ItemStack(ItemsRegister.CANDY_BAG), width(93.3F), (height(26.615F) - width(0.78F)), width(1.56F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\UIHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */