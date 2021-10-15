package net.mcreator.virtualstore.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.virtualstore.command.MschatCommand;
import net.mcreator.virtualstore.VirtualstoreMod;

import java.util.Map;

public class MschatCommandExecutedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency entity for procedure MschatCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String messageparams = (String) dependencies.get("messageparams");
		String id = MschatCommand.getbotreply(messageparams);
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"tellraw @s {\"text\":\"" + id + "\"}");
			}
		}
	}
}
