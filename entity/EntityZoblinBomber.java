package eldritchempires.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntityZoblinBomber extends EntityCreature{

	private PathEntity path;
	public boolean attacking = false;
	public int collectorX;
	public int collectorY;
	public int collectorZ;
	public int timer = 20;
	
	public EntityZoblinBomber(World par1World) {
		super(par1World);
//        this.tasks.addTask(1, new EntityAISwimming(this));
//        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.isImmuneToFire = true;

	}
	
	public void onLivingUpdate()
    {
		if (timer >= 2 && timer < 5 && this.rand.nextInt(10) == 0)
		{
			worldObj.spawnParticle("lava", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
		
		if (timer < 2  && this.rand.nextInt(2) == 0)
		{
			worldObj.spawnParticle("lava", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
		
        if (this.rand.nextInt(50) == 0)
        {
        	if (attacking == true)
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
        	//		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Pathing to " + pathX + " " + pathZ);
        			
        		}
        		else
        		{
//            		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Attacking!");
        			path = this.worldObj.getEntityPathToXYZ(this, collectorX, collectorY, collectorZ, 70.0F, true, true, false, false);
        			setPathToEntity(path);
        		}
            }
        	timer--;
        	if(timer < 1 && !this.worldObj.isRemote)
        	{
        		int diffX = 0;
        		int diffY = 0;
        		int diffZ = 0;
        		
        		if (collectorX > this.posX)
        		{
        			diffX = 1;
        		}
        		if (collectorX < this.posX)
        		{
        			diffX = -1;
        		}
        		if (collectorY > this.posY + 1)
        		{
        			diffY = 1;
        		}
        		if (collectorY < this.posY)
        		{
        			diffY = -1;
        		}
        		if (collectorZ > this.posZ)
        		{
        			diffZ = 1;
        		}
        		if (collectorZ < this.posZ)
        		{
        			diffZ = -1;
        		}
        		
        		boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
        		this.worldObj.createExplosion(null, this.posX + diffX, this.posY + 2 + diffY, this.posZ + diffZ, 2.0F, flag);
        		this.setDead();
        	}
        }
        
        super.onLivingUpdate();
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		// Default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		 // Default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(80.0D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.699D);
    }
    
    protected Entity findPlayerToAttack()
    {   
    	return null;
    }
    
    public Entity getEntityToAttack()
    {
        return null;
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
    
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (attacking == true)
        {
        	par1NBTTagCompound.setBoolean("Attacking", attacking);
        	par1NBTTagCompound.setInteger("AttackX", collectorX);
        	par1NBTTagCompound.setInteger("AttackY", collectorY);
        	par1NBTTagCompound.setInteger("AttackZ", collectorZ);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
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