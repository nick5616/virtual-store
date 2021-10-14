
package net.mcreator.virtualstore.painting;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class XboxScreenPainting {

	@SubscribeEvent
	public static void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(48, 32).setRegistryName("xbox_screen"));
	}

}
