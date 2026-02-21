package fr.paladium.palaclicker.client.ui.node.upgrade;

import com.google.gson.internal.LinkedTreeMap;
import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.client.ui.node.right.ClickerBuildingNode;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.common.network.packet.upgrade.CSPacketClickerUpgradeBuy;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeCondition;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeConditionObject;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeType;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;

public class ClickerUpgradeNode extends Node {
  public static final Color BORDER_COLOR = new Color(51, 8, 5);
  
  public static final Color[] COLORS = new Color[] { new Color(239, 57, 38), new Color(249, 110, 95), new Color(186, 35, 19), new Color(170, 39, 25), new Color(146, 19, 12) };
  
  private static final Color[] DISABLED_COLORS = new Color[] { new Color(67, 67, 80), new Color(117, 117, 135), new Color(59, 59, 71), new Color(42, 42, 51), new Color(34, 34, 39) };
  
  private static final Color[] BUYED_COLORS = new Color[] { new Color(38, 94, 239), new Color(95, 157, 249), new Color(19, 86, 186), new Color(25, 74, 170), new Color(12, 50, 146) };
  
  private ClickerUpgrade upgrade;
  
  private boolean unlocked;
  
  private boolean buyed;
  
  private boolean available;
  
  private PlayerClickerData data;
  
