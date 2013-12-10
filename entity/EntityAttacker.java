package eldritchempires.entity;

import eldritchempires.EldritchMethods;
import eldritchempires.Registration;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityAttacker extends EntityMob{

	private PathEntity path;
	public boolean attacking = false;
	public int collectorX;
	public int collectorY;
	public int collectorZ;
//	EldritchWorldData data = new EldritchWorldData();
	private TileEntityCollector collector;

	public int collectorDamage = 1;
	
	public EntityAttacker(World par1World) {
		super(par1World);
	}

	@Override
    public void onLivingUpdate()
    {
//		if (this.rand.nextInt(10) == 0 && data.isWaveActive() && !this.isDead)
		if (this.rand.nextInt(10) == 0 && attacking && !this.isDead)
		{
//			collectorX = data.getCollectorX();
//			collectorY = data.getCollectorY();
//			collectorZ = data.getCollectorZ();
			
			// Damage Collector
    		double xd = (collectorX + 0.5D) - this.posX;
    		double yd = collectorY - this.posY;
    		double zd = (collectorZ + 0.5D) - this.posZ;
    		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
    		TileEntity tileEntity = this.worldObj.getBlockTileEntity(collectorX, collectorY, collectorZ);
    		if (tileEntity instanceof TileEntityCollector)
    		{
    			collector = (TileEntityCollector) tileEntity;

    		
    		  if (distance < 1.7D && collector.roundActive && !this.worldObj.isRemote)
    		  {
    			path = this.worldObj.getEntityPathToXYZ(this, collectorX, collectorY, collectorZ, 40F, true, true, false, false);
    			setPathToEntity(path);
    			collector.damageCollector(collectorDamage);
//    			data.damageCollector(collectorDamage);
//    			this.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
//
//    			if (data.getCollectorHealth() > 0)
//    				EldritchMethods.attackMessage("Collector under attack! (" + data.getCollectorHealth() + "/100)" , collectorX, collectorY, collectorZ, 100, this.worldObj);
    			
    			this.worldObj.playAuxSFX(1010, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
    			this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 1.0D, 0.0D);
    			
//    			if (data.getCollectorHealth() <= 0){
//    				this.worldObj.setBlockToAir(collectorX, collectorY, collectorZ);
//    				this.worldObj.removeBlockTileEntity(collectorX, collectorY, collectorZ);
//    				EldritchMethods.broadcastMessageLocal("Collector destroyed!" , collectorX, collectorY, collectorZ, 100, this.worldObj);
//    			}
    		  }
    		}
		}
		
//		if (this.rand.nextInt(50) == 0 && data.isWaveActive())
        if (this.rand.nextInt(50) == 0 && attacking)
        {
//			collectorX = data.getCollectorX();
//			collectorY = data.getCollectorY();
//			collectorZ = data.getCollectorZ();
        	
        	double xd = collectorX - this.posX;
        	double yd = collectorY - this.posY;
        	double zd = collectorZ - this.posZ;
        	double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
        	if (distance > 40.0D)
        		{
        			double deltaX = Math.sin(Math.atan2(xd,zd));
        			double deltaZ = Math.cos(Math.atan2(xd, zd));
        			int pathX = (int)(this.posX + (20*deltaX));
        			int pathZ = (int)(this.posZ + (20*deltaZ));
        			path = this.worldObj.getEntityPathToXYZ(this, pathX, getFirstUncoveredBlockHeight(pathX, pathZ), pathZ, 40F, true, true, false, false);
        			setPathToEntity(path);
        		}
        		else
        		{
        			path = this.worldObj.getEntityPathToXYZ(this, collectorX, collectorY, collectorZ, 40F, true, true, false, false);
        			setPathToEntity(path);
        		}
        }
        
        
        super.onLivingUpdate();
    }
	
	@Override
    protected void dropFewItems(boolean hitByPlayer, int lootLevel)
    {
        super.dropFewItems(hitByPlayer, lootLevel);
        
        int itemChance = this.rand.nextInt(200);
        
        if (isBetween(itemChance, 1, 5)) {
        	this.dropItem(Item.leather.itemID, 1);
        } else if (isBetween(itemChance, 6, 7)) {
        	this.dropItem(Item.appleRed.itemID, 1);
        } else if (isBetween(itemChance, 8, 9)) {
        	this.dropItem(Item.bread.itemID, 1);
        } else if (isBetween(itemChance, 10, 15)) {
        	this.dropItem(Item.beefCooked.itemID, 1);
        } else if (isBetween(itemChance, 16, 16)) {
        	this.dropItem(Item.diamond.itemID, 1);
        } else if (isBetween(itemChance, 17, 17)) {
        	this.dropItem(Item.expBottle.itemID, 1);
        } else if (isBetween(itemChance, 18, 18)) {
        	this.dropItem(Item.emerald.itemID, 1);
        } else if (isBetween(itemChance, 19, 19)) {
        	this.dropItem(Item.bow.itemID, 1);
        } else if (isBetween(itemChance, 20, 35)) {
        	this.dropItem(Item.bone.itemID, 1);
        } else if (isBetween(itemChance, 36, 40)) {
        	this.dropItem(Item.leather.itemID, 1);
        } else if (isBetween(itemChance, 41, 46)) {
        	this.dropItem(Item.arrow.itemID, 10);
        } else if (isBetween(itemChance, 47, 50)) {
        	this.dropItem(Item.ingotIron.itemID, 1);
        }
    }
	
	public static boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
		}
	
    public int getFirstUncoveredBlockHeight(int par1, int par2)
    {
        int k;

        for (k = 63; !this.worldObj.isAirBlock(par1, k + 1, par2); ++k)
        {
            ;
        }

        return k;
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (attacking)
        {
        	par1NBTTagCompound.setInteger("AttackX", collectorX);
        	par1NBTTagCompound.setInteger("AttackY", collectorY);
        	par1NBTTagCompound.setInteger("AttackZ", collectorZ);
        	par1NBTTagCompound.setBoolean("attacking", attacking);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("AttackX"))
        {
            collectorX = par1NBTTagCompound.getInteger("AttackX");
            collectorY = par1NBTTagCompound.getInteger("AttackY");
            collectorZ = par1NBTTagCompound.getInteger("AttackZ");
            attacking = par1NBTTagCompound.getBoolean("attacking");
        }
        
    }
	
}
