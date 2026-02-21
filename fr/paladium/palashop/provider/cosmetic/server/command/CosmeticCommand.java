package fr.paladium.palashop.provider.cosmetic.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Range;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator.NotEmpty;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(command = {"cosmetic", "cosmetics", "palacosmetic", "palacosmetics", "cosmetique", "cosmetiques"}, description = "Commande de gestion des cosmétiques", permission = "palashop.command.cosmetic")
public class CosmeticCommand {
  @SubCommand(command = "cosmetic update", description = "Mettre à jour les cosmétiques", permission = "palashop.command.cosmetic.update")
  public void update(CommandContext context) {
    (new Thread(() -> CosmeticProvider.getInstance().update().thenAccept(()).exceptionally(()), "CosmeticCommand/Update"))
      
      .start();
  }
  
  @SubCommand(command = "cosmetic give <player> <factory> <cosmetic> [<count>]", description = "Donner un cosmétique à un joueur", permission = "palashop.command.cosmetic.give")
  public void give(CommandContext context, OfflinePlayer player, @NotEmpty String factory, @NotEmpty String cosmetic, Optional<Integer> count) {
    int amount = ((Integer)count.orElse(Integer.valueOf(1))).intValue();
    if (amount <= 0) {
      context.error("Le montant doit être supérieur à 0.");
      return;
    } 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(factory);
    if (!optionalFactory.isPresent()) {
      context.error("La catégorie de cosmétiques n'existe pas.");
      return;
    } 
    CosmeticFactory factoryInstance = optionalFactory.get();
    if ("all".equalsIgnoreCase(cosmetic)) {
      for (ICosmetic cosmeticInstance : factoryInstance.getCosmetics())
        give(context, player, cosmeticInstance.getId(), factory); 
      return;
    } 
    if (!factoryInstance.getCosmetic(cosmetic).isPresent()) {
      context.error("Le cosmétique n'existe pas.");
      return;
    } 
    give(context, player, cosmetic, factory);
  }
  
  private void give(@NonNull CommandContext context, @NonNull OfflinePlayer player, @NonNull String cosmetic, @NonNull String factory) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (factory == null)
      throw new NullPointerException("factory is marked non-null but is null"); 
    CosmeticProvider.getInstance()
      .addCosmetic(player.getUuidString(), cosmetic, factory)
      .thenAccept(data -> {
          if (data == null) {
            context.error("Le cosmétique " + cosmetic + " n'existe pas.");
            return;
          } 
          context.success("Le cosmétique " + data.getCosmetic() + " a été donné à " + player.getName());
        }).exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de l'ajout du cosmétique " + cosmetic + " à " + player.getName());
          return null;
        });
  }
  
  @SubCommand(command = "cosmetic equip <factory> <cosmetic> [<index>]", description = "Equiper un cosmétique", permission = "palashop.command.cosmetic.equip", sender = {SenderType.PLAYER})
  public void equip(CommandContext context, @NotEmpty String factory, @NotEmpty String cosmetic, Optional<Integer> index) {
    CosmeticPlayer player = CosmeticPlayer.get((Entity)context.getPlayer());
    if (player == null) {
      context.error("Vous n'avez pas de cosmétiques.");
      return;
    } 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(factory);
    if (!optionalFactory.isPresent()) {
      context.error("La catégorie de cosmétiques n'existe pas.");
      return;
    } 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(cosmetic);
    if (!optionalCosmetic.isPresent()) {
      context.error("Le cosmétique n'existe pas.");
      return;
    } 
    CosmeticProvider.getInstance()
      .hasCosmetic(UUIDUtils.toString((Entity)context.getPlayer()), cosmetic)
      .thenAccept(has -> {
          if (!has.booleanValue()) {
            context.error("Vous ne possédez pas ce cosmétique.");
            return;
          } 
          Optional<CosmeticPlayer.EquippedCosmetic> equippedCosmetic = player.getOutfit().get(factory);
          if (!equippedCosmetic.isPresent()) {
            context.error("La catégorie de cosmétiques n'existe pas.");
            return;
          } 
          if (index.isPresent()) {
            ((CosmeticPlayer.EquippedCosmetic)equippedCosmetic.get()).set(((Integer)index.get()).intValue(), optionalCosmetic.get());
          } else {
            ((CosmeticPlayer.EquippedCosmetic)equippedCosmetic.get()).equip(optionalCosmetic.get());
          } 
          player.sync();
          context.success("Le cosmétique " + ((ICosmetic)optionalCosmetic.get()).getName() + " a été équipé.");
        }).exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de la récupération du cosmétique.");
          return null;
        });
  }
  
  @SubCommand(command = "cosmetic outfit <outfitId>", description = "Choisir sa tenue cosmétique", permission = "palashop.command.cosmetic.outfit", sender = {SenderType.PLAYER})
  public void outfit(CommandContext context, @Range(min = 0.0D, max = 9.0D) int outfitId) {
    CosmeticPlayer player = CosmeticPlayer.get((Entity)context.getPlayer());
    if (player == null) {
      context.error("Vous n'avez pas de cosmétiques.");
      return;
    } 
    player.setOutfitId(outfitId).sync();
    context.success("Votre tenue cosmétique a été changée.");
  }
  
  @SubCommand(command = "cosmetic unequipall <factory>", description = "Déséquiper tous les cosmétiques", permission = "palashop.command.cosmetic.unequipall", sender = {SenderType.PLAYER})
  public void unequipall(CommandContext context, @NotEmpty String factory) {
    CosmeticPlayer player = CosmeticPlayer.get((Entity)context.getPlayer());
    if (player == null) {
      context.error("Vous n'avez pas de cosmétiques.");
      return;
    } 
    player.getOutfit().get(factory).ifPresent(equippedCosmetic -> {
          equippedCosmetic.clear();
          player.sync();
        });
    context.success("Tous les cosmétiques ont été déséquipés.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\server\command\CosmeticCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */