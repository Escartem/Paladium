package fr.paladium.palaspawner.common.item;

import com.google.common.base.Strings;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palaspawner.common.event.GatherSpawnerSoulEvent;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import fr.paladium.palaspawner.common.tile.Tier;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCavernHammer extends ItemPickaxe implements ITooltipWiki {
  public static final String NAME = "cavern_hammer";
  
  public ItemCavernHammer() {
    super(Item.ToolMaterial.EMERALD);
    func_77637_a((CreativeTabs)SpawnerTabs.getInstance());
    func_77655_b("cavern_hammer");
    func_111206_d("palaspawner:cavern_hammer");
    func_77656_e(256);
  }
  
  public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
    World world = player.field_70170_p;
    Block block = world.func_147439_a(x, y, z);
    if (world.field_72995_K || !(block instanceof net.minecraft.block.BlockMobSpawner) || CommonModule.getInstance().getConfig().getServerType() != ServerType.MINAGE)
      return super.onBlockStartBreak(itemstack, x, y, z, player); 
    if (((IJobsPlayer)PalaJobsAPI.inst().getJobsPlayer(player).get()).getLevel(JobType.HUNTER) < 5) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez être niveau 5 pour utiliser cet item."));
      return true;
    } 
    if (!player.field_71071_by.func_146028_b(Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER)))
      return true; 
    TileEntityMobSpawner tile = (TileEntityMobSpawner)world.func_147438_o(x, y, z);
    if (tile == null)
      return true; 
    String spawnerType = tile.func_145881_a().func_98276_e();
    ItemStack foundItemStack = null;
    for (ItemStack stack : player.field_71071_by.field_70462_a) {
      if (stack != null && stack.func_77973_b() == Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER)) {
        NBTTagCompound nBTTagCompound = stack.func_77978_p();
        if (nBTTagCompound == null || !nBTTagCompound.func_74764_b("entityId") || !nBTTagCompound.func_74764_b("souls")) {
          foundItemStack = stack;
        } else {
          String str = nBTTagCompound.func_74779_i("entityId");
          int souls = nBTTagCompound.func_74762_e("souls");
          if (str == null || str.isEmpty() || (str.equalsIgnoreCase(spawnerType) && souls < Tier.FOUR.getRequiredSouls())) {
            foundItemStack = stack;
            break;
          } 
        } 
      } 
    } 
    if (foundItemStack == null)
      return true; 
    NBTTagCompound compound = foundItemStack.func_77978_p();
    if (compound == null) {
      compound = new NBTTagCompound();
      foundItemStack.func_77982_d(compound);
    } 
    int currentSoulCount = compound.func_74762_e("souls");
    String entityId = compound.func_74779_i("entityId");
    if (!Strings.isNullOrEmpty(entityId) && !entityId.equalsIgnoreCase(spawnerType))
      return true; 
    int newSoulCount = currentSoulCount + 1;
    compound.func_74768_a("souls", newSoulCount);
    compound.func_74778_a("entityId", spawnerType);
    foundItemStack.func_77964_b(Tier.getTier(newSoulCount).ordinal());
    foundItemStack.func_77982_d(compound);
    MinecraftForge.EVENT_BUS.post((Event)new GatherSpawnerSoulEvent(player));
    return super.onBlockStartBreak(itemstack, x, y, z, player);
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    list.add("§cNe fonctionne que en serveur minage");
    super.func_77624_a(p_77624_1_, p_77624_2_, list, p_77624_4_);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\item\ItemCavernHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */