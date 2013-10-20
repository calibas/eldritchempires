package eldritchempires.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eldritchempires.EldritchEmpires;
import eldritchempires.EldritchEvents;
import eldritchempires.EldritchWorldData;
import eldritchempires.ParticleEffects;
import eldritchempires.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockPortal extends Block{

	EldritchWorldData data = new EldritchWorldData();
	
	public BlockPortal(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(2000.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.1F, 1F);
		this.setBlockUnbreakable();
		this.setLightValue(1.0F);
		this.setTickRandomly(true);
	}

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		ParticleEffects.spawnParticle("zPortal", par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, 0, 0, 0);
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		if (data.getPortalX() != par2 || data.getPortalZ() != par4)
		{
			par1World.destroyBlock(par2, par3, par4, false);
			par1World.removeBlockTileEntity(par2, par3, par4);
		}
	}
	
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
  
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
         icons = new Icon[2];
        
         for(int i = 0; i < icons.length; i++)
         {
                icons[i] = par1IconRegister.registerIcon(EldritchEmpires.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
         }
    }
  
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
         return icons[par2];
    }
  
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int var4 = 0; var4 < 1; ++var4)
    	{
    		par3List.add(new ItemStack(par1, 1, var4));
    	}
    }
}
