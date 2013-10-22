package eldritchempires.entity.ai;

import eldritchempires.entity.EntityStoneArcher;
import eldritchempires.entity.EntityStoneMage;
import eldritchempires.entity.EntityGuard;
import eldritchempires.entity.EntityStoneWarrior;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIGolemStill extends EntityAIBase
{
	public EntityLiving entity = null;//entity ai code should be run on
	private int stillX = 0;
	private int stillZ = 0;
	private int stillCount = 0;

	public EntityAIGolemStill(EntityLiving entityLiving)//constructor
	{
		entity=entityLiving;
	}

	@Override
	public boolean shouldExecute()//returns if the ai code should be run
	{
		boolean guarding = false;
			
    	if (entity instanceof EntityGuard)
    	{
    		EntityGuard entityGuard = (EntityGuard)entity;
    		guarding = entityGuard.guarding;
    	}

		return guarding;
	}
	
	@Override
	public boolean continueExecuting()
	{
//   		EntityGuard entityGuard = (EntityGuard)entity;
//   		System.out.println(this.entity.getAttackTarget() == (EntityLivingBase)null);
//   		System.out.println(entity.posX != entityGuard.homeX || entity.posZ != entityGuard.homeZ);
//    	return this.entity.getAttackTarget() == (EntityLivingBase)null && (entity.posX != entityGuard.homeX || entity.posZ != entityGuard.homeZ);
		return !this.entity.getNavigator().noPath() && this.entity.isEntityAlive();
	}

	@Override
    public void startExecuting()
    {
    	if (entity instanceof EntityGuard)
    	{
    		EntityGuard entityGuard = (EntityGuard)entity;
    		if (entity instanceof EntityStoneWarrior)
    		{
    			entity.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(30.0D);
    			entity.getNavigator().tryMoveToXYZ(entityGuard.homeX, entityGuard.homeY, entityGuard.homeZ, 0.5D);
    			entity.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(5.0D);
    		}
    		else
    		{
    			entity.getNavigator().tryMoveToXYZ(entityGuard.homeX, entityGuard.homeY, entityGuard.homeZ, 1.0D);
    		}
    		
    		if ((int)entityGuard.posX != stillX || (int)entityGuard.posZ != stillZ)
    		{
    			stillX = (int) entityGuard.posX;
    			stillZ = (int) entityGuard.posZ;
    			stillCount = 0;
    		}
    		else
    		{
    			stillCount++;
    		}
    		if (stillCount > 100)
    		{
    			entity.setPosition(entityGuard.homeX + 0.5D, entityGuard.homeY + 0.1D, entityGuard.homeZ + 0.5D);
    			stillCount = 0;
    		}
    	}
    }

}