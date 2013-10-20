package eldritchempires;

import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

public class CreativeTabsEldritch extends CreativeTabs
{
	public CreativeTabsEldritch(int position, String tabLabel)
	{
		super(position, tabLabel);
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(Registration.condensedEssence, 1);
	}
}