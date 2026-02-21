package fr.paladium.palamod.modules.alchimiste.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.ajobs.common.eep.EnchantData;
import fr.paladium.palamod.modules.ajobs.common.eep.EnchantSeed;
import fr.paladium.palamod.modules.ajobs.common.eep.PalaEnchantEEP;
import fr.paladium.palamod.modules.alchimiste.common.container.ContainerReadableEnchantment;
import fr.paladium.palavanillagui.client.ui.util.EnchantmentOption;
import fr.paladium.palavanillagui.client.ui.util.node.EnchantmentOptionNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

@UIData(background = false, container = true)
public class UIReadableEnchant extends UI {
  private static final ResourceLocation BOOK = ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/book.png");
  
  private static final ResourceLocation BACKGROUND = ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/enchant_table_background.png");
  
  private BackgroundNode background;
  
  private final IntegerSignal playerLevelSignal = new IntegerSignal(0);
  
  private final ListSignal<EnchantmentOption> enchantOptionSignal = new ListSignal(Arrays.asList(new EnchantmentOption[] { new EnchantmentOption(0), new EnchantmentOption(0), new EnchantmentOption(0) }));
  
  private final ContainerReadableEnchantment containerEnchant;
  
  private final World world;
  
  private final int posX;
  
  private final int posY;
  
  private final int posZ;
  
  public UIReadableEnchant(SimpleGHandler.GuiHandlerData data) {
    super((Container)new ContainerReadableEnchantment(data));
    this.containerEnchant = (ContainerReadableEnchantment)getContainer();
    this.world = data.getWorld();
    this.posX = data.getX();
    this.posY = data.getY();
    this.posZ = data.getZ();
  }
  
  public void init() {
    ContainerNode.create(0.0D, -3.0D, 1920.0D, 1080.0D)
      .body(container -> this.background = (BackgroundNode)((BackgroundNode)BackgroundNode.create(760.0D, 689.0D).body(())).attach(container))
      
      .attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    containerBounds(this.background.getAbsoluteX(), this.background.getAbsoluteY(), this.background.getWidth(), this.background.getHeight());
    int playerLevel = (Minecraft.func_71410_x()).field_71439_g.field_71068_ca;
    if (((Integer)this.playerLevelSignal.getOrDefault()).intValue() != playerLevel)
      this.playerLevelSignal.set(Integer.valueOf(playerLevel)); 
    for (EnchantmentOption eOption : this.enchantOptionSignal.getOrDefault()) {
      int index = this.enchantOptionSignal.indexOf(eOption);
      if (eOption.getLevel() != this.containerEnchant.enchantLevels[index])
        this.enchantOptionSignal.set(index, new EnchantmentOption(this.containerEnchant.enchantLevels[index])); 
    } 
  }
  
  private List<String> getTooltip(int EOIndex) {
    List<String> tooltip = new ArrayList<>();
    float power = 0.0F;
    for (int j = -1; j <= 1; j++) {
      for (int k1 = -1; k1 <= 1; k1++) {
        if ((j != 0 || k1 != 0) && this.world.func_147437_c(this.posX + k1, this.posY, this.posZ + j) && this.world.func_147437_c(this.posX + k1, this.posY + 1, this.posZ + j)) {
          power += ForgeHooks.getEnchantPower(this.world, this.posX + k1 * 2, this.posY, this.posZ + j * 2);
          power += ForgeHooks.getEnchantPower(this.world, this.posX + k1 * 2, this.posY + 1, this.posZ + j * 2);
          if (k1 != 0 && j != 0) {
            power += ForgeHooks.getEnchantPower(this.world, this.posX + k1 * 2, this.posY, this.posZ + j);
            power += ForgeHooks.getEnchantPower(this.world, this.posX + k1 * 2, this.posY + 1, this.posZ + j);
            power += ForgeHooks.getEnchantPower(this.world, this.posX + k1, this.posY, this.posZ + j * 2);
            power += ForgeHooks.getEnchantPower(this.world, this.posX + k1, this.posY + 1, this.posZ + j * 2);
          } 
        } 
      } 
    } 
    int enchantLevel = this.containerEnchant.enchantLevels[EOIndex];
    if (enchantLevel != 0) {
      PalaEnchantEEP eep = PalaEnchantEEP.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
      EnchantSeed selectedSeed = null;
      for (EnchantSeed seed : eep.enchantSeeds) {
        if (this.containerEnchant.func_75139_a(0).func_75211_c() != null && seed.itemStack.func_77973_b() == this.containerEnchant.func_75139_a(0).func_75211_c().func_77973_b() && seed.enchantPower == (int)power && seed.enchantLvl == enchantLevel) {
          selectedSeed = seed;
          break;
        } 
      } 
      if (selectedSeed != null)
        for (EnchantData enchant : selectedSeed.enchantDataList) {
          for (Enchantment e : Enchantment.field_77331_b) {
            if (e != null && e.field_77352_x == enchant.enchantId) {
              tooltip.add(e.func_77316_c(enchant.enchantStrenght));
              break;
            } 
          } 
        }  
    } 
    return tooltip;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\clien\\ui\UIReadableEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */