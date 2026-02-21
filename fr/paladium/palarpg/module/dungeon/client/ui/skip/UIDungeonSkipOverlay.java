package fr.paladium.palarpg.module.dungeon.client.ui.skip;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.module.dungeon.common.network.skip.BBPacketRPGDungeonSkip;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.Optional;
import lombok.NonNull;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

@UIData(background = false, anchorX = Align.END, anchorY = Align.END)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, always = true), interaction = @UIDataOverlayInteraction(active = true, cancelClick = true, cancelKeyboard = true, cancelScroll = false))
public class UIDungeonSkipOverlay extends UI {
  private final String roomId;
  
  private final TweenAnimator appearAnimator;
  
  public UIDungeonSkipOverlay(@NonNull String roomId) {
    if (roomId == null)
      throw new NullPointerException("roomId is marked non-null but is null"); 
    this.roomId = roomId;
    this.appearAnimator = TweenAnimator.create(0.0F);
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public void init() {
    this.appearAnimator.setValue(0.0F);
    this.appearAnimator.sequence(700.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    ContainerNode.create(500.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          BackgroundNode.create(1520.0D, 940.0D, 373.0D, 85.0D).innerRadius(4.0D).outerRadius(14.0D).attach(container);
          TextNode.create(1542.0D, 955.0D).text(Text.create("bloqué ?", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 19.0F, Color.WHITE))).attach(container);
          TextNode.create(1544.0D, 989.0D).text(Text.create("Passer à la salle suivante", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 12.0F, Color.WHITE))).attach(container);
          ButtonNode.create(1817.0D, 964.0D).size(59.0D, 39.0D).body(()).onClick(()).attach(container);
        }).onAnimate((node, animator, value) -> node.x((500.0F * (1.0F - value))))
      .animate(this.appearAnimator)
      .attach(this);
  }
  
  public void update() {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      ZUI.close(this); 
    if (!((DungeonWorld)optionalDungeonWorld.get()).getActiveRoomId().equals(this.roomId))
      ZUI.close(this); 
  }
  
  @SubscribeEvent
  public void onKeyPressed(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    if (Keyboard.isKeyDown(68) && ZUI.isOpen(this)) {
      (new BBPacketRPGDungeonSkip(this.roomId)).send();
      ZUI.close(this);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\skip\UIDungeonSkipOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */