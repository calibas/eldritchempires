package eldritchempires.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import eldritchempires.EldritchEmpires;
import eldritchempires.EldritchEvents;
import eldritchempires.EldritchMethods;
import eldritchempires.EldritchWorldData;
import eldritchempires.Registration;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.Zoblin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
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
		this.setLightValue(0.5F);
	    float f = 0.25F;		
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.5F, 0.5F + f);
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
	{
		if (!par1World.isRemote)
		{
			String announce = "";
			double distance;
			boolean goodDistance = true;
			data = EldritchWorldData.forWorld(par1World);
			
//			if (data.checkPortal())
//			{
//        		double xd = data.getPortalX() - par2;
//        		double yd = data.getPortalY() - par3;
//        		double zd = data.getPortalZ() - par4;
//        		distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
//        		if (distance >= 80.0D)
//        		{
//        			announce = "Portal too far";
//        			goodDistance = false;
//        		}
//        		if (distance <= 25.0D)
//        		{
//        			announce = "Portal too near";
//        			goodDistance = false;
//        		}	
//			}
			
			if (!(par1World.provider.dimensionId == 0))
				announce = "Must be placed in surface dimension";
			
			if (data.checkCollector())
				announce = "Collector already placed. (" + data.getCollectorX() + ", " + data.getCollectorY() + ", " + data.getCollectorZ() + ")";
			
			if (!data.checkCollector() && goodDistance == true && par1World.provider.dimensionId == 0)
			{
//				int[] location = EldritchMethods.createPortal("zoblin", par2, par3, par4, par1World);
//				data.setPortal(location[0], location[1], location[2]);
				data.setCollector(par2, par3, par4);
//				EldritchEvents.waveActive = true;
				par1World.perWorldStorage.setData(EldritchWorldData.name, data);
				data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
				announce = "Collector set";
				
			}
			else
			{
				par1World.setBlockToAir(par2, par3, par4);
				par1World.removeBlockTileEntity(par2, par3, par4);
				ItemStack droppedItem = new ItemStack(Registration.collector, 1);
				EntityItem entityitem = new EntityItem(par1World, (double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, droppedItem);
				entityitem.delayBeforeCanPickup = 10;
				par1World.spawnEntityInWorld(entityitem);
				
			}
			
			int announceRadius = 100;
			List<?> var4 = par1World.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(par2 - announceRadius, par3 - announceRadius, par4 - announceRadius, par2 + announceRadius, par3 + announceRadius, par4 + announceRadius));

			if (var4 != null && !var4.isEmpty()) {
				Iterator<?> var5 = var4.iterator();

				while (var5.hasNext()) {
					EntityPlayer var6 = (EntityPlayer)var5.next();
					var6.addChatMessage(announce);
				}
			}
			
		}
	}
	
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if (!par1World.isRemote)
    	{
    		if (!data.isWaveActive())
    		{
    			data.setActiveWave(true);
    			par1World.perWorldStorage.setData(EldritchWorldData.name, data);
    			data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
    			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
    		}
    		else
    		{
    			data.setActiveWave(false);
    			par1World.perWorldStorage.setData(EldritchWorldData.name, data);
    			data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
    			EldritchMethods.broadcastMessageLocal("Collector Inactive", par2, par3, par4, 100, par1World);
    		}
    	}
		return true;
    }
	
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
//		System.out.println("Collector unset" );
//		data.unSetCollector();
//		world.perWorldStorage.setData(EldritchWorldData.name, data);
//		EldritchEvents.wave = 0;
//		world.setBlockToAir(data.getPortalX(), data.getPortalY(), data.getPortalZ());
//		data.unSetPortal();
        return world.setBlockToAir(x, y, z);
    }
    
    public void onBlockDestroyedByExplosion(World par1World, int x, int y, int z, Explosion par5Explosion) 
    {
//		System.out.println("Collector unset" );
//		data.unSetCollector();
//		par1World.perWorldStorage.setData(EldritchWorldData.name, data);
//		par1World.setBlockToAir(data.getPortalX(), data.getPortalY(), data.getPortalZ());
//		data.unSetPortal();
//		EldritchEvents.wave = 0;
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
//		if (i == 1)
//		{
//			List<?> var4 = par1World.getEntitiesWithinAABB(Zoblin.class, AxisAlignedBB.getAABBPool().getAABB(par2 - searchRadius, par3 - searchRadius, par4 - searchRadius, par2 + searchRadius, par3 + searchRadius, par4 + searchRadius));
//			int j = var4.size();
//        
//			
//			
//			if (j <= 5)
//			{
//				Zoblin zoblin = new Zoblin(par1World);
//				zoblin.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
//				par1World.spawnEntityInWorld(zoblin);
//			}
//			
//
////			List<?> var4 = par1World.getEntitiesWithinAABB(Zoblin.class, AxisAlignedBB.getAABBPool().getAABB(par2 - searchRadius, par3 - searchRadius, par4 - searchRadius, par2 + searchRadius, par3 + searchRadius, par4 + searchRadius));
//
//			if (var4 != null && !var4.isEmpty()) {
//				Iterator<?> var5 = var4.iterator();
//
//				while (var5.hasNext()) {
//					EntityLiving var6 = (EntityLiving)var5.next();
//					var6.setPosition((double)par2 + 0.5D, (double)par3 + 20.0D, (double)par4 + 0.5D);
//				}
//			}
//
//		}
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        try
        {
        	return new TileEntityCollector();
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
    
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
             ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
             int count = quantityDropped(metadata, fortune, world.rand);
             for(int i = 0; i < count; i++)
             {
                     ret.add(new ItemStack(Registration.collector, 1, 0));
             }
             return ret;
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
