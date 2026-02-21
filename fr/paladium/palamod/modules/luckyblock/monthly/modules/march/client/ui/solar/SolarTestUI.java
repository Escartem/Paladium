package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.solar;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.solar.node.PlanetNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSSolarTestHandlePacket;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SolarTestUI extends UI {
  private static final Color SHADOW_COLOR = Color.decode("#8aa5c5");
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  private final List<String> defaultList;
  
  private final HashSet<String> clickedList;
  
  private String currentObjective;
  
  public SolarTestUI(int x, int y, int z) {
    this.defaultList = Lists.newArrayList((Object[])new String[] { "terre", "iss", "mars", "asteroide", "jupiter", "fusee", "saturne", "soleil" });
    this.clickedList = new HashSet<>();
    this.currentObjective = pickupObjective();
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void init() {
    ImageNode.create(311.0D, 180.0D)
      .resource(Resource.of(new ResourceLocation("palamod", "textures/ui/march/solar/background.png")))
      .size(1241.0D, 727.0D)
      .attach(this);
    ((PlanetNode)PlanetNode.create("terre", 612.0D, 480.0D, 60.0D, 60.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("iss", 712.0D, 384.0D, 124.0D, 124.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("mars", 853.0D, 637.0D, 57.0D, 57.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("asteroide", 1009.0D, 554.0D, 90.0D, 70.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("jupiter", 1128.0D, 340.0D, 160.0D, 160.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("fusee", 1023.0D, 722.0D, 107.0D, 115.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("saturne", 1288.0D, 594.0D, 180.0D, 180.0D)
      .onClick(this::onClick))
      .attach(this);
    ((PlanetNode)PlanetNode.create("soleil", 351.0D, 596.0D, 279.0D, 279.0D)
      .onClick(this::onClick))
      .attach(this);
    CloseButtonNode.create(1454.0D, 250.0D)
      .onClick((node, mouseX, mouseY, clickType) -> Minecraft.func_71410_x().func_147108_a(null))
      .attach(this);
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    double imageWidth = 162.0D;
    double imageHeight = 162.0D;
    DrawUtils.RESOURCE.drawImage(mouseX - 81.0D, mouseY - 81.0D, 162.0D, 162.0D, Resource.of(new ResourceLocation("palamod", "textures/ui/march/solar/icon/cursor.png")));
    String text = "Cliquez sur: " + ((this.currentObjective == null) ? "Aucun" : this.currentObjective);
    TextInfo info = PaladiumText.HEADER.copy().color(Color.WHITE).shadow(SHADOW_COLOR);
    DrawUtils.TEXT.drawText(408.0D, 227.0D, Text.create(text, info));
  }
  
  public void onClick(PlanetNode node, double mouseX, double mouseY, ClickType clickType) {
    if (this.currentObjective == null) {
      handlePacket(true);
      return;
    } 
    if (this.currentObjective.equals(node.getName())) {
      this.clickedList.add(this.currentObjective);
      this.currentObjective = pickupObjective();
      if (this.currentObjective == null)
        handlePacket(true); 
      return;
    } 
    handlePacket(false);
  }
  
  private void handlePacket(boolean win) {
    CSSolarTestHandlePacket cSSolarTestHandlePacket = new CSSolarTestHandlePacket(this.x, this.y, this.z, win);
    CommonMarch.getInstance().getNetwork().sendToServer((IMessage)cSSolarTestHandlePacket);
    Minecraft.func_71410_x().func_147108_a(null);
  }
  
  private String pickupObjective() {
    Random random = new Random();
    if (this.clickedList.isEmpty() && this.currentObjective == null)
      return this.defaultList.get(random.nextInt(this.defaultList.size())); 
    Set<String> remaining = new HashSet<>(this.defaultList);
    remaining.removeAll(this.clickedList);
    if (remaining.isEmpty())
      return null; 
    return (String)remaining.toArray()[random.nextInt(remaining.size())];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\clien\\ui\solar\SolarTestUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */