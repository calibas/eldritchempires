package eldritchempires.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eldritchempires.EldritchEmpires;
import eldritchempires.EldritchEvents;
import eldritchempires.EldritchWorldData;
import eldritchempires.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockMarker extends Block{

	EldritchWorldData data = new EldritchWorldData();
	
	public BlockMarker(int par1) {
		super(par1, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setCreativeTab(EldritchEmpires.tabEldritch);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.1F, 1F);
	}

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
	{
		if (!par1World.isRemote)
		{
			data = EldritchWorldData.forWorld(par1World);
			if (!data.checkMarker())
			{
				data.setMarker(par2, par3, par4);
				par1World.perWorldStorage.setData(EldritchWorldData.name, data);
				data = (EldritchWorldData) par1World.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
				System.out.println("Marker set");
			}
			else
			{
				par1World.setBlockToAir(par2, par3, par4);
			}
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
