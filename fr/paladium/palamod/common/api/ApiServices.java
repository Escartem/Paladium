package fr.paladium.palamod.common.api;

import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.common.api.http.ProfileServices;

public class ApiServices {
  public static class Http {
    private static ProfileServices minecraftApi = null;
    
    public static ProfileServices getMinecraftApi() {
      return minecraftApi;
    }
    
    public static void setMinecraftApi(ProfileServices minecraftApi) {
      Http.minecraftApi = minecraftApi;
    }
    
    private static PaladiumServices.MAILBOX mailboxService = null;
    
    public static PaladiumServices.MAILBOX getMailboxService() {
      return mailboxService;
    }
    
    public static void setMailboxService(PaladiumServices.MAILBOX mailboxService) {
      Http.mailboxService = mailboxService;
    }
    
    private static PaladiumServices.BOSS bossService = null;
    
    public static PaladiumServices.BOSS getBossService() {
      return bossService;
    }
    
    public static void setBossService(PaladiumServices.BOSS bossService) {
      Http.bossService = bossService;
    }
    
    private static PaladiumServices.EGGHUNT eggHuntService = null;
    
    public static PaladiumServices.EGGHUNT getEggHuntService() {
      return eggHuntService;
    }
    
    public static void setEggHuntService(PaladiumServices.EGGHUNT eggHuntService) {
      Http.eggHuntService = eggHuntService;
    }
    
    private static PaladiumServices.HALLOWEEN halloweenService = null;
    
    public static PaladiumServices.HALLOWEEN getHalloweenService() {
      return halloweenService;
    }
    
    public static void setHalloweenService(PaladiumServices.HALLOWEEN halloweenService) {
      Http.halloweenService = halloweenService;
    }
    
    private static PaladiumServices.TRIXIUM trixiumService = null;
    
    public static PaladiumServices.TRIXIUM getTrixiumService() {
      return trixiumService;
    }
    
    public static void setTrixiumService(PaladiumServices.TRIXIUM trixiumService) {
      Http.trixiumService = trixiumService;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\ApiServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */