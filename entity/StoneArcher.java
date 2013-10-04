package eldritchempires.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.world.World;

public class StoneArcher extends EntityMob implements IRangedAttackMob 
{
//	private PathEntity path;
	public boolean guarding = false;
	public int nodeX;
	public int nodeY;
	public int nodeZ;
	private EntityMoveHelper moveHelper;
//	private PathFinder findPath;
	
	public StoneArcher(World par1World) {
		super(par1World);
		this.experienceValue = 0;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2,  new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
//		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
//		this.tasks.addTask(4, new EntityAIWander(this, 0.5D));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, ZoblinBomber.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, ZoblinBoss.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, ZoblinWarrior.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, Zoblin.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        moveHelper = new EntityMoveHelper(this);

	}
	
    public boolean isAIEnabled()
    {
        return true;
    }
	
	@Override
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(50) == 0 && guarding == true)
        {
//			path = this.worldObj.getEntityPathToXYZ(this, nodex, nodey + 1, nodez, 40F, true, true, false, false);
//        	setPathToEntity(path);
//        	this.getMoveHelper().setMoveTo((double)nodex, (double)nodey + 1.0D, (double)nodez, 0.25D);
//        	Minecraft.getMinecraft().thePlayer.addChatMessage("SA: I'm supposed to path to" + nodex + " " + nodey + " " + nodez + " " + guarding);
        	this.setPosition((double)(nodeX + 0.5D), (double)(nodeY + 0.1D), (double)(nodeZ + 0.5D));
        }
        
		super.onLivingUpdate();        
    }
	
	@Override
    public EntityMoveHelper getMoveHelper()
    {
 //   		this.moveHelper.setMoveTo((double)nodex, (double)nodey + 1.0D, (double)nodez, 0.25D);
            return moveHelper;
    }

    protected boolean isMovementCeased()
    {
        return true;
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		// Default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		 // Default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0D);
		// Default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.2D);
    }
    
	@Override
    protected Entity findPlayerToAttack()
    {   
    	return null;
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase,
			float f) {
        EntityArrow entityarrow = new EntityArrow(this.worldObj, this, entitylivingbase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4));
        entityarrow.setDamage((double)(f * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting * 0.11F));
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
		
	}
	
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (guarding == true)
        {
        	par1NBTTagCompound.setBoolean("Guarding", guarding);
        	par1NBTTagCompound.setInteger("GuardX", nodeX);
        	par1NBTTagCompound.setInteger("GuardY", nodeY);
        	par1NBTTagCompound.setInteger("GuardZ", nodeZ);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Guarding"))
        {
            guarding = par1NBTTagCompound.getBoolean("Guarding");
            nodeX = par1NBTTagCompound.getInteger("GuardX");
            nodeY = par1NBTTagCompound.getInteger("GuardY");
            nodeZ = par1NBTTagCompound.getInteger("GuardZ");
        }
        
    }
	
}
