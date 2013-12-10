package eldritchempires.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import eldritchempires.AttackRound;
import eldritchempires.EldritchMethods;
import eldritchempires.Registration;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldSavedData;

public class TileEntityCollector extends TileEntity implements IInventory
{
	private int i = 0;
	private double searchRadius = 1.5;
//	EldritchWorldData data = new EldritchWorldData();
	private ItemStack[] inventory;
	public AttackRound attackRound = new AttackRound(this);
	public boolean roundActive = false;
	public int currentRound;
	public int progress = 1;
	public int portalX;
	public int portalY;
	public int portalZ;
	public int health;
	long lastMessage;
	long bossUUIDleast;
	long bossUUIDmost;
	
	
	public TileEntityCollector()
    {
		inventory = new ItemStack[1];
    }
	
	public void updateEntity() 
	{
		i++;
		if (i >= 10 )
		{
			//Spawn Particles
			if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) != 0)
			{
				if (health + 10 < this.worldObj.rand.nextInt(100))
				{
					this.worldObj.spawnParticle("crit", this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 1.0D - 2* this.worldObj.rand.nextDouble(), 1.0D, 1.0D - 2* this.worldObj.rand.nextDouble());
				}
				if (i % 5 == 0)
				{
					this.worldObj.spawnParticle("reddust", this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, 0.0D, 0.0D, 0.0D);
				}
				if (i % 10 == 0)
				{
					this.worldObj.spawnParticle("reddust", this.xCoord + 0.5D, this.yCoord + 0.25D, this.zCoord + 0.25D, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("reddust", this.xCoord + 0.25D, this.yCoord + 0.25D, this.zCoord + 0.5D, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("reddust", this.xCoord + 0.75D, this.yCoord + 0.25D, this.zCoord + 0.5D, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("reddust", this.xCoord + 0.5D, this.yCoord + 0.25D, this.zCoord + 0.75D, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("smoke", this.xCoord + 0.5D, this.yCoord + 0.75D, this.zCoord + 0.5D, 0.0D, 0.0D, 0.0D);
				}
			}
			
			//Search for Magic Essence
			if (!this.worldObj.isRemote)
			{
				List<?> var4 = this.worldObj.getEntitiesWithinAABB(EntityMagicEssence.class, AxisAlignedBB.getAABBPool().getAABB(this.xCoord - searchRadius, this.yCoord - searchRadius, this.zCoord - searchRadius, this.xCoord + searchRadius, this.yCoord + searchRadius, this.zCoord + searchRadius));

				if (var4 != null && !var4.isEmpty()) {
				  Iterator<?> var5 = var4.iterator();

				  while (var5.hasNext()) {
					EntityLiving var6 = (EntityLiving)var5.next();
					if (!this.worldObj.isRemote && !var6.isDead)
					{
					    var6.setDead();
						ItemStack droppedItem = new ItemStack(Registration.condensedEssence, 1);
						EntityItem entityitem = new EntityItem(this.worldObj, (double)this.xCoord, (double)this.yCoord + 1, (double)this.zCoord, droppedItem);
						entityitem.delayBeforeCanPickup = 10;
						this.worldObj.spawnEntityInWorld(entityitem);
					}
				  }
				}
			}
			
			if (roundActive && !this.worldObj.isRemote)
			{
				attackRound.update();
			}
			
			i = 0;
		}
		
	}
	
	public void startRound(int round)
	{
		if (!this.worldObj.isRemote)
		{
			attackRound.initialize();
			currentRound = round;
			health = 100;
			roundActive = true;
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, progress, 2);
		}
	}
	
	public void endRound()
	{		
		if (!this.worldObj.isRemote)
		{
			roundActive = false;
			attackRound.wave = 0;
			attackRound.removePortal();
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 2);
		}
	}
	
	public void damageCollector(int damage)
	{
		if (roundActive)
		{
			health = health - damage;
			if (health > 0)
			{
				if (lastMessage + 3000 < System.currentTimeMillis())
				{
					EldritchMethods.broadcastMessageLocal("Collector under attack! " + health + "% health left.", this.xCoord, this.yCoord, this.zCoord, 100, this.worldObj);
					lastMessage = System.currentTimeMillis();
				}
			} 
			else 
			{
				EldritchMethods.broadcastMessageLocal("Collector deactivated!", this.xCoord, this.yCoord, this.zCoord, 100, this.worldObj);
				endRound();
			}
		}

	}
	
	public void bossKilled(long UUIDleast, long UUIDmost)
	{
		if (UUIDleast == bossUUIDleast && UUIDmost == bossUUIDmost && roundActive)
		{
			EldritchMethods.broadcastMessageLocal("Round completed.", this.xCoord, this.yCoord, this.zCoord, 100, this.worldObj);
			endRound();
			// Increase progress
			if (currentRound == progress)
			{
				progress++;
			}
		}
	}
	
	public void setBossUUID (long UUIDleast, long UUIDmost)
	{
		bossUUIDleast = UUIDleast;
		bossUUIDmost = UUIDmost;
	}
	
	@Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("portalX", portalX);
        nbt.setInteger("portalY", portalY);
        nbt.setInteger("portalZ", portalZ);
        nbt.setBoolean("portalSet", attackRound.portalSet);
        nbt.setInteger("currentRound", currentRound);
        nbt.setInteger("progress", progress);
        nbt.setBoolean("roundActive", roundActive);
        nbt.setInteger("wave", attackRound.wave);
        nbt.setInteger("health", health);
        nbt.setLong("bossUUIDleast", bossUUIDleast);
        nbt.setLong("bossUUIDmost", bossUUIDmost);
     }
 
	@Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        portalX = nbt.getInteger("portalX");
        portalY = nbt.getInteger("portalY");
        portalZ = nbt.getInteger("portalZ");
        attackRound.portalSet = nbt.getBoolean("portalSet");
        currentRound = nbt.getInteger("currentRound");
        progress = nbt.getInteger("progress");
        roundActive = nbt.getBoolean("roundActive");
        attackRound.wave = nbt.getInteger("wave");
        health = nbt.getInteger("health");
        bossUUIDleast = nbt.getLong("bossUUIDleast");
        bossUUIDmost = nbt.getLong("bossUUIDmost");
    }

	
	
	// Methods required by IInventory
	
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
