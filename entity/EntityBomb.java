package eldritchempires.entity;

import eldritchempires.ParticleEffects;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBomb extends EntityCreature {

	int timer = 0;
	
	public EntityBomb(World par1World) {
		super(par1World);
		this.setSize(0.9F, 0.9F);
        this.rotationYaw = 0;
        this.rotationYawHead = this.rotationYaw;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
        this.rotationYaw = 0;
        this.rotationYawHead = this.rotationYaw;
        
        if (this.fallDistance != 0)
        	this.fallDistance = 0;
	
		if (!this.worldObj.isAirBlock((int)this.posX, (int)this.posY, (int)this.posZ))
		{
			Material material = this.worldObj.getBlockMaterial((int)this.posX, (int)this.posY, (int)this.posZ);
			if (material != Material.water && material != Material.lava && material != Material.plants && material != Material.vine && material != Material.snow )
			{
				this.setPosition(this.posX, this.posY + 1.0D, this.posZ);
				this.motionY = 0;
			}
		}
		
		
		if (this.rand.nextInt(10) == 0 && this.worldObj.isRemote)
			ParticleEffects.spawnParticle("fuse", this.posX, this.posY + 0.8D, this.posZ, 0, 0, 0);
		
		
		timer++;
		if (timer >= 200 && !this.worldObj.isRemote && !this.isDead)
		{
            	boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, flag);
                this.setDead();
		}

		if (this.getHealth() <= 0.0F)
		{
			this.setDead();
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 0.2D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.0D);
    }
	
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }
	
}
