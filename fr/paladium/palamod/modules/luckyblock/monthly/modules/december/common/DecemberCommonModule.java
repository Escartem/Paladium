package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.blocks.BlockChristmasWreath;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.blocks.BlockFirePlace;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.blocks.BlockGiftChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.items.ItemPotionJobTenMultiplier;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.potions.PotionInvincibility;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.potions.PotionJobTenMultiplier;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class DecemberCommonModule extends AbstractMonthlyModule {
  public static DecemberCommonModule INSTANCE;
  
  public DecemberCommonModule() {
    super(SideType.BOTH, LuckyType.CHRISTMAS);
    INSTANCE = this;
  }
  
  public void registerEventHandlers() {
    super.registerEventHandlers();
  }
  
  public void registerPotions() {
    RegistryUtils.potion(new APotion[] { PotionsRegister.JOB_TEN_MULTIPLIER = (new PotionJobTenMultiplier()).setIcon(ItemsRegister.POTION_JOB_TEN_MULTIPLIER), 
          PotionsRegister.INVINCIBILITY = (new PotionInvincibility()).setIcon(BlocksRegister.CHRISTMAS_WREATH) });
  }
  
  public void registerLuckyStats() {}
  
  public void registerEntities() {}
  
  public void registerTileEntities() {}
  
  public void registerPackets() {}
  
  public void registerItems() {
    RegistryUtils.item(new Item[] { ItemsRegister.POTION_JOB_TEN_MULTIPLIER = (Item)new ItemPotionJobTenMultiplier() });
  }
  
  public void registerBlocks() {
    RegistryUtils.block(new Block[] { BlocksRegister.FIRE_PLACE = (Block)new BlockFirePlace(), BlocksRegister.CHRISTMAS_WREATH = (Block)new BlockChristmasWreath(), BlocksRegister.GIFT_CHEST = (Block)new BlockGiftChest() });
  }
  
  public void registerCrafts() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\DecemberCommonModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */