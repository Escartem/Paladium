package fr.paladium.palajobs.client.ui.objective;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.field.MinecraftTextFieldNode;
import fr.paladium.lib.apollon.nodes.flex.FlexDirection;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.client.ui.objective.node.JobObjectiveEntityNode;
import fr.paladium.palajobs.client.ui.objective.node.JobObjectiveNode;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.core.pojo.objectives.comparator.ObjectiveComparator;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UIJobsObjectivesPopup extends UIPopup {
  private final JobAdvancement data;
  
  private MinecraftTextFieldNode search;
  
  private String lastSearch;
  
  private ScrollableArea area;
  
  private FlexNode flex;
  
  private List<JobObjectiveNode> objectives;
  
  public UIJobsObjectivesPopup(GuiScreen prev, JobAdvancement data) {
    super(prev, "palajobs:popup");
    this.data = data;
    this.objectives = new ArrayList<>();
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(28.1F), height(8.0F), TTT.format("gui.jobs.title", new Object[0])));
    addNode(
        (ANode)(this.search = (new MinecraftTextFieldNode(width(58.0F), height(18.5F), width(12.96875F))).setPlaceholder(TTT.format("gui.jobs.objectives.search.placeholder", new Object[0]))));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(28.1F), height(33.7F), TTT.format("gui.jobs.objectives.type", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(47.916F), height(33.7F), TTT.format("gui.jobs.objectives.action", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(61.25F), height(33.7F), TTT.format("gui.jobs.objectives.xp", new Object[0])));
    this.area = (ScrollableArea)MinecraftScrollableArea.builder().bounds(width(28.0F), height(39.0F), width(71.0F), height(90.0F)).scrollbar(width(72.0F), height(38.7F), height(51.3F));
    addScrollableArea(this.area);
    this.flex = new FlexNode(width(28.1F), height(39.1F), width(43.0F), 0.0D, FlexDirection.COLUMN);
    this.flex.setMargin(height(0.5F));
    this.flex.setArea(this.area);
    addNode((ANode)this.flex);
    Map<Object, ObjectiveValue> sortedObjectives = new HashMap<>();
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)this.data.getJob().getObjectives()) {
      for (Map.Entry<?, ObjectiveValue> entry : (Iterable<Map.Entry<?, ObjectiveValue>>)objective.getObjectives().entrySet())
        sortedObjectives.put(entry.getKey(), entry.getValue()); 
    } 
    ObjectiveComparator comparator = new ObjectiveComparator();
    sortedObjectives = (Map<Object, ObjectiveValue>)sortedObjectives.entrySet().stream().sorted((Comparator)comparator).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new));
    this.objectives.clear();
    for (Map.Entry<?, ObjectiveValue> entry : sortedObjectives.entrySet()) {
      JobObjectiveEntityNode jobObjectiveEntityNode;
      Object object = entry.getKey();
      ObjectiveValue value = entry.getValue();
      ObjectiveType type = value.getType();
      double xp = value.getGivedExperience();
      int requiredLevel = value.getRequiredLevel();
      boolean unlocked = (this.data.getLevel() >= requiredLevel);
      Object icon = null;
      switch (type) {
        case BREAK_BLOCK:
          icon = object;
          break;
        case SMELT:
          icon = object;
          break;
        case CRUSHER_GENERATE:
          icon = object;
          break;
        case COBBLE_BREAKER:
          icon = object;
        case EXTRACT_SEVE:
          icon = object;
        case CRAFT_CAULDRON:
          icon = object;
        case CRAFT:
          icon = object;
          break;
        case DROP_CAULDRON:
          icon = object;
          break;
        case WITHER_SPAWN:
          icon = new ItemStack(Items.field_151144_bL, 1, 1);
          break;
        case FISH:
          icon = object;
          break;
        case ARROW_KILL:
          icon = new ItemStack(Items.field_151032_g);
          break;
        case ENTITY_KILL:
          icon = object;
          break;
        case ENTITY_KILL_SPECIAL:
          icon = object;
          break;
        case CRAFT_PORTAL:
          icon = object;
          break;
      } 
      JobObjectiveNode node = null;
      if (icon instanceof ItemStack) {
        this.objectives.add(node = new JobObjectiveNode(0.0D, 0.0D, width(42.8F), type, (ItemStack)icon, xp));
      } else if (icon instanceof Class) {
        try {
          Entity entity = ((Class<Entity>)icon).getConstructor(new Class[] { World.class }).newInstance(new Object[] { (Minecraft.func_71410_x()).field_71441_e });
          entity.field_70177_z = -45.0F;
          this.objectives.add(jobObjectiveEntityNode = new JobObjectiveEntityNode(0.0D, 0.0D, width(42.8F), type, null, xp, entity, type.name().toUpperCase().contains("SPECIAL")));
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } 
      if (jobObjectiveEntityNode != null && !unlocked) {
        jobObjectiveEntityNode.setEnabled(false);
        jobObjectiveEntityNode.addHover("§cCette méthode se débloque au niveau " + requiredLevel);
        jobObjectiveEntityNode.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
      } 
    } 
    this.objectives.forEach(this.flex::addChild);
    addNode((new MinecraftCloseNode(width(73.0F), height(7.0F))).setCallback(node -> closePopup()));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(53.85F), height(90.27F), true);
    drawSplittedString(TTT.format("gui.jobs.objectives.subtitle", new Object[] { TTT.format(this.data.getJob().getType().getName(), new Object[0]) }).toUpperCase(), width(28.1F), height(20.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 25, width(25.0F), TextAlign.LEFT);
    if (this.lastSearch == null)
      this.lastSearch = this.search.getText(); 
    if (this.lastSearch != this.search.getText()) {
      this.flex.getChildren().clear();
      if (!this.search.getText().isEmpty()) {
        this.objectives.stream().filter(node -> node.match(this.search.getText())).forEach(this.flex::addChild);
      } else {
        this.objectives.forEach(this.flex::addChild);
      } 
      this.area.setScroll(0.0D);
      this.flex.setScroll(0.0D);
      this.flex.setY(this.flex.getDefaultY());
    } 
    this.lastSearch = this.search.getText();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\objective\UIJobsObjectivesPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */