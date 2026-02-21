package fr.paladium.palamod.common.guihandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SimpleGHandler extends GHandler {
  private Supplier<Object> clientSupplier;
  
  private Supplier<Container> serverSupplier;
  
  private Function<GuiHandlerData, Container> serverFunction;
  
  private Function<GuiHandlerData, Object> clientFunction;
  
  public SimpleGHandler(Supplier<Container> server, Supplier<Object> client) {
    this.serverSupplier = server;
    this.clientSupplier = client;
  }
  
  public SimpleGHandler(Function<GuiHandlerData, Container> serverFunction, Function<GuiHandlerData, Object> clientFunction) {
    this.serverFunction = serverFunction;
    this.clientFunction = clientFunction;
  }
  
  @SideOnly(Side.SERVER)
  public Container getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    if (this.serverSupplier != null)
      return this.serverSupplier.get(); 
    return this.serverFunction.apply(new GuiHandlerData(player, world, x, y, z));
  }
  
  @SideOnly(Side.CLIENT)
  public GuiScreen getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
    if (this.clientSupplier != null)
      return (GuiScreen)this.clientSupplier.get(); 
    return (GuiScreen)this.clientFunction.apply(new GuiHandlerData(player, world, x, y, z));
  }
  
  public class GuiHandlerData {
    private final EntityPlayer player;
    
    private final World world;
    
    private final int x;
    
    private final int y;
    
    private final int z;
    
    public GuiHandlerData(EntityPlayer player, World world, int x, int y, int z) {
      this.player = player;
      this.world = world;
      this.x = x;
      this.y = y;
      this.z = z;
    }
    
    public EntityPlayer getPlayer() {
      return this.player;
    }
    
    public World getWorld() {
      return this.world;
    }
    
    public int getX() {
      return this.x;
    }
    
    public int getY() {
      return this.y;
    }
    
    public int getZ() {
      return this.z;
    }
    
    public final <T extends TileEntity> T getTileEntity(Class<T> clazz) {
      TileEntity tile = this.world.func_147438_o(this.x, this.y, this.z);
      if (tile != null && clazz.isInstance(tile))
        return clazz.cast(tile); 
      return null;
    }
    
    public final InventoryPlayer getInventory() {
      return this.player.field_71071_by;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\guihandler\SimpleGHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */