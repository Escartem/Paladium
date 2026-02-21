package fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.container;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.palarpg.module.profile.client.constant.SkillTreePositionConstants;
import fr.paladium.palarpg.module.profile.client.ui.UIRPGProfile;
import fr.paladium.palarpg.module.profile.common.skilltree.RPGSkillTreePosition;
import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.palarpg.module.profile.server.action.RPGSkillTreeServerAction;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RPGProfileSkillTreeMapContainer extends ImageNode {
  public static final Resource BACKGROUND = Resource.of(new ResourceLocation("palarpg", "textures/profile/skilltree/background.png")).blocking();
  
  private static final TextInfo SKILL_TITLE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE).shadow(0.0F, 4.0F);
  
  protected RPGProfileSkillTreeMapContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
    resource(BACKGROUND);
  }
  
  public static RPGProfileSkillTreeMapContainer create(double x, double y, double width, double height) {
    return new RPGProfileSkillTreeMapContainer(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    clearChildren();
    draggable(DraggableProperty.custom(getAbsoluteDefaultX() - (getWidth() - getParent().getWidth()) / 2.0D, getAbsoluteDefaultY() - (getHeight() - getParent().getHeight()) / 2.0D, getWidth() + getWidth() - getParent().getWidth(), getHeight() + getHeight() - getParent().getHeight()));
    TextButtonNode.create(getWidth() / 2.0D, getHeight() / 2.0D - 15.0D)
      .text("valider")
      .size(155.0D, 40.0D)
      .anchor(Align.CENTER, Align.END)
      .zlevel(10.0D)
      .enabled(node -> !((UIRPGProfile)getUi()).getToBuySignal().isEmpty())
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.open((UI)(new UIYesNoPopup("Achat", "Êtes vous sûr de vouloir dépenser " + ((UIRPGProfile)getUi()).getTotalCostSignal().getOrDefault() + " points de compétence ?")).yesCallback(())))
      
      .attach((Node)this);
    ((TextButtonNode)((TextButtonNode)((TextButtonNode)TextButtonNode.create(getWidth() / 2.0D, getHeight() / 2.0D + 15.0D)
      .enabled(node -> (node.getText() == "annuler") ? true : (!((UIRPGProfile)getUi()).getProfileData().getUnlockedSkills().isEmpty())))
      
      .anchor(Align.CENTER, Align.START)
      .zlevel(10.0D)
      .onInit(node -> {
          if (((UIRPGProfile)getUi()).getToBuySignal().isEmpty()) {
            node.text("reset").size(155.0D, 40.0D);
          } else {
            node.text("annuler").size(155.0D, 40.0D);
          } 
        })).onClick((node, mouseX, mouseY, clickType) -> {
          if (node.getText() == "reset") {
            ZUI.open((UI)(new UIYesNoPopup("reset", "Êtes vous sûr de vouloir réinitialiser votre arbre de compétence ?")).yesCallback(()));
          } else if (node.getText() == "annuler") {
            ((UIRPGProfile)getUi()).getTotalCostSignal().set(Integer.valueOf(0));
            ((UIRPGProfile)getUi()).getToBuySignal().clear();
          } 
        })).watch((Signal)((UIRPGProfile)getUi()).getToBuySignal(), new WatchProperty[] { WatchProperty.RELOAD }).attach((Node)this);
    for (Map.Entry<String, SkillTree> entry : (Iterable<Map.Entry<String, SkillTree>>)RPGSkillTreeManager.getSkillTrees().entrySet()) {
      SkillTree skillTree = entry.getValue();
      final RPGSkillTreePosition skillTreePosition = skillTree.getPosition();
      Color fontColor = SkillTreePositionConstants.getFontColor(skillTreePosition);
      Color fontShadowColor = SkillTreePositionConstants.getFontShadowColor(skillTreePosition);
      ((TextNode)TextNode.create(dw(2.0D) + skillTreePosition.getX(), dh(2.0D) + skillTreePosition.getY())
        .text(Text.create(skillTree.getName(), SKILL_TITLE_INFO.copy().color(fontColor).shadow(fontShadowColor)))
        .anchor(Align.CENTER, Align.CENTER)
        .onRender(new NodeRenderCallback<TextNode>() {
            @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
            public void pre(@NonNull TextNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
              if (node == null)
                throw new NullPointerException("node is marked non-null but is null"); 
              if (context == null)
                throw new NullPointerException("context is marked non-null but is null"); 
              if (skillTreePosition == RPGSkillTreePosition.LEFT || skillTreePosition == RPGSkillTreePosition.RIGHT) {
                GL11.glTranslated(node.getX() + node.dw(2.0D), node.getY() + node.dh(2.0D), 0.0D);
                GL11.glRotated((skillTreePosition == RPGSkillTreePosition.LEFT) ? 90.0D : -90.0D, 0.0D, 0.0D, 1.0D);
                GL11.glTranslated(-(node.getX() + node.dw(2.0D)), -(node.getY() + node.dh(2.0D)), 0.0D);
              } 
            }
            
            public void apply(@NonNull TextNode node, double mouseX, double mouseY, float partialTicks) {
              if (node == null)
                throw new NullPointerException("node is marked non-null but is null"); 
            }
          })).attach((Node)this);
      RPGSkillTreeContainer.create(dw(2.0D), dh(2.0D))
        .skilltree(skillTree)
        .watch((Signal)((UIRPGProfile)getUi()).getToBuySignal(), new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach((Node)this);
    } 
  }
  
  public boolean isHovered(double mouseX, double mouseY) {
    return ((UIRPGProfile)getUi()).getSkillTreeContainer().isHovered(mouseX, mouseY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\skilltree\container\RPGProfileSkillTreeMapContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */