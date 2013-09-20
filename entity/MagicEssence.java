package eldritchempires.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class MagicEssence extends EntityMob{

	int[] closestNode = new int[3];
	int shortestDistance = 200;
	private PathEntity path;
	public boolean attacking = false;
	public int nodex;
	public int nodey;
	public int nodez;
	
	public MagicEssence(World par1World) {
		super(par1World);
//		this.getNavigator().setBreakDoors(true);
//		this.tasks.addTask(0, new EntityAISwimming(this));
//		this.tasks.addTask(1, new EntityAIBreakDoor(this));
	}
	
	@Override
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(100) == 0 && attacking == true)
        {
//            	Minecraft.getMinecraft().thePlayer.addChatMessage("MagicEssence: Attacking!");
				path = this.worldObj.getEntityPathToXYZ(this, nodex, nodey, nodez, 40F, true, true, false, false);
        		setPathToEntity(path);
        }
        
   		if (this.rand.nextInt(20) == 0)
    		{
    			worldObj.spawnParticle("magicCrit", posX, posY + 1.5D, posZ, 0.0D, 0.0D, 0.0D);
    		}
        
        super.onLivingUpdate();
    }
	
    protected void func_110147_ax()
	 {
		 super.func_110147_ax();
		 // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
		 // Follow Range - default 32.0D - min 0.0D - max 2048.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
		 // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
		 // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.699D);
		 // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
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