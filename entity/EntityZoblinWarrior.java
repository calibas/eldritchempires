package eldritchempires.entity;

import eldritchempires.EldritchMethods;
import eldritchempires.EldritchWorldData;
import eldritchempires.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntityZoblinWarrior extends EntityAttacker{

	public EntityZoblinWarrior(World par1World) {
		super(par1World);

		this.setCurrentItemOrArmor(0, new ItemStack(Item.swordIron));		
		this.collectorDamage = 2;

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
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.3D);
		// Default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.599D);
		// Default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
    }
    
}
