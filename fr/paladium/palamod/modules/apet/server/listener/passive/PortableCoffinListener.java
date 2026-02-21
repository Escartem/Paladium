package fr.paladium.palamod.modules.apet.server.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionClaim;
import fr.paladium.factions.api.util.Location;
import fr.paladium.factions.core.cache.CacheSettings;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.ChestUtils;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PortableCoffinListener {
  private static final int CHEST_INVENTORY_SIZE = 27;
  
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
    int x = MathHelper.func_76128_c(player.field_70165_t);
    int y = MathHelper.func_76128_c(player.field_70163_u);
    int z = MathHelper.func_76128_c(player.field_70161_v);
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.PORTABLE_COFFIN.getResponse(pet);
    if (!response.isHas() || LuckyBlockUtils.isInCombat((EntityPlayer)player))
      return; 
    Location location = new Location(x, y, z);
    IFactionClaim claim = ClaimController.getInstance().getClaim(location.toChunkVector());
    CacheSettings settings = new CacheSettings(true, 120000L);
    if (claim != null && claim.getFactionUuid() != null) {
      IFaction faction = FactionController.getInstance().getExistingFactionSync(claim.getFactionUuid(), settings);
      if (faction != null && faction.getTownEntity().isInTerritory(location))
        return; 
    } 
    if (!PetUtils.canInteract((EntityPlayer)player, x, y, z) || !PetUtils.canInteract((EntityPlayer)player, x, y + 1, z) || (isEmpty(player.field_71071_by.field_70462_a) && isEmpty(player.field_71071_by.field_70460_b)))
      return; 
    World world = player.field_70170_p;
    List<ItemStack> items = getAndRemoveItems(player);
    if (items.size() <= 27) {
      world.func_147449_b(x, y, z, (Block)Blocks.field_150486_ae);
      ChestUtils.fillChest(world, x, y, z, items.<ItemStack>toArray(new ItemStack[0]));
      return;
    } 
    world.func_147449_b(x, y, z, (Block)Blocks.field_150486_ae);
    world.func_147449_b(x, y + 1, z, (Block)Blocks.field_150486_ae);
    ChestUtils.fillChest(world, x, y, z, (ItemStack[])items.subList(0, 27).toArray((Object[])new ItemStack[0]));
    ChestUtils.fillChest(world, x, y + 1, z, (ItemStack[])items.subList(27, items.size()).toArray((Object[])new ItemStack[0]));
  }
  
  private boolean isEmpty(ItemStack[] items) {
    for (ItemStack item : items) {
      if (item != null)
        return false; 
    } 
    return true;
  }
  
  private List<ItemStack> getAndRemoveItems(EntityPlayerMP player) {
    List<ItemStack> items = new ArrayList<>();
    int i;
    for (i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack item = player.field_71071_by.func_70301_a(i);
      if (item != null) {
        items.add(item);
        player.field_71071_by.func_70299_a(i, null);
      } 
    } 
    for (i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
      ItemStack item = player.field_71071_by.field_70460_b[i];
      if (item != null) {
        items.add(item);
        player.field_71071_by.field_70460_b[i] = null;
      } 
    } 
    return items;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\listener\passive\PortableCoffinListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */