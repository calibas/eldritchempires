package eldritchempires.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import eldritchempires.EldritchEmpires;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.TileEntitySpawner;

public class EldritchGuiHandler implements IGuiHandler{

	public EldritchGuiHandler() 
	{
		NetworkRegistry.instance().registerGuiHandler(EldritchEmpires.instance, this);
	}
	
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
		case 1:
			if(entity != null && entity instanceof TileEntitySpawner) {
				return new ContainerSpawner(player.inventory, (TileEntitySpawner) entity);
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
		  case 1:
			if(entity != null && entity instanceof TileEntitySpawner) {
				return new GuiSpawner(player.inventory, (TileEntitySpawner) entity);
			} else {
				return null;
			}
		  default:
			return null;
		}
	}


}
