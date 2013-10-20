package eldritchempires;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
			int attackLevel;
			int healthLevel;
			int x;
			int y;
			int z;
			
			if (world.isRemote)
			{
				System.out.println("isRemote");
			}
			
			if (!world.isRemote)
			{
				System.out.println("!isRemote");
			}
			
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			try {
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
			System.out.println("attackLevel " + attackLevel + "healthLevel " + healthLevel + "x " + x);
			
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntitySpawner)
			{
				TileEntitySpawner spawner = (TileEntitySpawner) tileEntity;
				if (attackLevel != 0)
				{
					spawner.attackLevel = attackLevel;
					System.out.println("attackLevel set");
				}
				if (healthLevel != 0)
				{
					spawner.healthLevel = healthLevel;
					System.out.println("healthLevel set");
				}
			}
	}

	
}
