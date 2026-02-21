package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityUltraSafeChest;
import fr.paladium.palamod.modules.paladium.client.render.AlchemyCreatorRender;
import fr.paladium.palamod.modules.paladium.client.render.AmethystChestRender;
import fr.paladium.palamod.modules.paladium.client.render.BetBlockRenderInventory;
import fr.paladium.palamod.modules.paladium.client.render.BlockBushRender;
import fr.paladium.palamod.modules.paladium.client.render.EndiumChestRender;
import fr.paladium.palamod.modules.paladium.client.render.EntityAncientHammerEffectRenderer;
import fr.paladium.palamod.modules.paladium.client.render.GPaladiumChestRender;
import fr.paladium.palamod.modules.paladium.client.render.ItemAlchemyCreatorRender;
import fr.paladium.palamod.modules.paladium.client.render.ItemAncientHammerRenderer;
import fr.paladium.palamod.modules.paladium.client.render.ItemChestRender;
import fr.paladium.palamod.modules.paladium.client.render.ItemStatueRenderer;
import fr.paladium.palamod.modules.paladium.client.render.PaladiumChestRender;
import fr.paladium.palamod.modules.paladium.client.render.RenderAncientHammerFakePlayer;
import fr.paladium.palamod.modules.paladium.client.render.RenderBlockSpike;
import fr.paladium.palamod.modules.paladium.client.render.RenderChrismasHammer;
import fr.paladium.palamod.modules.paladium.client.render.RenderDynamite;
import fr.paladium.palamod.modules.paladium.client.render.RenderDynamiteEndium;
import fr.paladium.palamod.modules.paladium.client.render.RenderDynamiteNinja;
import fr.paladium.palamod.modules.paladium.client.render.RenderEntityPotion;
import fr.paladium.palamod.modules.paladium.client.render.RenderHalloweenHat;
import fr.paladium.palamod.modules.paladium.client.render.RenderPistolTank;
import fr.paladium.palamod.modules.paladium.client.render.RenderTotem;
import fr.paladium.palamod.modules.paladium.client.render.TileEntityBetBlockRenderer;
import fr.paladium.palamod.modules.paladium.client.render.TileEntityStatueRenderer;
import fr.paladium.palamod.modules.paladium.client.render.TitaneChestRender;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityBetBlock;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.AncientHammerFakePlayerEntity;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEndiumEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteNinjaEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityPotionGun;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntitySplashPotion;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import fr.paladium.palamod.modules.paladium.common.logics.AmethystChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.EndiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.GPaladiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityStatue;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityTotem;
import fr.paladium.palamod.modules.paladium.common.logics.TitaneChestLogic;
import fr.paladium.palamod.modules.paladium.dummy.EntityDummy;
import fr.paladium.palamod.modules.paladium.dummy.EntityFloatingNumber;
import fr.paladium.palamod.modules.paladium.dummy.RenderDummy;
import fr.paladium.palamod.modules.paladium.dummy.RenderFloatingNumber;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class PRegister_Renderer {
  public static int renderBlockSpikeId;
  
  public static int renderStatue;
  
  public static int renderBetBlock;
  
  public static void init() {
    ClientRegistry.bindTileEntitySpecialRenderer(PaladiumChestLogic.class, (TileEntitySpecialRenderer)new PaladiumChestRender());
    ClientRegistry.bindTileEntitySpecialRenderer(AlchemyCreatorLogic.class, (TileEntitySpecialRenderer)new AlchemyCreatorRender());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockBushRender());
    renderBlockSpikeId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockSpike());
    ClientRegistry.bindTileEntitySpecialRenderer(EndiumChestLogic.class, (TileEntitySpecialRenderer)new EndiumChestRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TitaneChestLogic.class, (TileEntitySpecialRenderer)new TitaneChestRender());
    ClientRegistry.bindTileEntitySpecialRenderer(AmethystChestLogic.class, (TileEntitySpecialRenderer)new AmethystChestRender());
    ClientRegistry.bindTileEntitySpecialRenderer(GPaladiumChestLogic.class, (TileEntitySpecialRenderer)new GPaladiumChestRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTotem.class, (TileEntitySpecialRenderer)new RenderTotem());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, (TileEntitySpecialRenderer)new TileEntityStatueRenderer());
    renderStatue = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBetBlock.class, (TileEntitySpecialRenderer)new TileEntityBetBlockRenderer());
    renderBetBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BetBlockRenderInventory());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.PALADIUM_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new PaladiumChestLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK), (IItemRenderer)new ItemAlchemyCreatorRender((TileEntitySpecialRenderer)new AlchemyCreatorRender(), (TileEntity)new AlchemyCreatorLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.SAFE_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntitySafeChest()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.SAFE_CHEST_MEGA), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityMegaSafeChest()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.SAFE_CHEST_ULTRA), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityUltraSafeChest()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.BRUTEFORCER), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityBruteforcer()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.ENDIUM_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new EndiumChestLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.TITANE_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new TitaneChestLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.AMETHYST_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new AmethystChestLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.GPALADIUM_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new GPaladiumChestLogic()));
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.STATUE), (IItemRenderer)new ItemStatueRenderer());
    RenderingRegistry.registerEntityRenderingHandler(DynamiteEntity.class, (Render)new RenderDynamite(0));
    RenderingRegistry.registerEntityRenderingHandler(DynamiteNinjaEntity.class, (Render)new RenderDynamiteNinja(1));
    RenderingRegistry.registerEntityRenderingHandler(EntityPotionGun.class, (Render)new RenderSnowball((Item)Items.field_151068_bn, 16449));
    RenderingRegistry.registerEntityRenderingHandler(EntitySplashPotion.class, (Render)new RenderEntityPotion());
    RenderingRegistry.registerEntityRenderingHandler(DynamiteEndiumEntity.class, (Render)new RenderDynamiteEndium(0));
    RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class, (Render)new RenderDummy());
    RenderingRegistry.registerEntityRenderingHandler(EntityFloatingNumber.class, (Render)new RenderFloatingNumber());
    RenderingRegistry.registerEntityRenderingHandler(EntityAncientHammerEffect.class, (Render)new EntityAncientHammerEffectRenderer());
    RenderingRegistry.registerEntityRenderingHandler(AncientHammerFakePlayerEntity.class, (Render)new RenderAncientHammerFakePlayer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.HALLOWEEN_HAT, (IItemRenderer)new RenderHalloweenHat());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.CHRISMAS_HAMMER, (IItemRenderer)new RenderChrismasHammer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PISTOL_TANK, (IItemRenderer)new RenderPistolTank());
    MinecraftForgeClient.registerItemRenderer((Item)ItemsRegister.ANCIENT_HAMMER, (IItemRenderer)new ItemAncientHammerRenderer());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Renderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */