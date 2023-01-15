package dev.awesomebfm.nicknamepatrol.commands;

import dev.awesomebfm.nicknamepatrol.managers.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class HelloCommand extends SlashCommand {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getDescription() {
        return "Says hello";
    }

    @Override
    public Role getRequiredRole() {
        return null;
    }

    @Override
    public Permission getPermission() {
        return Permission.MESSAGE_SEND;
    }

    @Override
    public void execute(SlashCommandInteractionEvent e) {
        e.reply("Hello!").queue();
    }
}
