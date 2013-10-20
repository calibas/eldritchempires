package eldritchempires.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import eldritchempires.Registration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntitySpawner extends TileEntity implements IInventory
{
	boolean golemBound = false;
//	int golemId;
	int count = 0;
	long golemUUIDleast;
	long golemUUIDmost;
	private ItemStack[] inventory;
	public int attackLevel = 0;
	public int healthLevel = 0;
	
	public TileEntitySpawner()
    {

    }
	
	@Override
	public void updateEntity() 
	{
		
		if (count >= 500)
		{
			int i = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		
			if (!this.worldObj.isRemote)
			{
				System.out.println("attackLevel " + attackLevel);
			}
			
			// Stone Archer Spawner
			if (i == 0 && !this.worldObj.isRemote)
			{
				int searchRadius = 10;
				
				if (golemBound == true)
				{
					
					boolean found = false;

					List list = this.worldObj.getEntitiesWithinAABB(EntityStoneArcher.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));
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
				
					if (found != true)
					{
						golemBound = false;
					}
					
				}
			
				if (golemBound == false && !this.worldObj.isRemote)
				{
					EntityStoneArcher stoneArcher = new EntityStoneArcher(this.worldObj);
					stoneArcher.setLocationAndAngles((double)this.xCoord + 0.5D, (double)this.yCoord + 0.1D, (double)this.zCoord + 0.5D, 0.0F, 0.0F);
					stoneArcher.guarding = true;
					stoneArcher.homeX = this.xCoord;
					stoneArcher.homeY = this.yCoord;
					stoneArcher.homeZ = this.zCoord;
					golemUUIDmost = stoneArcher.getUniqueID().getMostSignificantBits();
					golemUUIDleast = stoneArcher.getUniqueID().getLeastSignificantBits();
					this.worldObj.spawnEntityInWorld(stoneArcher);
					golemBound = true;
				}
			

			}
		
		// Stone Mage Spawner
			if (i == 1 && !this.worldObj.isRemote)
			{
				int searchRadius = 10;
				
				if (golemBound == true)
				{
					
					boolean found = false;

						List list = this.worldObj.getEntitiesWithinAABB(EntityStoneMage.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));
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
					EntityStoneMage stoneMage = new EntityStoneMage(this.worldObj);
					stoneMage.setLocationAndAngles((double)this.xCoord + 0.5D, (double)this.yCoord + 0.1D, (double)this.zCoord + 0.5D, 0.0F, 0.0F);
					stoneMage.guarding = true;
					stoneMage.homeX = this.xCoord;
					stoneMage.homeY = this.yCoord;
					stoneMage.homeZ = this.zCoord;
					if (attackLevel > 0)
					{
						stoneMage.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
					}
					if (healthLevel > 0)
					{
						stoneMage.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(100D);
					}
					golemUUIDmost = stoneMage.getUniqueID().getMostSignificantBits();
					golemUUIDleast = stoneMage.getUniqueID().getLeastSignificantBits();
					this.worldObj.spawnEntityInWorld(stoneMage);
					golemBound = true;
				}
			}
			
			// Stone Warrior Spawner
			if (i == 2 && !this.worldObj.isRemote)
			{
				int searchRadius = 10;
				
				if (golemBound == true)
				{
					
					boolean found = false;

						List list = this.worldObj.getEntitiesWithinAABB(EntityStoneWarrior.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));
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
					EntityStoneWarrior stoneMage = new EntityStoneWarrior(this.worldObj);
					stoneMage.setLocationAndAngles((double)this.xCoord + 0.5D, (double)this.yCoord + 0.1D, (double)this.zCoord + 0.5D, 0.0F, 0.0F);
					stoneMage.guarding = true;
					stoneMage.homeX = this.xCoord;
					stoneMage.homeY = this.yCoord;
					stoneMage.homeZ = this.zCoord;
					golemUUIDmost = stoneMage.getUniqueID().getMostSignificantBits();
					golemUUIDleast = stoneMage.getUniqueID().getLeastSignificantBits();
					this.worldObj.spawnEntityInWorld(stoneMage);
					golemBound = true;
				}
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
        	par1NBTTagCompound.setInteger("attackLevel", attackLevel);
        	par1NBTTagCompound.setInteger("healthLevel", healthLevel);
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
            attackLevel = par1NBTTagCompound.getInteger("attackLevel");
            healthLevel = par1NBTTagCompound.getInteger("healthLevel");
        }
        
    }
	
	//Methods required by IInventory
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemstack = getStackInSlot(slot);

		if(itemstack != null) 
		{
			if(itemstack.stackSize <= count) 
			{
				setInventorySlotContents(slot, null);
			} 
			else 
			{
				itemstack = itemstack.splitStack(count);
				onInventoryChanged();
			}
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack itemstack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		inventory[slot] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) 
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Collector";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 16;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}
