package fr.paladium.palaforgeutils.lib.tooltip;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.client.DrawHoveringTextEvent;
import fr.paladium.zephyrui.lib.event.impl.draw.UIDrawHoverEvent;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.debug.UIDataDebug;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.opengl.GL11;

public class CustomTooltipListener {
  private static final CustomTooltipFakeUI FAKE_UI = new CustomTooltipFakeUI();
  
  private ItemStack currentItem;
  
  private CustomTooltip.CustomTooltipData currentTooltip;
  
  private int currentScaleFactor = -1;
  
  @SubscribeEvent
  public void onItemTooltip(ItemTooltipEvent event) {
    if (event.itemStack != null)
      this.currentItem = event.itemStack; 
  }
  
  @SubscribeEvent
  public void onDrawUIHover(UIDrawHoverEvent event) {
    if (this.currentItem != null) {
      Optional<CustomTooltip.CustomTooltipData> optionalTooltip = CustomTooltip.getTooltipData(this.currentItem);
      if (optionalTooltip.isPresent()) {
        this.currentTooltip = optionalTooltip.get();
        try {
          this.currentScaleFactor = "UIItemListOverlay".equals(event.getUi().getClass().getSimpleName()) ? (new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d)).func_78325_e() : ((Integer)event.getUi().getScaleFactor().getOrDefault()).intValue();
        } catch (Throwable e) {
          this.currentScaleFactor = ((Integer)event.getUi().getScaleFactor().getOrDefault()).intValue();
        } 
        event.setCanceled(true);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onDrawItemTooltip(DrawHoveringTextEvent.PreItem event) {
    if (event.getItemStack() != null)
      this.currentItem = event.getItemStack(); 
    if (this.currentItem != null) {
      Optional<CustomTooltip.CustomTooltipData> optionalTooltip = CustomTooltip.getTooltipData(this.currentItem);
      if (optionalTooltip.isPresent()) {
        this.currentTooltip = optionalTooltip.get();
        this.currentScaleFactor = (new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d)).func_78325_e();
        event.setCanceled(true);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onDrawScreenPost(GuiScreenEvent.DrawScreenEvent.Post event) {
    if (this.currentItem != null && this.currentTooltip != null && this.currentTooltip.getPredicate().test(this.currentItem)) {
      if (this.currentItem != FAKE_UI.itemStack) {
        FAKE_UI.itemStack = this.currentItem;
        FAKE_UI.tooltipData = this.currentTooltip;
        FAKE_UI.reload();
      } 
      ScaledResolution scaled = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
      double scaledWidth = scaled.func_78327_c();
      double scaledHeight = scaled.func_78324_d();
      GL11.glPushMatrix();
      GL11.glTranslated(event.mouseX, event.mouseY, 1500.0D);
      GL11.glScaled(this.currentScaleFactor / 4.0D, this.currentScaleFactor / 4.0D, 1.0D);
      try {
        double scaleX, scaleY;
        Node node = (Node)FAKE_UI.getNodeList().getFirst();
        double tooltipWidth = node.getWidth();
        double tooltipHeight = node.getHeight();
        double baseRatio = 1.7777777777777777D;
        double ratio = scaledWidth / scaledHeight;
        if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D) {
          scaleX = 5.208333333333333E-4D * scaledWidth;
          scaleY = 9.25925925925926E-4D * scaledWidth / 16.0D * 9.0D;
        } else {
          scaleX = 5.208333333333333E-4D * scaledHeight / 9.0D * 16.0D;
          scaleY = 9.25925925925926E-4D * scaledHeight;
        } 
        GL11.glScaled(scaleX, scaleY, 1.0D);
        double totalScaleX = this.currentScaleFactor / 4.0D * scaleX;
        double totalScaleY = this.currentScaleFactor / 4.0D * scaleY;
        double offsetX = 32.0D;
        double offsetY = -(tooltipHeight + 20.0D);
        double rightEdge = event.mouseX + (offsetX + tooltipWidth) * totalScaleX;
        double topEdge = event.mouseY + offsetY * totalScaleY;
        double bottomEdge = event.mouseY;
        if (rightEdge > scaledWidth)
          if (event.mouseX + (offsetX - tooltipWidth - 32.0D) * totalScaleX < 0.0D) {
            double overflow = rightEdge - scaledWidth;
            offsetX -= overflow / totalScaleX;
          } else {
            offsetX = -tooltipWidth - 32.0D;
          }  
        if (topEdge < 0.0D) {
          double overflow = -topEdge;
          offsetY += overflow / totalScaleY;
        } 
        if (bottomEdge > scaledHeight) {
          double overflow = bottomEdge - scaledHeight;
          offsetY -= overflow / totalScaleY;
        } 
        node.position(offsetX, offsetY).render(offsetX, offsetY, event.renderPartialTicks);
      } finally {
        GL11.glPopMatrix();
        this.currentItem = null;
        this.currentTooltip = null;
        this.currentScaleFactor = -1;
      } 
    } 
  }
  
  @UIDataDebug(hotreload = false, profiler = false)
  private static class CustomTooltipFakeUI extends UI {
    private ItemStack itemStack;
    
    private CustomTooltip.CustomTooltipData tooltipData;
    
    private CustomTooltipFakeUI() {}
    
    public void init() {
      if (this.itemStack != null && this.tooltipData != null)
        ((CustomTooltipNode)this.tooltipData.getNode().get()).item(this.itemStack).attach(this); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tooltip\CustomTooltipListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */