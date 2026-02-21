package fr.paladium.palamod.modules.ranking;

import fr.paladium.ranking.server.api.Comparator;
import fr.paladium.ranking.server.api.EnumComparator;
import fr.paladium.ranking.server.api.EnumComparatorOrder;
import fr.paladium.ranking.server.manager.RankingModule;
import java.util.ArrayList;
import java.util.List;

public class RankingManager {
  public static void init() {
    List<Comparator> comparators = new ArrayList<>();
    comparators.add(new Comparator(EnumComparator.VALUE, EnumComparatorOrder.DESCENDING));
    comparators.add(new Comparator(EnumComparator.TIMESTAMP, EnumComparatorOrder.ASCENDING));
    RankingModule moneyModule = new RankingModule("Argent", "ranking:textures/gui/icons/money.png", "", "$", "money", comparators);
    RankingModule jobModuleAlchemist = new RankingModule("Métier Alchimiste", "ranking:textures/gui/icons/alchimiste.png", "", "LVL", "job.alchemist", comparators);
    RankingModule jobModuleHunter = new RankingModule("Métier Hunter", "ranking:textures/gui/icons/hunter.png", "", "LVL", "job.hunter", comparators);
    RankingModule jobModuleMiner = new RankingModule("Métier Mineur", "ranking:textures/gui/icons/mineur.png", "", "LVL", "job.miner", comparators);
    RankingModule jobModuleFarmer = new RankingModule("Métier Farmeur", "ranking:textures/gui/icons/farmeur.png", "", "LVL", "job.farmer", comparators);
    RankingModule bossModule = new RankingModule("Boss Tués", "ranking:textures/gui/icons/boss.png", "", "", "boss", comparators);
    RankingModule egghuntModule = new RankingModule("Possession Egghunt", "ranking:textures/gui/icons/egghunt.png", "", "MIN", "egghunt", comparators);
    RankingModule endModule = new RankingModule("Possession End", "ranking:textures/gui/icons/end.png", "", "MIN", "end", comparators);
    RankingModule chorusModule = new RankingModule("Chorus Récupérés", "ranking:textures/gui/icons/chorus.png", "Nombre maximum de chorus durant une session", "", "chorus", comparators);
    RankingModule kothModule = new RankingModule("Koth Gagnés", "ranking:textures/gui/icons/koth.png", "", "", "koth", comparators);
    RankingModule clickerModule = new RankingModule("ClicCoin Gagnés", "ranking:textures/gui/icons/clicker.png", "", "", "clicker", comparators);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(moneyModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(jobModuleAlchemist);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(jobModuleHunter);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(jobModuleMiner);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(jobModuleFarmer);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(bossModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(egghuntModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(endModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(chorusModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(kothModule);
    fr.paladium.ranking.server.manager.RankingManager.getInstance().addModule(clickerModule);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ranking\RankingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */