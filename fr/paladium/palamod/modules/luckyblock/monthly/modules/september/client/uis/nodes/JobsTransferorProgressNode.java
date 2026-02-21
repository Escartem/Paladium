package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JobsTransferorProgressNode extends AClickableNode {
  public void setSelected(boolean selected) {
    this.selected = selected;
  }
  
  public void setSelectedMillis(long selectedMillis) {
    this.selectedMillis = selectedMillis;
  }
  
  private static final Map<JobType, ResourceLocation> TEXTURES = new HashMap<>();
  
  private final ResourceLocation texture;
  
  static {
    TEXTURES.put(JobType.ALCHEMIST, new ResourceLocation("palajobs", "textures/gui/icons/alchimiste.png"));
    TEXTURES.put(JobType.FARMER, new ResourceLocation("palajobs", "textures/gui/icons/farmer.png"));
    TEXTURES.put(JobType.HUNTER, new ResourceLocation("palajobs", "textures/gui/icons/hunter.png"));
    TEXTURES.put(JobType.MINER, new ResourceLocation("palajobs", "textures/gui/icons/miner.png"));
  }
  
  public ResourceLocation getTexture() {
    return this.texture;
  }
  
  private static final Map<JobType, Color> COLORS = new HashMap<>();
  
  private final Color color;
  
  private final JobAdvancement advancement;
  
  private boolean showLevel;
  
  private boolean selected;
  
  private long selectedMillis;
  
  static {
    COLORS.put(JobType.ALCHEMIST, new Color(252, 100, 201));
    COLORS.put(JobType.FARMER, new Color(255, 209, 1));
    COLORS.put(JobType.HUNTER, new Color(47, 103, 255));
    COLORS.put(JobType.MINER, new Color(255, 47, 47));
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public JobAdvancement getAdvancement() {
    return this.advancement;
  }
  
  public boolean isShowLevel() {
    return this.showLevel;
  }
  
  public boolean isSelected() {
    return this.selected;
  }
  
  public long getSelectedMillis() {
    return this.selectedMillis;
  }
  
  public JobsTransferorProgressNode(double x, double y, double size, JobAdvancement job) {
    super(x, y, size, size * 1.041825095057034D);
    this.texture = TEXTURES.get(job.getJob().getType());
    this.color = COLORS.get(job.getJob().getType());
    this.advancement = job;
    this.selected = false;
    this.selectedMillis = 0L;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawShape(new Color(50, 50, 50), new Vector2d[] { new Vector2d(this.x + 
            width(50.0F), this.y + height(15.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(35.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(50.0F), this.y + height(90.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(35.0F)) });
    float p = (this.advancement.getLevel() == Integer.MAX_VALUE) ? 1.0F : ((float)this.advancement.getExperience() / (float)this.advancement.getNextLevelExperience());
    float p1 = Math.min(1.0F, p * 2.0F);
    float p2 = Math.min(1.0F, Math.max(0.0F, (p - 0.5F) * 2.0F));
    GL11.glPushMatrix();
    GL11.glEnable(3089);
    GuiUtils.scissor(mc, this.x + this.width / 2.0D, this.y + height(15.0F), this.width / 2.0D, (height(75.0F) * p1));
    GuiUtils.drawShape(this.color, new Vector2d[] { new Vector2d(this.x + 
            width(50.0F), this.y + height(15.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(35.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(50.0F), this.y + height(90.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(35.0F)) });
    GL11.glDisable(3089);
    GL11.glEnable(3089);
    GuiUtils.scissor(mc, this.x, this.y + height(15.0F) + (height(75.0F) * (1.0F - p2)), this.width / 2.0D, (height(75.0F) * p2));
    GuiUtils.drawShape(this.color, new Vector2d[] { new Vector2d(this.x + 
            width(50.0F), this.y + height(15.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(35.0F)), new Vector2d(this.x + 
            width(15.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(50.0F), this.y + height(90.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(70.0F)), new Vector2d(this.x + 
            width(85.0F), this.y + height(35.0F)) });
    GL11.glDisable(3089);
    GL11.glPopMatrix();
    GuiUtils.drawImageTransparent(this.x, this.y, this.texture, this.width, this.height, true);
    if (!this.showLevel)
      return; 
    GuiUtils.drawRect(this.x + width(30.0F), this.y + height(74.0F), this.x + width(70.0F), this.y + height(95.0F), this.color);
    GuiUtils.drawRect(this.x + width(30.0F), this.y + height(95.0F), this.x + width(70.0F), this.y + height(98.0F), this.color.darker(0.5F));
    GuiUtils.drawCenteredStringWithCustomFont(mc, "LVL.", this.x + width(49.2F), this.y + height(78.0F) - (GuiUtils.getFontHeight(mc, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), -20) / 2), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), -20);
    String level = String.valueOf(this.advancement.getLevel());
    int fontSize = (int)width(30.0F);
    GuiUtils.drawCenteredStringWithCustomFont(mc, level, this.x + width(49.2F), this.y + height(87.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), fontSize) / 2), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), fontSize);
    if (this.selected)
      GuiUtils.drawBorder(this.x, this.y, this.x + this.width, this.y + this.height, Color.WHITE); 
  }
  
  public void handleSelect() {
    this.selected = !this.selected;
    if (this.selected)
      this.selectedMillis = System.currentTimeMillis(); 
  }
  
  public JobsTransferorProgressNode setShowLevel(boolean showLevel) {
    this.showLevel = showLevel;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\nodes\JobsTransferorProgressNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */