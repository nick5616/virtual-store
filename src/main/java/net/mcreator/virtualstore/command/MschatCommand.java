
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

@Mod.EventBusSubscriber
public class MschatCommand {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher()
				.register(LiteralArgumentBuilder.<CommandSource>literal("mschat")
						.then(LiteralArgumentBuilder.<CommandSource>literal("xbox").executes(MschatCommand::execute))
						.then(LiteralArgumentBuilder.<CommandSource>literal("surface").executes(MschatCommand::execute))
						.then(Commands.argument("arguments", StringArgumentType.greedyString()).executes(MschatCommand::execute))
						.executes(MschatCommand::execute));
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

	public static String sendmessagetobot(String message){
		try{
			String auth = "Bearer 3xPwe7lPojg.j6eZmSkXZejFcqSHJsxpc-d4ReFbod4_K_ZViOXraSQ";
			String convId = startconversation();
			String jsonInputString = "{\"locale\":\"en-EN\",\"type\":\"message\",\"from\":{\"id\":\"dev\"},\"text\":\""+ message +"\"}";
			byte[] input = jsonInputString.getBytes("utf-8");
			URL url = new URL("https://directline.botframework.com/v3/directline/conversations/"+convId+"/activities");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", auth);
	        conn.setRequestProperty("Content-Type","application/json");
	        conn.setRequestProperty("Content-Length", "" + input.length);
	        conn.setDoOutput(true);
		    conn.getOutputStream().write(input, 0, input.length);
		    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
	        String response = getbotresponse(convId);
	        return response;
		}
		catch (Exception ex) {
			VirtualstoreMod.LOGGER.info("EX:"+ex.getMessage());
			return ex.getMessage();}
	}

	private static String getbotresponse(String convId){
		try{
			String auth = "Bearer 3xPwe7lPojg.j6eZmSkXZejFcqSHJsxpc-d4ReFbod4_K_ZViOXraSQ";
			URL url = new URL("https://directline.botframework.com/v3/directline/conversations/"+convId+"/activities");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", auth);
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        
	        VirtualstoreMod.LOGGER.info(sb.toString());
	        
	        JsonArray activities = new JsonParser().parse(sb.toString()).getAsJsonObject().getAsJsonArray("activities");
	        
	        int last = activities.size()-1;
	        JsonObject obj = activities.get(last).getAsJsonObject();
	        String response = obj.get("text").getAsString();
	        return response;
        }
		catch (Exception ex) {
			VirtualstoreMod.LOGGER.info("EX:"+ex.getMessage());
			return ex.getMessage();}
	}

	private static String startconversation(){
		try
		{
			String auth = "Bearer 3xPwe7lPojg.j6eZmSkXZejFcqSHJsxpc-d4ReFbod4_K_ZViOXraSQ";
	        URL url = new URL("https://directline.botframework.com/v3/directline/conversations");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", auth);
	        conn.setRequestProperty("Content-Length", "0");
	        conn.setDoOutput(true);
	        conn.getOutputStream();
	        
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
	        
	        String response = jsonObject.get("conversationId").getAsString();
	        
	        return response;
		}
		catch (Exception ex) {return ex.getMessage();}
	}
}
