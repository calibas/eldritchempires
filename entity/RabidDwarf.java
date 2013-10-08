package eldritchempires.entity;

import eldritchempires.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class RabidDwarf extends EntityMob{

	int[] closestNode = new int[3];
	int shortestDistance = 200;
	private PathEntity path;
	public boolean attacking = false;
	public int collectorX;
	public int collectorY;
	public int collectorZ;
	public String type = "normal";
	private int lastX = 0;
	private int lastZ = 0;
	private int stillCounter = 0;
	public boolean removingBlock = false;
	private int blockRemovalCounter = 0;
	private int removingX;
	private int removingY;
	private int removingZ;
	
	public RabidDwarf(World par1World) {
		super(par1World);
		this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.setCurrentItemOrArmor(0, new ItemStack(Item.pickaxeStone));

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
      
	  if (this.rand.nextInt(10) == 0 && !this.worldObj.isRemote) {
//		path = this.worldObj.getEntityPathToXYZ(this, 1, getFirstUncoveredBlockHeight(1, 1), 1, 40F, true, true, false, false);
//		setPathToEntity(path);
		int targetX = 1;
		int targetY = 1;
		
		if (removingBlock)
		{
//    		path = this.worldObj.getEntityPathToXYZ(this, removingX, removingY, removingZ, 40F, true, true, false, false);
//    		setPathToEntity(path);
    		System.out.println(removingBlock);
    		blockRemovalCounter++;
    		if (this.getDistance(removingX + 0.5D, removingY - 0.5D, removingZ + 0.5D) > 2.0D) {
    			removingBlock = false;
    			this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
    		}
    		
    		if (blockRemovalCounter > 10) {
    			if (!this.worldObj.isAirBlock(removingX, removingY, removingZ)) {
    				int blockID = this.worldObj.getBlockId(removingX, removingY, removingZ);
    				this.worldObj.destroyBlock(removingX, removingY, removingZ, true);
//    				this.worldObj.setBlockToAir(removingX, removingY, removingZ);
//    				ItemStack droppedItem = new ItemStack(blockID, 1, 0);
//    				EntityItem entityitem = new EntityItem(this.worldObj, (double)removingX + 0.5D, (double)removingY + 0.5D, (double)removingZ + 0.5D, droppedItem);
//    				entityitem.delayBeforeCanPickup = 10;
//    				this.worldObj.spawnEntityInWorld(entityitem);
    			}
    			else if (!this.worldObj.isAirBlock(removingX, removingY + 1, removingZ)) {
    				int blockID = this.worldObj.getBlockId(removingX, removingY + 1, removingZ);
    				this.worldObj.destroyBlock(removingX, removingY + 1, removingZ, true);
//    				this.worldObj.setBlockToAir(removingX, removingY + 1, removingZ);
//    				ItemStack droppedItem = new ItemStack(blockID, 1, 0);
//    				EntityItem entityitem = new EntityItem(this.worldObj, (double)removingX + 0.5D, (double)removingY + 1.5D, (double)removingZ + 0.5D, droppedItem);
//    				entityitem.delayBeforeCanPickup = 10;
//    				this.worldObj.spawnEntityInWorld(entityitem);
    			}
    			blockRemovalCounter = 0;
    			
    			if (this.worldObj.isAirBlock(removingX, removingY + 1, removingZ)) {
    				removingBlock = false;
    				this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
    			}
    		}
		}
		else if ((int)this.posX != lastX || (int)this.posZ != lastZ) {
			lastX = (int)this.posX;
			lastZ = (int)this.posZ;
//			removingBlock = false;
			System.out.println("Position changed " + this.posX + " " + lastX + removingBlock);
			stillCounter = 0;
		}
		else {
			stillCounter++;
			System.out.println("Position same " + stillCounter  + removingBlock);
		}
		
		if (stillCounter >= 6) {
			stillCounter = 0;
    		double xd = targetX - this.posX;
    		double zd = targetY - this.posZ;
    		double deltaX = Math.sin(Math.atan2(xd,zd));
    		double deltaZ = Math.cos(Math.atan2(xd, zd));
    		int directionX = (int)(deltaX * 1.9D);
    		int directionZ = (int)(deltaZ * 1.9D);
    		System.out.println("Standing still " + directionX + " " + directionZ);
    		removingX = (int)this.posX + directionX;
    		removingY = (int)this.posY;
    		removingZ = (int)this.posZ + directionZ;
    		if (!this.worldObj.isAirBlock(removingX, removingY, removingZ) || !this.worldObj.isAirBlock(removingX, removingY + 1, removingZ)){
    			removingBlock = true;
    			blockRemovalCounter = 0;
    			this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
    		}
		}
	    }
		
		
        if (this.rand.nextInt(50) == 0 && !removingBlock)
        {
    		path = this.worldObj.getEntityPathToXYZ(this, 1, getFirstUncoveredBlockHeight(1, 1), 1, 40F, true, true, false, false);
    		setPathToEntity(path);
//        	int i;
//        	int k;
//			if (attacking == false)
//			{
//			  for (i = (int)this.posX - 10; i < (int)this.posX + 10; i++)
//        	  {
//        		for (k = (int)this.posZ - 10; k < (int)this.posZ + 10; k++)
//        		{
//        			if (this.worldObj.getFirstUncoveredBlock(i, k) == 252)
//        			{
//        				int distance = (int) (Math.pow((i - (int)this.posX), 2) + Math.pow((k - (int)this.posZ), 2));
//        				if (shortestDistance == 200 || distance < shortestDistance)
//        				{
//        					shortestDistance = distance;
//        					closestNode[0] = i;
//        					closestNode[1] = getFirstUncoveredBlockHeight(i,k);
//        					closestNode[2] = k;
//        				}
//
//        			}
//        		}
//        	  }
//        	  if (shortestDistance != 200)
//        	  {
//				path = this.worldObj.getEntityPathToXYZ(this, closestNode[0], closestNode[1], closestNode[2], 40F, true, true, false, false);
//        		setPathToEntity(path);
//        	  }
//        	  shortestDistance = 200;
//			}
        	
//        	if (attacking == true)
//            {
//        		double xd = collectorX - this.posX;
//        		double yd = collectorY - this.posY;
//        		double zd = collectorZ - this.posZ;
//        		double distance = Math.sqrt(xd*xd + yd*yd + zd*zd);
//        		if (distance > 40.0D)
//        		{
//        			double deltaX = Math.sin(Math.atan2(xd,zd));
//        			double deltaZ = Math.cos(Math.atan2(xd, zd));
//        			int pathX = (int)(this.posX + (20*deltaX));
//        			int pathZ = (int)(this.posZ + (20*deltaZ));
//        			path = this.worldObj.getEntityPathToXYZ(this, pathX, getFirstUncoveredBlockHeight(pathX, pathZ), pathZ, 40F, true, true, false, false);
//        			setPathToEntity(path);
//        	//		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Pathing to " + pathX + " " + pathZ);
//        			
//        		}
//        		else
//        		{
////            		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Attacking!");
//        			path = this.worldObj.getEntityPathToXYZ(this, collectorX, collectorY, collectorZ, 40F, true, true, false, false);
//        			setPathToEntity(path);
//        	//		Minecraft.getMinecraft().thePlayer.addChatMessage("Zoblin: Pathing to " + nodeX + " " + nodeZ);
//        		}
//            }
        }
        
        

    }
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
		// Default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		 // Default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(80.0D);
		// Default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.699D);
		// Default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
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
    
    public boolean getRemovingBlock(){
    	return this.dataWatcher.getWatchableObjectByte(16) == 1;
    }
    
    @Override
    protected boolean isMovementCeased()
    {
        return removingBlock;
    }
    
	@Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (attacking == true)
        {
        	par1NBTTagCompound.setBoolean("Attacking", attacking);
        	par1NBTTagCompound.setInteger("AttackX", collectorX);
        	par1NBTTagCompound.setInteger("AttackY", collectorY);
        	par1NBTTagCompound.setInteger("AttackZ", collectorZ);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
	@Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("Attacking"))
        {
            attacking = par1NBTTagCompound.getBoolean("Attacking");
            collectorX = par1NBTTagCompound.getInteger("AttackX");
            collectorY = par1NBTTagCompound.getInteger("AttackY");
            collectorZ = par1NBTTagCompound.getInteger("AttackZ");
        }
        
    }
    
}
