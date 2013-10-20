package eldritchempires.entity;

import eldritchempires.entity.ai.EntityAIGolemStill;
import eldritchempires.entity.projectile.EntityNiceArrow;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.world.World;

public class EntityStoneArcher extends EntityGuard implements IRangedAttackMob 
{
//	private PathEntity path;
//	public boolean guarding = false;
//	public int homeX;
//	public int homeY;
//	public int homeZ;
//	private EntityMoveHelper moveHelper;
//	private PathEntity path;
//	private PathFinder findPath;
	
	public EntityStoneArcher(World par1World) {
		super(par1World);
		this.experienceValue = 0;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2,  new EntityAIGolemStill(this));
		this.tasks.addTask(3,  new EntityAIArrowAttack(this, 0.0D, 20, 60, 15.0F));
//		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
//		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityZoblinBomber.class, 0, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityRabidMiner.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAttacker.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
//        moveHelper = new EntityMoveHelper(this);
        this.setCurrentItemOrArmor(0, new ItemStack(Item.bow));


	}
	
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
    public boolean isAIEnabled()
    {
        return true;
    }
    
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }
    
    public void setBesideClimbableBlock(boolean par1)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }
    
    public boolean isBesideClimbableBlock()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }
    
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D);
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase,
			float f) {
        EntityNiceArrow entityarrow = new EntityNiceArrow(this.worldObj, this, entitylivingbase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4));
        entityarrow.setDamage((double)(f * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting * 0.11F));
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
		
	}
	
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        if (guarding == true)
//        {
//        	par1NBTTagCompound.setBoolean("Guarding", guarding);
//        	par1NBTTagCompound.setInteger("GuardX", homeX);
//        	par1NBTTagCompound.setInteger("GuardY", homeY);
//        	par1NBTTagCompound.setInteger("GuardZ", homeZ);
//        }
//    }
//
//    /**
//     * (abstract) Protected helper method to read subclass entity data from NBT.
//     */
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
//    {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        if (par1NBTTagCompound.hasKey("Guarding"))
//        {
//            guarding = par1NBTTagCompound.getBoolean("Guarding");
//            homeX = par1NBTTagCompound.getInteger("GuardX");
//            homeY = par1NBTTagCompound.getInteger("GuardY");
//            homeZ = par1NBTTagCompound.getInteger("GuardZ");
//        }
//        
//    }
	
}
