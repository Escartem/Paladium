package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.server;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import net.minecraft.command.ICommand;

public class JulyServerModule extends AbstractMonthlyModule {
  public JulyServerModule() {
    super(SideType.SERVER);
  }
  
  public void registerCommands(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new StructureCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\server\JulyServerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */