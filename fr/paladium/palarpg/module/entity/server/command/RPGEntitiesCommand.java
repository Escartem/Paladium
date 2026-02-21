package fr.paladium.palarpg.module.entity.server.command;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameterTabComplete;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palarpg.module.entity.common.EntityCommonProxy;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;
import fr.paladium.palarpg.module.entity.server.loader.cache.RPGEntityCache;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class RPGEntitiesCommand {
  @SubCommand(command = "/rpg entities <name>", description = "Faire apparaitre une entité RPG", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void spawnEntity(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete(method = "completeEntitiesID")) String name) {
    RPGEntityData entityData = RPGEntityCache.get(name).copy();
    if (entityData != null) {
      EntityPlayerMP player = context.getPlayer();
      RPGMobEntity entity = entityData.create(player.field_70170_p);
      if (entity == null) {
        context.error("Une erreur est survenue lors de la création de l'entité");
        return;
      } 
      entity.func_70107_b(player.field_70165_t, player.field_70163_u + 2.0D, player.field_70161_v);
      player.field_70170_p.func_72838_d((Entity)entity);
      context.success("Entité spawn avec succès");
      return;
    } 
    context.error("Aucune entité n'existe pour l'id \"" + name + "\"");
  }
  
  @SubCommand(command = "/rpg entities list", description = "Lister les entités RPG chargées", permission = "rpg.command.admin.use")
  public void list(CommandContext context) {
    if (RPGEntityCache.isEmpty()) {
      context.error("Aucune entité n'est chargée");
      return;
    } 
    RPGEntityCache.ENTITY_CACHE.asMap().forEach((id, entityData) -> context.send("- " + id + " (model: " + id + ")"));
  }
  
  @SubCommand(command = "/rpg entities reload", description = "Recharger les entités RPG depuis les fichiers de configuration", permission = "rpg.command.admin.use")
  public void reload(CommandContext context) {
    RPGEntityCache.clear();
    RPGEntityLoader.load(EntityCommonProxy.CONFIG_DIRECTORY);
    context.success("Reload effectué avec succès");
  }
  
  @SideOnly(Side.SERVER)
  private String[] completeEntitiesID(CommandContext context) {
    return (String[])RPGEntityCache.getIDs().toArray((Object[])new String[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\command\RPGEntitiesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */