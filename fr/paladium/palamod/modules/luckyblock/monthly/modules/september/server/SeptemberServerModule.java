package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands.ForgeStepCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands.HolidayCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands.SandCastleCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands.SummerIslandCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events.ButcherEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events.ExperienceEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.events.TickEventHandler;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.command.ICommand;

public class SeptemberServerModule extends AbstractMonthlyModule {
  public SeptemberServerModule() {
    super(SideType.SERVER);
  }
  
  public void registerEventHandlers() {
    LuckyEvents.values(LuckyType.SEPTEMBER).forEach(luckyEvent -> {
          ALuckyEvent event = luckyEvent.getEvent();
          if (!(event instanceof fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic))
            registerEventHandler(event); 
        });
    registerEventHandler(new ExperienceEventHandler());
    registerEventHandler(new TickEventHandler());
    registerEventHandler(new ButcherEventHandler());
  }
  
  public void registerTasks() {}
  
  public void registerCommands(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new HolidayCommand());
    event.registerServerCommand((ICommand)new SandCastleCommand());
    event.registerServerCommand((ICommand)new ForgeStepCommand());
    event.registerServerCommand((ICommand)new SummerIslandCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\SeptemberServerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */