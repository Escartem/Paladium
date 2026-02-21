package fr.paladium.palamod.modules.luckyblock.monthly;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.AugustClientModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.AugustCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.AugustServerModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.DecemberClientModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.DecemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.DecemberServerModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.JulyClientModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.JulyCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.server.JulyServerModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ClientMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.ServerMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.NovemberClientModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.NovemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.NovemberServerModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.SeptemberClientModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.SeptemberServerModule;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MonthlyManager {
  private static MonthlyManager instance;
  
  private final List<SidedModule> modules;
  
  public List<SidedModule> getModules() {
    return this.modules;
  }
  
  public MonthlyManager() {
    instance = this;
    this.modules = Arrays.asList(new SidedModule[] { SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new JulyClientModule())
          .serverModule((AbstractMonthlyModule)new JulyServerModule())
          .commonModule((AbstractMonthlyModule)new JulyCommonModule())
          .build(), 
          SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new AugustClientModule())
          .serverModule((AbstractMonthlyModule)new AugustServerModule())
          .commonModule((AbstractMonthlyModule)new AugustCommonModule())
          .build(), 
          SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new SeptemberClientModule())
          .serverModule((AbstractMonthlyModule)new SeptemberServerModule())
          .commonModule((AbstractMonthlyModule)new SeptemberCommonModule())
          .build(), 
          SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new NovemberClientModule())
          .serverModule((AbstractMonthlyModule)new NovemberServerModule())
          .commonModule((AbstractMonthlyModule)new NovemberCommonModule())
          .build(), 
          SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new DecemberClientModule())
          .serverModule((AbstractMonthlyModule)new DecemberServerModule())
          .commonModule((AbstractMonthlyModule)new DecemberCommonModule())
          .build(), 
          SidedModule.builder()
          .clientModule((AbstractMonthlyModule)new ClientMarch())
          .serverModule((AbstractMonthlyModule)new ServerMarch())
          .commonModule((AbstractMonthlyModule)new CommonMarch())
          .build() });
  }
  
  public static MonthlyManager getInstance() {
    if (instance == null)
      instance = new MonthlyManager(); 
    return instance;
  }
  
  public List<AbstractMonthlyModule> getModules(SideType side) {
    return (List<AbstractMonthlyModule>)this.modules.stream()
      .map(module -> {
          if (module == null)
            return null; 
          switch (side) {
            case CLIENT:
              return module.getClientModule();
            case SERVER:
              return module.getServerModule();
            case BOTH:
              return module.getCommonModule();
          } 
          return null;
        }).filter(Objects::nonNull)
      .collect(Collectors.toList());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\MonthlyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */