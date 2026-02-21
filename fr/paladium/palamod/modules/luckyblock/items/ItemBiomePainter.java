package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketUpdateBiome;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemBiomePainter extends Item implements ITooltipWiki {
  public ItemBiomePainter() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(1024);
    func_77655_b("biome_painter");
    func_111206_d("palamod:biome_painter");
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (world.func_147439_a(x, y, z) == Blocks.field_150349_c && EventUtils.canInteract(player, x, y, z)) {
      if (item.func_77942_o()) {
        if (item.func_77978_p().func_74764_b("biomeId")) {
          if (item.func_77960_j() < item.func_77958_k()) {
            if (!world.field_72995_K)
              if (!EventUtils.canInteract(player, x, y, z)) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§cCet item ne peut être utilisé ici."));
              } else {
                byte biome = item.func_77978_p().func_74771_c("biomeId");
                int relBlockX = x & 0xF;
                int relBlockZ = z & 0xF;
                if (world.func_72938_d(x, z).func_76605_m()[relBlockZ * 16 + relBlockX] != biome) {
                  world.func_72938_d(x, z).func_76605_m()[relBlockZ * 16 + relBlockX] = biome;
                  (world.func_72938_d(x, z)).field_76643_l = true;
                  int radius = 30;
                  for (Object o : world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((x - radius), 0.0D, (z - radius), (x + radius), 255.0D, (z + radius)))) {
                    if (o instanceof EntityPlayerMP) {
                      EntityPlayerMP p = (EntityPlayerMP)o;
                      PalaMod.getNetwork().sendTo((IMessage)new PacketUpdateBiome(x, z, biome), p);
                    } 
                  } 
                  item.func_77972_a(1, (EntityLivingBase)player);
                } else {
                  player.func_145747_a((IChatComponent)new ChatComponentText("§cCe biome est déjà un biome " + (BiomeGenBase.func_150568_d(biome)).field_76791_y + "."));
                } 
              }  
          } else {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cVotre biome painter n'est pas chargé."));
          } 
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cVotre biome painter n'est pas chargé."));
        } 
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVotre biome painter n'est pas chargé."));
      } 
    } else {
      player.func_145747_a((IChatComponent)new ChatComponentText("§cCet item ne peut être utilisé que sur de l'herbe."));
    } 
    return super.func_77648_a(item, player, world, x, y, z, side, px, py, pz);
  }
  
  public boolean onEntityItemUpdate(EntityItem entityItem) {
    ItemStack item = entityItem.func_92059_d();
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    int damage = item.func_77960_j();
    if (!compound.func_74764_b("biomeId"))
      damage = item.func_77958_k(); 
    if (entityItem.field_70173_aa % 20 == 0) {
      damage--;
      EventUtils.spawnParticle(entityItem.field_70170_p, "spell", entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, 10, 0.05D);
    } 
    setDamage(item, damage);
    compound.func_74774_a("biomeId", (byte)(entityItem.field_70170_p.getBiomeGenForCoordsBody((int)entityItem.field_70165_t, (int)entityItem.field_70161_v)).field_76756_M);
    item.func_77982_d(compound);
    return super.onEntityItemUpdate(entityItem);
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lore, boolean flag) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("biomeId")) {
      lore.add("§eBiome : §b" + (BiomeGenBase.func_150568_d(item.func_77978_p().func_74771_c("biomeId"))).field_76791_y);
      lore.add("§eUtilisations restantes : §b" + (item.func_77958_k() - item.func_77960_j()));
      lore.add("§cCet item dispawn au §6PalaLag");
    } 
    super.func_77624_a(item, player, lore, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBiomePainter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */