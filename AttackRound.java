package eldritchempires;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import eldritchempires.entity.EntityMagicEssence;
import eldritchempires.entity.EntityRabidDemo;
import eldritchempires.entity.EntityRabidDwarf;
import eldritchempires.entity.EntityRabidMiner;
import eldritchempires.entity.EntityRabidWarrior;
import eldritchempires.entity.EntityZoblin;
import eldritchempires.entity.EntityZoblinBomber;
import eldritchempires.entity.EntityZoblinBoss;
import eldritchempires.entity.EntityZoblinWarrior;
import eldritchempires.entity.TileEntityCollector;

public class AttackRound {
	TileEntityCollector collector;
	public boolean portalSet = false;
	public boolean createdPortal = false;
	public int wave = 0;
	public long lastSpawn;
	public String mobList = "";
	
	public AttackRound(TileEntityCollector entity)
	{
		collector = entity;
	}
	
	public void update()
	{
		if (System.currentTimeMillis() >= lastSpawn + 30000)
		{
			wave++;
			waves(collector.currentRound, wave, collector.portalX, collector.portalY, collector.portalZ, collector.worldObj);
			System.out.println("attackRound.update() 30 sec timer " + collector.portalX + " " + collector.portalY + " " + collector.portalZ );
			lastSpawn = System.currentTimeMillis();
			if (!mobList.equals(""))
			{
				String announce = "Wave " + wave + "/10: " + mobList;
				EldritchMethods.broadcastMessageLocal(announce, collector.xCoord, collector.yCoord, collector.zCoord, 100, collector.worldObj);
				mobList = "";
			}
		}
	}
	
	public void initialize()
	{
		// Check if portal has been set and if it's still there
		if (portalSet)
		{
			int id = collector.worldObj.getBlockId(collector.portalX, collector.portalY, collector.portalZ);
			if (id != Registration.portal.blockID)
			{
				portalSet = false;
			}
		}
		
		// If portal isn't already set, search within 30 blocks for portal
		if (!portalSet)
		{
			findPortal();
		}
		
		// If no portal found, create one
		if (!portalSet)
		{
			int portalCoords[] = EldritchMethods.createPortal("", collector.xCoord, collector.yCoord, collector.zCoord, collector.worldObj);
			collector.portalX = portalCoords[0];
			collector.portalY = portalCoords[1];
			collector.portalZ = portalCoords[2];
		}
		lastSpawn = System.currentTimeMillis() - 25000;
		wave = 0;
	}
	
	private void findPortal()
	{
		int x = collector.xCoord;
		int y = collector.yCoord;
		int z = collector.zCoord;

		int range  = 40;
		for(int i = -range; i<range; i++){
			for(int j = -range; j<range; j++){
				for(int k = -range; k<range; k++){
					int id = collector.worldObj.getBlockId(x+i, y+j, z+k);
					if(id == Registration.portal.blockID)
					{
						collector.portalX = x + i;
						collector.portalY = y + j;
						collector.portalZ = z + k;
						portalSet = true;
						return;
					}
				}
			}
		}
	}
	
	public void removePortal()
	{
		if (!portalSet && collector.worldObj.getBlockId(collector.portalX, collector.portalY, collector.portalZ) == Registration.portal.blockID)
		{
			collector.worldObj.setBlockToAir(collector.portalX, collector.portalY, collector.portalZ);
		}
	}
	
	public void waves(int round, int wave, int x, int y, int z, World world)
	{
		if (round == 1)
		{
		switch (wave){
			case 0: 
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
				break;
			}
		}
		
		if (round == 2)
		{
		switch (wave){
			case 0: 
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
				break;
			}
		}
		
		if (round == 3)
		{
		switch (wave){			
			case 0: 
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
			}
		}
		
		if (round == 4)
		{
		switch (wave){			
			case 0: 
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
			}
		}
		
		if (round == 5)
		{
		switch (wave){			
			case 0: 
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
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Zoblin";
			}
			if (mobName == "zoblinBomber")
			{
				EntityZoblinBomber entity = new EntityZoblinBomber(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.599D);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
        		double xd = collector.xCoord - x;
        		double yd = collector.yCoord - y;
        		double zd = collector.zCoord - z;
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
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Magic Essence";
			}
			if (mobName == "zoblinBoss")
			{
				EntityZoblinBoss entity = new EntityZoblinBoss(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				collector.setBossUUID(entity.getUniqueID().getLeastSignificantBits(), entity.getUniqueID().getMostSignificantBits());
				world.spawnEntityInWorld(entity);
				name = "Zoblin Boss";
			}
			if (mobName == "zoblinWarrior")
			{
				EntityZoblinWarrior entity = new EntityZoblinWarrior(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Zoblin Warrior";
			}
			if (mobName == "rabidMiner")
			{
				EntityRabidMiner entity = new EntityRabidMiner(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Rabid Miner";
			}
			if (mobName == "rabidDwarf")
			{
				EntityRabidDwarf entity = new EntityRabidDwarf(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Rabid Dwarf";
			}
			if (mobName == "rabidWarrior")
			{
				EntityRabidWarrior entity = new EntityRabidWarrior(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				world.spawnEntityInWorld(entity);
				name = "Rabid Warrior";
			}
			if (mobName == "rabidDemo")
			{
				EntityRabidDemo entity = new EntityRabidDemo(world);
				entity.setLocationAndAngles(dx, (double)y + 1, dz, 0.0F, 0.0F);
				entity.attacking = true;
				entity.collectorX = collector.xCoord;
				entity.collectorY = collector.yCoord;
				entity.collectorZ = collector.zCoord;
				collector.setBossUUID(entity.getUniqueID().getLeastSignificantBits(), entity.getUniqueID().getMostSignificantBits());
				world.spawnEntityInWorld(entity);
				name = "Rabid Demolitionist";
			}

		}
		mobList = mobList + name + " x" + count + "  ";
	}

}
