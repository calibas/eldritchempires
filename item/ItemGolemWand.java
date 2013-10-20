package eldritchempires.item;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import eldritchempires.EldritchEmpires;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGolemWand extends Item{

	public ItemGolemWand(int par1) {
		super(par1);
	}

	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
//		System.out.println(par4 + " " + par5 + " " + par6 + " " + par7 + " " + par8 + " " + par9 + " " + par10);
    	if (!par3World.isRemote)
    	{
    		FMLNetworkHandler.openGui(par2EntityPlayer, EldritchEmpires.instance, 1, par3World, par4, par5, par6);
    	}
        return true;
    }
	
}
