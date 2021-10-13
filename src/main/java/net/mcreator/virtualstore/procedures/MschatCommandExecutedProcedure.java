package net.mcreator.virtualstore.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.virtualstore.VirtualstoreMod;

import java.util.Map;
import java.util.HashMap;

public class MschatCommandExecutedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency entity for procedure MschatCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency cmdparams for procedure MschatCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("Tell"))) {
			VirtualstoreMod.LOGGER.info("Tell argument sent");
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/tellraw @a {\"text\":\"The Xbox is the best gaming console!\\nAny other questions?\"}");
				}
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("How"))) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/tellraw @a {\"text\":\"Starts at $1,099, go to ms.com to customize yours!\"}");
				}
			}
		}
	}
}
