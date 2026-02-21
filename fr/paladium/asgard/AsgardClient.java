package fr.paladium.asgard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class AsgardClient {
  private int titlesTimer;
  
  public int getTitlesTimer() {
    return this.titlesTimer;
  }
  
  private String displayedTitle = "";
  
  public String getDisplayedTitle() {
    return this.displayedTitle;
  }
  
  private String displayedSubTitle = "";
  
  private int titleFadeIn;
  
  private int titleDisplayTime;
  
  private int titleFadeOut;
  
  public String getDisplayedSubTitle() {
    return this.displayedSubTitle;
  }
  
  public int getTitleFadeIn() {
    return this.titleFadeIn;
  }
  
  public int getTitleDisplayTime() {
    return this.titleDisplayTime;
  }
  
  public int getTitleFadeOut() {
    return this.titleFadeOut;
  }
  
  private Minecraft mc = FMLClientHandler.instance().getClient();
  
  public Minecraft getMc() {
    return this.mc;
  }
  
  private GuiIngameForge gui = null;
  
  private Field recordT;
  
  private static AsgardClient instance;
  
  public GuiIngameForge getGui() {
    return this.gui;
  }
  
  public Field getRecordT() {
    return this.recordT;
  }
  
  public static AsgardClient getInstance() {
    return instance;
  }
  
  AsgardClient() {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
    instance = this;
    setDefaultTitlesTimes();
    try {
      this.recordT = GuiIngame.class.getDeclaredField("field_73845_h");
    } catch (Exception e) {
      try {
        this.recordT = GuiIngame.class.getDeclaredField("recordPlayingUpFor");
      } catch (Exception e1) {
        e1.printStackTrace();
      } 
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRender(RenderGameOverlayEvent.Post evt) {
    if (this.gui == null)
      this.gui = (GuiIngameForge)this.mc.field_71456_v; 
    if (evt.type == RenderGameOverlayEvent.ElementType.ALL) {
      ScaledResolution res = this.gui.getResolution();
      if (res == null)
        return; 
      int width = res.func_78326_a();
      int height = res.func_78328_b();
      renderTitle(width, height, evt.partialTicks);
    } 
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.RenderTickEvent tick) {
    if (tick.phase == TickEvent.Phase.START && 
      this.titlesTimer > 0) {
      this.titlesTimer--;
      if (this.titlesTimer <= 0) {
        this.displayedTitle = "";
        this.displayedSubTitle = "";
      } 
    } 
  }
  
  public FontRenderer getFontRenderer() {
    return this.mc.field_71466_p;
  }
  
  protected void renderTitle(int width, int height, float partialTicks) {
    if (this.titlesTimer > 0) {
      this.mc.field_71424_I.func_76320_a("titleAndSubtitle");
      float age = this.titlesTimer - partialTicks;
      int opacity = 255;
      if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime) {
        float f3 = (this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut) - age;
        opacity = (int)(f3 * 255.0F / this.titleFadeIn);
      } 
      if (this.titlesTimer <= this.titleFadeOut)
        opacity = (int)(age * 255.0F / this.titleFadeOut); 
      opacity = MathHelper.func_76125_a(opacity, 0, 255);
      if (opacity > 8) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((width / 2), (height / 2), 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.scale(4.0F, 4.0F, 4.0F);
        int l = opacity << 24 & 0xFF000000;
        getFontRenderer().func_85187_a(this.displayedTitle, 
            -getFontRenderer().func_78256_a(this.displayedTitle) / 2, -10, 0xFFFFFF | l, true);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0F, 2.0F, 2.0F);
        getFontRenderer().func_85187_a(this.displayedSubTitle, 
            -getFontRenderer().func_78256_a(this.displayedSubTitle) / 2, 5, 0xFFFFFF | l, true);
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
      } 
      this.mc.field_71424_I.func_76319_b();
    } 
  }
  
  public void setDefaultTitlesTimes() {
    this.titleFadeIn = 10;
    this.titleDisplayTime = 70;
    this.titleFadeOut = 20;
  }
  
  public void displayTitle(String title, String subTitle, int timeFadeIn, int displayTime, int timeFadeOut) {
    if (title == null && subTitle == null && timeFadeIn < 0 && displayTime < 0 && timeFadeOut < 0) {
      this.displayedTitle = "";
      this.displayedSubTitle = "";
      this.titlesTimer = 0;
    } else if (title != null) {
      this.displayedTitle = title;
      this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
    } else if (subTitle != null) {
      this.displayedSubTitle = subTitle;
    } else {
      if (timeFadeIn >= 0)
        this.titleFadeIn = timeFadeIn; 
      if (displayTime >= 0)
        this.titleDisplayTime = displayTime; 
      if (timeFadeOut >= 0)
        this.titleFadeOut = timeFadeOut; 
      if (this.titlesTimer > 0)
        this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut; 
    } 
  }
  
  public void setRecordPlaying(String text, boolean isPlaying) {
    this.gui.func_110326_a(text, isPlaying);
    try {
      this.recordT.setAccessible(true);
      this.recordT.set(this.gui, Integer.valueOf(this.titleDisplayTime));
      this.recordT.setAccessible(false);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void setRecordPlaying(IChatComponent component, boolean isPlaying) {
    setRecordPlaying(component.func_150260_c(), isPlaying);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\AsgardClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */