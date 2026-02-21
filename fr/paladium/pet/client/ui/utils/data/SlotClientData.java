package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SlotClientData {
  public String toString() {
    return "SlotClientData(slot=" + getSlot() + ", requiredLevel=" + getRequiredLevel() + ", id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", logo=" + getLogo() + ", nextChangeMillis=" + getNextChangeMillis() + ", colorInfo=" + getColorInfo() + ")";
  }
  
  public SlotClientData(int slot, int requiredLevel, String id, String name, String description, ResourceLocation logo, long nextChangeMillis, SkillColorInfo colorInfo) {
    this.slot = slot;
    this.requiredLevel = requiredLevel;
    this.id = id;
    this.name = name;
    this.description = description;
    this.logo = logo;
    this.nextChangeMillis = nextChangeMillis;
    this.colorInfo = colorInfo;
  }
  
  private static final ResourceLocation UNKNOWN_LOGO = new ResourceLocation("palapet:textures/ui/home/unknown_logo.png");
  
  private static final ResourceLocation LOCKED_LOGO = new ResourceLocation("palapet:textures/ui/home/slot_locked.png");
  
  private int slot;
  
  private int requiredLevel;
  
  private String id;
  
  private String name;
  
  private String description;
  
  private ResourceLocation logo;
  
  private long nextChangeMillis;
  
  private SkillColorInfo colorInfo;
  
  public int getSlot() {
    return this.slot;
  }
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public ResourceLocation getLogo() {
    return this.logo;
  }
  
  public long getNextChangeMillis() {
    return this.nextChangeMillis;
  }
  
  public SkillColorInfo getColorInfo() {
    return this.colorInfo;
  }
  
  public static SlotClientData from(int slot, Skill skill, EntityPlayer player, PetPlayer pet, long nextChangeMillis) {
    SkillColorInfo color;
    double value = skill.getPersonalValue(pet);
    if (nextChangeMillis > 0L) {
      color = (skill.getType() == SkillType.PASSIVE) ? SkillColorInfo.COOLDOWN_PASSIVE : SkillColorInfo.COOLDOWN_ACTIVE;
    } else {
      color = (skill.getType() == SkillType.PASSIVE) ? SkillColorInfo.PASSIVE : SkillColorInfo.ACTIVE;
    } 
    if (nextChangeMillis < 0L)
      nextChangeMillis = 0L; 
    nextChangeMillis = System.currentTimeMillis() + nextChangeMillis;
    return new SlotClientData(slot, skill
        
        .getRequiredLevel(), skill
        .getId(), skill
        .getName(player), skill
        .getFormattedDescription(player, skill.getPersonalValue(pet)), new ResourceLocation("palapet:textures/ui/skill/" + skill
          .getIcon() + ".png"), nextChangeMillis, color);
  }
  
  public static SlotClientData none(EntityPlayer player, int slot) {
    return new SlotClientData(slot, 0, "", PetTranslateEnum.GUI_SLOT_NOT_CONFIGURED
        
        .text(player), PetTranslateEnum.GUI_SLOT_CLICK_TO_SELECT
        .text(player), UNKNOWN_LOGO, 0L, SkillColorInfo.NONE);
  }
  
  public static SlotClientData locked(EntityPlayer player, int slot, int requiredLevel) {
    return new SlotClientData(slot, requiredLevel, "", PetTranslateEnum.GUI_SLOT_LOCKED
        
        .text(player), PetTranslateEnum.GUI_SLOT_LOCKED_LEVEL
        .text(player, new Object[] { Integer.valueOf(requiredLevel) }), LOCKED_LOGO, 0L, SkillColorInfo.LOCKED);
  }
  
  public enum SkillColorInfo {
    PASSIVE((String)Color.decode("#2DC7FF"), Color.decode("#112E38")),
    ACTIVE((String)Color.decode("#5ED42A"), Color.decode("#212A23")),
    COOLDOWN_ACTIVE((String)Color.decode("#DF611A"), Color.decode("#2A221F")),
    COOLDOWN_PASSIVE((String)COOLDOWN_ACTIVE.borderColor, COOLDOWN_ACTIVE.backgroundColor),
    LOCKED((String)Color.decode("#FFFFFF"), new Color(68, 68, 68, 30)),
    NONE((String)Color.decode("#FFFFFF"), new Color(68, 68, 68, 30));
    
    private final Color borderColor;
    
    private final Color backgroundColor;
    
    public Color getBorderColor() {
      return this.borderColor;
    }
    
    public Color getBackgroundColor() {
      return this.backgroundColor;
    }
    
    SkillColorInfo(Color borderColor, Color backgroundColor) {
      this.borderColor = borderColor;
      this.backgroundColor = backgroundColor;
    }
    
    public boolean isActive() {
      return (this == ACTIVE || this == COOLDOWN_ACTIVE);
    }
    
    public boolean isPassive() {
      return (this == PASSIVE || this == COOLDOWN_PASSIVE);
    }
    
    public boolean isCooldown() {
      return (this == COOLDOWN_ACTIVE || this == COOLDOWN_PASSIVE);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\SlotClientData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */