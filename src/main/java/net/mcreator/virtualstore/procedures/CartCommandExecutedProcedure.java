package net.mcreator.virtualstore.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.virtualstore.VirtualstoreMod;

import java.util.Map;

public class CartCommandExecutedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency entity for procedure CartCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"/tellraw @a {\"text\":\"Open Your Cart here\",\"color\":\"#02FF00\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.microsoft.com/en-us/store/cart\"}}");
			}
		}
	}
}
