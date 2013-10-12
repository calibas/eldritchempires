package eldritchempires.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemIceCrystal extends Item{

	public ItemIceCrystal(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("eldritchempires:" + (this.getUnlocalizedName().substring(5)));
    }

}
