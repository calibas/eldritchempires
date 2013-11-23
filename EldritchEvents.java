package eldritchempires;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import eldritchempires.entity.EntityMagicEssence;
import eldritchempires.entity.EntityRabidDemo;
import eldritchempires.entity.EntityRabidDwarf;
import eldritchempires.entity.EntityRabidMiner;
import eldritchempires.entity.EntityRabidWarrior;
import eldritchempires.entity.EntityZoblin;
import eldritchempires.entity.EntityZoblinBomber;
import eldritchempires.entity.EntityZoblinBoss;
import eldritchempires.entity.EntityZoblinWarrior;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EldritchEvents {
	
	int tickCount = 0;
	long lastSpawn = 0;
//	public static int wave = 0;
	int announceRadius = 100;
//	public static boolean waveActive = true;
	String announce = "";
	EldritchWorldData data = new EldritchWorldData();
	
	@ForgeSubscribe
	public void onWorldEvent(WorldEvent event)
	{
		tickCount++;
//		List playerList = event.world.playerEntities;
		if (tickCount >= 5 && event.world.provider.dimensionId == 0 && !event.world.playerEntities.isEmpty())
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
				int wave = data.getWave();
				int round = data.getRound();
//				if (event.world.checkChunksExist(markerX, markerY, markerZ, nodeX, nodeY, nodeZ))
//				if (event.world.activeChunkSet != null)
//				{
					
//					System.out.println("Server time: " + event.world.provider.getWorldTime());

				
				if(data.checkPortal() && !data.checkCollector() && event.world.blockExists(portalX, portalY, portalZ))
				{
//					event.world.getChunkProvider().loadChunk(portalX >> 4, portalZ >> 4);
//					Chunk portalChunk = event.world.getChunkFromBlockCoords(portalX, portalZ);
//					if (portalChunk.isChunkLoaded)
//					{
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					
						if (event.world.getBlockId(portalX, portalY, portalZ) != Registration.portal.blockID)
						{
							System.out.println("Portal unset" );
							data.unSetPortal();
							data.setPortalFocus(false);
							data.setActiveWave(false);
							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
						}
//					}
				}
				
				if(data.checkCollector() && event.world.blockExists(collectorX, collectorY, collectorZ))
				{
//					event.world.getChunkProvider().loadChunk(collectorX >> 4, collectorZ >> 4);
//					Chunk nodeChunk = event.world.getChunkFromBlockCoords(collectorX, collectorZ);
//			        Chunk chunk = event.world.getChunkFromBlockCoords(MathHelper.floor_double(collectorX), MathHelper.floor_double(collectorZ));
//
//					if (chunk.isChunkLoaded)
//					{
//					System.out.println("Marker found at:" + markerX + " " + markerY + " " + markerZ);
//					System.out.println("BlockID:" + event.world.getBlockId(markerX, markerY, markerZ));
					
						if (event.world.getBlockId(collectorX, collectorY, collectorZ) != Registration.collector.blockID)
						{
							System.out.println("Collector unset" );
							data.unSetCollector();
							data.setActiveWave(false);
							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
						}
//					}
				}
				
				
				//Just in case WorldTime changes
				if (data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) < 0)
				{
					lastSpawn = event.world.provider.getWorldTime();
				}
				
//				System.out.println(data.isWaveActive() + data.checkCollector());
				
				if (data.isWaveActive() && data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) >= 600 && event.world.blockExists(collectorX, collectorY, collectorZ))
				{
					if (!data.checkPortal())
					{
						System.out.println("Trying to create portal" );
						if (!data.checkPortalFocus())
						{
							int[] location = EldritchMethods.createPortal("zoblin", collectorX, collectorY, collectorZ, event.world);
							if (location[0] == 0 && location[1] == 0 && location[2] == 0)
							{
									data.setActiveWave(false);
							}
							else 
							{
								data.setPortal(location[0], location[1], location[2]);
							}
						}

						event.world.setBlockMetadataWithNotify(collectorX, collectorY, collectorZ, 1, 2);
					}
					
//					if (wave > 12)
//					{
//						data.setWave(0);
//						wave = 0;
//						event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//					}
					
					waves(round, wave, portalX, portalY, portalZ, event.world);
//					System.out.println("Spawn code here");
//					List<?> var4 = event.world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(collectorX - announceRadius, collectorY - announceRadius, collectorZ - announceRadius, collectorX + announceRadius, collectorY + announceRadius, collectorZ + announceRadius));
//
//					if (var4 != null && !var4.isEmpty()) {
//						Iterator<?> var5 = var4.iterator();
//
//						while (var5.hasNext()) {
//							EntityPlayer var6 = (EntityPlayer)var5.next();
//							var6.addChatMessage(announce);
//						}
//					}
					
					if (!announce.equals(""))
						EldritchMethods.broadcastMessageLocal(announce, collectorX, collectorY, collectorZ, 100, event.world);
					
					
					wave++;
					data.setWave(wave++);
					event.world.perWorldStorage.setData(EldritchWorldData.name, data);
					if (data.getWave() >= 1 && data.getWave() <= 10)
					{
						announce = "Wave " + data.getWave() + "/10: ";
					}
					else
					{
						announce = "";
					}
					lastSpawn = event.world.provider.getWorldTime();
//					System.out.println("Spawn time: " + lastSpawn);
//				}
				}
				
				if (!data.isWaveActive() && data.checkPortal() && !data.checkPortalFocus() && event.world.blockExists(portalX, portalY, portalZ))
				{
					EldritchMethods.broadcastMessageLocal("The portal closes", portalX, portalY, portalZ, 100, event.world);
					event.world.setBlockMetadataWithNotify(collectorX, collectorY, collectorZ, 0, 2);
					data.unSetPortal();
					event.world.perWorldStorage.setData(EldritchWorldData.name, data);
					event.world.setBlockToAir(portalX, portalY, portalZ);
					event.world.removeBlockTileEntity(portalX, portalY, portalZ);
				}
					
			}
			
