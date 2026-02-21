package fr.paladium.palaforgeutils.lib.subcommand.builder;

import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.AnnotatedSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.ICommandSender;

public class SubCommandBuilder {
  public static final String NO_DESCRIPTION_MESSAGE = "Cette commande n'a pas de description";
  
  private final String name;
  
  private boolean firstNode;
  
  private String permission;
  
  private String description;
  
  private boolean executable;
  
  private Number min;
  
  private Number max;
  
  private Class<? extends Enum<?>> enumClass;
  
  private ASubCommand command;
  
  public String toString() {
    return "SubCommandBuilder(name=" + getName() + ", firstNode=" + isFirstNode() + ", permission=" + getPermission() + ", description=" + getDescription() + ", executable=" + isExecutable() + ", min=" + getMin() + ", max=" + getMax() + ", enumClass=" + getEnumClass() + ", command=" + getCommand() + ", type=" + getType() + ", senderTypes=" + getSenderTypes() + ", freeArgs=" + getFreeArgs() + ")";
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean isFirstNode() {
    return this.firstNode;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public boolean isExecutable() {
    return this.executable;
  }
  
  public Number getMin() {
    return this.min;
  }
  
  public Number getMax() {
    return this.max;
  }
  
  public Class<? extends Enum<?>> getEnumClass() {
    return this.enumClass;
  }
  
  public ASubCommand getCommand() {
    return this.command;
  }
  
  private SubCommandType type = SubCommandType.STRING;
  
  public SubCommandType getType() {
    return this.type;
  }
  
  private List<SenderType> senderTypes = new ArrayList<>();
  
  public List<SenderType> getSenderTypes() {
    return this.senderTypes;
  }
  
  private List<String> freeArgs = new ArrayList<>();
  
  public List<String> getFreeArgs() {
    return this.freeArgs;
  }
  
  private SubCommandBuilder(String name, String description) {
    this.name = name;
    this.description = description;
    if (this.description == null)
      this.description = "Cette commande n'a pas de description"; 
  }
  
  public static SubCommandBuilder create(Class<? extends ASubCommand> command) {
    return of(command);
  }
  
  public static SubCommandBuilder create(String name) {
    return new SubCommandBuilder(name, null);
  }
  
  public static SubCommandBuilder create(String name, String description) {
    return new SubCommandBuilder(name, description);
  }
  
  public SubCommandBuilder sender(SenderType... senderTypes) {
    this.senderTypes = Arrays.asList(senderTypes);
    return this;
  }
  
  public SubCommandBuilder string() {
    this.type = SubCommandType.STRING;
    return this;
  }
  
  public SubCommandBuilder executable() {
    this.executable = true;
    return this;
  }
  
  public SubCommandBuilder freeArgument() {
    this.type = SubCommandType.FREE_ARGUMENT;
    this.freeArgs = new ArrayList<>();
    return this;
  }
  
  public SubCommandBuilder freeArgument(String... freeArgs) {
    this.type = SubCommandType.FREE_ARGUMENT;
    this.freeArgs = Arrays.asList(freeArgs);
    return this;
  }
  
  public SubCommandBuilder player() {
    this.type = SubCommandType.PLAYER;
    return this;
  }
  
  public SubCommandBuilder number(Number min, Number max) {
    this.type = SubCommandType.NUMBER;
    this.min = min;
    this.max = max;
    return this;
  }
  
  public SubCommandBuilder number() {
    return number(Integer.valueOf(0), Integer.valueOf(0));
  }
  
  public SubCommandBuilder enumeration(Class<? extends Enum<?>> enumClass) {
    this.type = SubCommandType.ENUM;
    this.enumClass = enumClass;
    return this;
  }
  
  public SubCommandBuilder permission(String permission) {
    this.permission = permission;
    return this;
  }
  
  public SubCommandBuilder firstNode(boolean firstNode) {
    this.firstNode = firstNode;
    return this;
  }
  
  public ASubCommand build(ASubCommand current) {
    if (this.type == null)
      throw new RuntimeException("You must specify a type for the sub command"); 
    if (this.type == SubCommandType.ENUM && this.enumClass == null)
      throw new RuntimeException("You must specify an enum class for the enum type"); 
    current.setBuilder(this);
    this.command = current;
    registerNodes(current);
    return current;
  }
  
  public ASubCommand build(ASubCommand parent, ASubCommand current) {
    current = build(current);
    return parent(parent);
  }
  
  public ASubCommand build(ASubCommand current, ISubCallback callback) {
    current = build(current);
    return current.setCallback(callback);
  }
  
  public ASubCommand build(ASubCommand parent, ASubCommand current, ISubCallback callback) {
    current = build(current);
    return parent(parent)
      .setCallback(callback);
  }
  
  public ASubCommand parent(ASubCommand parent) {
    if (this.command == null)
      throw new RuntimeException("You must build the sub command before setting the parent"); 
    parent.addSubCommand(this.command);
    return this.command;
  }
  
  public ASubCommand register(Class<? extends ASubCommand> command) {
    firstNode(true);
    return PalaForgeUtilsMod.getServer().registerSubCommand(command, this);
  }
  
  public ASubCommand register() {
    return PalaForgeUtilsMod.getServer().registerSubCommand(this.command);
  }
  
  public void registerNodes(ASubCommand parent) {
    for (Method method : parent.getClass().getDeclaredMethods()) {
      SubCommandInfo info = method.<SubCommandInfo>getAnnotation(SubCommandInfo.class);
      if (info != null) {
        if (method.getReturnType() != boolean.class && method.getReturnType() != Boolean.class)
          throw new RuntimeException("Method " + method.getName() + " must return a boolean"); 
        Class<?>[] params = method.getParameterTypes();
        if (params.length != 2 && !params[0].equals(ICommandSender.class) && !params[1].equals(CommandData.class))
          throw new RuntimeException("Method " + method.getName() + " must have ICommandSender and CommandData as parameters"); 
        ISubCallback callback = (sender, data) -> {
            try {
              return ((Boolean)method.invoke(parent, new Object[] { sender, data })).booleanValue();
            } catch (Exception e) {
              throw new RuntimeException("Unable to invoke method " + method.getName(), e);
            } 
          };
        SubCommandBuilder builder = of(info);
        AnnotatedSubCommand subCommand = new AnnotatedSubCommand(builder);
        builder.build(parent, (ASubCommand)subCommand, callback);
      } 
    } 
  }
  
  public static ASubCommand addNode(ASubCommand parent, Class<? extends ASubCommand> command) {
    SubCommandBuilder builder = of(command);
    return builder.build(parent, builder.getCommand());
  }
  
  public static SubCommandBuilder of(Class<? extends ASubCommand> command) {
    try {
      ASubCommand instance = command.newInstance();
      SubCommandInfo info = command.<SubCommandInfo>getAnnotation(SubCommandInfo.class);
      SubCommandBuilder builder = of(info);
      builder.command = instance;
      instance.setBuilder(builder);
      return builder;
    } catch (Exception e) {
      throw new RuntimeException("Unable to get builder for " + command.getSimpleName(), e);
    } 
  }
  
  public static SubCommandBuilder of(SubCommandInfo info) {
    SubCommandBuilder builder = new SubCommandBuilder(info.name(), info.description());
    builder.executable = info.executable();
    builder.permission = info.permission();
    builder.min = Double.valueOf(info.min());
    builder.max = Double.valueOf(info.max());
    builder.enumClass = info.enumClass();
    builder.type = info.type();
    builder.sender(info.senderTypes());
    builder.freeArgs = Arrays.asList(info.freeArgs());
    return builder;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\builder\SubCommandBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */