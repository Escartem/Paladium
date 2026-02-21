package fr.paladium.palarpg.module.profile.client.ui.overlay;

import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.profile.client.ui.kit.hotbar.PlayerHudContainer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

@UIData(background = false, anchorX = Align.START, anchorY = Align.START)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, type = RenderGameOverlayEvent.ElementType.ALL))
@UIDataGuiscale(active = true)
public class UIOverlayRPGPlayerHUD extends UI {
  private static final Resource DAMAGE_1 = Resource.of(new ResourceLocation("palarpg", "textures/gui/damage/1.png")).blocking().linear();
  
  private static final Resource DAMAGE_2 = Resource.of(new ResourceLocation("palarpg", "textures/gui/damage/2.png")).blocking().linear();
  
  private static final Resource POISON_1 = Resource.of(new ResourceLocation("palarpg", "textures/gui/poison/1.png")).blocking().linear();
  
  private static final Resource POISON_2 = Resource.of(new ResourceLocation("palarpg", "textures/gui/poison/2.png")).blocking().linear();
  
  private final ListSignal<DungeonPlayer> offlineDJPlayerSignal = new ListSignal(new ArrayList());
  
  private double lerpHealth;
  
  public void init() {
    if ((Minecraft.func_71410_x()).field_71439_g == null || (Minecraft.func_71410_x()).field_71439_g.field_70170_p == null) {
      ZUI.close(this);
      return;
    } 
    ImageNode.create(0.0D, 0.0D, 765.0D, 810.0D)
      .resource(Resource.of(new ResourceLocation("palarpg", "textures/profile/hotbar/blur.png")))
      .attach(this);
    ContainerNode.create(45.0D, 30.0D, 441.0D, 136.0D)
      .body(node -> {
          if (this.offlineDJPlayerSignal.size() <= 0)
            return; 
          DungeonPlayer self = ((List<DungeonPlayer>)this.offlineDJPlayerSignal.getOrDefault()).stream().filter(()).findFirst().get();
          PlayerHudContainer.create(0.0D, 0.0D, node.w(), node.h()).dungeonPlayer(self).attach(node);
          ((FlexNode)FlexNode.vertical(0.0D, node.ah(30.0D), node.w()).margin(20.0D).onInit(())).attach(node);
        }).watch((Signal)this.offlineDJPlayerSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    this.lerpHealth = lerpByFramerate(this.lerpHealth, ((Minecraft.func_71410_x()).field_71439_g.func_110143_aJ() / (Minecraft.func_71410_x()).field_71439_g.func_110138_aP()), 0.04D, 0.04D, false);
    Optional<DungeonPlayer> optionalDungeonPlayer = DungeonPlayer.getClient();
    if (optionalDungeonPlayer.isPresent() && ((DungeonPlayer)optionalDungeonPlayer.get()).isAlive() && this.lerpHealth < 1.0D) {
      ScaledResolution scaled = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
      double scaledWidth = scaled.func_78327_c();
      double scaledHeight = scaled.func_78324_d();
      float dmg1 = (float)(((this.lerpHealth <= 1.0D && this.lerpHealth >= 0.6000000238418579D) ? ((1.0D - this.lerpHealth) / 0.4000000059604645D) : 0.0D) + ((this.lerpHealth <= 0.6000000238418579D && this.lerpHealth >= 0.30000001192092896D) ? ((this.lerpHealth - 0.30000001192092896D) / 0.30000001192092896D) : 0.0D));
      float dmg2 = (float)((this.lerpHealth <= 0.6000000238418579D && this.lerpHealth >= 0.30000001192092896D) ? ((0.6000000238418579D - this.lerpHealth) / 0.30000001192092896D) : ((this.lerpHealth < 0.30000001192092896D) ? 1.0D : 0.0D));
      GL11.glPushMatrix();
      GL11.glPushAttrib(1048575);
      GL11.glAlphaFunc(516, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      boolean poison = (Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76436_u);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, Math.min(dmg1, 1.0F));
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, scaledWidth, scaledHeight, poison ? POISON_1 : DAMAGE_1);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, Math.min(dmg2, 1.0F));
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, scaledWidth, scaledHeight, poison ? POISON_2 : DAMAGE_2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
    } 
  }
  
  public void update() {
    if ((Minecraft.func_71410_x()).field_71439_g == null || (Minecraft.func_71410_x()).field_71439_g.field_70170_p == null || !DungeonWorld.getClient().isPresent())
      return; 
    DungeonWorld dungeonWorld = DungeonWorld.getClient().get();
    this.offlineDJPlayerSignal.set(dungeonWorld.getPlayers().stream().filter(DungeonPlayer::isOnline).collect(Collectors.toList()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\overlay\UIOverlayRPGPlayerHUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */