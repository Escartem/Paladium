package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemSpaceFood extends ItemFood implements ITooltipWiki {
  public ItemSpaceFood() {
    super(1, 1.0F, false);
    func_77655_b("spacefood");
    func_111206_d("palamod:spacefood");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(16);
  }
  
  protected void func_77849_c(ItemStack item, final World world, final EntityPlayer player) {
    if (!(player instanceof EntityPlayerMP))
      return; 
    final EntityPlayerMP p = (EntityPlayerMP)player;
    if (UsersManager.getSpacefood().contains(player))
      return; 
    ServerManager.addFreeze(p, player.field_70165_t, player.field_70163_u, player.field_70161_v);
    UsersManager.getSpacefood().add(player);
    (new Thread(new Runnable() {
          public void run() {
            double d = 0.0D;
            try {
              while (UsersManager.getSpacefood().contains(player)) {
                Thread.sleep(10L);
                EventUtils.spawnParticle(world, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10, d);
                d += 0.01D;
                if (d > 0.2D)
                  d = 0.2D; 
              } 
            } catch (Exception exception) {}
          }
        })).start();
    (new Thread(new Runnable() {
          public void run() {
            try {
              MinecraftServer.func_71276_C().func_71203_ab()
                .func_148539_a((IChatComponent)new ChatComponentText("§6[§eLuckyBlock§6] §b" + player
                    .func_70005_c_() + " s'en va vers les étoiles !!"));
              Thread.sleep(100L);
              int i;
              for (i = (int)player.field_70163_u; i < 256; i++) {
                for (int ox = -2; ox < 3; ox++) {
                  for (int oz = -2; oz < 3; oz++) {
                    if (EventUtils.canInteract(player, (int)player.field_70165_t + ox, i, (int)player.field_70161_v + oz))
                      world.func_147468_f((int)player.field_70165_t + ox, i, (int)player.field_70161_v + oz); 
                  } 
                } 
                Thread.sleep(20L);
              } 
              for (i = 10; i > 0; i--) {
                if (!UsersManager.getSpacefood().contains(player)) {
                  ServerManager.removeFreeze((EntityPlayerMP)player);
                  return;
                } 
                Thread.sleep(1000L);
                PlayerUtils.sendActionBar((EntityPlayerMP)player, "§4§l" + i);
              } 
              PlayerUtils.sendActionBar((EntityPlayerMP)player, "§4Décollage !!");
              int startingTicks = player.field_70173_aa;
              while (player.field_70163_u < 256.0D && UsersManager.getSpacefood().contains(player) && player.field_70173_aa - startingTicks < 300 && player
                .func_70089_S()) {
                Thread.sleep(50L);
                p.field_71075_bZ.field_75101_c = true;
                p.func_71016_p();
                player.field_70181_x += 0.1D;
                player.field_70143_R = 0.0F;
                if (!EventUtils.isAir(world, (int)player.field_70165_t, (int)player.field_70163_u + 1, (int)player.field_70161_v)) {
                  player.field_70181_x = 0.0D;
                  ((EntityPlayerMP)player).field_71135_a
                    .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
                  p.field_71075_bZ.field_75101_c = false;
                  p.func_71016_p();
                  UsersManager.getSpacefood().remove(player);
                  ServerManager.removeFreeze((EntityPlayerMP)player);
                  break;
                } 
                ((EntityPlayerMP)player).field_71135_a
                  .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
                EventUtils.spawnParticle(world, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 100, 0.01D);
                EventUtils.spawnParticle(world, "lava", player.field_70165_t, player.field_70163_u, player.field_70161_v, 100, 0.02D);
              } 
              if (UsersManager.getSpacefood().contains(player))
                UsersManager.getSpacefood().remove(player); 
              player.field_70181_x = 0.0D;
              ((EntityPlayerMP)player).field_71135_a
                .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
              p.field_71075_bZ.field_75101_c = false;
              p.func_71016_p();
              ServerManager.removeFreeze((EntityPlayerMP)player);
            } catch (Exception e) {
              p.field_71075_bZ.field_75101_c = false;
              p.func_71016_p();
              UsersManager.getSpacefood().remove(player);
              ServerManager.removeFreeze((EntityPlayerMP)player);
            } 
          }
        })).start();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSpaceFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */