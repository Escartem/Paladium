package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.ui.UIVoiceConfig;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
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
import java.util.List;
import javax.sound.sampled.SourceDataLine;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class SpeakerSettingsSection extends ContainerNode {
  protected SpeakerSettingsSection(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static SpeakerSettingsSection create(double x, double y, double width, double height) {
    return new SpeakerSettingsSection(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    BooleanSignal editMicSignal = ((UIVoiceConfig)getUi()).getEditMicSignal();
    BooleanSignal editSpeakerSignal = ((UIVoiceConfig)getUi()).getEditSpeakerSignal();
    TextNode.create(w(), 0.0D)
      .text(Text.create("Haut parleur", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    TextNode.create(w(), 30.0D)
      .text(Text.create("Périphérique", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    DisplayNode.create(0.0D, 60.0D, w(), 38.0D)
      .border(4.0D)
      .body(display -> FlexNode.horizontal(16.0D, display.dh(2.0D), 0.0D).margin(12.0D).align(Align.CENTER).body(()).width(display.w()).anchorY(Align.CENTER).attach(display))
      
      .onClick((node, mouseX, mouseY, clickType) -> {
          if (((Boolean)editMicSignal.getOrDefault()).booleanValue())
            editMicSignal.set(Boolean.valueOf(false)); 
          editSpeakerSignal.set(Boolean.valueOf(!((Boolean)editSpeakerSignal.getOrDefault()).booleanValue()));
        }).watch((Signal)editSpeakerSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((Node)this);
    FlexNode.vertical(-20.0D + -w(), 98.0D, w())
      .margin(8.0D)
      .body(flex -> {
          List<String> speakerDevices = DataLines.getDeviceNames(SourceDataLine.class);
          speakerDevices.forEach(());
        }).visible(node -> ((Boolean)editSpeakerSignal.getOrDefault()).booleanValue())
      .anchorY(Align.END)
      .watch((Signal)editSpeakerSignal)
      .attach((Node)this);
    TextNode.create(w(), 115.0D)
      .text(Text.create("Volume", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    VolumeSliderNode.create(0.0D, 145.0D, w(), 0.0D)
      .value((float)PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceChatVolume() * 1000.0F)
      .onChange((node, value) -> PalaVoiceChatMod.getClientProxy().getClientConfig().setVoiceChatVolume(value.floatValue() / 1000.0D))
      
      .height(3.0D)
      .anchorY(Align.CENTER)
      .attach((Node)this);
    ImageNode.create(0.0D, 155.0D, 10.0D, 10.0D)
      .resource(Resource.of((ResourceLocation)MCResource.of("palavoicechat", "textures/icon/speaker_low.png")).nearest())
      .attach((Node)this);
    ImageNode.create(w() - 10.0D, 155.0D, 10.0D, 10.0D)
      .resource(Resource.of((ResourceLocation)MCResource.of("palavoicechat", "textures/icon/speaker.png")).nearest())
      .attach((Node)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\SpeakerSettingsSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */