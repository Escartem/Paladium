package fr.paladium.palajobs.client.ui.boss;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.constant.BossConstants;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenBossContainer;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import fr.paladium.palajobs.core.pojo.boss.BossState;
import fr.paladium.palajobs.core.utils.DurationConverter;
import fr.paladium.palajobs.core.utils.TimeUtil;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIBoss extends UI {
  private static final ResourceLocation SLOT = new ResourceLocation("apollon", "textures/gui/buttons/minecraft/slot.png");
  
  private static final ResourceLocation BAR_EMPTY = new ResourceLocation("palajobs", "textures/gui/boss/bar_empty.png");
  
  private static final ResourceLocation BAR_FILL = new ResourceLocation("palajobs", "textures/gui/boss/bar_fill.png");
  
  private BossData bossData;
  
  public UIBoss(GuiScreen prev, BossData bossData) {
    super(prev, "palajobs:boss");
    this.bossData = bossData;
    this.bossData.value = Math.min(((Integer)BossConstants.BOSS_QUANTITY.get(this.bossData.type)).intValue(), this.bossData.value);
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(31.4F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(31.4F), height(24.0F), TTT.format("gui.jobs.boss.subtitle", new Object[0])));
    TextNodeLabel label;
    addNode((ANode)(label = (new TextNodeLabel(width(40.0F), height(46.0F), TTT.format(this.bossData.type.getName(), new Object[0]), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 60, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(20.0F));
    addNode((ANode)(label = (new TextNodeLabel(width(40.0F), height(50.0F), this.bossData.value + "/" + BossConstants.BOSS_QUANTITY.get(this.bossData.type), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 60, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(20.0F));
    addNode((new MinecraftTextCallToActionNode(width(39.16F), height(79.26F), width(21.66F), TTT.format("gui.jobs.boss.deposite", new Object[0]).toUpperCase())).setCallback(n -> {
            this.field_146297_k.func_147108_a(null);
            PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketOpenBossContainer());
          }).setEnabled((this.bossData.state == BossState.OPEN)));
    addNode((ANode)new MinecraftBackNode(width(66.0F), height(13.5F), this.prev));
    addNode((ANode)new MinecraftCloseNode(width(68.1F), height(13.0F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.02F), height(76.481F));
    GuiUtils.drawImageTransparent(width(46.09F), height(29.7F), SLOT, width(7.8125F), width(7.8125F), false);
    GuiUtils.renderScaledItemStackIntoGUI((ItemStack)BossConstants.BOSS_ITEM.get(this.bossData.type), width(47.2F), height(31.8F), width(0.35F), new Color(1.0F, 1.0F, 1.0F, 0.3F));
    float p = this.bossData.value / ((Integer)BossConstants.BOSS_QUANTITY.get(this.bossData.type)).intValue();
    GuiUtils.drawImageTransparent(width(33.28125F), height(56.11F), BAR_EMPTY, width(33.49F), height(7.037F));
    GuiUtils.drawImageTransparent(width(33.28125F), height(56.11F), 0.0D, 0.0D, (width(33.49F) * p), height(7.037F), width(33.49F), height(7.037F), BAR_FILL, Color.WHITE, 1.0F);
    drawSplittedString(getStringFromData(), (this.field_146294_l / 2), height(67.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 60, width(33.54F), TextAlign.CENTER);
  }
  
  private String getStringFromData() {
    if (this.bossData.state == BossState.OPEN)
      return TTT.format("gui.jobs.boss.data.open", new Object[0]); 
    if (this.bossData.state == BossState.SPAWNING)
      return TTT.format("gui.jobs.boss.data.spawning", new Object[] { DurationConverter.fromMillisToString((this.bossData.timestamp - TimeUtil.now()) * 1000L) }); 
    if (this.bossData.state == BossState.SPAWNED)
      return TTT.format("gui.jobs.boss.data.spawned", new Object[0]); 
    if (this.bossData.state == BossState.FINISHED)
      return TTT.format("gui.jobs.boss.data.finished", new Object[] { DurationConverter.fromMillisToString((this.bossData.timestamp - TimeUtil.now()) * 1000L) }); 
    return TTT.format("gui.jobs.boss.data.error", new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\boss\UIBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */