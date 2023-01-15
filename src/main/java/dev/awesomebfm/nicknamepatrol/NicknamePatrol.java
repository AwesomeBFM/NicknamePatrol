package dev.awesomebfm.nicknamepatrol;

import dev.awesomebfm.nicknamepatrol.commands.HelloCommand;
import dev.awesomebfm.nicknamepatrol.commands.RequestCommand;
import dev.awesomebfm.nicknamepatrol.managers.JDAManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class NicknamePatrol {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        JDA bot = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .addEventListeners(new JDAManager())
                .build();

        SubcommandData data = new SubcommandData("new", "world");

        bot.upsertCommand("ping", "world").addSubcommands(data).queue();

        JDAManager manager = new JDAManager(bot);
        manager.registerCommand(new HelloCommand());
        OptionData[] options = {
                new OptionData(OptionType.STRING, "nickname", "The nickname you are requesting", true)
        };
        manager.registerCommand(new RequestCommand(), options);

    }
}
