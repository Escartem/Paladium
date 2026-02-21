package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class BlazingSunEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Soleil de plomb";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "august/blazing_sun";
  
  public static final String STRUCTURE_NAME = "lead";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("lead"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "lead");
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onDrop(EntityJoinWorldEvent event) {
    Entity entity = event.entity;
    World world = entity.field_70170_p;
    if (world.field_72995_K)
      return; 
    if (!(entity instanceof EntityItem))
      return; 
    EntityItem item = (EntityItem)entity;
    if (item.func_92059_d() == null || !item.func_92059_d().func_77973_b().func_77658_a().equals(BlocksRegister.LEAD.func_149739_a()))
      return; 
    int x = MathHelper.func_76128_c(item.field_70165_t);
    int y = MathHelper.func_76128_c(item.field_70163_u);
    int z = MathHelper.func_76128_c(item.field_70161_v);
    while (world.func_147437_c(x, y, z) && y > 0)
      y--; 
    Block block = item.field_70170_p.func_147439_a(x, y, z);
    if (!(block instanceof net.minecraft.block.BlockCauldron))
      return; 
    ItemUtils.spawnItemAtEntity(entity, new ItemStack(Items.field_151043_k));
    item.func_70106_y();
  }
  
  public String getName() {
    return "Soleil de plomb";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "august/blazing_sun";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\BlazingSunEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */