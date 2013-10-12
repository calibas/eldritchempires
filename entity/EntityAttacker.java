package eldritchempires.entity;

import eldritchempires.EldritchMethods;
import eldritchempires.EldritchWorldData;
import eldritchempires.Registration;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntityAttacker extends EntityMob{

	private PathEntity path;
	public boolean attacking = false;
	public int collectorX;
	public int collectorY;
	public int collectorZ;
	EldritchWorldData data = new EldritchWorldData();

	public int collectorDamage = 1;
	
	public EntityAttacker(World par1World) {
		super(par1World);
	}

	@Override
    public void onLivingUpdate()
    {
		if (this.rand.nextInt(10) == 0 && attacking && !this.isDead)
		{
    		double xd = (collectorX + 0.5D) - this.posX;
    		double yd = collectorY - this.posY;
    		double zd = (collectorZ + 0.5D) - this.posZ;
    		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
    		
    		if (distance < 1.7D && data.checkCollector() && !this.worldObj.isRemote)
    		{
    			path = this.worldObj.getEntityPathToXYZ(this, collectorX, collectorY, collectorZ, 40F, true, true, false, false);
    			setPathToEntity(path);
    			data.damageCollector(collectorDamage);
    			this.worldObj.perWorldStorage.setData(EldritchWorldData.name, data);
//    			collectorDamage++;
    			if (data.getCollectorHealth() > 0)
    				EldritchMethods.attackMessage("Collector under attack! (" + data.getCollectorHealth() + "/100)" , collectorX, collectorY, collectorZ, 100, this.worldObj);
    			this.worldObj.playAuxSFX(1010, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
    	//		this.worldObj.destroyBlockInWorldPartially(this.entityId, collectorX, collectorY, collectorZ, 1);
    			this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 1.0D, 0.0D);
    			if (data.getCollectorHealth() <= 0){
    				this.worldObj.setBlockToAir(collectorX, collectorY, collectorZ);
    				this.worldObj.removeBlockTileEntity(collectorX, collectorY, collectorZ);
    				EldritchMethods.broadcastMessageLocal("Collector destroyed!" , collectorX, collectorY, collectorZ, 100, this.worldObj);
    				attacking = false;
//    				ItemStack droppedItem = new ItemStack(Registration.collector, 1);
//    				EntityItem entityitem = new EntityItem(this.worldObj, (double)collectorX + 0.5D, (double)collectorY + 0.5D, (double)collectorZ + 0.5D, droppedItem);
//    				entityitem.delayBeforeCanPickup = 10;
//    				this.worldObj.spawnEntityInWorld(entityitem);
    			}
    			//this.worldObj.destroyBlockInWorldPartially(this.entityId, this.posX, this.posY, this.posZ, i);
    		}
		}
		
        if (this.rand.nextInt(50) == 0)
        {
        	int blockId = this.worldObj.getBlockId(collectorX, collectorY, collectorZ);
        	if (blockId != Registration.collector.blockID)
        		attacking = false;
        	
        	if (attacking)
            {
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
        }
        
        
        super.onLivingUpdate();
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
        	par1NBTTagCompound.setBoolean("Attacking", attacking);
        	par1NBTTagCompound.setInteger("AttackX", collectorX);
        	par1NBTTagCompound.setInteger("AttackY", collectorY);
        	par1NBTTagCompound.setInteger("AttackZ", collectorZ);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Attacking"))
        {
            attacking = par1NBTTagCompound.getBoolean("Attacking");
            collectorX = par1NBTTagCompound.getInteger("AttackX");
            collectorY = par1NBTTagCompound.getInteger("AttackY");
            collectorZ = par1NBTTagCompound.getInteger("AttackZ");
        }
        
    }
	
}
