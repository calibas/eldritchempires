package eldritchempires.entity;

import eldritchempires.ParticleEffects;
import eldritchempires.Registration;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBomb extends EntityCreature {

	int timer = 0;
	int collectorDamage = 40;
	
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
		// Explode
		if (timer >= 200 && !this.worldObj.isRemote && !this.isDead)
		{
			// Search within 2 blocks for collector
			int x = (int)this.posX;
			int y = (int)this.posY;
			int z = (int)this.posZ;

			int range  = 2;
			for(int i = -range; i < range; i++){
				for(int j = -range; j < range; j++){
					for(int k = -range; k < range; k++){
						int id = this.worldObj.getBlockId(x+i, y+j, z+k);
						if(id == Registration.collector.blockID)
						{
							TileEntity tileEntity = this.worldObj.getBlockTileEntity(x+i, y+j, z+k);
			        		if (tileEntity instanceof TileEntityCollector)
			        		{
			        			TileEntityCollector collector = (TileEntityCollector) tileEntity;
			        			collector.damageCollector(collectorDamage);
			        		}
			        	}
					}
				}
			}
			
			
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
