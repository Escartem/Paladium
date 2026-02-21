package fr.paladium.pet.server.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import fr.paladium.pet.server.commands.assignment.AssignmentSubCommand;
import fr.paladium.pet.server.commands.bypass.BypassSubCommand;
import fr.paladium.pet.server.commands.debug.DebugSubCommand;
import fr.paladium.pet.server.commands.debug.ResetSubCommand;
import fr.paladium.pet.server.commands.debug.UnlockSubCommand;
import fr.paladium.pet.server.commands.experience.ExperienceSubCommand;
import fr.paladium.pet.server.commands.give.GiveSubCommand;
import fr.paladium.pet.server.commands.give.ResetAdminSkinSubCommand;
import fr.paladium.pet.server.commands.happiness.HappinessSubCommand;
import fr.paladium.pet.server.commands.level.LevelSubCommand;
import fr.paladium.pet.server.commands.open.OpenSubCommand;
import fr.paladium.pet.server.commands.skill.SkillSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PetSubCommand extends ASubCommand {
  public static final String NAME = "pet";
  
  public static final String DESCRIPTION = "Gérer son familier";
  
  public PetSubCommand() {
    SubCommandBuilder.create("assignment")
      .permission("palapet.command.assignment")
      .string()
      .build(this, (ASubCommand)new AssignmentSubCommand());
    SubCommandBuilder.create("debug", "Debug un joueur")
      .permission("palapet.command.debug")
      .string()
      .build(this, (ASubCommand)new DebugSubCommand());
    SubCommandBuilder.create("reset", "Reset soi-même")
      .permission("palapet.command.reset")
      .sender(new SenderType[] { SenderType.PLAYER }).build(this, (ASubCommand)new ResetSubCommand());
    SubCommandBuilder.create("unlock", "Unlock soi-même")
      .permission("palapet.command.unlock")
      .sender(new SenderType[] { SenderType.PLAYER }).build(this, (ASubCommand)new UnlockSubCommand());
    SubCommandBuilder.create("skill")
      .permission("palapet.command.skill")
      .string()
      .build(this, (ASubCommand)new SkillSubCommand());
    SubCommandBuilder.create("happiness")
      .permission("paladium.pet.happiness")
      .string()
      .build(this, (ASubCommand)new HappinessSubCommand());
    SubCommandBuilder.create("give", "Give un skin à un joueur")
      .permission("palapet.command.give")
      .string()
      .build(this, (ASubCommand)new GiveSubCommand());
    SubCommandBuilder.create("reset-admin", "Reset les skins d'un admin")
      .permission("palapet.command.reset.admin")
      .string()
      .build(this, (ASubCommand)new ResetAdminSkinSubCommand());
    SubCommandBuilder.create("open", "Ouvrir l'interface")
      .sender(new SenderType[] { SenderType.PLAYER }).string()
      .build(this, (ASubCommand)new OpenSubCommand());
    SubCommandBuilder.create("bypass", "Bypass un joueur")
      .permission("paladium.pet.bypass")
      .string()
      .build(this, (ASubCommand)new BypassSubCommand());
    SubCommandBuilder.create("level")
      .permission("paladium.pet.level")
      .string()
      .build(this, (ASubCommand)new LevelSubCommand());
    SubCommandBuilder.create("experience")
      .permission("paladium.pet.experience")
      .string()
      .build(this, (ASubCommand)new ExperienceSubCommand());
  }
  
  public static boolean openHomeUI(EntityPlayerMP sender) {
    EntityPlayerMP player = sender;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has()) {
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.message((ICommandSender)player);
      return true;
    } 
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBOpenUIPacket(player, pet), player);
    return true;
  }
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    return openHomeUI((EntityPlayerMP)sender);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\PetSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */