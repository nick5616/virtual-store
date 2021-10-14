
package net.mcreator.virtualstore.item;

@VirtualstoreModElements.ModElement.Tag
public class MicorosoftHeadphonesItem extends VirtualstoreModElements.ModElement {

	@ObjectHolder("virtualstore:micorosoft_headphones")
	public static final Item block = null;

	public MicorosoftHeadphonesItem(VirtualstoreModElements instance) {
		super(instance, 6);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("micorosoft_headphones");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("headphones"));
		}

	}

}
