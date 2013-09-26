package eldritchempires.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import eldritchempires.EldritchEmpires;
import eldritchempires.entity.StoneArcher;
import eldritchempires.entity.TileEntitySpawner;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSpawner extends BlockContainer{

	private PathEntity path;
	
	public BlockSpawner(int par1, Material par2Material) {
		super(par1, Material.plants);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.1F, 1F);
	}

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		
		// Stone Archer Spawner
		if (i == 0)
		{
			int searchRadius = 2;
			int j = par1World.getEntitiesWithinAABB(StoneArcher.class, AxisAlignedBB.getAABBPool().getAABB(par2 - searchRadius, par3 - searchRadius, par4 - searchRadius, par2 + searchRadius, par3 + searchRadius, par4 + searchRadius)).size();
        
			if (j < 1)
			{
				StoneArcher stoneArcher = new StoneArcher(par1World);
				stoneArcher.setLocationAndAngles((double)par2 + 0.5D, (double)par3 + 0.1D, (double)par4 + 0.5D, 0.0F, 0.0F);
				stoneArcher.guarding = true;
				stoneArcher.nodeX = par2;
				stoneArcher.nodeY = par3;
				stoneArcher.nodeZ = par4;
				par1World.spawnEntityInWorld(stoneArcher);
			}
		}
		
		// Placeholder Spawner
		if (i == 1)
		{

		}
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        try
        {
        	return new TileEntitySpawner();
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
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
