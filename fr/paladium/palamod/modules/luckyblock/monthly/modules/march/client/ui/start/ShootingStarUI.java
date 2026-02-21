package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.start;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSShootingStarHandlePacket;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ShootingStarUI extends UI {
  private final long animationStartMillis = System.currentTimeMillis();
  
  private double starX = 0.0D;
  
  private double starY = 0.0D;
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
    long now = System.currentTimeMillis();
    long elapsedMillis = now - this.animationStartMillis;
    double speedX = 0.515D;
    double speedY = 0.115D;
    this.starY = getHeight() / 2.5D;
    this.starX += 0.515D * elapsedMillis / 16.9D;
    DrawUtils.RESOURCE.drawImage(this.starX, this.starY, getWidth() / 10.0D, getHeight() / 10.0D, Resource.of(new ResourceLocation("palamod", "textures/ui/march/star/shooting_star.png")).nearest());
    if (this.starX > getWidth() || this.starY > getHeight()) {
      Minecraft.func_71410_x().func_147108_a(null);
      close();
    } 
  }
  
  public boolean close() {
    CSShootingStarHandlePacket cSShootingStarHandlePacket = new CSShootingStarHandlePacket();
    CommonMarch.getInstance().getNetwork().sendToServer((IMessage)cSShootingStarHandlePacket);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\clien\\ui\start\ShootingStarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */