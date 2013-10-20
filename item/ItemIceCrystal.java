package eldritchempires.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eldritchempires.entity.EntityBomb;
import eldritchempires.entity.EntityZoblin;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	if(!par3World.isRemote)
    	{
    		EntityBomb entity = new EntityBomb(par3World);
    		entity.setLocationAndAngles((double)par4, (double)par5 + 1, (double)par6, 0.0F, 0.0F);
    		entity.motionX = 1.0;
    		entity.motionY = 0.2;
    		entity.motionZ = 0;
    		par3World.spawnEntityInWorld(entity);
    		return true;
    	}
    	else
    		return false;
    }

}
