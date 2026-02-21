package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.commands.LuckyRouletteCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events.AugustChestEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events.JumpEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events.SummerAmuletEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.events.TickEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.tasks.HydrationTask;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.tasks.StormTask;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.command.ICommand;

public class AugustServerModule extends AbstractMonthlyModule {
  public AugustServerModule() {
    super(SideType.SERVER);
  }
  
  public void registerEventHandlers() {
    LuckyEvents.values(LuckyType.AUGUST).forEach(luckyEvent -> {
          ALuckyEvent event = luckyEvent.getEvent();
          if (!(event instanceof fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic))
            registerEventHandler(event); 
        });
    registerEventHandler(new JumpEventHandler());
    registerEventHandler(new SummerAmuletEventHandler());
    registerEventHandler(new TickEventHandler());
    registerEventHandler(new AugustChestEventHandler());
  }
  
  public void registerCommands(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new LuckyRouletteCommand());
  }
  
  public void registerTasks() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate((TimerTask)new HydrationTask(), 0L, 1000L);
    timer.scheduleAtFixedRate((TimerTask)new StormTask(), 0L, 500L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\AugustServerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */