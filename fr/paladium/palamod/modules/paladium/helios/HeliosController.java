package fr.paladium.palamod.modules.paladium.helios;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.Helios;
import fr.paladium.palamod.modules.design.modules.currentspell.ModuleCurrentSpell;
import fr.paladium.palamod.modules.design.modules.legendarypower.ModuleLegendaryPower;
import fr.paladium.palamod.modules.design.modules.omniscience.ModuleOmniscience;
import fr.paladium.palamod.modules.design.modules.palalag.ModulePalaLag;
import fr.paladium.palamod.modules.design.modules.spawnerfinder.ModuleSpawnerFinder;
import fr.paladium.palamod.modules.design.modules.stickcooldown.ModuleStickCooldown;
import fr.paladium.palamod.modules.design.modules.unclaimfinder.ModuleUnclaimFinder;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;

public class HeliosController {
  public static void register(Side side) {
    if (side == Side.CLIENT) {
      Helios.getClient().addModule(ModuleStickCooldown.class);
      Helios.getClient().addModule(ModulePalaLag.class);
      Helios.getClient().addModule(ModuleBoost.class);
      Helios.getClient().addModule(ModuleCurrentSpell.class);
      Helios.getClient().addModule(ModuleLegendaryPower.class);
      Helios.getClient().addModule(ModuleOmniscience.class);
      Helios.getClient().addModule(ModuleSpawnerFinder.class);
      Helios.getClient().addModule(ModuleUnclaimFinder.class);
    } else if (side == Side.SERVER) {
      Helios.getServer().addModule(ModulePalaLag.class);
      Helios.getServer().addModule(ModuleBoost.class);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\helios\HeliosController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */