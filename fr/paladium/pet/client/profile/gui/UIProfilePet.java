package fr.paladium.pet.client.profile.gui;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.pet.client.profile.gui.node.ProfilePetSkillNode;
import fr.paladium.pet.client.ui.home.node.stat.HomeStatNode;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.network.packet.skill.CSRequestSkillList;
import fr.paladium.pet.common.profile.dto.Pet;
import fr.paladium.pet.common.profile.dto.PetSkill;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class UIProfilePet extends UIProfile<Pet> {
  private EntityDummyPet entityPet;
  
  public UIProfilePet(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    RectNode.create(271.0D, 292.0D, 321.0D, 638.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
    ImageNode.create(631.0D, 298.0D, 35.0D, 35.0D)
      .resource(new ResourceLocation("palapet", HomeStatNode.StatType.HAPPINESS.getTexture()))
      .attach((UI)this);
    TextNode.create(685.0D, 295.0D)
      .text(Text.create("bonheur", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 32.0F, Color.WHITE)))
      .attach((UI)this);
    ImageNode.create(1153.0D, 298.0D, 35.0D, 35.0D)
      .resource(new ResourceLocation("palapet", HomeStatNode.StatType.EXPERIENCE.getTexture()))
      .attach((UI)this);
    TextNode.create(1210.0D, 295.0D)
      .text(Text.create("expérience", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 32.0F, Color.WHITE)))
      .attach((UI)this);
    ImageNode.create(631.0D, 451.0D, 35.0D, 35.0D)
      .resource(new ResourceLocation("palapet", "textures/ui/home/skill_logo.png"))
      .attach((UI)this);
    TextNode.create(685.0D, 449.0D)
      .text(Text.create("compétences", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 32.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(960.0D, 469.0D, 683.0D, 1.0D)
      .color(Color.WHITE)
      .attach((UI)this);
  }
  
  public void onPostInit() {
    Pet data = (Pet)getProfileData().getOrDefault();
    if (data.getCurrentSkin() == null) {
      TextNode.create(1137.0D, 600.0D)
        .text(Text.create(getStore().getUsername() + " n'a pas de familier", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 50.0F, Color.WHITE).shadow().shadow(0.0F, 6.0F), Align.CENTER))
        .attach((UI)this);
      return;
    } 
    (new CSRequestSkillList()).subscribe(result -> {
          EntityViewerNode.create(302.0D, 457.0D, 261.0D, 261.0D).rotationPitchRange(0.0D, 0.0D).entity((EntityLivingBase)(this.entityPet = PetRenderUtils.getPetFromEnum(data.getCurrentSkin()))).rotationYaw(-45.0D).effect((NodeEffect)MaskNodeEffect.create(-31.0D, -65.0D, 321.0D, 638.0D)).attach((UI)this);
          ProgressNode.create(631.0D, 346.0D, 490.0D, 56.0D).color(new Color(252, 100, 201)).padding(6.0D).progress(0.0F, data.getMaxHappiness(), Math.min(data.getHappiness(), data.getMaxHappiness())).attach((UI)this);
          TextNode.create(1121.0D, 298.0D).text(Text.create(data.getHappiness() + " / " + data.getMaxHappiness(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 18.0F, Color.WHITE), Align.END)).anchorX(Align.END).attach((UI)this);
          double currentExperience = data.getExperience();
          double beforeLevelExperience = data.getRequiredExperience(data.getLevel() - 1);
          double nextLevelExperience = data.getRequiredExperience(data.getLevel());
          int maxExperience = (int)Math.floor(nextLevelExperience - beforeLevelExperience);
          int experience = Math.min((int)Math.floor(currentExperience - beforeLevelExperience), maxExperience);
          float xp = experience / maxExperience;
          ProgressNode.create(1153.0D, 346.0D, 490.0D, 56.0D).color(ColorConstant.GREEN).padding(6.0D).progress(xp).attach((UI)this);
          TextNode.create(1643.0D, 298.0D).text(Text.create(experience + " / " + maxExperience, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 18.0F, Color.WHITE), Align.END)).anchorX(Align.END).attach((UI)this);
          ContainerNode.create(631.0D, 511.0D, 1012.0D, 418.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(1030.0D, 0.0D, 418.0D)).body(()).attach((UI)this);
        }).send();
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.entityPet != null) {
      this.entityPet.field_70142_S = 0.0D;
      this.entityPet.field_70137_T = 0.0D;
      this.entityPet.field_70136_U = 0.0D;
      this.entityPet.func_70634_a(0.0D, 0.0D, 0.0D);
      this.entityPet.field_70173_aa = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\profile\gui\UIProfilePet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */