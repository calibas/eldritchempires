package eldritchempires;

import eldritchempires.entity.TileEntityCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerCollector extends Container{
	private TileEntityCollector tileEntity;

	public ContainerCollector(InventoryPlayer invPlayer, TileEntityCollector entity)
	{
		this.tileEntity = entity;
		
	}
	
	@Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
    }

}
