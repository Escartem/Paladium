package fr.paladium.palaclicker.client.ui.node.left;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.common.network.packet.click.BBPacketClickerClick;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClickerNode extends Node {
  private static final Resource ITEM_GLOW_TEXTURE = Resource.of(new ResourceLocation("palaclicker", "textures/gui/left/item_glow.png"));
  
  private static final Resource CURSOR_TEXTURE = Resource.of(new ResourceLocation("palaclicker", "textures/gui/left/cursor.png"));
  
  private final TweenAnimator animator;
  
  private long lastClick;
  
  protected ClickerNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    hoverDuration(100L);
    this.animator = TweenAnimator.create(1.0F);
    onClick((node, mouseX, mouseY, clickType) -> {
          if (clickType != ClickType.LEFT)
            return; 
          long now = System.currentTimeMillis();
          long diff = now - this.lastClick;
          if (diff < 50L)
            return; 
          this.lastClick = now;
          (new BBPacketClickerClick()).subscribe(()).send();
        });
  }
  
  public static ClickerNode create(double x, double y, double width, double height) {
    return new ClickerNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    GL11.glPushMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    GL11.glRotated(System.currentTimeMillis() / 20.0D % 360.0D, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ITEM_GLOW_TEXTURE.linear());
    GL11.glPopMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    double scale = (this.animator.getTimeline() != null) ? this.animator.getValue() : (1.0F + hoverValue(0.1F));
    GL11.glScaled(scale, scale, 1.0D);
    GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
    DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - dw(4.0D), getY() + dh(2.0D) - dw(4.0D), dw(2.0D), (ItemStack)((UIClicker)getUi()).getItemSignal().getOrDefault());
    GL11.glTranslated(0.0D, 0.0D, 1.0D);
    DrawUtils.RESOURCE.drawImage(getX() + dw(4.0D), getY() + dw(1.7D), CURSOR_TEXTURE.linear());
    GL11.glTranslated(0.0D, 0.0D, -1.0D);
    GL11.glPopMatrix();
  }
  
  public void drawSkeleton(double mouseX, double mouseY, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    GL11.glRotated(System.currentTimeMillis() / 20.0D % 360.0D, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ITEM_GLOW_TEXTURE.linear());
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\left\ClickerNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */