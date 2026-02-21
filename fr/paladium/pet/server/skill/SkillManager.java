package fr.paladium.pet.server.skill;

import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class SkillManager {
  private static SkillManager instance;
  
  private HashSet<ASkillHandler> handlers;
  
  private HashSet<UUID> bypassSet;
  
  public HashSet<ASkillHandler> getHandlers() {
    return this.handlers;
  }
  
  public HashSet<UUID> getBypassSet() {
    return this.bypassSet;
  }
  
  public SkillManager() {
    instance = this;
    this.handlers = new HashSet<>();
    this.bypassSet = new HashSet<>();
  }
  
  public static SkillManager getInstance() {
    if (instance == null)
      instance = new SkillManager(); 
    return instance;
  }
  
  public boolean isBypassed(EntityPlayer player) {
    return this.bypassSet.contains(player.func_110124_au());
  }
  
  public void registerHandler(Class<? extends ASkillHandler> clazz) {
    try {
      ASkillHandler handler = clazz.newInstance();
      this.handlers.add(handler);
    } catch (InstantiationException|IllegalAccessException e) {
      e.printStackTrace();
    } 
  }
  
  public void updateSkills() {
    SkillConfig config = SkillConfig.get();
    this.handlers.forEach(handler -> {
          Skill skill = config.findSkillById(handler.getId()).orElse(null);
          if (skill == null)
            return; 
          skill.setHandler(handler);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\SkillManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */