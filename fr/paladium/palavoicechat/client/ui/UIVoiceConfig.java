package fr.paladium.palavoicechat.client.ui;

import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.checkbox.CheckboxNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.config.VoIPClientConfig;
import fr.paladium.palavoicechat.client.listener.KeyBindHandlerListener;
import fr.paladium.palavoicechat.client.ui.node.DisplayNode;
import fr.paladium.palavoicechat.client.ui.node.MicrophoneSettingsSection;
import fr.paladium.palavoicechat.client.ui.node.SpeakerSettingsSection;
import fr.paladium.palavoicechat.client.ui.node.SpeakingPlayerNode;
import fr.paladium.palavoicechat.client.ui.node.VolumeSliderNode;
import fr.paladium.palavoicechat.client.ui.overlay.UIOverlayVoiceChat;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.common.network.BBPacketGetPlayersVoice;
import fr.paladium.palavoicechat.common.network.CSDisconnectVCPacket;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.checkbox.CheckboxNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@UIData(active = true, background = false, anchorX = Align.START, anchorY = Align.START)
public class UIVoiceConfig extends UI {
  private final TweenAnimator openCloseAnimator;
  
  public TweenAnimator getOpenCloseAnimator() {
    return this.openCloseAnimator;
  }
  
  private final StringSignal playerSearchSignal = new StringSignal("");
  
  public StringSignal getPlayerSearchSignal() {
    return this.playerSearchSignal;
  }
  
  private final BooleanSignal editMicSignal = new BooleanSignal(false);
  
  public BooleanSignal getEditMicSignal() {
    return this.editMicSignal;
  }
  
  private final BooleanSignal editSpeakerSignal = new BooleanSignal(false);
  
  public BooleanSignal getEditSpeakerSignal() {
    return this.editSpeakerSignal;
  }
  
  private final BooleanSignal mutedSignal = new BooleanSignal(IoNettyVoIPClient.getInstance().isMutedRaw());
  
  public BooleanSignal getMutedSignal() {
    return this.mutedSignal;
  }
  
  private final ListSignal<String> connectedPlayersSignal = new ListSignal(new ArrayList());
  
  public ListSignal<String> getConnectedPlayersSignal() {
    return this.connectedPlayersSignal;
  }
  
  public UIVoiceConfig() {
    this.openCloseAnimator = TweenAnimator.create(1.0F);
    this.openCloseAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
  }
  
  public void init() {
    (new BBPacketGetPlayersVoice()).subscribe(response -> this.connectedPlayersSignal.set(response.getPlayerNames()))
      
      .send();
    ContainerNode.create(2537.0D, 0.0D, 290.0D, 1080.0D)
      .body(container -> {
          ImageNode.create(-298.0D, 0.0D, 612.0D, 613.0D).resource(Resource.of((ResourceLocation)MCResource.of("palavoicechat", "textures/ui/top_right_gradient.png"))).attach(container);
          ImageNode.create(-617.0D, 0.0D, 931.0D, 1080.0D).resource(Resource.of((ResourceLocation)MCResource.of("palavoicechat", "textures/ui/right_gradient.png"))).attach(container);
          TextNode.create(container.w(), 17.0D).text(Text.create("Voice Chat", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchorX(Align.END).attach(container);
          FlexNode.horizontal(container.w(), 28.0D, 36.0D).align(Align.CENTER).margin(16.0D).body(()).anchorX(Align.END).attach(container);
          TextFieldNode.create(4.0D, 75.0D, 282.0D, 34.0D).placeholder("Rechercher").margin(12.0D).onChange(()).attach(container);
          ContainerNode.create(0.0D, 125.0D, 290.0D, 236.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(280.0D, 0.0D, 236.0D, 10.0D)).body(()).attach(container);
          TextNode.create(container.w() - 4.0D, 381.0D).text(Text.create("Vos paramètres", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 15.0F, Color.WHITE).shadow().shadow(0.0F, 1.0F))).anchorX(Align.END).attach(container);
          ((TextButtonNode)((TextButtonNode)((TextButtonNode)TextButtonNode.create(4.0D, 388.0D).text("Désactiver").textInfo(TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 10.0F, Color.WHITE).shadow().shadow(0.0F, 1.0F)).horizontalPadding(5.0D).verticalPadding(3.0D).onInit(())).onClick(())).onUpdate(())).attach(container);
          DisplayNode.create(0.0D, 420.0D, container.w(), 52.0D).border(4.0D).hoverable(()).body(()).attach(container);
          ((CheckboxNode)CheckboxNode.create(container.w() - 20.0D, 487.0D, 16.0D).checked(PalaVoiceChatMod.getClientProxy().getClientConfig().isAnimatedPlayerHead()).body(())).onChange(()).attach(container);
          MicrophoneSettingsSection.create(0.0D, 530.0D, container.w(), 210.0D).attach(container);
          SpeakerSettingsSection.create(0.0D, 840.0D, container.w(), 100.0D).attach(container);
        }).onAnimate((node, animator, value) -> node.x(1606.0D + 931.0D * value))
      
      .animate(this.openCloseAnimator)
      .attach(this);
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.isCancelled())
      return; 
    if (((Boolean)this.editMicSignal.getOrDefault()).booleanValue())
      context.cancel(() -> this.editMicSignal.set(Boolean.valueOf(false))); 
    if (((Boolean)this.editSpeakerSignal.getOrDefault()).booleanValue())
      context.cancel(() -> this.editSpeakerSignal.set(Boolean.valueOf(false))); 
  }
  
  public void keyPressed(char c, int keyCode, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.isCancelled())
      return; 
    if (keyCode == KeyBindHandlerListener.VOICE_CONFIG.func_151463_i()) {
      context.cancel(() -> ZUI.close(this));
    } else if (keyCode == 1) {
      if (((Boolean)this.editMicSignal.getOrDefault()).booleanValue())
        context.cancel(() -> this.editMicSignal.set(Boolean.valueOf(false))); 
      if (((Boolean)this.editSpeakerSignal.getOrDefault()).booleanValue())
        context.cancel(() -> this.editSpeakerSignal.set(Boolean.valueOf(false))); 
    } 
  }
  
  public boolean close() {
    IoNettyVoIPClient.getInstance().setTestingMic(false);
    this.openCloseAnimator.clear();
    this.openCloseAnimator.sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.openCloseAnimator.setCallback(tween -> ZUI.close(this, true));
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\UIVoiceConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */