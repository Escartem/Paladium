package fr.paladium.palamod.modules.luckyblock.luckystats.common.utils;

public enum LuckyStatsError {
  LOADING("§8[§6Paladium§8] §cLes statistiques sont déjà en cours de chargement."),
  DB_ERROR("§8[§6Paladium§8] §cImpossible de charger vos statistiques."),
  REWARD_ERROR("§8[§6Paladium§8] §cVous n'avez pas assez de place dans votre inventaire.");
  
  private String defaultError;
  
  public String getDefaultError() {
    return this.defaultError;
  }
  
  LuckyStatsError(String defaultError) {
    this.defaultError = defaultError;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\commo\\utils\LuckyStatsError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */