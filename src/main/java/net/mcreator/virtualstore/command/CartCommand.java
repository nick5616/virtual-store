
package net.mcreator.virtualstore.command;

@Mod.EventBusSubscriber
public class CartCommand {

	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(LiteralArgumentBuilder.<CommandSource>literal("checkout")

				.then(Commands.argument("arguments", StringArgumentType.greedyString()).executes(CartCommand::execute))
				.executes(CartCommand::execute));
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

			CartCommandExecutedProcedure.executeProcedure($_dependencies);
		}

		return 0;
	}

}
