package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.nodes.SwitchButtonNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.CSSummerSalePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSGoodFortunePacket;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SummerSalesUI extends UI {
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/august/";
  
  private static final String UI_TITLE = "ROULETTE";
  
  private static final String CLICK = "CLIQUE!";
  
  private static final int MAX_COUNT = 2;
  
  private static final int MAX_DELAY = 60;
  
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/background_sale.png");
  
  private static final ResourceLocation UNION = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/union_right.png");
  
  private static final Color COLOR_TITLE = Color.decode("#EF3926");
  
  private static final Color COLOR_TITLE_SHADOW = Color.decode("#B91C0C");
  
  private int id;
  
  private final ItemStack reward;
  
  private final List<ItemStack> rewards;
  
  private int currentAngle;
  
  private int count;
  
  private int delay;
  
  private boolean started;
  
  private boolean playedSound;
  
  public int getId() {
    return this.id;
  }
  
  public ItemStack getReward() {
    return this.reward;
  }
  
  public List<ItemStack> getRewards() {
    return this.rewards;
  }
  
  public int getCurrentAngle() {
    return this.currentAngle;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public int getDelay() {
    return this.delay;
  }
  
  public boolean isStarted() {
    return this.started;
  }
  
  public boolean isPlayedSound() {
    return this.playedSound;
  }
  
  public SummerSalesUI(List<ItemStack> rewards, ItemStack reward, int id) {
    this.reward = reward;
    this.rewards = rewards;
    this.rewards.removeIf(stack -> (stack.func_77973_b().equals(reward.func_77973_b()) && ItemStack.func_77970_a(stack, reward)));
    this.id = id;
    this.currentAngle = 0;
    this.delay = 0;
    this.started = false;
    this.playedSound = false;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    SwitchButtonNode node = new SwitchButtonNode(width(46.25D), height(77.196295D), width(7.5000005D), height(9.444445D));
    addNode((ANode)node);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "ROULETTE", 
        
        width(30.677F), height(14.259F), COLOR_TITLE, Fonts.MINECRAFT_DUNGEONS_REGULAR
        .getFont(), 150, true, COLOR_TITLE_SHADOW);
    this.currentAngle++;
    if (this.count >= 2) {
      this.currentAngle = 0;
      this.delay++;
      if (!this.playedSound) {
        this.playedSound = true;
        SoundUtils.LEVEL_UP.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
      } 
    } 
    if (this.delay >= 60) {
      this.delay = 0;
      handlePacket();
    } 
    if (this.currentAngle >= 360) {
      this.currentAngle = 0;
      if (this.started)
        this.count++; 
    } 
    if (this.started && this.currentAngle % 20 == 0 && this.count < 2)
      SoundUtils.BUTTON_CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F); 
    GL11.glPushMatrix();
    GuiUtils.drawImageTransparent(
        width(62.552F), height(47.685F), UNION, 
        
        width(2.916F), height(4.629F), true);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(width(50.0F), height(50.0F), 0.0F);
    GL11.glRotatef(this.currentAngle, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(-width(50.0F), -height(50.0F), 0.0F);
    GuiUtils.drawImageTransparent(
        width(40.156F), height(31.852F), BACKGROUND, 
        
        width(19.687F), height(35.371F), true);
    drawItems();
    GL11.glPopMatrix();
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "CLIQUE!", width(50.0F), height(72.18519F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 150);
  }
  
  private void drawItems() {
    float scale = 4.0F;
    GL11.glScalef(scale, scale, scale);
    GuiUtils.renderItemStackIntoGUI(this.reward, 
        
        width(54.010414D) / scale, (height(43.148148F) / scale));
    GuiUtils.renderItemStackIntoGUI(this.rewards
        .get(0), 
        width(52.083332D) / scale, height(55.462963D) / scale);
    GuiUtils.renderItemStackIntoGUI(this.rewards
        .get(1), 
        width(44.166668D) / scale, height(55.18519D) / scale);
    GuiUtils.renderItemStackIntoGUI(this.rewards
        .get(2), 
        width(41.927086D) / scale, height(42.77778D) / scale);
    GuiUtils.renderItemStackIntoGUI(this.rewards
        .get(3), 
        width(48.333332D) / scale, height(35.09259D) / scale);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void func_73869_a(char c, int keyCode) {
    if (keyCode == 1)
      return; 
    super.func_73869_a(c, keyCode);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
    if (this.started)
      return; 
    List<SwitchButtonNode> switchButtonNodes = (List<SwitchButtonNode>)getNodes().stream().filter(node -> node instanceof SwitchButtonNode).map(node -> (SwitchButtonNode)node).collect(Collectors.toList());
    for (SwitchButtonNode switchButtonNode : switchButtonNodes) {
      if (switchButtonNode.switchType(mouseX, mouseY, buttonID)) {
        switchButtonNode.setResource(SwitchButtonNode.EMPTY);
        this.started = true;
        break;
      } 
    } 
  }
  
  private void handlePacket() {
    this.field_146297_k.func_147108_a(null);
    this.field_146297_k.func_71381_h();
    if (this.id == 0) {
      PalaMod.network.sendToServer((IMessage)new CSSummerSalePacket());
    } else if (this.id == 1) {
      SeptemberCommonModule.INSTANCE.getNetwork().sendToServer((IMessage)new CSGoodFortunePacket());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\clien\\uis\SummerSalesUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */