package fr.paladium.palapass.common.pojo.quest;

import fr.paladium.palapass.common.quest.factions.FactionEnterClaimQuest;
import fr.paladium.palapass.common.quest.factions.PvpAreaKillQuest;
import fr.paladium.palapass.common.quest.market.MarketBuyQuest;
import fr.paladium.palapass.common.quest.market.MarketSellQuest;
import fr.paladium.palapass.common.quest.misc.BlockBreakQuest;
import fr.paladium.palapass.common.quest.misc.EnterWorldQuest;
import fr.paladium.palapass.common.quest.misc.EntityKillQuest;
import fr.paladium.palapass.common.quest.misc.EquipmentSetQuest;
import fr.paladium.palapass.common.quest.misc.FishingQuest;
import fr.paladium.palapass.common.quest.misc.FurnaceCraftQuest;
import fr.paladium.palapass.common.quest.misc.ItemCraftQuest;
import fr.paladium.palapass.common.quest.misc.ItemEnchantQuest;
import fr.paladium.palapass.common.quest.misc.ItemGiveQuest;
import fr.paladium.palapass.common.quest.misc.ItemUseQuest;
import fr.paladium.palapass.common.quest.misc.VisitServerQuest;
import fr.paladium.palapass.common.quest.misc.WalkDistanceQuest;
import fr.paladium.palapass.common.quest.palamod.GrinderCraftQuest;
import fr.paladium.palapass.common.quest.palamod.GrinderSmeltQuest;
import fr.paladium.palapass.common.quest.palamod.ModifierApplyQuest;
import fr.paladium.palapass.common.quest.palamod.PalamachineCraftQuest;
import fr.paladium.palapass.common.quest.palamod.PrintBookQuest;
import fr.paladium.palapass.common.quest.palamod.RingRepairQuest;
import fr.paladium.palapass.common.quest.palamod.SpellUseQuest;
import fr.paladium.palapass.common.quest.shop.ShopBuyQuest;
import fr.paladium.palapass.common.quest.shop.ShopSellQuest;

public enum EnumQuestsType {
  FACTION_ENTER_CLAIM((Class)FactionEnterClaimQuest.class),
  PVP_AREA_KILL((Class)PvpAreaKillQuest.class),
  MARKET_SELL((Class)MarketSellQuest.class),
  MARKET_BUY((Class)MarketBuyQuest.class),
  ITEM_GIVE((Class)ItemGiveQuest.class),
  EQUIPMENT((Class)EquipmentSetQuest.class),
  WALK_DISTANCE((Class)WalkDistanceQuest.class),
  ITEM_USE((Class)ItemUseQuest.class),
  ITEM_ENCHANT((Class)ItemEnchantQuest.class),
  FISHING((Class)FishingQuest.class),
  ENTITY_KILL((Class)EntityKillQuest.class),
  ITEM_CRAFT((Class)ItemCraftQuest.class),
  BLOCK_BREAK((Class)BlockBreakQuest.class),
  ENTER_WORLD((Class)EnterWorldQuest.class),
  SERVER_VISIT((Class)VisitServerQuest.class),
  FURNACE_CRAFT((Class)FurnaceCraftQuest.class),
  SPELL_USE((Class)SpellUseQuest.class),
  PRINT_BOOK((Class)PrintBookQuest.class),
  GRINDER_CRAFT((Class)GrinderCraftQuest.class),
  MODIFIER_APPLY((Class)ModifierApplyQuest.class),
  GRINDER_SMELT((Class)GrinderSmeltQuest.class),
  RING_REPAIR((Class)RingRepairQuest.class),
  PALAMACHINE_CRAFT((Class)PalamachineCraftQuest.class),
  SHOP_SELL((Class)ShopSellQuest.class),
  SHOP_BUY((Class)ShopBuyQuest.class);
  
  public Class<? extends Quest> linkedClass;
  
  EnumQuestsType(Class<? extends Quest> linkedClass) {
    this.linkedClass = linkedClass;
  }
  
  public static Class<? extends Quest> getClassFromQuestType(String questType) {
    return (valueOf(questType)).linkedClass;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\pojo\quest\EnumQuestsType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */