package fr.paladium.palamod.modules.enderchest.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.enderchest.render.PaladiumEnderChestRender;
import fr.paladium.palamod.modules.enderchest.tileentity.TileEntityPaladiumEnderChest;
import fr.paladium.palamod.modules.paladium.client.render.ItemChestRender;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
  public void register() {
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPaladiumEnderChest.class, (TileEntitySpecialRenderer)new PaladiumEnderChestRender());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a((Block)BlocksRegister.PALADIUM_ENDER_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityPaladiumEnderChest()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */