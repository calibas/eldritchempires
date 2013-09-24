package eldritchempires.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import eldritchempires.EldritchEmpires;
import eldritchempires.EldritchWorldData;
import eldritchempires.entity.TileEntityNode;
import eldritchempires.entity.Zoblin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCollector extends BlockContainer{

	private PathEntity path;
	private int searchRadius = 20;
	EldritchWorldData data = new EldritchWorldData();
	
	public BlockCollector(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setTickRandomly(true);
	    float f = 0.25F;		
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.5F, 0.5F + f);
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
	{
		if (!par1World.isRemote)
		{
			data = EldritchWorldData.forWorld(par1World);
			if (!data.checkNode())
			{
				data.setNode(par2, par3, par4);
				par1World.perWorldStorage.setData(EldritchWorldData.name, data);
				data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
				System.out.println("Node set");
			}
			else
			{
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		
		// Player Node
		if (i == 0)
		{
//			Zoblin zoblin = new Zoblin(par1World);
//			zoblin.setLocationAndAngles((double)par2 - 30.5D, (double)zoblin.getFirstUncoveredBlockHeight(par2 - 30, par4), (double)par4 + 0.5D, 0.0F, 0.0F);
//			zoblin.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.699D);
//			zoblin.attacking = true;
//			zoblin.nodex = par2;
//			zoblin.nodey = par3;
//			zoblin.nodez = par4;
//			par1World.spawnEntityInWorld(zoblin);

//			path = par1World.getEntityPathToXYZ(zoblin, par2 - 20, par3, par4, 64F, true, true, false, false);
//    		zoblin.setPathToEntity(path);

		}
		
		// Zoblin Node
		if (i == 1)
		{
			List<?> var4 = par1World.getEntitiesWithinAABB(Zoblin.class, AxisAlignedBB.getAABBPool().getAABB(par2 - searchRadius, par3 - searchRadius, par4 - searchRadius, par2 + searchRadius, par3 + searchRadius, par4 + searchRadius));
			int j = var4.size();
        
			
			
			if (j <= 5)
			{
				Zoblin zoblin = new Zoblin(par1World);
				zoblin.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
				par1World.spawnEntityInWorld(zoblin);
			}
			

//			List<?> var4 = par1World.getEntitiesWithinAABB(Zoblin.class, AxisAlignedBB.getAABBPool().getAABB(par2 - searchRadius, par3 - searchRadius, par4 - searchRadius, par2 + searchRadius, par3 + searchRadius, par4 + searchRadius));

			if (var4 != null && !var4.isEmpty()) {
				Iterator<?> var5 = var4.iterator();

				while (var5.hasNext()) {
					EntityLiving var6 = (EntityLiving)var5.next();
					var6.setPosition((double)par2 + 0.5D, (double)par3 + 20.0D, (double)par4 + 0.5D);
				}
			}

		}
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        try
        {
        	return new TileEntityNode();
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
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
    	for (int var4 = 0; var4 < 2; ++var4)
    	{
    		par3List.add(new ItemStack(par1, 1, var4));
    	}
    }
}
