package fr.paladium.palaautomation.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

public class CSPipeMachinePickupItemsPacket extends ForgePacket {
  public static final int MAX_PICKUP_AMOUNT = 576;
  
  @PacketData
  private double x;
  
  @PacketData
  private double y;
  
  @PacketData
  private double z;
  
  @PacketData
  private int amount;
  
  public CSPipeMachinePickupItemsPacket() {}
  
  public CSPipeMachinePickupItemsPacket(double x, double y, double z, int amount) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.amount = amount;
  }
  
  @SideOnly(Side.CLIENT)
  public static void send(IPipeMachine pipeMachine, int amount) {
    if (pipeMachine == null)
      return; 
    boolean shift = (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(29));
    TileEntity tile = (TileEntity)pipeMachine;
    PipeItemData itemData = pipeMachine.getItemData();
    if (itemData == null)
      return; 
    ItemStack targetStack = itemData.toMaxSizeItemStack();
    if (shift && targetStack != null)
      amount = targetStack.field_77994_a; 
    CSPipeMachinePickupItemsPacket packet = new CSPipeMachinePickupItemsPacket(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, amount);
    packet.send();
  }
  
  public void processServer(EntityPlayerMP player) {
    if (this.amount <= 0 || this.amount > 576)
      return; 
    if (!PipeUtils.isUseableByPlayer(this.x, this.y, this.z, (EntityPlayer)player))
      return; 
    TileEntity tile = player.field_70170_p.func_147438_o(MathHelper.func_76128_c(this.x), MathHelper.func_76128_c(this.y), MathHelper.func_76128_c(this.z));
    if (!(tile instanceof IPipeMachine))
      return; 
    IPipeMachine pipeMachine = (IPipeMachine)tile;
    PipeItemData itemData = pipeMachine.getItemData();
    if (itemData == null)
      return; 
    this.amount = Math.min(this.amount, itemData.getCount());
    itemData.toItemStacks(this.amount).forEach(stack -> InventoryUtils.giveOrDropitems((EntityPlayer)player, stack));
    if (!itemData.decrement(this.amount))
      pipeMachine.setItemData(null, false); 
    pipeMachine.onPipeItemDataChanged();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\packet\CSPipeMachinePickupItemsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */