package fr.paladium.palaschematicmod.server.command;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.server.async.PasteCallback;

class null implements PasteCallback {
  public void onPasteComplete(TimedSchematic schematic) {
    context.success("Schematic §e" + name + "§a collée avec succès.");
    if (createUndo)
      context.send("§7Tapez §e/psm undo§7 pour annuler."); 
  }
  
  public void onPasteProgress(TimedSchematic schematic, int progress, int total) {
    context.send("§7Collage en cours... §e" + progress + "§7/§e" + total + " §8(" + String.format("%.2f", new Object[] { Float.valueOf(progress * 100.0F / total) }) + "%)§7.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\command\PalaSchematicCommand$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */