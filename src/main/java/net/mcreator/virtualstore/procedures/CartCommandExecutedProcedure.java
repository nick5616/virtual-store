package net.mcreator.virtualstore.procedures;

public class CartCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency entity for procedure CartCommandExecuted!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				VirtualstoreMod.LOGGER.warn("Failed to load dependency world for procedure CartCommandExecuted!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");

		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(MicrosoftBadgeItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(
							new StringTextComponent((new ItemStack(MicrosoftBadgeItem.block).getDisplayName().getString())), ChatType.SYSTEM,
							Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(XboxoneItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent((new ItemStack(XboxoneItem.block).getDisplayName().getString())),
							ChatType.SYSTEM, Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(MicrosoftKeyboardItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(
							new StringTextComponent((new ItemStack(MicrosoftKeyboardItem.block).getDisplayName().getString())), ChatType.SYSTEM,
							Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(XboxpassItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent((new ItemStack(XboxpassItem.block).getDisplayName().getString())),
							ChatType.SYSTEM, Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(MicrosoftMiceItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(
							new StringTextComponent((new ItemStack(MicrosoftMiceItem.block).getDisplayName().getString())), ChatType.SYSTEM,
							Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(MicorosoftHeadphonesItem.block))
				: false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(
							new StringTextComponent((new ItemStack(MicorosoftHeadphonesItem.block).getDisplayName().getString())), ChatType.SYSTEM,
							Util.DUMMY_UUID);
			}
		}
		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(SurfacepenItem.block)) : false)) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent((new ItemStack(SurfacepenItem.block).getDisplayName().getString())),
							ChatType.SYSTEM, Util.DUMMY_UUID);
			}
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"/tellraw @a {\"text\":\"Open Your Cart here\",\"color\":\"#02FF00\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.microsoft.com/en-us/store/cart\"}}");
			}
		}
	}

}
