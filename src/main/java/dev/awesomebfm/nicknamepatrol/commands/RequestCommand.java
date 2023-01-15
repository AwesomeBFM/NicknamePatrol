package dev.awesomebfm.nicknamepatrol.commands;

import dev.awesomebfm.nicknamepatrol.managers.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RequestCommand extends SlashCommand {
    @Override
    public String getName() {
        return "request";
    }

    @Override
    public String getDescription() {
        return "Request a nickname.";
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
        EmbedBuilder errorEmbed = new EmbedBuilder();
        EmbedBuilder responseEmbed = new EmbedBuilder();
        EmbedBuilder requestEmbed = new EmbedBuilder();

        errorEmbed.setColor(7684163);
        errorEmbed.setTitle("ERROR");
        errorEmbed.setFooter("NicknamePatrol v1.0");
        errorEmbed.setTimestamp(e.getTimeCreated());

        String nickname = e.getOption("nickname").getAsString();

        responseEmbed.setTitle("Request Submitted!");
        responseEmbed.setDescription("Your request has been submitted to the server moderators." +
                " Please be patient while moderators review your request.");
        responseEmbed.setColor(8768307);
        responseEmbed.setFooter("Nickname Patrol v1.0");
        responseEmbed.setTimestamp(e.getTimeCreated());

        requestEmbed.setAuthor(e.getUser().getName() + "#" + e.getUser().getDiscriminator(), e.getUser().getAvatarUrl(), e.getUser().getAvatarUrl());
        requestEmbed.setTitle("New Nickname Request");
        requestEmbed.setColor(14003278);
        requestEmbed.addField("User", e.getUser().getAsMention(), false);
        requestEmbed.addField("Requested Nickname", nickname, false);
        if (e.getMember().getNickname() != null) {
            requestEmbed.addField("Current Nickname", e.getMember().getNickname(), false);
        }
        requestEmbed.addField("Prior Nicknames", "coming soon", true);
        requestEmbed.addField("Last Nickname Change", "Coming soon", true);
        requestEmbed.setFooter("Nickname Patrol v1.0");
        requestEmbed.setTimestamp(e.getTimeCreated());
        e.getGuild().getChannelById(TextChannel.class, "1014369162921001010").sendMessageEmbeds(requestEmbed.build()).queue();

        e.replyEmbeds(responseEmbed.build()).setEphemeral(true).queue();


    }
}
