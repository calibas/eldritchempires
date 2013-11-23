package eldritchempires.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import eldritchempires.EldritchEmpires;
import eldritchempires.Registration;
import eldritchempires.entity.EntityStoneArcher;
import eldritchempires.entity.TileEntitySpawner;
import net.minecraft.block.Block;
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
import net.minecraftforge.common.ForgeDirection;

public class BlockSpawner extends BlockContainer{

	private PathEntity path;
	private int entityId;
	
	public BlockSpawner(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(1.5F);
		this.setResistance(50.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.5F, 1F);
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
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return !(par1World.getBlockId(par2, par3 - 1, par4) == Registration.spawner.blockID || par1World.getBlockId(par2, par3 + 1, par4) == Registration.spawner.blockID);
    }
	
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            boolean flag = this.canPlaceBlockAt(par1World, par2, par3, par4);

            if (!flag)
            {
//                this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
//                par1World.setBlockToAir(par2, par3, par4);
            	par1World.destroyBlock(par2, par3, par4, true);
            }

            super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        }
    }
	
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
       	return new TileEntitySpawner();
    }
	
	@Override
    public int damageDropped(int par1)
    {
        return par1;
    }
	
//	@Override
//    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
//    {
//             ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
//           	 if(world.getBlockMetadata(x, y, z) == 0);
//           	 	ret.add(new ItemStack(Registration.spawner, 1, 0));
//             if(world.getBlockMetadata(x, y, z) == 1);
//                ret.add(new ItemStack(Registration.spawner, 1, 1));   
//             if(world.getBlockMetadata(x, y, z) == 2);
//                ret.add(new ItemStack(Registration.spawner, 1, 2));  
//             
//             return ret;
//    }
	
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
  
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
         icons = new Icon[4];
        
         for(int i = 0; i < 3; i++)
         {
                icons[i] = par1IconRegister.registerIcon(EldritchEmpires.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
         }
         
         icons[3] = par1IconRegister.registerIcon(EldritchEmpires.modid + ":" + "spawner-side");
    }
  
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata)
    {
    	if (side == 1 || side == 0)
    	{
    		return icons[metadata];
    	}
    	else
    	{
    		return icons[3];
    	}
    }
  
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int var4 = 0; var4 < 3; ++var4)
    	{
    		par3List.add(new ItemStack(par1, 1, var4));
    	}
    }
}
