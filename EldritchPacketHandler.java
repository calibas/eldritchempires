package eldritchempires;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import eldritchempires.entity.TileEntitySpawner;

public class EldritchPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player playerEntity)
	{
		EntityPlayer player = (EntityPlayer) playerEntity;
		World world = player.worldObj;
		int packetID;
		int attackLevel;
		int healthLevel;
		int x;
		int y;
		int z;
		
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			packetID = inputStream.readInt();
   	     	attackLevel = inputStream.readInt();
   	     	healthLevel = inputStream.readInt();
   	     	x = inputStream.readInt();
   	     	y = inputStream.readInt();
   	     	z = inputStream.readInt();
    	} 
		catch (IOException e) {
			e.printStackTrace();
			return;
		}

		if (packetID == 1) {
		  if (!player.capabilities.isCreativeMode)
		  {
//			System.out.println("attackLevel " + attackLevel + "healthLevel " + healthLevel + "x " + x);
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntitySpawner)
			{
				// Check inventory for condensed essence
				ItemStack[] inventory = player.inventory.mainInventory;
				int essenceCount = 0;
				ItemStack essenceInv = null;
				for (ItemStack is : inventory) {
					if (is != null && is.getItem().itemID == Registration.condensedEssence.itemID) {
						essenceCount = is.stackSize;
						essenceInv = is;
					}
				}

				TileEntitySpawner spawner = (TileEntitySpawner) tileEntity;
				System.out.println("attackLevel " + attackLevel + " player.experienceLevel " + player.experienceLevel + " spawner.attackLevel " + spawner.attackLevel);
//				if(!player.capabilities.isCreativeMode)
//				{
				 if (attackLevel == 1 && player.experienceLevel >= 5 && spawner.attackLevel == 0)
				 {
					spawner.attackLevel = 1;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-5);
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 }
				 if (attackLevel == 2 && player.experienceLevel >= 10 && essenceCount >= 1 && spawner.attackLevel == 1)
				 {
					spawner.attackLevel = 2;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-10);
						essenceInv.stackSize = essenceInv.stackSize - 1;
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 }
				 if (attackLevel == 3 && player.experienceLevel >= 15 && essenceCount >= 2 && spawner.attackLevel == 2)
				 {
					spawner.attackLevel = 3;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-15);
						essenceInv.stackSize = essenceInv.stackSize - 2;
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 }
				
				 if (healthLevel == 1 && player.experienceLevel >= 5 && spawner.healthLevel == 0)
				 {
					spawner.healthLevel = 1;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-5);
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 }
				 if (healthLevel == 2 && player.experienceLevel >= 10 && essenceCount >= 1 && spawner.healthLevel == 1)
				 {
					spawner.healthLevel = 2;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-10);
						essenceInv.stackSize = essenceInv.stackSize - 1;
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 }
				 if (healthLevel == 3 && player.experienceLevel >= 15 && essenceCount >= 2 && spawner.healthLevel == 2)
				 {
					spawner.healthLevel = 3;
					if (!world.isRemote)
					{
						player.addExperienceLevel(-15);
						essenceInv.stackSize = essenceInv.stackSize - 2;
					}
					spawner.killGolem();
					spawner.spawnGolem();
				 
				}
			}
		  }
		  else
		  {
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
				if (tileEntity instanceof TileEntitySpawner)
				{
					TileEntitySpawner spawner = (TileEntitySpawner) tileEntity;
					spawner.attackLevel = attackLevel;
					spawner.healthLevel = healthLevel;
				} 
		  }
		}
		
		if (packetID == 2 && world.isRemote)
		{
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntitySpawner)
			{
				TileEntitySpawner spawner = (TileEntitySpawner) tileEntity;
				spawner.attackLevel = attackLevel;
				spawner.healthLevel = healthLevel;
			}
		}
		
	}

	
}
