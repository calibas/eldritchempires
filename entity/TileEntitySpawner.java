package eldritchempires.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySpawner extends TileEntity
{
	private int i = 0;
	private double randX = 0.0D;
	private double randZ = 0.0D;
	
	public TileEntitySpawner()
    {

    }
	
//	public void updateEntity() 
//	{
//		if (i < 200)
//		{
//			i++;
//		}
//		else
//		{
//			int k = this.worldObj.rand.nextInt(4);
//			if (k == 0)
//			{
//				randX = -30.5D;
//			}
//			
//			if (k == 1)
//			{
//				randX = 30.5D;
//			}
//			
//			if (k == 2)
//			{
//				randZ = -30.5D;
//			}
//			
//			if (k == 3)
//			{
//				randZ = 30.5D;
//			}
//			
//			i = 0;
//			if (!this.worldObj.isRemote && this.blockMetadata == 0)
//			{
//				for (int j = 0;j < 2; j++)
//				{
//					Zoblin zoblin = new Zoblin(this.worldObj);
//					zoblin.setLocationAndAngles((double)this.xCoord + randX, (double)zoblin.getFirstUncoveredBlockHeight(this.xCoord + (int)randX, this.zCoord + (int)randZ) + 1.0D, (double)this.zCoord + randZ, 0.0F, 0.0F);
//					zoblin.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.699D);
//					zoblin.attacking = true;
//					zoblin.nodex = this.xCoord;
//					zoblin.nodey = this.yCoord;
//					zoblin.nodez = this.zCoord;
//					this.worldObj.spawnEntityInWorld(zoblin);
//				}
//				ZoblinBomber zoblinBomber = new ZoblinBomber(this.worldObj);
//				zoblinBomber.setLocationAndAngles((double)this.xCoord + randX, (double)zoblinBomber.getFirstUncoveredBlockHeight(this.xCoord + (int)randX, this.zCoord + (int)randZ) + 1.0D, (double)this.zCoord + randZ, 0.0F, 0.0F);
//				zoblinBomber.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.699D);
//				zoblinBomber.attacking = true;
//				zoblinBomber.nodex = this.xCoord;
//				zoblinBomber.nodey = this.yCoord;
//				zoblinBomber.nodez = this.zCoord;
//				this.worldObj.spawnEntityInWorld(zoblinBomber);
//			}
//			
//			randX = 0.0D;
//			randZ = 0.0D;
//		}
//	}
}
