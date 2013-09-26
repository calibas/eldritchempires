package eldritchempires.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class ZoblinBoss extends EntityMob{

	private PathEntity path;
	public boolean attacking = false;
	public int nodeX;
	public int nodeY;
	public int nodeZ;
	
	public ZoblinBoss(World par1World) {
		super(par1World);
		this.experienceValue = 25;
	}
	
	@Override
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(100) == 0 && attacking == true)
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
//            		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Attacking!");
        			path = this.worldObj.getEntityPathToXYZ(this, nodeX, nodeY, nodeZ, 40F, true, true, false, false);
        			setPathToEntity(path);
        	//		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Pathing to " + nodeX + " " + nodeZ);
        	}
        }
        
        
        super.onLivingUpdate();
    }
	
    protected void func_110147_ax()
	 {
		 super.func_110147_ax();
		 // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(120.0D);
		 // Follow Range - default 32.0D - min 0.0D - max 2048.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(80.0D);
		 // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.9D);
		 // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.699D);
		 // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
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