package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.utils.JobUIData;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.nodes.JobsTransferorProgressNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobTransferor;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSJobTransferorPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class JobTransferorUI extends UI {
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/september/transferor/";
  
  private static final long CONFIRMED_DELAY = TimeUnit.SECONDS.toMillis(5L);
  
  private static final String CONFIRM_TEXT = "CONFIRMER";
  
  private static final String EXPERIENCE_TEXT = "%s XP";
  
  private static final String TRANSFER_TEXT = "SERONT TRANSFÉRÉS VERS L'AUTRE MÉTIER";
  
  private static final ResourceLocation ARROW = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/transferor/arrow.png");
  
  private static final ResourceLocation ARROW_HOVER = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/transferor/arrow_hover.png");
  
  private boolean confirmed;
  
  private long confirmedMillis;
  
  private final List<JobsTransferorProgressNode> jobNodes;
  
  private final JobUIData data;
  
  public boolean isConfirmed() {
    return this.confirmed;
  }
  
  public long getConfirmedMillis() {
    return this.confirmedMillis;
  }
  
  public List<JobsTransferorProgressNode> getJobNodes() {
    return this.jobNodes;
  }
  
  public JobUIData getData() {
    return this.data;
  }
  
  public JobTransferorUI(List<JobsTransferorProgressNode> nodes, JobUIData data) {
    super((GuiScreen)new JobTransferorHomeUI(), "palamod:trasnferor");
    this.confirmed = false;
    this.confirmedMillis = 0L;
    this.jobNodes = nodes;
    this.data = data;
  }
  
  private long getRemaining(long now) {
    return this.confirmedMillis - now;
  }
  
  private boolean isFinished(long now, long remaining) {
    return (now >= this.confirmedMillis && remaining <= 0L);
  }
  
  private void confirm(long now) {
    if (this.confirmed)
      return; 
    this.confirmed = true;
    this.confirmedMillis = now + CONFIRMED_DELAY;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(30.95F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    JobAdvancement firstJobAdvancement = getByNode(this.jobNodes.get(0));
    JobAdvancement secondJobAdvancement = getByNode(this.jobNodes.get(1));
    if (!canTransfer(firstJobAdvancement, secondJobAdvancement)) {
      this.field_146297_k.func_147108_a(null);
      this.field_146297_k.func_71381_h();
      MonthlyUtils.sendMessage((EntityPlayer)this.field_146297_k.field_71439_g, ItemJobTransferor.CONDITIONS);
      return;
    } 
    JobsTransferorProgressNode firstNew = new JobsTransferorProgressNode(width(27.864584F), height(28.88889F), width(18.75D), firstJobAdvancement);
    firstNew.setShowLevel(true);
    JobsTransferorProgressNode secondNew = new JobsTransferorProgressNode(width(52.499996F), height(28.88889F), width(18.75D), secondJobAdvancement);
    secondNew.setShowLevel(true);
    MinecraftTextCallToActionNode confirmButton = new MinecraftTextCallToActionNode(width(41.458332D), height(78.333336D), width(18.072916F), "CONFIRMER");
    confirmButton.setCallback(callback -> confirm(System.currentTimeMillis()));
    double defaultWidth = width(64.0F);
    MinecraftBackNode back = new MinecraftBackNode(defaultWidth, height(14.5F), this.prev);
    MinecraftCloseNode close = new MinecraftCloseNode(defaultWidth + width(2.92375F), height(14.0F));
    addNode((ANode)firstNew);
    addNode((ANode)secondNew);
    addNode((ANode)confirmButton);
    addNode((ANode)back);
    addNode((ANode)close);
  }
  
  public JobAdvancement getByNode(JobsTransferorProgressNode node) {
    return node.getAdvancement();
  }
  
  public void sendPacketAndClose() {
    this.field_146297_k.func_147108_a(null);
    this.field_146297_k.func_71381_h();
    JobType first = getByNode(this.jobNodes.get(0)).getJob().getType();
    JobType second = getByNode(this.jobNodes.get(1)).getJob().getType();
    SeptemberCommonModule.INSTANCE.getNetwork().sendToServer((IMessage)new CSJobTransferorPacket(first, second));
  }
  
  public boolean canTransfer(JobAdvancement first, JobAdvancement second) {
    if (first.getJob().getType() == second.getJob().getType())
      return false; 
    double afterValue = first.getExperience() - 10000.0D;
    if (afterValue <= 0.0D)
      return false; 
    afterValue = second.getExperience() + 10000.0D;
    if (afterValue >= second.getNextLevelExperience())
      return false; 
    return true;
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    double arrowX = width(47.760418D);
    double arrowY = height(45.0D);
    double arrowHeight = height(5.3703704D);
    double arrowWidth = width(4.53125D);
    GuiUtils.drawImageTransparent(arrowX, arrowY, ARROW, arrowWidth, arrowHeight, true);
    if (this.confirmed) {
      long now = System.currentTimeMillis();
      long remaining = getRemaining(now);
      if (isFinished(now, remaining)) {
        sendPacketAndClose();
      } else {
        float min = 0.0F;
        float max = 100.0F;
        float value = (float)(1.0D - remaining / CONFIRMED_DELAY) * 100.0F;
        if (value < 0.0F) {
          value = 0.0F;
        } else if (value > 100.0F) {
          value = 100.0F;
        } 
        GuiUtils.drawProgressBar(arrowX, arrowY, arrowWidth, arrowHeight, 0.0F, 100.0F, value, ARROW_HOVER, 1.0F);
      } 
    } 
    double posY = height(72.03703D);
    double posX = width(37.395832D);
    double maxWidth = posX + width(5.989583D);
    double maxHeight = posY + height(4.166667D);
    GuiUtils.drawRect(posX, posY, maxWidth, maxHeight, new Color(0, 0, 0, 100));
    drawCenteredString(String.format("%s XP", new Object[] { String.valueOf(10000) }), posX + width(3.0F), posY + height(1.25F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawString("SERONT TRANSFÉRÉS VERS L'AUTRE MÉTIER", posX + width(6.5F), posY + height(1.55F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 4);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\JobTransferorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */