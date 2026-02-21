package fr.paladium.palaforgeutils.lib.command.annotated.context;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.exception.CommandConditionException;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandParser;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.NonNull;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public final class CommandContext {
  private final ICommandSender sender;
  
  private final SenderType type;
  
  private final String command;
  
  private final String[] args;
  
  private CommandContext(ICommandSender sender, SenderType type, String command, String[] args) {
    this.sender = sender;
    this.type = type;
    this.command = command;
    this.args = args;
  }
  
  public ICommandSender getSender() {
    return this.sender;
  }
  
  public SenderType getType() {
    return this.type;
  }
  
  public String getCommand() {
    return this.command;
  }
  
  public String[] getArgs() {
    return this.args;
  }
  
  @NonNull
  public static CommandContext create(@NonNull ICommandSender sender, @NonNull ICommand command, @NonNull String[] args) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (command == null)
      throw new NullPointerException("command is marked non-null but is null"); 
    if (args == null)
      throw new NullPointerException("args is marked non-null but is null"); 
    return new CommandContext(sender, SenderType.get(sender), command.func_71517_b(), args);
  }
  
  @NonNull
  public static CommandContext create(@NonNull ICommandSender sender, @NonNull String command, @NonNull String[] args) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (command == null)
      throw new NullPointerException("command is marked non-null but is null"); 
    if (args == null)
      throw new NullPointerException("args is marked non-null but is null"); 
    return new CommandContext(sender, SenderType.get(sender), command, args);
  }
  
  @NonNull
  public final CompletableFuture<Void> async(@NonNull Runnable runnable) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    return CompletableFuture.runAsync(runnable);
  }
  
  @NonNull
  public final CompletableFuture<Void> async(@NonNull Runnable runnable, @NonNull Executor executor) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    if (executor == null)
      throw new NullPointerException("executor is marked non-null but is null"); 
    return CompletableFuture.runAsync(runnable, executor);
  }
  
  @NonNull
  private final CompletableFuture<Void> schedule(@NonNull Runnable runnable, long value, @NonNull TimeUnit unit) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    if (unit == null)
      throw new NullPointerException("unit is marked non-null but is null"); 
    CompletableFuture<Void> future = new CompletableFuture<>();
    (new Thread(() -> {
          try {
            Thread.sleep(unit.toMillis(value));
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
              FMLClientScheduler.getInstance().add(new Runnable[] { () }, );
            } else {
              FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
            } 
          } catch (InterruptedException e) {
            future.completeExceptionally(e);
          } 
        }"CommandContext-Scheduler/" + this.command)).start();
    return future;
  }
  
  @NonNull
  public final CommandContext condition(@NonNull Supplier<Boolean> supplier, @NonNull String message) {
    if (supplier == null)
      throw new NullPointerException("supplier is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    if (!((Boolean)supplier.get()).booleanValue())
      throw new CommandConditionException(this, message); 
    return this;
  }
  
  @NonNull
  public final CommandContext breakLine() {
    this.sender.func_145747_a((IChatComponent)new ChatComponentText(""));
    return this;
  }
  
  @NonNull
  public final CommandContext send(@NonNull IChatComponent message) {
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    this.sender.func_145747_a(message);
    return this;
  }
  
  @NonNull
  public final CommandContext send(@NonNull String message) {
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    this.sender.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §r" + message)).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RESET)));
    return this;
  }
  
  @NonNull
  public final CommandContext success(@NonNull String success) {
    if (success == null)
      throw new NullPointerException("success is marked non-null but is null"); 
    this.sender.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §a" + success)).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
    return this;
  }
  
  @NonNull
  public final CommandContext error(@NonNull String error) {
    if (error == null)
      throw new NullPointerException("error is marked non-null but is null"); 
    this.sender.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §c" + error)).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public final CommandContext kick(@NonNull String reason) {
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    if (this.sender instanceof EntityPlayerMP)
      ((EntityPlayerMP)this.sender).field_71135_a.func_147360_c(reason); 
    return this;
  }
  
  @SideOnly(Side.SERVER)
  public final EntityPlayerMP getPlayer() {
    if (this.sender instanceof EntityPlayerMP)
      return (EntityPlayerMP)this.sender; 
    return null;
  }
  
  public final EntityPlayer getRawPlayer() {
    if (this.sender instanceof EntityPlayer)
      return (EntityPlayer)this.sender; 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public final MinecraftServer getConsole() {
    if (this.sender instanceof MinecraftServer)
      return (MinecraftServer)this.sender; 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public final CommandBlockLogic getCommandBlock() {
    if (this.sender instanceof CommandBlockLogic)
      return (CommandBlockLogic)this.sender; 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public final RConConsoleSource getRcon() {
    if (this.sender instanceof RConConsoleSource)
      return (RConConsoleSource)this.sender; 
    return null;
  }
  
  public final boolean isPlayer() {
    return (this.type == SenderType.PLAYER);
  }
  
  public final boolean isConsole() {
    return (this.type == SenderType.CONSOLE);
  }
  
  public final boolean isCommandBlock() {
    return (this.type == SenderType.COMMAND_BLOCK);
  }
  
  public final boolean isRcon() {
    return (this.type == SenderType.RCON);
  }
  
  @SideOnly(Side.SERVER)
  public final boolean hasPermission(String permission) {
    if (permission == null || permission.isEmpty())
      return true; 
    if (this.sender instanceof Entity)
      return BukkitUtils.hasPermission((Entity)this.sender, permission); 
    return true;
  }
  
  public final <T> T get(int index, @NonNull Class<T> type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (index < 0 || index >= this.args.length)
      return null; 
    return (T)CommandParser.parseArgument(this, this.args[index], type);
  }
  
  public final String get(int index) {
    if (index < 0 || index >= this.args.length)
      return null; 
    return this.args[index];
  }
  
  public final String getFull() {
    return this.command + " " + String.join(" ", (CharSequence[])this.args);
  }
  
  public final int size() {
    return this.args.length;
  }
  
  public final int length() {
    return this.args.length;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\context\CommandContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */