package eldritchempires.entity;

import eldritchempires.entity.ai.EntityAIGolemStill;
import eldritchempires.entity.projectile.EntityNiceArrow;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.world.World;

public class EntityStoneWarrior extends EntityGuard 
{
	
	public EntityStoneWarrior(World par1World) {
		super(par1World);
		this.experienceValue = 0;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2,  new EntityAIGolemStill(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityMob.class, 0.5D, false));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityZoblinBomber.class, 0, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityRabidMiner.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAttacker.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));

        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0;
        }
        
	}
	
	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
        
        if (this.rand.nextInt(50) == 0)
        {
        	this.setAttackTarget((EntityLivingBase)null);
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
    
    @Override
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.5D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(5.0D);
    }
	
}
