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
		this.setResistance(5.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.1F, 1F);
		this.setBlockUnbreakable();
		this.setLightValue(1.0F);
	}

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		ParticleEffects.spawnParticle("zPortal", par2 + 0.5D, par3 + 0.1D, par4 + 0.5D, 0, 0, 0);
//		par1World.spawnParticle("reddust", par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, 0.0D, 0.0D, 0.0D);
	}
	
//    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
//    {
//		System.out.println("Portal unset" );
//		data.unSetPortal();
//		world.perWorldStorage.setData(EldritchWorldData.name, data);
//		EldritchEvents.wave = 0;
//        return world.setBlockToAir(x, y, z);
//    }
	
//	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
//	{
//		if (!par1World.isRemote)
//		{
//			data = EldritchWorldData.forWorld(par1World);
//			if (!data.checkPortal())
//			{
//				data.setPortal(par2, par3, par4);
//				par1World.perWorldStorage.setData(EldritchWorldData.name, data);
//				data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//				System.out.println("Marker set");
//			}
//			else
//			{
//				par1World.setBlockToAir(par2, par3, par4);
//			}
//		}
//	}
	
//	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
//	{
//		if (!par1World.isRemote)
//		{
//			String announce = "";
//			double distance;
//			boolean goodDistance = true;
//			data = EldritchWorldData.forWorld(par1World);
//			
//			if (data.checkCollector())
//			{
//        		double xd = data.getCollectorX() - par2;
//        		double yd = data.getCollectorY() - par3;
//        		double zd = data.getCollectorZ() - par4;
//        		distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
//        		if (distance >= 80.0D)
//        		{
//        			announce = "Collector too far";
//        			goodDistance = false;
//        		}
//        		if (distance <= 25.0D)
//        		{
//        			announce = "Collector too near";
//        			goodDistance = false;
//        		}	
//			}
//			
//			if (!(par1World.provider.dimensionId == 0))
//				announce = "Must be placed in surface dimension";
//			
//			if (data.checkPortal())
//				announce = "Portal already placed. (" + data.getPortalX() + ", " + data.getPortalY() + ", " + data.getPortalZ() + ")";
//			
//			if (!data.checkPortal() && goodDistance == true && par1World.provider.dimensionId == 0)
//			{
//				data.setPortal(par2, par3, par4);
//				par1World.perWorldStorage.setData(EldritchWorldData.name, data);
//				data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//				announce = "Portal set";
//			}
//			else
//			{
//				par1World.setBlockToAir(par2, par3, par4);
//				par1World.removeBlockTileEntity(par2, par3, par4);
////				ItemStack droppedItem = new ItemStack(Registration.portal, 1);
////				EntityItem entityitem = new EntityItem(par1World, (double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, droppedItem);
////				entityitem.delayBeforeCanPickup = 10;
////				par1World.spawnEntityInWorld(entityitem);
//				
//			}
//			
//			int announceRadius = 100;
//			List<?> var4 = par1World.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(par2 - announceRadius, par3 - announceRadius, par4 - announceRadius, par2 + announceRadius, par3 + announceRadius, par4 + announceRadius));
//
//			if (var4 != null && !var4.isEmpty()) {
//				Iterator<?> var5 = var4.iterator();
//
//				while (var5.hasNext()) {
//					EntityPlayer var6 = (EntityPlayer)var5.next();
//					var6.addChatMessage(announce);
//				}
//			}
//			
//		}
//	}
	
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
