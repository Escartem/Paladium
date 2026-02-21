package fr.paladium.palamod.modules.apet.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.modules.apet.common.PetCommonProxy;
import fr.paladium.palamod.modules.apet.server.assignment.handler.AngelicWaterAssignmentHandler;
import fr.paladium.palamod.modules.apet.server.listener.PetLootListener;
import fr.paladium.palamod.modules.apet.server.listener.active.LuckyMinerListener;
import fr.paladium.palamod.modules.apet.server.listener.passive.MiningFortuneListener;
import fr.paladium.palamod.modules.apet.server.listener.passive.PortableCoffinListener;
import fr.paladium.palamod.modules.apet.server.skill.handler.AutoHarvestSkill;
import fr.paladium.palamod.modules.apet.server.skill.handler.BlessedGrowthSkill;
import fr.paladium.palamod.modules.apet.server.skill.handler.ElectricCloudSkill;
import fr.paladium.palamod.modules.apet.server.skill.handler.LuckyMinerSkill;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.skill.SkillManager;

public class PetServerProxy extends PetCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    registerListeners();
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    registerAssignmentHandlers();
    registerSkillHandlers();
  }
  
  private void registerListeners() {
    addListener(new Class[] { MiningFortuneListener.class });
    addListener(new Class[] { LuckyMinerListener.class });
    addListener(new Class[] { PetLootListener.class });
    addListener(new Class[] { PortableCoffinListener.class });
  }
  
  private void registerAssignmentHandlers() {
    AssignmentManager manager = AssignmentManager.getInstance();
    manager.registerHandler(AngelicWaterAssignmentHandler.class);
  }
  
  private void registerSkillHandlers() {
    SkillManager manager = SkillManager.getInstance();
    manager.registerHandler(AutoHarvestSkill.class);
    manager.registerHandler(BlessedGrowthSkill.class);
    manager.registerHandler(LuckyMinerSkill.class);
    manager.registerHandler(ElectricCloudSkill.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\PetServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */