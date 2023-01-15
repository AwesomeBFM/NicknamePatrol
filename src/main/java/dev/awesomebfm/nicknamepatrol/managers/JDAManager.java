package dev.awesomebfm.nicknamepatrol.managers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class JDAManager extends ListenerAdapter {
    private JDA jda;
    private static ArrayList<SlashCommand> commands = new ArrayList<>();

    public JDAManager(JDA jda) {
        this.jda = jda;
    }

    public JDAManager() {}

    public void registerCommand(SlashCommand command) {
        jda.upsertCommand(command.getName(), command.getDescription())
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getPermission()))
                .queue();
        commands.add(command);
    }

    public void registerCommand(SlashCommand command, OptionData[] options) {
        jda.upsertCommand(command.getName(), command.getDescription())
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getPermission()))
                .addOptions(options)
                .queue();
        commands.add(command);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        EmbedBuilder errorEmbed = new EmbedBuilder();
        errorEmbed.setTitle("ERROR");
        errorEmbed.setColor(Color.RED);
        errorEmbed.setFooter("Nickname Patrol");
        errorEmbed.setTimestamp(e.getTimeCreated());

        for (SlashCommand command : commands) {
            if (e.getName().equalsIgnoreCase(command.getName())) {
                if (command.getRequiredRole() == null) {
                    command.execute(e);
                    return;
                } else if (e.getMember().getRoles().contains(command.getRequiredRole())) {
                    command.execute(e);
                    return;
                } else {
                    errorEmbed.setDescription("⛔Missing Required Role!");
                    MessageEmbed builtErrorEmbed = errorEmbed.build();
                    e.replyEmbeds(builtErrorEmbed).queue();
                    return;
                }
            }
        }
    }
}
