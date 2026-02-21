package fr.paladium.palamod.modules.hunter.proxies;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityGoat;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import fr.paladium.palamod.modules.hunter.entites.EntityParrot;
import fr.paladium.palamod.modules.hunter.entites.EntitySnail;
import fr.paladium.palamod.modules.hunter.entites.EntitySnake;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import fr.paladium.palamod.modules.hunter.renders.BambooRenderInventory;
import fr.paladium.palamod.modules.hunter.renders.RenderCavernousZombie;
import fr.paladium.palamod.modules.hunter.renders.RenderCrab;
import fr.paladium.palamod.modules.hunter.renders.RenderDolphin;
import fr.paladium.palamod.modules.hunter.renders.RenderElephant;
import fr.paladium.palamod.modules.hunter.renders.RenderFarmerChicken;
import fr.paladium.palamod.modules.hunter.renders.RenderFlowerMonster;
import fr.paladium.palamod.modules.hunter.renders.RenderGoat;
import fr.paladium.palamod.modules.hunter.renders.RenderJellyFish;
import fr.paladium.palamod.modules.hunter.renders.RenderMegaCreeper;
import fr.paladium.palamod.modules.hunter.renders.RenderPanda;
import fr.paladium.palamod.modules.hunter.renders.RenderParrot;
import fr.paladium.palamod.modules.hunter.renders.RenderSnail;
import fr.paladium.palamod.modules.hunter.renders.RenderSnake;
import fr.paladium.palamod.modules.hunter.renders.RenderTurtle;
import fr.paladium.palamod.modules.hunter.renders.TileEntityBambooRenderer;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityBamboo;
import fr.paladium.palamod.modules.paladium.client.render.ItemPrintPressRenderer;
import fr.paladium.palamod.modules.paladium.client.render.ItemTypeMachineRenderer;
import fr.paladium.palamod.modules.paladium.client.render.TileEntityPrintPressRenderer;
import fr.paladium.palamod.modules.paladium.client.render.TileEntityTypeMachineRenderer;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityTypeMachine;
import fr.paladium.palamod.modules.paladium.common.tileentities.TileEntityPrintPress;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
  public static int renderBamboo;
  
  public static int rituel = -1;
  
  public static List<String> players = new ArrayList<>();
  
  public static int printpressID;
  
  public static int typemachineID;
  
  public void registerRenders() {
    RenderingRegistry.registerEntityRenderingHandler(EntityFarmerChicken.class, (Render)new RenderFarmerChicken());
    RenderingRegistry.registerEntityRenderingHandler(EntityCavernousZombie.class, (Render)new RenderCavernousZombie());
    RenderingRegistry.registerEntityRenderingHandler(EntityFlowerMonster.class, (Render)new RenderFlowerMonster());
    RenderingRegistry.registerEntityRenderingHandler(EntityCrab.class, (Render)new RenderCrab());
    RenderingRegistry.registerEntityRenderingHandler(EntitySnail.class, (Render)new RenderSnail());
    RenderingRegistry.registerEntityRenderingHandler(EntityJellyFish.class, (Render)new RenderJellyFish());
    RenderingRegistry.registerEntityRenderingHandler(EntitySnake.class, (Render)new RenderSnake());
    RenderingRegistry.registerEntityRenderingHandler(EntityTurtle.class, (Render)new RenderTurtle());
    RenderingRegistry.registerEntityRenderingHandler(EntityParrot.class, (Render)new RenderParrot());
    RenderingRegistry.registerEntityRenderingHandler(EntityPanda.class, (Render)new RenderPanda());
    RenderingRegistry.registerEntityRenderingHandler(EntityGoat.class, (Render)new RenderGoat());
    RenderingRegistry.registerEntityRenderingHandler(EntityDolphin.class, (Render)new RenderDolphin());
    RenderingRegistry.registerEntityRenderingHandler(EntityElephant.class, (Render)new RenderElephant());
    RenderingRegistry.registerEntityRenderingHandler(EntityMegaCreeper.class, (Render)new RenderMegaCreeper());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrintPress.class, (TileEntitySpecialRenderer)new TileEntityPrintPressRenderer());
    printpressID = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTypeMachine.class, (TileEntitySpecialRenderer)new TileEntityTypeMachineRenderer());
    typemachineID = RenderingRegistry.getNextAvailableRenderId();
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.PRINT_PRESS), (IItemRenderer)new ItemPrintPressRenderer());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.TYPE_MACHINE), (IItemRenderer)new ItemTypeMachineRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBamboo.class, (TileEntitySpecialRenderer)new TileEntityBambooRenderer());
    renderBamboo = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BambooRenderInventory());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\proxies\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */