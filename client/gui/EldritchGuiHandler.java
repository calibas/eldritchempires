package eldritchempires.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import eldritchempires.ContainerCollector;
import eldritchempires.EldritchEmpires;
import eldritchempires.entity.TileEntityCollector;

public class EldritchGuiHandler implements IGuiHandler{

	public EldritchGuiHandler() 
	{
		NetworkRegistry.instance().registerGuiHandler(EldritchEmpires.instance, this);
	}
	
//	@Override
//	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
//			int x, int y, int z) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity entity = world.getBlockTileEntity(x, y, z);

	switch(id) {
		case 0:
			if(entity != null && entity instanceof TileEntityCollector) {
				return new ContainerCollector(player.inventory, (TileEntityCollector) entity);
			} 
			else 	
			{
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity entity = world.getBlockTileEntity(x, y, z);

		switch(id) {
		case 0:
			if(entity != null && entity instanceof TileEntityCollector) {
				return new GuiCollector(player.inventory, (TileEntityCollector) entity);
			} else {
				return null;
			}
		default:
			return null;
		}
	}


}
