package eldritchempires.entity;

import eldritchempires.EldritchMethods;
import eldritchempires.EldritchWorldData;
import eldritchempires.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntityRabidDemo extends EntityAttacker{

	EldritchWorldData data = new EldritchWorldData();
	
	public EntityRabidDemo(World par1World) {
		super(par1World);
		
		this.collectorDamage = 2;
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		
    	if(this.rand.nextInt(130) == 0 && !this.worldObj.isRemote && (data.isWaveActive() || this.entityToAttack != null))
	   	{
    		if (data.isWaveActive())
    		{
    			EntityBomb entity = new EntityBomb(this.worldObj);
    			entity.setLocationAndAngles((double)this.posX, (double)this.posY + 1, (double)this.posZ, 0.0F, 0.0F);
 	
    			//Find direction of collector
    			double xd = this.collectorX - this.posX;
    			double zd = this.collectorZ - this.posZ;
    			double deltaX = Math.sin(Math.atan2(xd,zd));
    			double deltaZ = Math.cos(Math.atan2(xd, zd));
//    			float directionX = (float)(deltaX * 1.9D);
//    			float directionZ = (float)(deltaZ * 1.9D);
    			float directionX = (float)(deltaX);
    			float directionZ = (float)(deltaZ);

    			entity.motionX = directionX;
    			entity.motionY = 0.7F;
    			entity.motionZ = directionZ;
    			this.worldObj.spawnEntityInWorld(entity);
    		}
    		else
    		{
    			EntityBomb entity = new EntityBomb(this.worldObj);
    			entity.setLocationAndAngles((double)this.posX, (double)this.posY + 1, (double)this.posZ, 0.0F, 0.0F);
 	
    			//Find direction of collector
    			double xd = this.entityToAttack.posX - this.posX;
    			double zd = this.entityToAttack.posZ - this.posZ;
    			double deltaX = Math.sin(Math.atan2(xd,zd));
    			double deltaZ = Math.cos(Math.atan2(xd, zd));
//    			float directionX = (float)(deltaX * 1.9D);
//    			float directionZ = (float)(deltaZ * 1.9D);
    			float directionX = (float)(deltaX);
    			float directionZ = (float)(deltaZ);

    			entity.motionX = directionX;
    			entity.motionY = 0.7F;
    			entity.motionZ = directionZ;
    			this.worldObj.spawnEntityInWorld(entity);
    		}
	    }
	}
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		// Default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(130.0D);
		 // Default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(80.0D);
		// Default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.8D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.899D);
		// Default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
    }
	
	@Override
	public boolean canDespawn()
	{
		return false;
	}
    
}