  protected ClickerUpgradeNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ClickerUpgradeNode create(double x, double y, double width, double height) {
    return new ClickerUpgradeNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    if (!this.unlocked && !this.buyed) {
      ImageNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D))
        .resource(ClickerBuildingNode.UNKOWN_TEXTURE)
        .linear(false)
        .width(dw(2.0D))
        .aspectRatio(1.0D)
        .attach(this);
    } else if (this.upgrade.getImage() != null && !this.upgrade.getImage().isEmpty()) {
      ImageNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D))
        .resource(this.upgrade.getImage())
        .linear(false)
        .width(dw(2.0D))
        .aspectRatio(1.0D)
        .attach(this);
    } else if (this.upgrade.getItem() != null && !this.upgrade.getItem().isEmpty()) {
      ItemNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D), dw(2.0D))
        .item(this.upgrade.getItemStack())
        .stackSize(false)
        .attach(this);
    } 
    this.data = ((UIClicker)getUi()).getPlayerData();
    List<String> hover = new ArrayList<>();
    if (this.unlocked) {
      hover.add("§5§l" + this.upgrade.getName());
      hover.add("");
      for (ClickerUpgradeConditionObject condition : this.upgrade.getConditions()) {
        ClickerUpgradeCondition clickerUpgradeCondition = condition.getType();
        Object value = condition.getValue();
        if (value == null)
          continue; 
        if (clickerUpgradeCondition == ClickerUpgradeCondition.QUANTITY) {
          double mappedValue = ((Double)value).doubleValue();
          boolean unvalid = (this.data.getTotalPoints() < mappedValue);
          hover.add("§8[" + (!unvalid ? "§a✔" : "§c✘") + "§8] §e" + TTT.format("clicker.gui.upgrade.hover.condition.quantity", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(mappedValue) }) }));
          continue;
        } 
        if (clickerUpgradeCondition == ClickerUpgradeCondition.TIME) {
          long mappedValue = ((Double)value).longValue();
          long days = (TimeUtils.now() - 1763748000L) / 60L / 60L / 24L;
          boolean unvalid = (days < mappedValue && days >= 0L);
          hover.add("§8[" + (!unvalid ? "§a✔" : "§c✘") + "§8] §e" + TTT.format("clicker.gui.upgrade.hover.condition.time", new Object[] { Long.valueOf(mappedValue) }));
          continue;
        } 
        if (clickerUpgradeCondition == ClickerUpgradeCondition.BUILDING) {
          LinkedTreeMap<String, Object> mappedValue = (LinkedTreeMap<String, Object>)value;
          String buildingId = mappedValue.get("id").toString();
          double buildingCount = Double.parseDouble(mappedValue.get("count").toString());
          Optional<ClickerBuilding> building = ((List<ClickerBuilding>)((UIClicker)getUi()).getBuildingList().getOrDefault()).stream().filter(b -> b.getId().equals(buildingId)).findAny();
          if (building.isPresent()) {
            boolean unvalid = (this.data.getBuildingCount(buildingId) < buildingCount);
            hover.add("§8[" + (!unvalid ? "§a✔" : "§c✘") + "§8] §e" + TTT.format("clicker.gui.upgrade.hover.condition.building", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(buildingCount) }) + " " + ((ClickerBuilding)building.get()).getName() }));
          } 
          continue;
        } 
        if (clickerUpgradeCondition == ClickerUpgradeCondition.UPGRADE) {
          String mappedValue = (String)value;
          Optional<ClickerUpgrade> upgrade = ((List<ClickerUpgrade>)((UIClicker)getUi()).getUpgradeList().getOrDefault()).stream().filter(up -> up.getId().equals(mappedValue)).findAny();
          if (upgrade.isPresent()) {
            boolean unvalid = !this.data.hasUpgrade(mappedValue);
            hover.add("§8[" + (!unvalid ? "§a✔" : "§c✘") + "§8] §e" + TTT.format("clicker.gui.upgrade.hover.condition.upgrade", new Object[] { ((ClickerUpgrade)upgrade.get()).getName() }));
          } 
        } 
      } 
      ClickerUpgradeType type = this.upgrade.getType();
      double modifier = this.upgrade.getModifier();
      String target = this.upgrade.getTarget();
      hover.add("");
      if (type == ClickerUpgradeType.CLICK) {
        hover.add(TTT.format("clicker.gui.upgrade.hover.click", new Object[0]));
      } else if (type == ClickerUpgradeType.GLOBAL) {
        hover.add(TTT.format("clicker.gui.upgrade.hover.global", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%" }));
      } else if (type == ClickerUpgradeType.TERRAIN) {
        hover.add(TTT.format("clicker.gui.upgrade.hover.terrain", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%", TTT.format("clicker.category." + target, new Object[0]), TTT.format(JobType.valueOf(this.upgrade.getAdditionalData().toString().toUpperCase()).getName(), new Object[0]) }));
      } else if (type == ClickerUpgradeType.BUILDING) {
        Optional<ClickerBuilding> building = ((List<ClickerBuilding>)((UIClicker)getUi()).getBuildingList().getOrDefault()).stream().filter(b -> b.getId().equals(target)).findAny();
        if (building.isPresent())
          hover.add(TTT.format("clicker.gui.upgrade.hover.building", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%", ((ClickerBuilding)building.get()).getName() })); 
      } else if (type == ClickerUpgradeType.MANY) {
        Optional<ClickerBuilding> building = ((List<ClickerBuilding>)((UIClicker)getUi()).getBuildingList().getOrDefault()).stream().filter(b -> b.getId().equals(target)).findAny();
        if (building.isPresent())
          hover.add(TTT.format("clicker.gui.upgrade.hover.many", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%", ((ClickerBuilding)building.get()).getName() })); 
      } else if (type == ClickerUpgradeType.POSTERIOR) {
        Optional<ClickerBuilding> building = ((List<ClickerBuilding>)((UIClicker)getUi()).getBuildingList().getOrDefault()).stream().filter(b -> b.getId().equals(target)).findAny();
        if (building.isPresent())
          hover.add(TTT.format("clicker.gui.upgrade.hover.posterior", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%", ((ClickerBuilding)building.get()).getName() })); 
      } else if (type == ClickerUpgradeType.CATEGORY) {
        hover.add(TTT.format("clicker.gui.upgrade.hover.category", new Object[] { String.format("%.0f", new Object[] { Double.valueOf(modifier * 100.0D) }) + "%", TTT.format("clicker.category." + target, new Object[0]) }));
      } 
      if (!this.buyed) {
        hover.add("");
        hover.add(TTT.format("clicker.gui.upgrade.hover.cost", new Object[] { UIClicker.formatBigNumber(Double.valueOf(this.upgrade.getPrice())) }));
      } 
    } 
    hoverLines(() -> hover);
    onClick((node, mouseX, mouseY, clickType) -> {
          if (this.data.getPoints() < this.upgrade.getPrice() || this.buyed || !this.unlocked)
            return; 
          (new CSPacketClickerUpgradeBuy(this.upgrade.getId())).subscribe(()).send();
        });
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double offset = dw(16.0D);
    double bigOffset = dw(11.7D);
    float brightness = (this.available && this.data.getPoints() >= this.upgrade.getPrice()) ? hoverValue(0.2F) : 0.0F;
    Color[] colors = this.buyed ? BUYED_COLORS : ((this.unlocked && this.data.getPoints() >= this.upgrade.getPrice()) ? COLORS : DISABLED_COLORS);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), colors[0].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), offset, colors[1].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY(), offset, getHeight(), colors[2].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX() + aw(-offset), getY(), offset, getHeight(), colors[2].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset - offset), getWidth(), offset, colors[3].brighter(brightness));
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset), getWidth(), bigOffset, colors[4].brighter(brightness));
  }
  
  public ClickerUpgradeNode data(ClickerUpgrade upgrade, boolean unlocked, boolean buyed) {
    this.upgrade = upgrade;
    this.unlocked = unlocked;
    this.buyed = buyed;
    available((unlocked && !buyed));
    return this;
  }
  
  public ClickerUpgradeNode available(boolean available) {
    this.available = available;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\nod\\upgrade\ClickerUpgradeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */