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
	public int nodex;
	public int nodey;
	public int nodez;
	private EntityMoveHelper moveHelper;
//	private PathFinder findPath;
	
	public StoneArcher(World par1World) {
		super(par1World);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2,  new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
//		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
//		this.tasks.addTask(4, new EntityAIWander(this, 0.5D));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, ZoblinBomber.class, 0, true));
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
        	this.setPosition((double)nodex + 0.5D, nodey + 0.2D, nodez + 0.5D);
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
    
    protected void func_110147_ax()
	 {
		 super.func_110147_ax();
		 // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
		 // Follow Range - default 32.0D - min 0.0D - max 2048.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
		 // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20D);
		 // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
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
        	par1NBTTagCompound.setInteger("GuardX", nodex);
        	par1NBTTagCompound.setInteger("GuardY", nodey);
        	par1NBTTagCompound.setInteger("GuardZ", nodez);
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
            nodex = par1NBTTagCompound.getInteger("GuardX");
            nodey = par1NBTTagCompound.getInteger("GuardY");
            nodez = par1NBTTagCompound.getInteger("GuardZ");
        }
        
    }
	
}