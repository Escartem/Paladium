package fr.paladium.palarpg.module.dungeon.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientChatListener;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientDeathListener;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientLoadingListener;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientSoundListener;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientSynchronizedUIListener;
import fr.paladium.palarpg.module.dungeon.client.render.block.TileEntityDungeonChestRenderer;
import fr.paladium.palarpg.module.dungeon.client.render.block.TileEntityDungeonHubRenderer;
import fr.paladium.palarpg.module.dungeon.client.render.block.TileEntityDungeonRoomRenderer;
import fr.paladium.palarpg.module.dungeon.client.render.entity.room.boost.RenderEntityDungeonPunchingBall;
import fr.paladium.palarpg.module.dungeon.client.render.entity.room.merchant.RenderEntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.DungeonCommonProxy;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonHub;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.entity.room.boost.EntityDungeonPunchingBall;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class DungeonClientProxy extends DungeonCommonProxy {
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    addListener(new Class[] { DungeonClientChatListener.class });
    addListener(new Class[] { DungeonClientSoundListener.class });
    addListener(new Class[] { DungeonClientDeathListener.class });
    addListener(new Class[] { DungeonClientLoadingListener.class });
    addListener(new Class[] { DungeonClientSynchronizedUIListener.class });
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDungeonHub.class, (TileEntitySpecialRenderer)new TileEntityDungeonHubRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDungeonRoom.class, (TileEntitySpecialRenderer)new TileEntityDungeonRoomRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDungeonChest.class, (TileEntitySpecialRenderer)new TileEntityDungeonChestRenderer());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.DUNGEON_HUB), (IItemRenderer)new TileEntityDungeonHubRenderer());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.DUNGEON_CHEST), (IItemRenderer)new TileEntityDungeonChestRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityDungeonMerchant.class, (Render)new RenderEntityDungeonMerchant());
    RenderingRegistry.registerEntityRenderingHandler(EntityDungeonPunchingBall.class, (Render)new RenderEntityDungeonPunchingBall());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\DungeonClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */