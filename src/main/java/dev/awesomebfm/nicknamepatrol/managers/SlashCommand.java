package dev.awesomebfm.nicknamepatrol.managers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class SlashCommand {
    public abstract String getName();
    public abstract String getDescription();
    public abstract Role getRequiredRole();
    public abstract Permission getPermission();
    public abstract void execute(SlashCommandInteractionEvent e);
}
