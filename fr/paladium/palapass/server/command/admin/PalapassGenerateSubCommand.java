package fr.paladium.palapass.server.command.admin;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassCopyText;
import java.util.Base64;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PalapassGenerateSubCommand extends ASubCommand {
  public static final String NAME = "generate";
  
  public static final String PERMISSION = "palapass.admin";
  
  public PalapassGenerateSubCommand() {
    StringSubCommand.create("b64", "Format Base64")
      .build(this, generateItem(true));
    StringSubCommand.create("minecraft", "Format Minecraft")
      .build(this, generateItem(false));
    StringSubCommand.create("entity", "Entity List ID")
      .build(this, generateEntity());
  }
  
  private ISubCallback generateItem(boolean isEncoded) {
    return (sender, data) -> {
        String encoded;
        if (!(sender instanceof EntityPlayerMP)) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        } 
        EntityPlayerMP player = (EntityPlayerMP)sender;
        if (player.func_70694_bm() == null) {
          player.func_146105_b((IChatComponent)new ChatComponentText("§cVous devez avoir un item en main pour cela."));
          return true;
        } 
        if (isEncoded) {
          encoded = Base64.getEncoder().encodeToString(ItemStackSerializer.write(player.func_70694_bm()).getBytes()).toString();
        } else {
          encoded = (GameRegistry.findUniqueIdentifierFor(player.func_70694_bm().func_77973_b())).modId + ":" + (GameRegistry.findUniqueIdentifierFor(player.func_70694_bm().func_77973_b())).name;
        } 
        (new Notification(Notification.NotificationType.SUCCESS, encoded, "paladium")).send(player);
        (new SCPacketPalapassCopyText(encoded)).send(player);
        return true;
      };
  }
  
  private ISubCallback generateEntity() {
    return (sender, data) -> {
        if (!(sender instanceof EntityPlayerMP)) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        } 
        EntityPlayerMP player = (EntityPlayerMP)sender;
        double searchRadius = 50.0D;
        Entity closestEntity = null;
        double closestDistance = 50.0D;
        List<Entity> entities = player.field_70170_p.func_72839_b((Entity)player, player.field_70121_D.func_72314_b(50.0D, 50.0D, 50.0D));
        for (Entity entity : entities) {
          if (entity instanceof net.minecraft.entity.player.EntityPlayer)
            continue; 
          double distanceToEntity = player.func_70032_d(entity);
          if (distanceToEntity < closestDistance) {
            closestEntity = entity;
            closestDistance = distanceToEntity;
          } 
        } 
        System.out.println(EntityList.field_75626_c);
        if (closestEntity != null) {
          if (EntityList.field_75626_c.containsKey(closestEntity.getClass())) {
            String entityID = (String)EntityList.field_75626_c.get(closestEntity.getClass());
            player.func_145747_a((IChatComponent)new ChatComponentText("§aEntité la plus proche : §e" + closestEntity.getClass().getSimpleName() + " (Palapass ID : " + entityID + ")"));
            (new Notification(Notification.NotificationType.SUCCESS, entityID, "paladium")).send(player);
            (new SCPacketPalapassCopyText(entityID)).send(player);
          } 
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cAucune entité proche trouvée."));
        } 
        return true;
      };
  }
  
  public static void spawnParticle(World world, String particle, double x, double y, double z, int count, double vel) {
    if (world instanceof WorldServer) {
      WorldServer worldServer = (WorldServer)world;
      worldServer.func_147487_a(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, vel);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\admin\PalapassGenerateSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */