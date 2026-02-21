package fr.paladium.palarpg.module.dungeon.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.lindworm.Lindworm;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.common.entity.room.boost.EntityDungeonPunchingBall;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.network.chest.CSPacketRPGDungeonChestValidate;
import fr.paladium.palarpg.module.dungeon.common.network.chest.SCPacketRPGDungeonChestOpen;
import fr.paladium.palarpg.module.dungeon.common.network.chest.SCPacketRPGDungeonChestRewardBroadcast;
import fr.paladium.palarpg.module.dungeon.common.network.choice.CSPacketRPGDungeonChoiceNext;
import fr.paladium.palarpg.module.dungeon.common.network.death.BBPacketRPGDungeonDeathRespawnPersonal;
import fr.paladium.palarpg.module.dungeon.common.network.death.BBPacketRPGDungeonDeathRespawnTeam;
import fr.paladium.palarpg.module.dungeon.common.network.death.SCPacketRPGDungeonDeathUseRespawn;
import fr.paladium.palarpg.module.dungeon.common.network.global.BBPacketRPGDungeonGlobalGetDungeons;
import fr.paladium.palarpg.module.dungeon.common.network.global.CSPacketRPGDungeonGlobalSpawnCommand;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubGetEntity;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubInvitePlayer;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubKickPlayer;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubSearchPlayer;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubSetLevel;
import fr.paladium.palarpg.module.dungeon.common.network.room.boost.SCPacketRPGDungeonRoomBoostPunchingBallAnimation;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.CSPacketRPGDungeonRoomMerchantSelect;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.SCPacketRPGDungeonRoomMerchantAnimation;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.SCPacketRPGDungeonRoomMerchantOpen;
import fr.paladium.palarpg.module.dungeon.common.network.skip.BBPacketRPGDungeonSkip;
import fr.paladium.palarpg.module.dungeon.common.network.start.CSPacketRPGDungeonReady;
import fr.paladium.palarpg.module.dungeon.common.network.start.SCPacketRPGDungeonLoading;
import fr.paladium.palarpg.module.dungeon.common.network.start.SCPacketRPGDungeonStart;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIClose;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUICursorMove;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIKeyPress;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseDrag;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMousePress;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseRelease;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseScroll;
import fr.paladium.palarpg.module.dungeon.common.network.world.SCPacketRPGDungeonWorldSync;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost.BoostFountainDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost.BoostPunchingBallDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost.BoostRespawnDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.puzzle.PuzzleDropperDungeonRoomBehavior;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DungeonCommonProxy extends AModProxy {
  private CreativeTabs tabDungeon;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    initNetwork("palarpg_dungeon");
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonStart.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRPGDungeonReady.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonLoading.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonWorldSync.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonGlobalGetDungeons.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRPGDungeonGlobalSpawnCommand.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonHubSetLevel.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonHubGetEntity.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonHubKickPlayer.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonHubSearchPlayer.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonHubInvitePlayer.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRPGDungeonChoiceNext.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSkip.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonChestOpen.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRPGDungeonChestValidate.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonChestRewardBroadcast.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonDeathUseRespawn.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonDeathRespawnTeam.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonDeathRespawnPersonal.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIClose.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUICursorMove.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIKeyPress.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIMouseDrag.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIMousePress.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIMouseRelease.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRPGDungeonSynchronizedUIMouseScroll.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonRoomMerchantOpen.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketRPGDungeonRoomMerchantSelect.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonRoomMerchantAnimation.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketRPGDungeonRoomBoostPunchingBallAnimation.class);
    DungeonRoomBehavior.registerBehavior("BOOST_RESPAWN", BoostRespawnDungeonRoomBehavior.class);
    DungeonRoomBehavior.registerBehavior("BOOST_FOUNTAIN", BoostFountainDungeonRoomBehavior.class);
    DungeonRoomBehavior.registerBehavior("BOOST_PUNCHING_BALL", BoostPunchingBallDungeonRoomBehavior.class);
    DungeonRoomBehavior.registerBehavior("PUZZLE_DROPPER", PuzzleDropperDungeonRoomBehavior.class);
    RPGPlayer.registerPlayerData(new Class[] { RPGDungeonPlayerData.class });
    RegistryUtils.entity(EntityDungeonMerchant.class, Color.RED, 100, Lindworm.getInstance());
    RegistryUtils.entity(EntityDungeonPunchingBall.class, Color.decode("#734210"), 100, Lindworm.getInstance());
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    ItemsRegister.DUNGEON_ITEMS.forEach(item -> item.func_77637_a(getTabDungeon()));
    BlocksRegister.DUNGEON_BLOCKS.forEach(block -> block.func_149647_a(getTabDungeon()));
  }
  
  public CreativeTabs getTabDungeon() {
    if (this.tabDungeon == null)
      return this.tabDungeon = new CreativeTabs("tabDungeon") {
          public String func_78024_c() {
            return "RPG Dungeon";
          }
          
          public Item func_78016_d() {
            return Item.func_150898_a(BlocksRegister.DUNGEON_HUB);
          }
        }; 
    return this.tabDungeon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\DungeonCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */