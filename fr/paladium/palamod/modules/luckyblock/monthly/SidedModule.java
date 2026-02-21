package fr.paladium.palamod.modules.luckyblock.monthly;

public class SidedModule {
  private AbstractMonthlyModule clientModule;
  
  private AbstractMonthlyModule serverModule;
  
  private AbstractMonthlyModule commonModule;
  
  SidedModule(AbstractMonthlyModule clientModule, AbstractMonthlyModule serverModule, AbstractMonthlyModule commonModule) {
    this.clientModule = clientModule;
    this.serverModule = serverModule;
    this.commonModule = commonModule;
  }
  
  public static SidedModuleBuilder builder() {
    return new SidedModuleBuilder();
  }
  
  public static class SidedModuleBuilder {
    private AbstractMonthlyModule clientModule;
    
    private AbstractMonthlyModule serverModule;
    
    private AbstractMonthlyModule commonModule;
    
    public SidedModuleBuilder clientModule(AbstractMonthlyModule clientModule) {
      this.clientModule = clientModule;
      return this;
    }
    
    public SidedModuleBuilder serverModule(AbstractMonthlyModule serverModule) {
      this.serverModule = serverModule;
      return this;
    }
    
    public SidedModuleBuilder commonModule(AbstractMonthlyModule commonModule) {
      this.commonModule = commonModule;
      return this;
    }
    
    public SidedModule build() {
      return new SidedModule(this.clientModule, this.serverModule, this.commonModule);
    }
    
    public String toString() {
      return "SidedModule.SidedModuleBuilder(clientModule=" + this.clientModule + ", serverModule=" + this.serverModule + ", commonModule=" + this.commonModule + ")";
    }
  }
  
  public AbstractMonthlyModule getClientModule() {
    return this.clientModule;
  }
  
  public AbstractMonthlyModule getServerModule() {
    return this.serverModule;
  }
  
  public AbstractMonthlyModule getCommonModule() {
    return this.commonModule;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\SidedModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */