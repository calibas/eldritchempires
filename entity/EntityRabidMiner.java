package eldritchempires.entity;

import eldritchempires.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityRabidMiner extends EntityAttacker{

	private int lastX = 0;
	private int lastZ = 0;
	private int stillCounter = 0;
	private boolean removingBlock = false;
	private int blockRemovalCounter = 0;
	private int removingX;
	private int removingY;
	private int removingZ;
//	EldritchWorldData data = new EldritchWorldData();
	TileEntityCollector collector;
//	private PathEntity path;
	
	public EntityRabidMiner(World par1World) {
		super(par1World);
		
		this.setCurrentItemOrArmor(0, new ItemStack(Item.pickaxeIron));
		this.collectorDamage = 2;
	}
	
	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.rand.nextInt(10) == 0 && !this.worldObj.isRemote && this.getHeldItem().itemID == Item.pickaxeIron.itemID) {

			TileEntity tileEntity = this.worldObj.getBlockTileEntity(collectorX, collectorY, collectorZ);
			if (tileEntity instanceof TileEntityCollector)
			{
				collector = (TileEntityCollector) tileEntity;

				if (collector.roundActive)
				{

					int targetX = 1;
					int targetY = 1;

					if (removingBlock)
					{
						System.out.println(removingBlock);
						blockRemovalCounter++;
						if (this.getDistance(removingX + 0.5D, removingY - 0.5D, removingZ + 0.5D) > 2.0D) {
							removingBlock = false;
							this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
						}

						//Destroy blocks, if not portal
						if (blockRemovalCounter > 6) {
							if (!this.worldObj.isAirBlock(removingX, removingY, removingZ) && this.worldObj.getBlockId(removingX, removingY, removingZ) != Registration.portal.blockID && !this.worldObj.isAirBlock(removingX, removingY +2, removingZ)) {
								int blockID = this.worldObj.getBlockId(removingX, removingY, removingZ);
								this.worldObj.destroyBlock(removingX, removingY, removingZ, true);
							}
							else if (!this.worldObj.isAirBlock(removingX, removingY + 1, removingZ) && this.worldObj.getBlockId(removingX, removingY + 1, removingZ) != Registration.portal.blockID) {
								int blockID = this.worldObj.getBlockId(removingX, removingY + 1, removingZ);
								this.worldObj.destroyBlock(removingX, removingY + 1, removingZ, true);
							}
							blockRemovalCounter = 0;

							if (this.worldObj.isAirBlock(removingX, removingY + 1, removingZ) || this.worldObj.getBlockId(removingX, removingY, removingZ) == Registration.portal.blockID) {
								removingBlock = false;
								this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
							}
						}
					}
					else if ((int)this.posX != lastX || (int)this.posZ != lastZ) {
						lastX = (int)this.posX;
						lastZ = (int)this.posZ;
						stillCounter = 0;
					}
					else {
						stillCounter++;
					}

					if (stillCounter >= 5) {
						stillCounter = 0;

						//Find direction of collector
						double xd = this.collectorX - this.posX;
						double zd = this.collectorZ - this.posZ;
						double deltaX = Math.sin(Math.atan2(xd,zd));
						double deltaZ = Math.cos(Math.atan2(xd, zd));
						int directionX = (int)(deltaX * 1.9D);
						int directionZ = (int)(deltaZ * 1.9D);

						System.out.println("DirectionX/Y " + directionX + " " + directionZ);

						//If block is diagonal, target adjacent block instead
						if (directionX != 0 && directionZ != 0)
						{
							int random = this.rand.nextInt(2);
							if (random == 0)
								directionX = 0;
							else
								directionZ = 0;
						}
						System.out.println("DirectionX/Y " + directionX + " " + directionZ);

						System.out.println("posZ " + (int)this.posZ);
						System.out.println((int)this.posZ + directionZ);
						System.out.println("posX " + (int)this.posX);

						System.out.println(removingBlock);
						removingX = (int)this.posX + directionX;
						removingY = (int)this.posY;
						removingZ = (int)this.posZ + directionZ;

						//Casting to an int throws negative coordinates off by 1
						if (this.posX < 0)
							removingX = removingX - 1;
						if (this.posZ < 0)
							removingZ = removingZ - 1;


						System.out.println("Standing still " + removingX + " " + removingZ);

						//Make sure it's not an air block, begin removing block, datawatcher to start animation
						if (!this.worldObj.isAirBlock(removingX, removingY, removingZ) || !this.worldObj.isAirBlock(removingX, removingY + 1, removingZ)){
							removingBlock = true;
							blockRemovalCounter = 0;
							this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
						}
					}
				}
			}
		}
	}
	
	@Override
	public void setAttackTarget(EntityLivingBase entity)
	{
	}
	
	@Override
    protected Entity findPlayerToAttack()
    {
        return null;
    }
	
	
	@Override
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
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.699D);
		// Default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
    }
        
    public boolean getRemovingBlock(){
    	return this.dataWatcher.getWatchableObjectByte(16) == 1;
    }
    
    @Override
    protected boolean isMovementCeased()
    {
        return removingBlock;
    }
    
}
