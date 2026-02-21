package fr.paladium.palaschematicmod.server.command;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import fr.paladium.palaschematicmod.server.async.PasteCallback;

class null implements PasteCallback {
  public void onPasteComplete(TimedSchematic schematic) {
    context.success("Undo appliqué avec succès.");
    SchematicUtils.deleteUndoSchematic(playerUUID);
    synchronized (PalaSchematicCommand.access$000()) {
      PalaSchematicCommand.access$000().remove(playerUUID);
    } 
  }
  
  public void onPasteProgress(TimedSchematic schematic, int progress, int total) {
    context.send("§7Restauration en cours... §e" + progress + "§7/§e" + total + " §8(" + String.format("%.2f", new Object[] { Float.valueOf(progress * 100.0F / total) }) + "%)§7.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\command\PalaSchematicCommand$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */