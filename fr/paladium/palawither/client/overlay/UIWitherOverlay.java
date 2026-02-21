package fr.paladium.palawither.client.overlay;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.palawither.common.network.SCPacketWitherData;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@UIData(background = false, anchorY = Align.START)
@UIDataGuiscale(active = true)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, type = RenderGameOverlayEvent.ElementType.BOSSHEALTH))
public class UIWitherOverlay extends UI {
  private final Map<String, Long> updateMap = new HashMap<>();
  
  private final Map<String, Signal<SCPacketWitherData.WitherData>> dataMap = new HashMap<>();
  
  private final ListSignal<Signal<SCPacketWitherData.WitherData>> sortedDataMap = new ListSignal(new LinkedList());
  
  public void init() {
    FlexNode.vertical(0.0D, 30.0D, 1920.0D)
      .margin(20.0D)
      .body(flex -> {
          for (Signal<SCPacketWitherData.WitherData> signal : (Iterable<Signal<SCPacketWitherData.WitherData>>)this.sortedDataMap.getOrDefault())
            FlexNode.vertical(flex.dw(2.0D) - 451.0D, 0.0D, 903.0D).body(()).attach(flex); 
        }).watch((Signal)this.sortedDataMap, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void update() {
    if ((Minecraft.func_71410_x()).field_71439_g == null) {
      ZUI.close(this);
      return;
    } 
    Set<SCPacketWitherData.WitherData> toRemove = new HashSet<>();
    for (Signal<SCPacketWitherData.WitherData> signal : this.dataMap.values()) {
      long lastUpdate = ((Long)this.updateMap.getOrDefault(((SCPacketWitherData.WitherData)signal.getOrDefault()).getUniqueId(), Long.valueOf(System.currentTimeMillis()))).longValue();
      if (System.currentTimeMillis() - lastUpdate > 5000L || !((SCPacketWitherData.WitherData)signal.getOrDefault()).isInRange()) {
        toRemove.add(signal.getOrDefault());
        continue;
      } 
      if (((SCPacketWitherData.WitherData)signal.getOrDefault()).isInvocation() && (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72899_e(((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosX(), ((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosY(), ((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosZ())) {
        TileEntity te = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147438_o(((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosX(), ((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosY(), ((SCPacketWitherData.WitherData)signal.getOrDefault()).getPosZ());
        if (!(te instanceof TileEntityWitherReceptacle)) {
          toRemove.add(signal.getOrDefault());
          continue;
        } 
        TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)te;
        if (!receptacle.isActive())
          toRemove.add(signal.getOrDefault()); 
      } 
    } 
    toRemove.forEach(data -> (Signal)this.dataMap.remove(data.getUniqueId()));
    if (this.dataMap.isEmpty())
      ZUI.close(this); 
    List<Signal<SCPacketWitherData.WitherData>> list = (List<Signal<SCPacketWitherData.WitherData>>)this.dataMap.values().stream().sorted((a, b) -> (((SCPacketWitherData.WitherData)a.getOrDefault()).isInvocation() && !((SCPacketWitherData.WitherData)b.getOrDefault()).isInvocation()) ? -1 : (((!((SCPacketWitherData.WitherData)a.getOrDefault()).isInvocation() && ((SCPacketWitherData.WitherData)b.getOrDefault()).isInvocation()) || (((SCPacketWitherData.WitherData)a.getOrDefault()).isPaused() && !((SCPacketWitherData.WitherData)b.getOrDefault()).isPaused())) ? 1 : ((!((SCPacketWitherData.WitherData)a.getOrDefault()).isPaused() && ((SCPacketWitherData.WitherData)b.getOrDefault()).isPaused()) ? -1 : ((((SCPacketWitherData.WitherData)a.getOrDefault()).isInvocation() && ((SCPacketWitherData.WitherData)b.getOrDefault()).isInvocation()) ? Float.compare(((SCPacketWitherData.WitherData)a.getOrDefault()).getPercentage(), ((SCPacketWitherData.WitherData)b.getOrDefault()).getPercentage()) : ((!((SCPacketWitherData.WitherData)a.getOrDefault()).isInvocation() && !((SCPacketWitherData.WitherData)b.getOrDefault()).isInvocation()) ? Double.compare(((SCPacketWitherData.WitherData)a.getOrDefault()).getDistance(), ((SCPacketWitherData.WitherData)b.getOrDefault()).getDistance()) : 0))))).limit(7L).collect(Collectors.toCollection(LinkedList::new));
    this.sortedDataMap.set(list);
  }
  
  @NonNull
  public UIWitherOverlay add(@NonNull SCPacketWitherData.WitherData data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    if (!data.isInRange())
      return this; 
    String uniqueId = data.getUniqueId();
    if (data.getName() == null || data.getName().isEmpty()) {
      this.dataMap.remove(uniqueId);
      return this;
    } 
    this.updateMap.put(uniqueId, Long.valueOf(System.currentTimeMillis()));
    if (!this.dataMap.containsKey(uniqueId)) {
      this.dataMap.put(uniqueId, new Signal(data));
      return this;
    } 
    Signal<SCPacketWitherData.WitherData> signal = this.dataMap.get(uniqueId);
    if (data.equals(signal.getOrDefault()))
      return this; 
    signal.set(data);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\overlay\UIWitherOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */