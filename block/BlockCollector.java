package eldritchempires.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import eldritchempires.EldritchEmpires;
import eldritchempires.EldritchMethods;
import eldritchempires.Registration;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.EntityZoblin;
import eldritchempires.entity.TileEntitySpawner;
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
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockCollector extends BlockContainer{

	private PathEntity path;
	private int searchRadius = 20;
	int packetID = 4;
	int progress;
//	EldritchWorldData data = new EldritchWorldData();
	
	public BlockCollector(int par1) {
		super(par1, Material.rock);
		this.setHardness(25.0F);
		this.setResistance(2000.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setTickRandomly(true);
		this.setLightValue(0.5F);
	    float f = 0.25F;		
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.5F, 0.5F + f);
	}

	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if (!par1World.isRemote)
    	{
    		sendPacket(par2, par3, par4, par5EntityPlayer, par1World);
    		FMLNetworkHandler.openGui(par5EntityPlayer, EldritchEmpires.instance, 0, par1World, par2, par3, par4);
       	}
		return true;
    }
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) 
	{
		if (par1World.isRemote)
		{
			System.out.println("client block meta " + par1World.getBlockMetadata(par2, par3, par4));
		}
		else {
			System.out.println("server block meta " + par1World.getBlockMetadata(par2, par3, par4));
		}
	}
    
	private void sendPacket(int x, int y, int z, EntityPlayer entityPlayer, World world)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityCollector)
		{
			TileEntityCollector collector = (TileEntityCollector) tileEntity;
			int progress = collector.progress;
			int active = 0;
			if (collector.roundActive)
			{
				active = 1;
			}
			try {
				outputStream.writeInt(packetID);
				outputStream.writeInt(progress);
				outputStream.writeInt(active);
				outputStream.writeInt(x);
				outputStream.writeInt(y);
				outputStream.writeInt(z);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "Eldritch";
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(packet, (Player)entityPlayer);
		}
	}
	
	@Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
//		TileEntityCollector collector = (TileEntityCollector) par1World.getBlockTileEntity(par2, par3, par4);
//		int metadata = par1World.getBlockMetadata(par2, par3, par4);
//		if (metadata != 0)
//			progress = metadata;
//		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
//		if (par1World.isRemote)
//		{
//			System.out.println("client block meta " + par1World.getBlockMetadata(par2, par3, par4));
//		}
//		else {
//			System.out.println("server block meta " + par1World.getBlockMetadata(par2, par3, par4));
//		}
        return par9;
    }

	@Override
	public void onPostBlockPlaced(World par1World, int par2, int par3, int par4, int par5) 
	{
//		TileEntityCollector collector = (TileEntityCollector) par1World.getBlockTileEntity(par2, par3, par4);
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		if (metadata != 0)
			progress = metadata;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		if (par1World.isRemote)
		{
			System.out.println("client block meta " + par1World.getBlockMetadata(par2, par3, par4));
		}
		else {
			System.out.println("server block meta " + par1World.getBlockMetadata(par2, par3, par4));
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
	}
	
//    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
//    {
//        return world.setBlockToAir(x, y, z);
//    }
    
//    public void onBlockDestroyedByExplosion(World par1World, int x, int y, int z, Explosion par5Explosion) 
//    {
//
//    }
	
    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		System.out.println("breakBlock()");
        TileEntityCollector collector = (TileEntityCollector) par1World.getBlockTileEntity(par2, par3, par4);
        progress = collector.progress;
        par1World.removeBlockTileEntity(par2, par3, par4);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        try
        {
        	TileEntityCollector collector = new TileEntityCollector();
        	if (progress != 0)
        		collector.progress = progress;
        	return collector;
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
    public int getRenderType()
    {
        return -1;
    }
    
	@Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
             ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
             ret.add(new ItemStack(Registration.collector, 1, progress));
//             int count = quantityDropped(metadata, fortune, world.rand);
//             for(int i = 0; i < count; i++)
//             {
//                     ret.add(new ItemStack(Registration.collector, 1, progress));
//             }
             return ret;
    }
    
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
  
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
         icons = new Icon[2];
        
//         icons[0] = par1IconRegister.registerIcon(EldritchEmpires.modid + ":" + (this.getUnlocalizedName().substring(5)) + 0);
//         
         for(int i = 0; i < icons.length; i++)
         {
                icons[i] = par1IconRegister.registerIcon(EldritchEmpires.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
         }
    }
  
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
         return icons[0];
    }
  
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int var4 = 0; var4 < 10; ++var4)
    	{
    		par3List.add(new ItemStack(par1, 1, var4));
    	}
    }
}