//			data.setTest(8);
//			event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//			data = (EldritchWorldData) event.world.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//			int test = data.getTest();
//
//			
			tickCount = 0;
		}
	}
	
	public void endRound(World world)
	{

			int portalX = data.getPortalX();
			int portalY = data.getPortalY();
			int portalZ = data.getPortalZ();
			if (!data.checkPortalFocus())
			{
				data.unSetPortal();
			}
			data.setActiveWave(false);
			world.perWorldStorage.setData(EldritchWorldData.name, data);
			if (world.getBlockId(portalX, portalY, portalZ) == Registration.portal.blockID && !data.checkPortalFocus())
			{
				world.setBlockToAir(portalX, portalY, portalZ);
				world.removeBlockTileEntity(portalX, portalY, portalZ);
			}

	}
	
	public void startRound(World world)
	{
		
	}
	
	public void waves(int round, int wave, int x, int y, int z, World world)
	{
		if (round == 1)
		{
		switch (wave){
			case 0: 
				lastSpawn = lastSpawn + 580;
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
				spawnWave("zoblinWarrior", 1, x, y, z, world);
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
//				waveActive = false;
				break;
			case 11:
//				announce = "The zoblin attack seems to have ended";
				break;
			case 12:
//				announce = "The portal closes";
//				data.increaseProgress();
//				endRound(world);
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			}
		}
		
		if (round == 2)
		{
		switch (wave){
			case 0: 
				lastSpawn = lastSpawn + 580;
				break;
			case 1:
				spawnWave("zoblin", 3, x, y, z, world);
				break;
			case 2:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
			case 3:
				spawnWave("zoblin", 3, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 4:
				spawnWave("zoblin", 3, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
			case 5:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 3, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 6:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinWarrior", 1, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
			case 7:
				spawnWave("zoblin", 3, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 8:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinWarrior", 1, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 9:
				spawnWave("zoblinWarrior", 2, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 10:
				spawnWave("zoblinBoss", 1, x, y, z, world);
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
//				waveActive = false;
				break;
			case 11:
//				announce = "The zoblin attack seems to have ended";
				break;
			case 12:
//				announce = "The portal closes";
//				data.increaseProgress();
//				endRound(world);
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			}
		}
		
		if (round == 3)
		{
		switch (wave){			
			case 0: 
				lastSpawn = lastSpawn + 580;
				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			case 1:
				spawnWave("zoblinWarrior", 2, x, y, z, world);
				break;
			case 2:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinWarrior", 1, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 3:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinWarrior", 1, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				break;
			case 4:
				spawnWave("zoblinWarrior", 2, x, y, z, world);
				spawnWave("zoblinBomber", 3, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 5:
				spawnWave("zoblinWarrior", 4, x, y, z, world);
				break;
			case 6:
				spawnWave("zoblin", 4, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 7:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinWarrior", 2, x, y, z, world);
				spawnWave("zoblinBomber", 1, x, y, z, world);
				break;
			case 8:
				spawnWave("zoblinWarrior", 3, x, y, z, world);
				spawnWave("zoblinBomber", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 9:
				spawnWave("zoblin", 2, x, y, z, world);
				spawnWave("zoblinBomber", 4, x, y, z, world);
				break;
			case 10:
				spawnWave("zoblinWarrior", 2, x, y, z, world);
				spawnWave("zoblinBoss", 1, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 11:
//				announce = "The zoblin attack seems to have ended";
				break;
			case 12:
//				announce = "The portal closes";
//				data.increaseProgress();
//				endRound(world);
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			}
		}
		
		if (round == 4)
		{
		switch (wave){			
			case 0: 
				lastSpawn = lastSpawn + 580;
				if (data.getProgress() < 1)
					data.increaseProgress();
				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			case 1:
				spawnWave("rabidDwarf", 3, x, y, z, world);
				break;
			case 2:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 1, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 3:
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 1, x, y, z, world);
				break;
			case 4:
				spawnWave("rabidDwarf", 1, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 1, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 5:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				break;
			case 6:
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 7:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 1, x, y, z, world);
				break;
			case 8:
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 9:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 1, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				break;
			case 10:
				spawnWave("rabidDemo", 1, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 11:
//				announce = "The rabid dwarf attack seems to have ended";
				break;
			case 12:
//				announce = "The portal closes";
//				data.increaseProgress();
//				endRound(world);
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			}
		}
		
		if (round == 5)
		{
		switch (wave){			
			case 0: 
				lastSpawn = lastSpawn + 580;
				if (data.getProgress() < 1)
					data.increaseProgress();
				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			case 1:
				spawnWave("rabidWarrior", 3, x, y, z, world);
				break;
			case 2:
				spawnWave("rabidDwarf", 3, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 3:
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 3, x, y, z, world);
				break;
			case 4:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 5:
				spawnWave("rabidDwarf", 4, x, y, z, world);
				spawnWave("rabidMiner", 3, x, y, z, world);
				break;
			case 6:
				spawnWave("rabidDwarf", 3, x, y, z, world);
				spawnWave("rabidWarrior", 3, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 7:
				spawnWave("rabidDwarf", 3, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 2, x, y, z, world);
				break;
			case 8:
				spawnWave("rabidMiner", 4, x, y, z, world);
				spawnWave("rabidWarrior", 3, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 9:
				spawnWave("rabidDwarf", 2, x, y, z, world);
				spawnWave("rabidMiner", 2, x, y, z, world);
				spawnWave("rabidWarrior", 4, x, y, z, world);
				break;
			case 10:
				spawnWave("rabidDemo", 1, x, y, z, world);
				spawnWave("rabidWarrior", 4, x, y, z, world);
				spawnWave("magicEssence", 1, x, y, z, world);
				break;
			case 11:
//				announce = "The rabid dwarf attack seems to have ended";
				break;
			case 12:
//				announce = "The portal closes";
//				data.increaseProgress();
//				endRound(world);
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
				break;
			}
		}
	}
	
	public void spawnWave(String mobName, int count, int x, int y, int z, World world)
	{
		String name = "";
		
		for (int i = 1; i < (count + 1); i++)
		{
			Random rand = new Random();
			double dx = (double)x + rand.nextDouble();
			double dz = (double)z + rand.nextDouble();
			if (mobName == "zoblin")
			{
				EntityZoblin entity = new EntityZoblin(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(2.099D);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Zoblin";
			}
			if (mobName == "zoblinBomber")
			{
				EntityZoblinBomber entity = new EntityZoblinBomber(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.599D);
				entity.attacking = true;
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
        		double xd = data.getCollectorX() - x;
        		double yd = data.getCollectorY() - y;
        		double zd = data.getCollectorZ() - z;
        		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
				entity.timer = (int)(distance/2);
				world.spawnEntityInWorld(entity);
				name = "Zoblin Bomber";
			}
			if (mobName == "magicEssence")
			{
				EntityMagicEssence entity = new EntityMagicEssence(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(2.599D);
				entity.attacking = true;
				entity.nodeX = data.getCollectorX();
				entity.nodeY = data.getCollectorY();
				entity.nodeZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Magic Essence";
			}
			if (mobName == "zoblinBoss")
			{
				EntityZoblinBoss entity = new EntityZoblinBoss(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Zoblin Boss";
			}
			if (mobName == "zoblinWarrior")
			{
				EntityZoblinWarrior entity = new EntityZoblinWarrior(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Zoblin Warrior";
			}
			if (mobName == "rabidMiner")
			{
				EntityRabidMiner entity = new EntityRabidMiner(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Rabid Miner";
			}
			if (mobName == "rabidDwarf")
			{
				EntityRabidDwarf entity = new EntityRabidDwarf(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Rabid Dwarf";
			}
			if (mobName == "rabidWarrior")
			{
				EntityRabidWarrior entity = new EntityRabidWarrior(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Rabid Warrior";
			}
			if (mobName == "rabidDemo")
			{
				EntityRabidDemo entity = new EntityRabidDemo(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.collectorX = data.getCollectorX();
				entity.collectorY = data.getCollectorY();
				entity.collectorZ = data.getCollectorZ();
				world.spawnEntityInWorld(entity);
				name = "Rabid Demolitionist";
			}

		}
		announce = announce + name + " x" + count + "  ";
	}
	
	@ForgeSubscribe
	public void onMobKilled(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityZoblinBoss && data.isWaveActive())
		{
			data.increaseProgress();
			endRound(event.entity.worldObj);
			event.entity.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
			if (!event.entityLiving.worldObj.isRemote)
				EldritchMethods.broadcastMessageLocal("Round completed", (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ, announceRadius, event.entityLiving.worldObj);
		}
		
		if (event.entityLiving instanceof EntityRabidDemo && data.isWaveActive())
		{
			data.increaseProgress();
			endRound(event.entityLiving.worldObj);
			event.entityLiving.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
			if (!event.entityLiving.worldObj.isRemote)
				EldritchMethods.broadcastMessageLocal("Round completed", (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ, announceRadius, event.entityLiving.worldObj);
		}

	}
	
}
