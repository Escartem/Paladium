package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.ui.UIVoiceConfig;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.client.voip.micro.MicrophoneActivationType;
import fr.paladium.palavoicechat.client.voip.util.DataLines;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.TargetDataLine;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class MicrophoneSettingsSection extends ContainerNode {
  protected MicrophoneSettingsSection(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static MicrophoneSettingsSection create(double x, double y, double width, double height) {
    return new MicrophoneSettingsSection(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    BooleanSignal editMicSignal = ((UIVoiceConfig)getUi()).getEditMicSignal();
    BooleanSignal editSpeakerSignal = ((UIVoiceConfig)getUi()).getEditSpeakerSignal();
    TextNode.create(w(), 0.0D)
      .text(Text.create("Microphone", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    TextNode.create(w(), 30.0D)
      .text(Text.create("Périphérique", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    DisplayNode.create(0.0D, 50.0D, w(), 38.0D)
      .border(4.0D)
      .body(display -> FlexNode.horizontal(16.0D, display.dh(2.0D), 0.0D).margin(12.0D).align(Align.CENTER).body(()).width(display.w()).anchorY(Align.CENTER).attach(display))
      
      .onClick((node, mouseX, mouseY, clickType) -> {
          if (((Boolean)editSpeakerSignal.getOrDefault()).booleanValue())
            editSpeakerSignal.set(Boolean.valueOf(false)); 
          editMicSignal.set(Boolean.valueOf(!((Boolean)editMicSignal.getOrDefault()).booleanValue()));
        }).watch((Signal)editMicSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((Node)this);
    FlexNode.vertical(-20.0D + -w(), 88.0D, w())
      .margin(8.0D)
      .body(flex -> {
          List<String> micDevices = DataLines.getDeviceNames(TargetDataLine.class);
          micDevices.forEach(());
        }).visible(node -> ((Boolean)editMicSignal.getOrDefault()).booleanValue())
      .anchorY(Align.END)
      .watch((Signal)editMicSignal)
      .attach((Node)this);
    TextNode.create(w(), 105.0D)
      .text(Text.create("Mode d'activation", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    DisplayNode.create(0.0D, 125.0D, w(), 38.0D)
      .border(4.0D)
      .selected(() -> Boolean.valueOf((PalaVoiceChatMod.getClientProxy().getClientConfig().getMicActivationType() != MicrophoneActivationType.PTT)))
      .hoverable(() -> Boolean.valueOf((PalaVoiceChatMod.getClientProxy().getClientConfig().getMicActivationType() != MicrophoneActivationType.PTT)))
      .body(display -> TextNode.create(16.0D, display.dh(2.0D)).text(Text.create("Push to talk", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchorY(Align.CENTER).attach(display))
      
      .onClick((node, mouseX, mouseY, clickType) -> PalaVoiceChatMod.getClientProxy().getClientConfig().setMicActivationType(MicrophoneActivationType.PTT))
      
      .attach((Node)this);
    DisplayNode.create(0.0D, 173.0D, w(), 38.0D)
      .border(4.0D)
      .selected(() -> Boolean.valueOf((PalaVoiceChatMod.getClientProxy().getClientConfig().getMicActivationType() != MicrophoneActivationType.VOICE)))
      .hoverable(() -> Boolean.valueOf((PalaVoiceChatMod.getClientProxy().getClientConfig().getMicActivationType() != MicrophoneActivationType.VOICE)))
      .body(display -> TextNode.create(16.0D, display.dh(2.0D)).text(Text.create("Détection de la voix", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchorY(Align.CENTER).attach(display))
      
      .onClick((node, mouseX, mouseY, clickType) -> PalaVoiceChatMod.getClientProxy().getClientConfig().setMicActivationType(MicrophoneActivationType.VOICE))
      
      .attach((Node)this);
    TextNode.create(w(), 225.0D)
      .text(Text.create("Test du micro", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    ((TextButtonNode)TextButtonNode.create(4.0D, 255.0D)
      .text("Tester")
      .textInfo(TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 10.0F, Color.WHITE).shadow().shadow(0.0F, 1.0F))
      .horizontalPadding(5.0D)
      .verticalPadding(3.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          IoNettyVoIPClient.getInstance().setTestingMic(!IoNettyVoIPClient.getInstance().isTestingMic());
          if (IoNettyVoIPClient.getInstance().isTestingMic()) {
            node.text("Stop").width(50.0D);
          } else {
            node.text("Tester").width(50.0D);
          } 
        })).attach((Node)this);
    Set<Double> thresholds = new LinkedHashSet<>();
    double i;
    for (i = 0.0D; i <= 1270.0D; i++)
      thresholds.add(Double.valueOf(i)); 
    MicrophoneActivationNode.create(72.0D, 257.0D, aw(-75.0D), 14.0D)
      .valueSet(thresholds, Double.valueOf(Math.round(127.0D + PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceActivationThreshold()) * 10.0D))
      .onChange((node, value) -> {
          double computedValue = Math.round(value.doubleValue() / 10.0D - 127.0D);
          PalaVoiceChatMod.getClientProxy().getClientConfig().setVoiceActivationThreshold(computedValue);
        }).attach((Node)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\MicrophoneSettingsSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */