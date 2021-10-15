
package net.mcreator.virtualstore.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraft.command.Commands;
import net.minecraft.command.CommandSource;

import net.mcreator.virtualstore.procedures.MschatCommandExecutedProcedure;
import net.mcreator.virtualstore.VirtualstoreMod;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import com.google.gson.JsonArray;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

@Mod.EventBusSubscriber
public class MschatCommand {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		ArgumentBuilder args = argument("message", StringArgumentType.greedyString()).executes(MschatCommand::execute);
		ArgumentBuilder cost = literal("cost").then(args).executes(MschatCommand::execute);
		ArgumentBuilder xbox = literal("xbox").then(cost).then(args).executes(MschatCommand::execute);
		ArgumentBuilder surface = literal("surface").then(cost).then(args).executes(MschatCommand::execute);
		LiteralArgumentBuilder mschat = (LiteralArgumentBuilder)
			literal("mschat")
			.then(xbox)
			.then(surface)
			.then(args)
			.executes(MschatCommand::execute);

		event.getDispatcher().register(mschat);
	}
	
	private static int execute(CommandContext<CommandSource> ctx) {
		ServerWorld world = ctx.getSource().getWorld();
		double x = ctx.getSource().getPos().getX();
		double y = ctx.getSource().getPos().getY();
		double z = ctx.getSource().getPos().getZ();
		Entity entity = ctx.getSource().getEntity();
		if (entity == null)
			entity = FakePlayerFactory.getMinecraft(world);
		HashMap<String, String> cmdparams = new HashMap<>();
		int[] index = {-1};
		Arrays.stream(ctx.getInput().split("\\s+")).forEach(param -> {
			if (index[0] >= 0)
				cmdparams.put(Integer.toString(index[0]), param);
			index[0]++;
		});
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("cmdparams", cmdparams);
			$_dependencies.put("messageparams", String.join(" ",cmdparams.values()));
			MschatCommandExecutedProcedure.executeProcedure($_dependencies);
		}
		return 0;
	}

	public static LiteralArgumentBuilder<CommandSource> literal(String name) {
        return LiteralArgumentBuilder.<CommandSource>literal(name);
    }

    public static <T> RequiredArgumentBuilder<CommandSource, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.<CommandSource, T>argument(name, type);
    }

	public static String getbotreply(String message){
		try{
			String jsonInputString = "{\"message\":\""+ message +"\"}";
			byte[] input = jsonInputString.getBytes("utf-8");
			URL url = new URL("https://minecraft-mschat-bot.azurewebsites.net/api/get-bot-reply");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type","application/json");
	        conn.setRequestProperty("Content-Length", "" + input.length);
	        conn.setDoOutput(true);
		    conn.getOutputStream().write(input, 0, input.length);
		    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        return sb.toString();
		}
		catch (Exception ex) 
		{
			VirtualstoreMod.LOGGER.info("EX:"+ex.getMessage());
			return ex.getMessage();
		}
	}
}
