package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.listener.JupiterPotionListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.AsteroidRainEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.AstronautTrainingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.LikeARocketEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.VitalSpaceEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.AlienDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.BlacksmithDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.ShootingStarDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.SpatialNoiseDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener.AntiPersonalItemListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener.DollarStoneUpgradedListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener.InvincibilityListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.subcommand.satellite.SatelliteSubCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.command.ICommand;

public class ServerMarch extends AbstractMonthlyModule {
  public ServerMarch() {
    super(SideType.SERVER, LuckyType.MARCH);
  }
  
  public void registerTickables() {
    super.registerTickables();
    registerTickable((ATickable)VitalSpaceEvent.getInstance());
    registerTickable((ATickable)AsteroidRainEvent.getInstance());
    registerTickable((ATickable)AstronautTrainingEvent.getInstance());
    registerTickable((ATickable)LikeARocketEvent.getInstance());
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
    registerEventHandler(new Class[] { InvincibilityListener.class, AntiPersonalItemListener.class, JupiterPotionListener.class, DollarStoneUpgradedListener.class });
  }
  
  public void registerCommands(FMLServerStartingEvent event) {
    super.registerCommands(event);
    event.registerServerCommand((ICommand)new SatelliteSubCommand());
  }
  
  public void registerDialogManagers() {
    super.registerDialogManagers();
    registerDialogManager((AbstractDialogManager)new BlacksmithDialogManager());
    registerDialogManager((AbstractDialogManager)new AlienDialogManager());
    registerDialogManager((AbstractDialogManager)new SpatialNoiseDialogManager());
    registerDialogManager((AbstractDialogManager)new ShootingStarDialogManager());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\ServerMarch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */