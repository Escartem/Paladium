package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandParser;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public final class DynamicSubCommandParameter implements SubCommandEntry.SubCommandParameter {
  private final String name;
  
  private final String description;
  
  private final String error;
  
  private final TabCompleteEntry tabComplete;
  
  private final boolean optional;
  
  private final boolean infinite;
  
  private final Parameter parameter;
  
  private final Class<?> type;
  
  public DynamicSubCommandParameter(String name, String description, String error, TabCompleteEntry tabComplete, boolean optional, boolean infinite, Parameter parameter, Class<?> type) {
    this.name = name;
    this.description = description;
    this.error = error;
    this.tabComplete = tabComplete;
    this.optional = optional;
    this.infinite = infinite;
    this.parameter = parameter;
    this.type = type;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getError() {
    return this.error;
  }
  
  public TabCompleteEntry getTabComplete() {
    return this.tabComplete;
  }
  
  public boolean isOptional() {
    return this.optional;
  }
  
  public boolean isInfinite() {
    return this.infinite;
  }
  
  public Parameter getParameter() {
    return this.parameter;
  }
  
  public Class<?> getType() {
    return this.type;
  }
  
  public boolean test(@NonNull CommandContext context, String argument) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (this.optional && (argument == null || argument.isEmpty()))
      return true; 
    Class<?> type = this.infinite ? String.class : this.type;
    try {
      CommandParser.parseArgument(context, argument, type);
    } catch (Exception e) {
      return false;
    } 
    return true;
  }
  
  public CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (this.tabComplete != null && !this.tabComplete.isEmpty() && context.isPlayer())
      return this.tabComplete.getTabComplete(context); 
    List<String> list = new ArrayList<>();
    if (Number.class.isAssignableFrom(this.type) || this.type == int.class || this.type == double.class || this.type == float.class || this.type == long.class || this.type == short.class || this.type == byte.class) {
      list.add("0");
    } else if (Boolean.class.isAssignableFrom(this.type) || this.type == boolean.class) {
      list.add("true");
      list.add("false");
    } else if (Enum.class.isAssignableFrom(this.type)) {
      Enum[] arrayOfEnum = (Enum[])this.type.getEnumConstants();
      for (Enum<?> constant : arrayOfEnum)
        list.add(constant.name()); 
    } else if ((EntityPlayer.class.isAssignableFrom(this.type) || OfflinePlayer.class.isAssignableFrom(this.type)) && FMLCommonHandler.instance().getSide() == Side.SERVER) {
      list.addAll(Arrays.asList(MinecraftServer.func_71276_C().func_71213_z()));
    } 
    return CompletableFuture.completedFuture(list);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\SubCommandEntry$DynamicSubCommandParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */