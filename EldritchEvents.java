package eldritchempires;

import eldritchempires.entity.EntityAttacker;
import eldritchempires.entity.EntityRabidDemo;
import eldritchempires.entity.EntityZoblinBoss;
import eldritchempires.entity.TileEntityCollector;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EldritchEvents {
	
	
	@ForgeSubscribe
	public void onMobKilled(LivingDeathEvent event)
	{
		if (event.entityLiving instanceof EntityZoblinBoss && !event.entityLiving.worldObj.isRemote)
		{
			EntityAttacker entity = (EntityAttacker) event.entityLiving;
			if (entity.attacking && event.entity.worldObj.getBlockId(entity.collectorX, entity.collectorY, entity.collectorZ) == Registration.collector.blockID)
			{
				TileEntityCollector collector = (TileEntityCollector) event.entity.worldObj.getBlockTileEntity(entity.collectorX, entity.collectorY, entity.collectorZ);
				collector.bossKilled(entity.getUniqueID().getLeastSignificantBits(), entity.getUniqueID().getMostSignificantBits());
			}
//			data.increaseProgress();
//			event.entity.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
//			endRound(event.entity.worldObj);
//			if (!event.entityLiving.worldObj.isRemote)
//			EldritchMethods.broadcastMessageLocal("Round completed", (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ, 50, event.entityLiving.worldObj);
		}
		
		if (event.entityLiving instanceof EntityRabidDemo && !event.entityLiving.worldObj.isRemote)
		{
			EntityAttacker entity = (EntityAttacker) event.entityLiving;
			if (entity.attacking && event.entity.worldObj.getBlockId(entity.collectorX, entity.collectorY, entity.collectorZ) == Registration.collector.blockID)
			{
				TileEntityCollector collector = (TileEntityCollector) event.entity.worldObj.getBlockTileEntity(entity.collectorX, entity.collectorY, entity.collectorZ);
				collector.bossKilled(entity.getUniqueID().getLeastSignificantBits(), entity.getUniqueID().getMostSignificantBits());
			}
//			data.increaseProgress();
//			event.entityLiving.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
//			endRound(event.entityLiving.worldObj);
//			EldritchMethods.broadcastMessageLocal("Round completed", (int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ, 50, event.entityLiving.worldObj);
		}

	}
	
//	static int tickCount = 0;
//	static long lastRun = System.currentTimeMillis();
//	static long lastSpawn = 0;
////	public static int wave = 0;
//	static int announceRadius = 100;
////	public static boolean waveActive = true;
//	static String announce = "";
//	static EldritchWorldData data = new EldritchWorldData();
//	
//	@ForgeSubscribe
//	public void onWorldEvent(WorldEvent event)
//	{
//		tickCount++;
//		if (tickCount >= 5 && event.world.provider.dimensionId == 0 && !event.world.playerEntities.isEmpty() && lastRun + 1000 <= System.currentTimeMillis())
//		{
//			lastRun = System.currentTimeMillis();
//
//			data = EldritchWorldData.forWorld(event.world);
//			if (data != null && !event.world.isRemote)
//			{
//				int portalX = data.getPortalX();
//				int portalY = data.getPortalY();
//				int portalZ = data.getPortalZ();
//				int collectorX = data.getCollectorX();
//				int collectorY = data.getCollectorY();
//				int collectorZ = data.getCollectorZ();
//				int wave = data.getWave();
//				int round = data.getRound();
//
////				System.out.println("data.checkCollector() " + data.checkCollector() + " data.checkPortal() " + data.checkPortal() + " data.getWave() " + data.getWave() + " data.getRound() " + data.getRound() + " data.isWaveActive() " + data.isWaveActive() + " data.checkPortalFocus() " + data.checkPortalFocus());
////				System.out.println("Collector x,y,z " + collectorX + " " + collectorY + " " + collectorZ + " Portal x,y,z " + portalX + " " + portalY + " " + portalZ + " data.getProgress() " + data.getProgress() + " System.identityHashCode() " + System.identityHashCode(this));
//				
//				if(data.checkPortal() && !data.checkCollector() && event.world.blockExists(portalX, portalY, portalZ))
//				{
//						if (event.world.getBlockId(portalX, portalY, portalZ) != Registration.portal.blockID)
//						{
//							System.out.println("Portal unset" );
//							data.unSetPortal();
//							data.setPortalFocus(false);
//							data.setActiveWave(false);
//							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//						}
////					}
//				}
//				
//				if(data.checkCollector() && event.world.blockExists(collectorX, collectorY, collectorZ))
//				{
//					
//						if (event.world.getBlockId(collectorX, collectorY, collectorZ) != Registration.collector.blockID)
//						{
//							System.out.println("Collector unset" );
//							data.unSetCollector();
//							data.setActiveWave(false);
//							event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//						}
//				}
//				
//				
//				//Just in case WorldTime changes
//				if (data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) < 0)
//				{
//					lastSpawn = event.world.provider.getWorldTime();
//				}
//				
//				if (data.isWaveActive() && data.checkCollector() && (event.world.provider.getWorldTime() - lastSpawn) >= 600 && event.world.blockExists(collectorX, collectorY, collectorZ))
//				{
//					if (!data.checkPortal())
//					{
//						System.out.println("Trying to create portal" );
//						if (!data.checkPortalFocus())
//						{
//							int[] location = EldritchMethods.createPortal("zoblin", collectorX, collectorY, collectorZ, event.world);
//							if (location[0] == 0 && location[1] == 0 && location[2] == 0)
//							{
//									data.setActiveWave(false);
//							}
//							else 
//							{
//								data.setPortal(location[0], location[1], location[2]);
//							}
//						}
//
//					}
//					
//					event.world.setBlockMetadataWithNotify(collectorX, collectorY, collectorZ, 1, 2);
//					lastSpawn = event.world.provider.getWorldTime();
//					System.out.println("lastSpawn " + lastSpawn);
//					waves(round, wave, portalX, portalY, portalZ, event.world);
//					
//					if (!announce.equals("") && !event.world.isRemote)
//						EldritchMethods.broadcastMessageLocal(announce, collectorX, collectorY, collectorZ, 100, event.world);
//					
//					
//					wave++;
//					data.setWave(wave++);
//					event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//					if (data.getWave() >= 1 && data.getWave() <= 10)
//					{
//						announce = "Wave " + data.getWave() + "/10: ";
//					}
//					else
//					{
//						announce = "";
//					}
//				}
//				
//				if (!data.isWaveActive() && data.checkPortal() && !data.checkPortalFocus() && event.world.blockExists(portalX, portalY, portalZ))
//				{
//					EldritchMethods.broadcastMessageLocal("The portal closes", portalX, portalY, portalZ, 100, event.world);
//					event.world.setBlockMetadataWithNotify(collectorX, collectorY, collectorZ, 0, 2);
//					data.unSetPortal();
//					event.world.perWorldStorage.setData(EldritchWorldData.name, data);
//					event.world.setBlockToAir(portalX, portalY, portalZ);
//					event.world.removeBlockTileEntity(portalX, portalY, portalZ);
//				}
//					
//			}
//			
//			tickCount = 0;
//		}
//	}
//	
//	public static void endRound(World world)
//	{
//
//			int portalX = data.getPortalX();
//			int portalY = data.getPortalY();
//			int portalZ = data.getPortalZ();
//			if (!data.checkPortalFocus())
//			{
//				data.unSetPortal();
//			}
//			if (data.checkCollector())
//			{
//				world.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 0, 2);
//			}
//			data.setActiveWave(false);
//			world.perWorldStorage.setData(EldritchWorldData.name, data);
//			announce = "";
//			if (world.getBlockId(portalX, portalY, portalZ) == Registration.portal.blockID && !data.checkPortalFocus())
//			{
//				world.setBlockToAir(portalX, portalY, portalZ);
//				world.removeBlockTileEntity(portalX, portalY, portalZ);
//			}
//
//	}
//	
//	public void startRound(World world)
//	{
//		
//	}
//	
//	public static void waves(int round, int wave, int x, int y, int z, World world)
//	{
//		if (round == 1)
//		{
//		switch (wave){
//			case 0: 
//				lastSpawn = lastSpawn - 580;
//				System.out.println("lastSpawn + 580 " + lastSpawn);
//				break;
//			case 1:
//				spawnWave("zoblin", 2, x, y, z, world);
//				break;
//			case 2:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 3:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				data.increaseProgress();
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				endRound(world);
//				break;
//			case 4:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 5:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 6:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 7:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 8:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 1, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 9:
//				spawnWave("zoblin", 3, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 10:
//				spawnWave("zoblinBoss", 1, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
////				waveActive = false;
//				break;
//			case 11:
////				announce = "The zoblin attack seems to have ended";
//				break;
//			case 12:
////				announce = "The portal closes";
////				data.increaseProgress();
////				endRound(world);
////				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			}
//		}
//		
//		if (round == 2)
//		{
//		switch (wave){
//			case 0: 
//				lastSpawn = lastSpawn - 580;
//				break;
//			case 1:
//				spawnWave("zoblin", 3, x, y, z, world);
//				break;
//			case 2:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				break;
//			case 3:
//				spawnWave("zoblin", 3, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 4:
//				spawnWave("zoblin", 3, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				break;
//			case 5:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 3, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 6:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 1, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				break;
//			case 7:
//				spawnWave("zoblin", 3, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 8:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 1, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 9:
//				spawnWave("zoblinWarrior", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 10:
//				spawnWave("zoblinBoss", 1, x, y, z, world);
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
////				waveActive = false;
//				break;
//			case 11:
////				announce = "The zoblin attack seems to have ended";
//				break;
//			case 12:
////				announce = "The portal closes";
////				data.increaseProgress();
////				endRound(world);
////				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			}
//		}
//		
//		if (round == 3)
//		{
//		switch (wave){			
//			case 0: 
//				lastSpawn = lastSpawn - 580;
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			case 1:
//				spawnWave("zoblinWarrior", 2, x, y, z, world);
//				break;
//			case 2:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 1, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 3:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 1, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				break;
//			case 4:
//				spawnWave("zoblinWarrior", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 3, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 5:
//				spawnWave("zoblinWarrior", 4, x, y, z, world);
//				break;
//			case 6:
//				spawnWave("zoblin", 4, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 7:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinWarrior", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 1, x, y, z, world);
//				break;
//			case 8:
//				spawnWave("zoblinWarrior", 3, x, y, z, world);
//				spawnWave("zoblinBomber", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 9:
//				spawnWave("zoblin", 2, x, y, z, world);
//				spawnWave("zoblinBomber", 4, x, y, z, world);
//				break;
//			case 10:
//				spawnWave("zoblinWarrior", 2, x, y, z, world);
//				spawnWave("zoblinBoss", 1, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 11:
////				announce = "The zoblin attack seems to have ended";
//				break;
//			case 12:
////				announce = "The portal closes";
////				data.increaseProgress();
////				endRound(world);
////				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			}
//		}
//		
//		if (round == 4)
//		{
//		switch (wave){			
//			case 0: 
//				lastSpawn = lastSpawn - 580;
////				if (data.getProgress() < 1)
////					data.increaseProgress();
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			case 1:
//				spawnWave("rabidDwarf", 3, x, y, z, world);
//				break;
//			case 2:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 1, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 3:
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 1, x, y, z, world);
//				break;
//			case 4:
//				spawnWave("rabidDwarf", 1, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 1, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 5:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				break;
//			case 6:
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 7:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 1, x, y, z, world);
//				break;
//			case 8:
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 9:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 1, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				break;
//			case 10:
//				spawnWave("rabidDemo", 1, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 11:
////				announce = "The rabid dwarf attack seems to have ended";
//				break;
//			case 12:
////				announce = "The portal closes";
////				data.increaseProgress();
////				endRound(world);
////				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			}
//		}
//		
//		if (round == 5)
//		{
//		switch (wave){			
//			case 0: 
//				lastSpawn = lastSpawn - 580;
////				if (data.getProgress() < 1)
////					data.increaseProgress();
//				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			case 1:
//				spawnWave("rabidWarrior", 3, x, y, z, world);
//				break;
//			case 2:
//				spawnWave("rabidDwarf", 3, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 3:
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 3, x, y, z, world);
//				break;
//			case 4:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 5:
//				spawnWave("rabidDwarf", 4, x, y, z, world);
//				spawnWave("rabidMiner", 3, x, y, z, world);
//				break;
//			case 6:
//				spawnWave("rabidDwarf", 3, x, y, z, world);
//				spawnWave("rabidWarrior", 3, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 7:
//				spawnWave("rabidDwarf", 3, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 2, x, y, z, world);
//				break;
//			case 8:
//				spawnWave("rabidMiner", 4, x, y, z, world);
//				spawnWave("rabidWarrior", 3, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 9:
//				spawnWave("rabidDwarf", 2, x, y, z, world);
//				spawnWave("rabidMiner", 2, x, y, z, world);
//				spawnWave("rabidWarrior", 4, x, y, z, world);
//				break;
//			case 10:
//				spawnWave("rabidDemo", 1, x, y, z, world);
//				spawnWave("rabidWarrior", 4, x, y, z, world);
//				spawnWave("magicEssence", 1, x, y, z, world);
//				break;
//			case 11:
////				announce = "The rabid dwarf attack seems to have ended";
//				break;
//			case 12:
////				announce = "The portal closes";
////				data.increaseProgress();
////				endRound(world);
////				world.perWorldStorage.setData(EldritchWorldData.name, data);
//				break;
//			}
//		}
//	}
//	
//	public static void spawnWave(String mobName, int count, int x, int y, int z, World world)
//	{
//		String name = "";
//		
//		for (int i = 1; i < (count + 1); i++)
//		{
//			Random rand = new Random();
//			double dx = (double)x + rand.nextDouble();
//			double dz = (double)z + rand.nextDouble();
//			if (mobName == "zoblin")
//			{
//				EntityZoblin entity = new EntityZoblin(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(2.099D);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Zoblin";
//			}
//			if (mobName == "zoblinBomber")
//			{
//				EntityZoblinBomber entity = new EntityZoblinBomber(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.599D);
//				entity.attacking = true;
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//        		double xd = data.getCollectorX() - x;
//        		double yd = data.getCollectorY() - y;
//        		double zd = data.getCollectorZ() - z;
//        		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
//				entity.timer = (int)(distance/2);
//				world.spawnEntityInWorld(entity);
//				name = "Zoblin Bomber";
//			}
//			if (mobName == "magicEssence")
//			{
//				EntityMagicEssence entity = new EntityMagicEssence(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(2.599D);
//				entity.attacking = true;
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Magic Essence";
//			}
//			if (mobName == "zoblinBoss")
//			{
//				EntityZoblinBoss entity = new EntityZoblinBoss(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Zoblin Boss";
//			}
//			if (mobName == "zoblinWarrior")
//			{
//				EntityZoblinWarrior entity = new EntityZoblinWarrior(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Zoblin Warrior";
//			}
//			if (mobName == "rabidMiner")
//			{
//				EntityRabidMiner entity = new EntityRabidMiner(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Rabid Miner";
//			}
//			if (mobName == "rabidDwarf")
//			{
//				EntityRabidDwarf entity = new EntityRabidDwarf(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Rabid Dwarf";
//			}
//			if (mobName == "rabidWarrior")
//			{
//				EntityRabidWarrior entity = new EntityRabidWarrior(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Rabid Warrior";
//			}
//			if (mobName == "rabidDemo")
//			{
//				EntityRabidDemo entity = new EntityRabidDemo(world);
//				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
//				entity.collectorX = data.getCollectorX();
//				entity.collectorY = data.getCollectorY();
//				entity.collectorZ = data.getCollectorZ();
//				world.spawnEntityInWorld(entity);
//				name = "Rabid Demolitionist";
//			}
//
//		}
//		announce = announce + name + " x" + count + "  ";
//	}
	
}
