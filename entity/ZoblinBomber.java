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

public class ZoblinBomber extends EntityCreature{

	private PathEntity path;
	public boolean attacking = false;
	public int nodex;
	public int nodey;
	public int nodez;
	public int timer = 10;
	
	public ZoblinBomber(World par1World) {
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
		
        if (this.rand.nextInt(100) == 0)
        {
        	if (attacking == true)
            {
//            	Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Attacking!");
				path = this.worldObj.getEntityPathToXYZ(this, nodex, nodey, nodez, 70.0F, true, true, false, false);
        		setPathToEntity(path);
            }
        	timer--;
        	if(timer < 1 && !this.worldObj.isRemote)
        	{
        		int diffX = 0;
        		int diffY = 0;
        		int diffZ = 0;
        		
        		if (nodex > this.posX)
        		{
        			diffX = 1;
        		}
        		if (nodex < this.posX)
        		{
        			diffX = -1;
        		}
        		if (nodey > this.posY + 1)
        		{
        			diffY = 1;
        		}
        		if (nodey < this.posY)
        		{
        			diffY = -1;
        		}
        		if (nodez > this.posZ)
        		{
        			diffZ = 1;
        		}
        		if (nodez < this.posZ)
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
	
    protected void func_110147_ax()
	{
		 super.func_110147_ax();
		 // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
		 // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.699D);
		 // Follow Range - default 32.0D - min 0.0D - max 2048.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(60.0D);
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
        	par1NBTTagCompound.setInteger("AttackX", nodex);
        	par1NBTTagCompound.setInteger("AttackY", nodey);
        	par1NBTTagCompound.setInteger("AttackZ", nodez);
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
            nodex = par1NBTTagCompound.getInteger("AttackX");
            nodey = par1NBTTagCompound.getInteger("AttackY");
            nodez = par1NBTTagCompound.getInteger("AttackZ");
        }
        
    }

}
