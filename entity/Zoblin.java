package eldritchempires.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class Zoblin extends EntityMob{

	int[] closestNode = new int[3];
	int shortestDistance = 200;
	private PathEntity path;
	public boolean attacking = false;
	public int nodeX;
	public int nodeY;
	public int nodeZ;
	
	public Zoblin(World par1World) {
		super(par1World);
		this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
	}
	
	@Override
    public void onLivingUpdate()
    {
        if (this.rand.nextInt(100) == 0)
        {
        	int i;
        	int k;
			if (attacking == false)
			{
			  for (i = (int)this.posX - 10; i < (int)this.posX + 10; i++)
        	  {
        		for (k = (int)this.posZ - 10; k < (int)this.posZ + 10; k++)
        		{
        			if (this.worldObj.getFirstUncoveredBlock(i, k) == 252)
        			{
        				int distance = (int) (Math.pow((i - (int)this.posX), 2) + Math.pow((k - (int)this.posZ), 2));
        				if (shortestDistance == 200 || distance < shortestDistance)
        				{
        					shortestDistance = distance;
        					closestNode[0] = i;
        					closestNode[1] = getFirstUncoveredBlockHeight(i,k);
        					closestNode[2] = k;
        				}

        			}
        		}
        	  }
        	  if (shortestDistance != 200)
        	  {
				path = this.worldObj.getEntityPathToXYZ(this, closestNode[0], closestNode[1], closestNode[2], 40F, true, true, false, false);
        		setPathToEntity(path);
        	  }
        	  shortestDistance = 200;
			}
        	
        	if (attacking == true)
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
        }
        
        
        super.onLivingUpdate();
    }
	
    protected void func_110147_ax()
	 {
		 super.func_110147_ax();
		 // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
		 // Follow Range - default 32.0D - min 0.0D - max 2048.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(80.0D);
		 // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		 this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
		 // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.699D);
		 // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		 this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
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
