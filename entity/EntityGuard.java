package eldritchempires.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGuard extends EntityMob{

	public boolean guarding = false;
	public int homeX;
	public int homeY;
	public int homeZ;
	
	public EntityGuard(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}
	
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (guarding == true)
        {
        	par1NBTTagCompound.setBoolean("Guarding", guarding);
        	par1NBTTagCompound.setInteger("GuardX", homeX);
        	par1NBTTagCompound.setInteger("GuardY", homeY);
        	par1NBTTagCompound.setInteger("GuardZ", homeZ);
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
            homeX = par1NBTTagCompound.getInteger("GuardX");
            homeY = par1NBTTagCompound.getInteger("GuardY");
            homeZ = par1NBTTagCompound.getInteger("GuardZ");
        }
        
    }

}
