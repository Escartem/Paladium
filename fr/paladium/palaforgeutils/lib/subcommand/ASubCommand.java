package fr.paladium.palaforgeutils.lib.subcommand;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaforgeutils.lib.subcommand.impl.HelpSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.utils.SubCommandUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import joptsimple.internal.Strings;
import lombok.NonNull;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class ASubCommand extends CommandBase {
  private static final String NO_PERMISSION_MESSAGE = "§cVous n'avez pas la permission d'utiliser cette commande.";
  
  protected SubCommandBuilder builder;
  
  protected ISubCallback callback;
  
  private int subIndex;
  
  private ASubCommand parent;
  
  private List<ASubCommand> subCommands;
  
  public void setBuilder(SubCommandBuilder builder) {
    this.builder = builder;
  }
  
  public void setSubIndex(int subIndex) {
    this.subIndex = subIndex;
  }
  
  public void setParent(ASubCommand parent) {
    this.parent = parent;
  }
  
  public void setSubCommands(List<ASubCommand> subCommands) {
    this.subCommands = subCommands;
  }
  
  public SubCommandBuilder getBuilder() {
    return this.builder;
  }
  
  public ISubCallback getCallback() {
    return this.callback;
  }
  
  public int getSubIndex() {
    return this.subIndex;
  }
  
  public ASubCommand getParent() {
    return this.parent;
  }
  
  public List<ASubCommand> getSubCommands() {
    return this.subCommands;
  }
  
  public ASubCommand() {
    this.subCommands = new ArrayList<>();
    this.subIndex = 0;
    init();
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    int len = args.length;
    if (len == 0 && this.builder.isFirstNode()) {
      if (this.builder.isExecutable()) {
        if (!isValidSenderType(sender) || !hasPermission(sender)) {
          ChatUtils.sendPrefixedMessage(sender, new String[] { "§cVous n'avez pas la permission d'utiliser cette commande." });
          return;
        } 
        performCurrentNode(sender, null);
        return;
      } 
      sendHelp(sender);
      return;
    } 
    CommandData data = new CommandData(args);
    perform(sender, args, data);
  }
  
  public List<String> func_71516_a(ICommandSender sender, String[] args) {
    if (this.subCommands == null || this.subCommands.isEmpty())
      return new ArrayList<>(); 
    int len = args.length;
    List<ASubCommand> subs = findSubs(
        SubCommandUtils.extractSubCommands(sender, SubCommandUtils.getSubCommand(this, args)), len);
    if (subs.isEmpty())
      return new ArrayList<>(); 
    return SubCommandUtils.extractTabulationValues(args[len - 1], subs);
  }
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    if (this.callback != null)
      return this.callback.accept(sender, data); 
    return true;
  }
  
  protected void init() {}
  
  protected void perform(ICommandSender sender, String[] args, CommandData data) {
    if (!isValidSenderType(sender) || !hasPermission(sender)) {
      ChatUtils.sendPrefixedMessage(sender, new String[] { "§cVous n'avez pas la permission d'utiliser cette commande." });
      return;
    } 
    if (args.length == 0) {
      sendHelp(sender);
      return;
    } 
    String arg = args[0];
    Optional<ASubCommand> result = findSubCommand(arg, data);
    if (!result.isPresent()) {
      sendHelp(sender);
      ChatUtils.sendColoredMessage(sender, new String[] { SubCommandUtils.getSubCommandErrorMessage(arg, this) });
      return;
    } 
    int len = args.length;
    ASubCommand sub = result.get();
    if (sub.hasSubCommand()) {
      if (sub.getBuilder().isExecutable() && len == 1) {
        splitArgs(data.getTravelArgs());
        if (!sub.isValidSenderType(sender) || !sub.hasPermission(sender)) {
          ChatUtils.sendPrefixedMessage(sender, new String[] { "§cVous n'avez pas la permission d'utiliser cette commande." });
          return;
        } 
        sub.performCurrentNode(sender, data);
        return;
      } 
      sub.perform(sender, splitArgs(args), data);
      return;
    } 
    splitArgs(data.getTravelArgs());
    if (!sub.isValidSenderType(sender) || !sub.hasPermission(sender)) {
      ChatUtils.sendPrefixedMessage(sender, new String[] { "§cVous n'avez pas la permission d'utiliser cette commande." });
      return;
    } 
    if (!sub.performCurrentNode(sender, data))
      sendHelp(sender); 
  }
  
  private Optional<ASubCommand> findStringSubCommand(String name) {
    return this.subCommands.stream()
      .filter(sub -> sub.getBuilder().getName().equalsIgnoreCase(name))
      .findFirst();
  }
  
  private Optional<ASubCommand> findPlayerSubCommand(ASubCommand sub, String name, CommandData data) {
    if (Arrays.<String>asList(MinecraftServer.func_71276_C().func_71213_z()).stream().anyMatch(playerName -> playerName.equalsIgnoreCase(name))) {
      data.addPlayerName(name);
      return Optional.of(sub);
    } 
    return Optional.empty();
  }
  
  private Optional<ASubCommand> findEnumSubCommand(ASubCommand sub, String name, CommandData data) {
    SubCommandBuilder subBuilder = sub.getBuilder();
    Optional<String> value = Arrays.stream(subBuilder.getEnumClass().getEnumConstants()).map(Enum::name).filter(enumName -> enumName.equalsIgnoreCase(name)).findFirst();
    if (value.isPresent()) {
      data.addEnumValue(value.get());
      return Optional.of(sub);
    } 
    return Optional.empty();
  }
  
  private Optional<ASubCommand> findNumberSubCommand(ASubCommand sub, String name, CommandData data) {
    try {
      Number numberValue = Double.valueOf(Double.parseDouble(name));
      double min = sub.getBuilder().getMin().doubleValue();
      double max = sub.getBuilder().getMax().doubleValue();
      if (min == max || (min <= numberValue.doubleValue() && max >= numberValue.doubleValue())) {
        data.addNumber(numberValue);
        return Optional.of(sub);
      } 
    } catch (NumberFormatException numberFormatException) {}
    return Optional.empty();
  }
  
  private Optional<ASubCommand> findFreeArgSubCommand(ASubCommand sub, String name, CommandData data) {
    List<String> freeArgs = sub.getBuilder().getFreeArgs();
    if (freeArgs.isEmpty() || freeArgs.contains(name)) {
      data.addFreeArg(name);
      return Optional.of(sub);
    } 
    return Optional.empty();
  }
  
  private Optional<ASubCommand> findSubCommand(String name, CommandData data) {
    Iterator<ASubCommand> iterator = this.subCommands.iterator();
    if (iterator.hasNext()) {
      ASubCommand sub = iterator.next();
      switch (sub.getBuilder().getType()) {
        case STRING:
          return findStringSubCommand(name);
        case PLAYER:
          return findPlayerSubCommand(sub, name, data);
        case ENUM:
          return findEnumSubCommand(sub, name, data);
        case NUMBER:
          return findNumberSubCommand(sub, name, data);
        case FREE_ARGUMENT:
          return findFreeArgSubCommand(sub, name, data);
      } 
      return Optional.empty();
    } 
    return Optional.empty();
  }
  
  public List<ASubCommand> findSubs(List<ASubCommand> subs, int argLen) {
    if (subs == null || subs.isEmpty())
      return new ArrayList<>(); 
    return (List<ASubCommand>)subs.stream()
      .filter(sub -> 
        (getParent() != null && sub.getParent() != null) ? getParent().getBuilder().getName().equalsIgnoreCase(sub.getParent().getBuilder().getName()) : ((sub.getSubIndex() == argLen)))
      
      .collect(Collectors.toList());
  }
  
  protected List<String> getHelp(ICommandSender sender) {
    if (this.builder.isFirstNode()) {
      Optional<ASubCommand> helpCommand = findSubByName("help");
      if (helpCommand.isPresent()) {
        HelpSubCommand help = (HelpSubCommand)helpCommand.get();
        help.sendCustomHelp(sender, 1);
        return null;
      } 
    } 
    List<String> helps = new ArrayList<>();
    helps.add("§8§m---------------------------------");
    helps.addAll(SubCommandUtils.buildHelp(sender, this));
    helps.add("§8§m---------------------------------");
    return helps;
  }
  
  protected void sendHelp(ICommandSender sender) {
    List<String> help = getHelp(sender);
    if (help == null || help.isEmpty())
      return; 
    getHelp(sender).forEach(message -> sender.func_145747_a((IChatComponent)new ChatComponentText(message)));
  }
  
  public String func_71517_b() {
    return this.builder.getName();
  }
  
  public String func_71518_a(ICommandSender sender) {
    return this.builder.getName();
  }
  
  protected boolean isValidSenderType(ICommandSender sender) {
    List<SenderType> senderTypes = this.builder.getSenderTypes();
    if (senderTypes == null || senderTypes.isEmpty())
      return true; 
    return senderTypes.stream().anyMatch(type -> type.isAllowed(sender));
  }
  
  public boolean hasPermission(ICommandSender sender) {
    String permission = this.builder.getPermission();
    if (Strings.isNullOrEmpty(permission) || !(sender instanceof net.minecraft.entity.player.EntityPlayerMP))
      return true; 
    return BukkitUtils.hasPermission((Entity)sender, permission);
  }
  
  protected Optional<ASubCommand> findSubByName(String name) {
    return this.subCommands.stream()
      .filter(sub -> sub.getBuilder().getName().equalsIgnoreCase(name))
      .findFirst();
  }
  
  public boolean hasSubCommand() {
    return (this.subCommands != null && !this.subCommands.isEmpty());
  }
  
  @SafeVarargs
  public final void addNodes(Class<? extends ASubCommand>... classes) {
    Arrays.<Class<? extends ASubCommand>>stream(classes).forEach(this::addNode);
  }
  
  public void addNode(Class<? extends ASubCommand> clazz) {
    SubCommandBuilder.addNode(this, clazz);
  }
  
  public void addSubCommand(@NonNull ASubCommand sub) {
    if (sub == null)
      throw new NullPointerException("sub is marked non-null but is null"); 
    if (this.subCommands == null)
      this.subCommands = new ArrayList<>(); 
    if (findSubByName(sub.getBuilder().getName()).isPresent())
      return; 
    sub.setParent(this);
    this.subCommands.add(sub);
  }
  
  public ASubCommand sub(ASubCommand sub) {
    addSubCommand(sub);
    return sub;
  }
  
  private String[] splitArgs(String[] args) {
    String[] newArgs = new String[args.length - 1];
    System.arraycopy(args, 1, newArgs, 0, args.length - 1);
    return newArgs;
  }
  
  public ASubCommand setCallback(ISubCallback callback) {
    this.callback = callback;
    return this;
  }
  
  public void initSubIndexes() {
    SubCommandUtils.extractSubCommands(null, this).forEach(sub -> {
          if (sub.getParent() != null)
            sub.setSubIndex(sub.getParent().getSubIndex() + 1); 
        });
  }
  
  public void addHelpSubCommand() {
    sub(
        SubCommandBuilder.create("help", "Affiche l'aide de la commande")
        .string()
        .build(this, (ASubCommand)new HelpSubCommand()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\ASubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */