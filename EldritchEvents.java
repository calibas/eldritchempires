package eldritchempires;

import java.util.List;

import eldritchempires.entity.Zoblin;
import eldritchempires.entity.ZoblinBomber;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class EldritchEvents {
	
	int tickCount = 0;
	long lastSpawn = 0;
	int wave = 0;
	EldritchWorldData data = new EldritchWorldData();
	
	@ForgeSubscribe
	public void onWorldEvent(WorldEvent event)
	{
		tickCount++;
//		List playerList = event.world.playerEntities;
		if (tickCount >= 50 && event.world.provider.dimensionId == 0 && !event.world.playerEntities.isEmpty())
		{
//			data.markDirty();
//			event.world.perWorldStorage.setData(EldritchWorldData.name, data);
			data = EldritchWorldData.forWorld(event.world);
			if (data != null)
			{
//				System.out.println("Marker set:" + data.checkMarker());
				
				int markerX = data.getMarkerX();
				int markerY = data.getMarkerY();
				int markerZ = data.getMarkerZ();
				int nodeX = data.getNodeX();
				int nodeY = data.getNodeY();
				int nodeZ = data.getNodeZ();

				if(data.checkMarker())
				{
	
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					Chunk chunk = event.world.getChunkFromBlockCoords(markerX, markerZ);
					
					if (event.world.getBlockId(markerX, markerY, markerZ) != 254 && chunk.isChunkLoaded)
					{
						System.out.println("Marker unset" );
						data.unSetMarker();
						event.world.perWorldStorage.setData(EldritchWorldData.name, data);
					}
				}
				
				if(data.checkNode())
				{
	
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					Chunk chunk = event.world.getChunkFromBlockCoords(markerX, markerZ);
					
					if (event.world.getBlockId(nodeX, nodeY, nodeZ) != 252 && chunk.isChunkLoaded)
					{
						System.out.println("Node unset" );
						data.unSetNode();
						event.world.perWorldStorage.setData(EldritchWorldData.name, data);
					}
				}
				
				if (data.checkMarker() && data.checkNode() && (event.world.provider.getWorldTime() - lastSpawn) >= 600)
				{
					waves(wave, markerX, markerY, markerZ, event.world);
//					System.out.println("Spawn code here");
					wave++;
					lastSpawn = event.world.provider.getWorldTime();
				}
					
			}
			
//			data.setTest(8);
//			event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//			data = (EldritchWorldData) event.world.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//			int test = data.getTest();
//
//			Minecraft.getMinecraft().thePlayer.addChatMessage("Tile Entity Exists!" + test);
//			
			tickCount = 0;
		}
	}
	
	public void waves(int wave, int x, int y, int z, World world)
	{
		switch (wave){
			case 0: System.out.println("Zoblins Incoming!" );
				break;
			case 1:
				spawnWave("zoblin", 2, x, y, z, world);
				break;
			case 2:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 3:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
			case 4:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 5:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
		}
	}
	
	public void spawnWave(String mobName, int count, int x, int y, int z, World world)
	{
		for (int i = 1; i < (count + 1); i++)
		{
			if (mobName == "zoblin")
			{
				Zoblin zoblin = new Zoblin(world);
				zoblin.setLocationAndAngles((double)x, (double)y + 1, (double)z, 0.0F, 0.0F);
				zoblin.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.099D);
				zoblin.attacking = true;
				zoblin.nodex = data.getNodeX();
				zoblin.nodey = data.getNodeY();
				zoblin.nodez = data.getNodeZ();
				world.spawnEntityInWorld(zoblin);
			}
			if (mobName == "zoblinBomber")
			{
				ZoblinBomber zoblinBomber = new ZoblinBomber(world);
				zoblinBomber.setLocationAndAngles((double)x, (double)y + 1, (double)z, 0.0F, 0.0F);
				zoblinBomber.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.599D);
				zoblinBomber.attacking = true;
				zoblinBomber.nodex = data.getNodeX();
				zoblinBomber.nodey = data.getNodeY();
				zoblinBomber.nodez = data.getNodeZ();
				world.spawnEntityInWorld(zoblinBomber);
			}

		}
	}
	
}
