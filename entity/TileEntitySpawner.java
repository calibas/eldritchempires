package eldritchempires.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntitySpawner extends TileEntity
{
	boolean golemBound = false;
//	int golemId;
	int count = 0;
	long golemUUIDleast;
	long golemUUIDmost;
	
	public TileEntitySpawner()
    {

    }
	
	@Override
	public void updateEntity() 
	{
		if (count >= 500)
		{
			int i = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		
			// Stone Archer Spawner
			if (i == 0 && !this.worldObj.isRemote)
			{
				int searchRadius = 10;
				
				if (golemBound == true)
				{
					
					boolean found = false;
//					List list = this.worldObj.getEntitiesWithinAABB(StoneArcher.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));
//					Iterator iterator = list.iterator();
//
//	                while (iterator.hasNext())
//	                {
//	                    EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
//
//	                    if (entitylivingbase.getUniqueID().equals(uuid))
//	                    {
//
//	                    }
//	                }
//					System.out.println(golemUUIDmost);
//					System.out.println(golemUUIDleast);
						UUID uuid = new UUID(golemUUIDmost, golemUUIDleast);
						List list = this.worldObj.getEntitiesWithinAABB(StoneArcher.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));
		                Iterator iterator = list.iterator();

		                while (iterator.hasNext())
		                {
		                    EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();

		                    if (entitylivingbase.getUniqueID().getLeastSignificantBits() == golemUUIDleast && entitylivingbase.getUniqueID().getMostSignificantBits() == golemUUIDmost)
		                    {
		                        found = true;
		                        break;
		                    }
		                }
//					System.out.println("GolemId: " + golemId);
//					StoneArcher entity = (StoneArcher)this.worldObj.getEntityByID(golemId);
//					
					if (found != true)
					{
						golemBound = false;
					}
					
				}
			
				if (golemBound == false && !this.worldObj.isRemote)
				{
				StoneArcher stoneArcher = new StoneArcher(this.worldObj);
				stoneArcher.setLocationAndAngles((double)this.xCoord + 0.5D, (double)this.yCoord + 0.1D, (double)this.zCoord + 0.5D, 0.0F, 0.0F);
				stoneArcher.guarding = true;
				stoneArcher.collectorX = this.xCoord;
				stoneArcher.collectorY = this.yCoord;
				stoneArcher.collectorZ = this.zCoord;
				golemUUIDmost = stoneArcher.getUniqueID().getMostSignificantBits();
				golemUUIDleast = stoneArcher.getUniqueID().getLeastSignificantBits();
				this.worldObj.spawnEntityInWorld(stoneArcher);
				golemBound = true;
				}
			

			}
		
		// Placeholder Spawner
			if (i == 1)
			{

			}
			count = 0;
		}
		else
		{
			count++;
		}
    }
	
	@Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        if (golemBound == true)
        {
        	par1NBTTagCompound.setBoolean("golemBound", golemBound);
        	par1NBTTagCompound.setLong("golemUUIDmost", golemUUIDmost);
        	par1NBTTagCompound.setLong("golemUUIDleast", golemUUIDleast);
        }
    }

	@Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("golemBound"))
        {
            golemBound = par1NBTTagCompound.getBoolean("golemBound");
            golemUUIDmost = par1NBTTagCompound.getLong("golemUUIDmost");
            golemUUIDleast = par1NBTTagCompound.getLong("golemUUIDleast");
        }
        
    }
}
