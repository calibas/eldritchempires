package eldritchempires.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMagicEssence extends EntityMob{

	private PathEntity path;
	public boolean attacking = false;
	public int nodeX;
	public int nodeY;
	public int nodeZ;
	
	public EntityMagicEssence(World par1World) {
		super(par1World);
	}
	
	@Override
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(40) == 0 && attacking == true)
        {
       		double xd = nodeX - this.posX;
    		double yd = nodeY - this.posY;
    		double zd = nodeZ - this.posZ;
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
//        		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Attacking!");
    			path = this.worldObj.getEntityPathToXYZ(this, nodeX, nodeY, nodeZ, 40F, true, true, false, false);
    			setPathToEntity(path);
    	//		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Pathing to " + nodeX + " " + nodeZ);
    		}
        }
        
   		if (this.rand.nextInt(20) == 0)
    		{
    			worldObj.spawnParticle("magicCrit", posX, posY + 1.5D, posZ, 0.0D, 0.0D, 0.0D);
    		}
        
        super.onLivingUpdate();
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		// Default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
		 // Default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(80.0D);
		// Default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(2.699D);
		// Default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
    }
    
	@Override
    protected Entity findPlayerToAttack()
    {   
    	return null;
    }
	
    public Entity getEntityToAttack()
    {
        return null;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	return false;
    }
    
//    public int getFirstUncoveredBlockHeight(int par1, int par2)
//    {
//        int k;
//
//        for (k = 63; !this.worldObj.isAirBlock(par1, k + 1, par2); ++k)
//        {
//            ;
//        }
//
//        return k;
//    }
    
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
        	par1NBTTagCompound.setInteger("AttackX", nodeX);
        	par1NBTTagCompound.setInteger("AttackY", nodeY);
        	par1NBTTagCompound.setInteger("AttackZ", nodeZ);
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
            nodeX = par1NBTTagCompound.getInteger("AttackX");
            nodeY = par1NBTTagCompound.getInteger("AttackY");
            nodeZ = par1NBTTagCompound.getInteger("AttackZ");
        }
        
    }
    
}
