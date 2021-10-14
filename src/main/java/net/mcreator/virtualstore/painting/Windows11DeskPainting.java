
package net.mcreator.virtualstore.painting;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Windows11DeskPainting {

	@SubscribeEvent
	public static void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(32, 16).setRegistryName("windows_11_desk"));
	}

}
