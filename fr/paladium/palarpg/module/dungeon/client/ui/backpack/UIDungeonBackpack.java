package fr.paladium.palarpg.module.dungeon.client.ui.backpack;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.slot.FakeSlotNode;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
public class UIDungeonBackpack extends UI {
  private final int size;
  
  private final Set<RPGDungeonPlayerData.RPGDungeonItem> backpackItems;
  
  public UIDungeonBackpack(Integer size) {
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData("dungeon");
    if (data == null) {
      this.size = 0;
      this.backpackItems = null;
      return;
    } 
    this.size = size.intValue();
    this
      
      .backpackItems = (Set<RPGDungeonPlayerData.RPGDungeonItem>)data.getBackpack().stream().sorted((o1, o2) -> {
          int rarityCompare = o2.getRarity().compareTo((Enum)o1.getRarity());
          if (rarityCompare != 0)
            return rarityCompare; 
          int nameCompare = o1.getItem().func_82833_r().compareToIgnoreCase(o2.getItem().func_82833_r());
          return (nameCompare != 0) ? nameCompare : Integer.compare((o2.getItem()).field_77994_a, (o1.getItem()).field_77994_a);
        }).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
  }
  
  public void init() {
    if (this.backpackItems == null) {
      ZUI.close(this);
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les données du sac à dos", "paladium")).send();
      return;
    } 
    if (!PalaRPGMod.proxy.isDungeonWorld()) {
      ZUI.close(this);
      (new Notification(Notification.NotificationType.ERROR, "Vous ne pouvez pas ouvrir votre sac à dos en dehors d'un donjon", "paladium")).send();
      return;
    } 
    setTransition(new UIDungeonBackpackTransition());
    BackgroundNode.create(108.0D, 80.0D, 1704.0D, 1032.0D)
      .attach(this);
    TextNode.create(150.0D, 82.0D)
      .text(Text.create("backpack ", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE).lineHeight(-7.0F)).add(Text.create("(" + this.size + ")", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 40.0F, Color.WHITE))).verticalAlign(Align.END))
      .attach(this);
    BackgroundElementNode.create(1368.0D, 112.0D, 407.0D, 52.0D)
      .color(Color.decode("#282828"))
      .shadowColor(Color.decode("#1F1F1F"))
      .shadow(0.0F, 4.0F)
      .cornerSize(5.0D)
      .body(background -> {
          ImageNode.create(20.0D, background.dh(2.0D) + 1.0D).resource(new ResourceLocation("palarpg", "textures/gui/utils/backpack.png")).color(Color.decode("#FDCA00")).anchorY(Align.CENTER).size(17.0D, 14.0D).attach(background);
          TextNode.create(54.0D, background.dh(2.0D)).text(Text.create("stockage restant", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 15.0F, Color.WHITE))).anchorY(Align.CENTER).attach(background);
          TextNode.create(background.aw(-19.0D), background.dh(2.0D)).text(Text.create(String.valueOf(this.size - this.backpackItems.size()), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, Color.WHITE))).onInit(()).anchor(Align.END, Align.CENTER).attach(background);
        }).attach(this);
    ContainerNode.create(108.0D, 200.0D, 1704.0D, 880.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(1677.0D, 70.0D, 775.0D, 3.0D))
      .body(container -> {
          for (int i = 0; i < this.size; i++)
            FakeSlotNode.create((47 + 187 * i % 9), (70 + 167 * i / 9), 108.0D).item((this.backpackItems.size() > i) ? ((RPGDungeonPlayerData.RPGDungeonItem)this.backpackItems.stream().skip(i).findFirst().get()).getItem() : null).enabled(()).attach(container); 
        }).attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    float opacity = 0.0F;
    if (getTransition() != null) {
      if (getTransition().getIn() != null && getTransition().getIn().isRunning())
        opacity = getTransition().getIn().getAnimator().getValue(); 
      if (getTransition().getOut() != null && getTransition().getOut().isRunning())
        opacity = getTransition().getOut().getAnimator().getValue(); 
    } 
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), (new Color(14, 14, 14)).copyAlpha(opacity).toGradient((new Color(14, 14, 14)).copyAlpha(opacity * 0.6F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
  }
  
  private static class UIDungeonBackpackTransition extends Transition {
    public UIDungeonBackpackTransition() {
      super(new UIDungeonBackpackInTransition(null), new UIDungeonBackpackOutTransition(null));
    }
    
    private static class UIDungeonBackpackInTransition extends Transition.In {
      private UIDungeonBackpackInTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated(0.0D, (1080.0F * (1.0F - getAnimator().getValue())), 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
    
    private static class UIDungeonBackpackOutTransition extends Transition.Out {
      private UIDungeonBackpackOutTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_IN).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated(0.0D, (1080.0F * (1.0F - getAnimator().getValue())), 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\backpack\UIDungeonBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */