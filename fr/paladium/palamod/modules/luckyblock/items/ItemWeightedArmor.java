package fr.paladium.palamod.modules.luckyblock.items;

import com.google.common.collect.Queues;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.pathfinding.Vec3i;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.scheduler.PaladiumRunnable;
import java.util.Queue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWeightedArmor extends ItemArmor implements ITooltipWiki {
  protected String model;
  
  public ItemWeightedArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model) {
    super(material, 0, type);
    this.model = model;
    func_111206_d("palamod:" + texture);
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (slot == 2)
      return "palamod:textures/models/" + this.model + "_2.png"; 
    return "palamod:textures/models/" + this.model + "_1.png";
  }
  
  public void onArmorTick(final World world, final EntityPlayer player, ItemStack itemStack) {
    BlockPos pos = new BlockPos((Entity)player);
    if (player.field_70143_R > 50.0F && player.field_70163_u > 0.0D && player.field_70163_u < (world
      .func_72976_f(pos.getX(), pos.getZ()) + player.field_70143_R / 20.0F) && !world.field_72995_K) {
      int power = (int)((player.field_70143_R - 40.0F) / 12.0F);
      if (power > 20)
        power = 20; 
      final Queue<Vec3i> blocks = Queues.newLinkedBlockingDeque();
      final int offset = power;
      int oz = -offset - ((offset / 8 <= 0) ? 0 : world.field_73012_v.nextInt(offset / 8));
      while (oz < offset + ((offset / 8 <= 0) ? 0 : world.field_73012_v.nextInt(offset / 8))) {
        int ox = -offset - ((offset / 8 <= 0) ? 0 : world.field_73012_v.nextInt(offset / 8));
        for (;; oz++) {
          if (ox < offset + ((offset / 8 <= 0) ? 0 : world.field_73012_v.nextInt(offset / 8))) {
            int oy = 0;
            for (;; ox++) {
              if (oy > -offset - ((offset / 5 <= 0) ? 0 : world.field_73012_v.nextInt(offset / 5))) {
                blocks.add(new Vec3i(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz));
                oy--;
                continue;
              } 
            } 
            break;
          } 
        } 
      } 
      (new PaladiumRunnable() {
          final int MAX_REPLACE_PER_TICK = 500;
          
          public void run() {
            if (blocks.isEmpty()) {
              cancel();
              return;
            } 
            int itr = 0;
            while (itr < 500 && !blocks.isEmpty()) {
              itr++;
              Vec3i vec = blocks.poll();
              if (EventUtils.canInteract(player, vec.getX(), vec.getY(), vec.getZ())) {
                Block b = world.func_147439_a(vec.getX(), vec.getY(), vec.getZ());
                float resistance = b.func_149638_a((Entity)player);
                if (offset > resistance)
                  world.func_147468_f(vec.getX(), vec.getY(), vec.getZ()); 
              } 
            } 
          }
        }).runTaskTimer(1L, 2L);
      EventUtils.spawnParticle(world, "flame", (int)player.field_70165_t, ((int)player.field_70163_u - 2), (int)player.field_70161_v, 500, 1.5D);
      itemStack.func_77972_a(power, (EntityLivingBase)player);
      if (itemStack.func_77960_j() >= itemStack.func_77958_k()) {
        itemStack.field_77994_a--;
        if (itemStack.field_77994_a <= 0)
          player.func_70062_b(1, null); 
      } 
      player.field_71071_by.field_70459_e = true;
      player.field_70143_R = 0.0F;
    } 
    super.onArmorTick(world, player, itemStack);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemWeightedArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */