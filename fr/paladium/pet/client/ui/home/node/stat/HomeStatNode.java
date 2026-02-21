package fr.paladium.pet.client.ui.home.node.stat;

import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.HoverObject;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.pet.client.ui.utils.data.ConfigClientData;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class HomeStatNode extends ANode {
  private static final Color PROGRESS_VOID_COLOR = Color.decode("#323232");
  
  private static final Color PROGRESS_VOID_UNDER_COLOR = Color.decode("#1A1A1A");
  
  private final StatType type;
  
  private final ResourceLocation logo;
  
  private final int value;
  
  private final int maxValue;
  
  private final HomeData homeData;
  
  private final List<String> texts;
  
  public HomeStatNode(HomeData homedata, double x, double y, double width, double height, StatType type, int value, int maxValue) {
    super(x, y, width, height);
    this.homeData = homedata;
    this.logo = new ResourceLocation("palapet", type.getTexture());
    this.type = type;
    this.value = value;
    this.maxValue = maxValue;
    this.texts = initTexts();
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double logoX = this.ui.width(1.979F);
    double logoY = this.ui.width(1.979F);
    GuiUtils.drawImageTransparent(this.x, this.y, this.logo, logoX, logoY);
    double textX = this.x + logoX + this.ui.width(0.894F);
    double textY = this.y + this.ui.height(0.75F);
    int fontSize = 120;
    FontObj font = Fonts.MONTSERRAT_EXTRABOLD.getFont();
    GuiUtils.drawStringWithCustomFont(mc, this.type
        .getName().toUpperCase(), textX, textY, Color.WHITE, font, fontSize);
    String text = String.valueOf(this.value + "/" + this.maxValue);
    font = Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont();
    fontSize = 40;
    textY -= height(5.0F);
    GuiUtils.drawSplittedString(mc, text, this.x + 
        width(100), textY, Color.WHITE, font, fontSize, 
        width(90.0F), TextAlign.RIGHT);
    drawProgressBar(logoY);
    drawHover(mouseX, mouseY);
  }
  
  private void drawHover(int mouseX, int mouseY) {
    if (!isHovered(mouseX, mouseY))
      return; 
    GuiUtils.hovers.add(new HoverObject(mouseX, mouseY, this.texts, getHoverFont(), getHoverColor()));
  }
  
  private void drawProgressBar(double logoY) {
    double progressSpace = this.ui.height(1.389F);
    double progressBarX = this.x;
    double progressBarY = this.y + logoY + progressSpace;
    double progressbarEndY = progressBarY + this.height - logoY - progressSpace;
    GuiUtils.drawRect(progressBarX, progressBarY, progressBarX + this.width, progressbarEndY, PROGRESS_VOID_COLOR);
    double underHeight = this.ui.height(0.648F);
    GuiUtils.drawRect(progressBarX, progressbarEndY - underHeight, progressBarX + this.width, progressbarEndY, PROGRESS_VOID_UNDER_COLOR);
    double progress = this.value / this.maxValue;
    double progressWidth = progress * this.width;
    GuiUtils.drawRect(progressBarX, progressBarY, progressBarX + progressWidth, progressbarEndY, this.type
        
        .getProgressColor());
    GuiUtils.drawRect(progressBarX, progressbarEndY - underHeight, progressBarX + progressWidth, progressbarEndY, this.type
        
        .getProgressUnderColor());
  }
  
  private List<String> initTexts() {
    ConfigClientData config = ConfigClientData.get();
    if (this.type.equals(StatType.EXPERIENCE))
      return initExperienceText(config); 
    if (this.type.equals(StatType.HAPPINESS))
      return initHappinessText(config); 
    return new ArrayList<>();
  }
  
  private List<String> initHappinessText(ConfigClientData config) {
    String tttPrefix = "pet.gui.home.stat.happiness.line.";
    List<String> tttList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String key = tttPrefix + i;
      if (i == 1) {
        tttList.add(TTT.format(key, new Object[] { Integer.valueOf(config.getHappinessLoss()) }));
      } else {
        int happinessMultiplier = (int)Math.floor(this.homeData.getHappinessMultiplier() * 100.0D);
        if (i == 4) {
          tttList.add(TTT.format(key, new Object[] { Integer.valueOf(happinessMultiplier) }));
        } else {
          tttList.add(TTT.format(key, new Object[0]));
        } 
      } 
    } 
    return tttList;
  }
  
  private List<String> initExperienceText(ConfigClientData config) {
    String tttPrefix = "pet.gui.home.stat.experience.line.";
    List<String> tttList = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      String key = tttPrefix + i;
      if (i == 5) {
        tttList.add(TTT.format(key, new Object[] { Integer.valueOf(this.homeData.getLevel()) }));
      } else {
        tttList.add(TTT.format(key, new Object[0]));
      } 
    } 
    return tttList;
  }
  
  public boolean onClick(int i, int i1, int i2) {
    return false;
  }
  
  public void onRelease(int i, int i1, int i2) {}
  
  public void onKeyTyped(char c, int i) {}
  
  public void onHover(int i, int i1) {}
  
  public void onHoverOut(int i, int i1) {}
  
  public void fixedUpdate() {}
  
  public enum StatType {
    HAPPINESS("Bonheur", "textures/ui/home/happiness_logo.png", 
      
      (String)Color.decode("#FC64C9"), (String)Color.decode("#9B3077")),
    EXPERIENCE("Expérience", "textures/ui/home/experience_logo.png", 
      
      (String)Color.decode("#5ED42A"), (String)Color.decode("#3B8818"));
    
    private final String name;
    
    private final String texture;
    
    private final Color progressColor;
    
    private final Color progressUnderColor;
    
    public String getTexture() {
      return this.texture;
    }
    
    public Color getProgressColor() {
      return this.progressColor;
    }
    
    public Color getProgressUnderColor() {
      return this.progressUnderColor;
    }
    
    StatType(String name, String texture, Color progressColor, Color progressUnderColor) {
      this.name = name;
      this.texture = texture;
      this.progressColor = progressColor;
      this.progressUnderColor = progressUnderColor;
    }
    
    public String getName() {
      if (equals(EXPERIENCE))
        return PetTranslateEnum.GUI_NODE_STATS_EXPERIENCE.text(); 
      return PetTranslateEnum.GUI_NODE_STATS_HAPPINESS.text();
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\stat\HomeStatNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */