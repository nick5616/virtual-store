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
		Entity entity = (Entity) dependencies.get("entity");
		String joinParams = String.join(" ", ((HashMap) dependencies.get("cmdparams")).values());
		if (joinParams.toLowerCase().contains("xbox")) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/tellraw @a {\"text\":\"The Xbox is the best gaming console!Any other questions?\"}");
				}
			}
		} else if (joinParams.toLowerCase().contains("surface")) {
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
