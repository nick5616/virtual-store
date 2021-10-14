
package net.mcreator.virtualstore.item;

@VirtualstoreModElements.ModElement.Tag
public class WindowsKeyItem extends VirtualstoreModElements.ModElement {

	@ObjectHolder("virtualstore:windows_key")
	public static final Item block = null;

	public WindowsKeyItem(VirtualstoreModElements instance) {
		super(instance, 12);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("windows_key");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

	}

}
