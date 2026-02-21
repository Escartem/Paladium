package fr.paladium.pet.client.profile.gui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ProfilePetSkillNode extends Node {
  private static final ResourceLocation LOCKED_ICON = new ResourceLocation("palapet", "textures/ui/home/slot_locked.png");
  
  private static final ResourceLocation UNKNOWN_ICON = new ResourceLocation("palapet", "textures/ui/home/unknown_logo.png");
  
  private Skill skill;
  
  private boolean unlocked;
  
  private boolean setup;
  
  protected ProfilePetSkillNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfilePetSkillNode create(double x, double y, double width, double height) {
    return new ProfilePetSkillNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(!this.unlocked ? Color.BLACK.copyAlpha(0.05F) : (!this.setup ? new Color(68, 68, 68, 38) : ((this.skill.getType() == SkillType.PASSIVE) ? new Color(17, 46, 56) : new Color(28, 37, 31))))
      .border(!this.unlocked ? Color.BLACK.copyAlpha(0.25F) : (!this.setup ? Color.WHITE : ((this.skill.getType() == SkillType.PASSIVE) ? new Color(45, 199, 255) : new Color(94, 212, 42))), 1.0D, true)
      .attach(this);
    if (!this.unlocked) {
      ImageNode.create(25.0D, dh(2.0D) - 24.0D, 48.0D, 48.0D)
        .resource(LOCKED_ICON)
        .linear(false)
        .attach(this);
    } else if (!this.setup) {
      ImageNode.create(25.0D, dh(2.0D) - 24.0D, 48.0D, 48.0D)
        .resource(UNKNOWN_ICON)
        .linear(false)
        .attach(this);
    } else {
      ImageNode.create(25.0D, dh(2.0D) - 24.0D, 48.0D, 48.0D)
        .resource(new ResourceLocation("palapet", "textures/ui/skill/" + this.skill.getIcon() + ".png"))
        .linear(false)
        .attach(this);
      TextNode.create(aw(-10.0D), 10.0D)
        .text(Text.create(((this.skill.getType() == SkillType.PASSIVE) ? "compétence passive" : "compétence active").toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 12.0F, (this.skill.getType() == SkillType.PASSIVE) ? new Color(45, 199, 255) : new Color(94, 212, 42)), Align.END))
        .anchorX(Align.END)
        .attach(this);
    } 
    TextNode.create(100.0D, 29.0D)
      .text(Text.create((!this.unlocked ? TTT.format("pet.gui.slot.locked", new Object[0]) : (!this.setup ? TTT.format("pet.gui.slot.not.configured", new Object[0]) : TTT.format("pet.skill." + this.skill.getId() + ".name", new Object[0]))).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE)))
      .attach(this);
  }
  
  @NonNull
  public final ProfilePetSkillNode skill(Skill skill, boolean unlocked, boolean setup) {
    this.skill = skill;
    this.unlocked = unlocked;
    this.setup = setup;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\profile\gui\node\ProfilePetSkillNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */