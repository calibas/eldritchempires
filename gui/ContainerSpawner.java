package eldritchempires.gui;

import eldritchempires.entity.TileEntitySpawner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSpawner extends Container{
	
	private TileEntitySpawner tileEntity;

	public ContainerSpawner(InventoryPlayer invPlayer, TileEntitySpawner entity)
	{
		this.tileEntity = entity;
		
	}
	
	@Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }

}
