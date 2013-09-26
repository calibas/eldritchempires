package eldritchempires;

import java.util.Iterator;
import java.util.List;

import eldritchempires.entity.MagicEssence;
import eldritchempires.entity.Zoblin;
import eldritchempires.entity.ZoblinBomber;
import eldritchempires.entity.ZoblinBoss;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class EldritchEvents {
	
	int tickCount = 0;
	long lastSpawn = 0;
	public static int wave = 0;
	int announceRadius = 100;
	boolean waveActive = true;
	String announce = "Incomming: ";
	EldritchWorldData data = new EldritchWorldData();
	
	@ForgeSubscribe
	public void onWorldEvent(WorldEvent event)
	{
		tickCount++;
//		List playerList = event.world.playerEntities;
		if (tickCount >= 20 && event.world.provider.dimensionId == 0 && !event.world.playerEntities.isEmpty())
		{
//			data.markDirty();
//			event.world.perWorldStorage.setData(EldritchWorldData.name, data);
			data = EldritchWorldData.forWorld(event.world);
			if (data != null && !event.world.isRemote)
			{
//				System.out.println("Marker set:" + data.checkMarker());
				
				int portalX = data.getPortalX();
				int portalY = data.getPortalY();
				int portalZ = data.getPortalZ();
				int collectorX = data.getCollectorX();
				int collectorY = data.getCollectorY();
				int collectorZ = data.getCollectorZ();
//				if (event.world.checkChunksExist(markerX, markerY, markerZ, nodeX, nodeY, nodeZ))
//				if (event.world.activeChunkSet != null)
//				{
					
//					System.out.println("Server time: " + event.world.provider.getWorldTime());

				
				if(data.checkPortal())
				{
					Chunk portalChunk = event.world.getChunkFromBlockCoords(portalX, portalZ);
					if (portalChunk.isChunkLoaded)
					{
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					
						if (event.world.getBlockId(portalX, portalY, portalZ) != 254)
						{
							System.out.println("Portal unset" );
							data.unSetPortal();
							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
							wave = 0;
						}
					}
				}
				
				if(data.checkCollector())
				{
					Chunk nodeChunk = event.world.getChunkFromBlockCoords(collectorX, collectorZ);
					if (nodeChunk.isChunkLoaded)
					{
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					
						if (event.world.getBlockId(collectorX, collectorY, collectorZ) != 252)
						{
							System.out.println("Collector unset" );
							data.unSetCollector();
							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
							wave = 0;
						}
					}
				}
				
				if (data.checkPortal() && data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) < 0)
				{
					lastSpawn = event.world.provider.getWorldTime();
				}
				
				if (waveActive && data.checkPortal() && data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) >= 600)
				{
					waves(wave, portalX, portalY, portalZ, event.world);
//					System.out.println("Spawn code here");
					List<?> var4 = event.world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(collectorX - announceRadius, collectorY - announceRadius, collectorZ - announceRadius, collectorX + announceRadius, collectorY + announceRadius, collectorZ + announceRadius));

					if (var4 != null && !var4.isEmpty()) {
						Iterator<?> var5 = var4.iterator();

						while (var5.hasNext()) {
							EntityPlayer var6 = (EntityPlayer)var5.next();
							var6.addChatMessage(announce);
						}
					}
					announce = "Incomming: ";
					wave++;
					lastSpawn = event.world.provider.getWorldTime();
//					System.out.println("Spawn time: " + lastSpawn);
//				}
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
			case 0: announce = "Zoblin Waves Approaching!";
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
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 4:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 5:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 6:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 7:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 8:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 9:
				spawnWave("zoblin", 3, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 10:
				spawnWave("zoblinBoss", 1, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				waveActive = false;
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
				zoblin.nodeX = data.getCollectorX();
				zoblin.nodeY = data.getCollectorY();
				zoblin.nodeZ = data.getCollectorZ();
				world.spawnEntityInWorld(zoblin);
				announce = announce + "Zoblin, ";
			}
			if (mobName == "zoblinBomber")
			{
				ZoblinBomber zoblinBomber = new ZoblinBomber(world);
				zoblinBomber.setLocationAndAngles((double)x, (double)y + 1, (double)z, 0.0F, 0.0F);
				zoblinBomber.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.599D);
				zoblinBomber.attacking = true;
				zoblinBomber.nodeX = data.getCollectorX();
				zoblinBomber.nodeY = data.getCollectorY();
				zoblinBomber.nodeZ = data.getCollectorZ();
        		double xd = data.getCollectorX() - x;
        		double yd = data.getCollectorY() - y;
        		double zd = data.getCollectorZ() - z;
        		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
				zoblinBomber.timer = (int)((2*distance)/3);
//				System.out.println("ZoblinBomber distance timer: " + distance + " " + zoblinBomber.timer );
				world.spawnEntityInWorld(zoblinBomber);
				announce = announce + "Zoblin Bomber, ";
			}
			if (mobName == "magicEssence")
			{
				MagicEssence entity = new MagicEssence(world);
				entity.setLocationAndAngles((double)x, (double)y + 1, (double)z, 0.0F, 0.0F);
				entity.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.599D);
				entity.attacking = true;
				entity.nodeX = data.getCollectorX();
				entity.nodeY = data.getCollectorY();
				entity.nodeZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				announce = announce + "Magic Essence, ";
			}
			if (mobName == "zoblinBoss")
			{
				ZoblinBoss entity = new ZoblinBoss(world);
				entity.setLocationAndAngles((double)x, (double)y + 1, (double)z, 0.0F, 0.0F);
				entity.attacking = true;
				entity.nodeX = data.getCollectorX();
				entity.nodeY = data.getCollectorY();
				entity.nodeZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				announce = announce + "Zoblin Boss, ";
			}

		}
	}
	
}
