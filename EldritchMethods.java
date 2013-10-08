package eldritchempires;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EldritchMethods {
	
	private static long messageTime = 0;

    public static int getFirstUncoveredBlockHeight(int par1, int par2, World world)
    {
        int k;
        int j = 62;
        
        if (world.isAirBlock(par1, j, par2) && world.isAirBlock(par1, j + 1, par2) && world.isAirBlock(par1, j + 2, par2))
        	j = 50;

        for (k = j; !(world.isAirBlock(par1, k + 1, par2) && world.isAirBlock(par1, k + 2, par2) && world.isAirBlock(par1, k + 3, par2)); ++k)
        {
            ;
        }

        return k;
    }
    
    public static void broadcastMessageLocal(String announce, int posX, int posY, int posZ, int announceRadius, World world)
    {
    	List<?> localPlayers = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(posX - announceRadius, posY - announceRadius, posZ - announceRadius, posX + announceRadius, posY + announceRadius, posZ + announceRadius));

    	if (localPlayers != null && !localPlayers.isEmpty()) 
    	{
    		Iterator<?> iterator = localPlayers.iterator();

    		while (iterator.hasNext()) 
    		{
    			EntityPlayer player = (EntityPlayer)iterator.next();
    			player.addChatMessage(announce);
    		}
    	}
    }
    
    public static void attackMessage(String announce, int posX, int posY, int posZ, int announceRadius, World world)
    {
    	if ((world.getWorldTime() - messageTime) < 0)
		{
			messageTime = world.getWorldTime();
		}
    	
//    	messageTime = world.getWorldTime();
    	if (world.getWorldTime() - messageTime > 50)
    	{
    		broadcastMessageLocal(announce, posX, posY, posZ, announceRadius, world);
    		messageTime = world.getWorldTime();
    	}
    }
    
    public static int[] createPortal(String type, int x, int y, int z, World world)
    {
//    	int x = posX;
//    	int y = 0;
//    	int z = posZ;
    	Random generator = new Random();
    	int randomInt = generator.nextInt(4);
//    	broadcastMessageLocal("randomInt: " + randomInt, x, y, z, 100, world);
    	
    	if (randomInt == 0)
    	{
    		x = x + 40 + generator.nextInt(21);
    		z = z - 10 + generator.nextInt(21);
    	}
    	if (randomInt == 1)
    	{
    		x = x - 40 - generator.nextInt(21);
    		z = z - 10 + generator.nextInt(21);
    	}
    	if (randomInt == 2)
    	{
    		x = x - 10 + generator.nextInt(21);
    		z = z + 40 + generator.nextInt(21);
    	}
    	if (randomInt == 3)
    	{
    		x = x - 10 + generator.nextInt(21);
    		z = z - 40 - generator.nextInt(21);
    	}
    	y = getFirstUncoveredBlockHeight(x, z, world);
    	if (!(world.getBlockId(x, y, z) == Block.snow.blockID || world.getBlockId(x, y, z) == Block.tallGrass.blockID || world.getBlockId(x, y, z) == Block.vine.blockID))
    	{
    		y = y + 1;
    	}
    		
    	int[] location = {x, y, z};
    	world.setBlock(location[0], location[1], location [2], Registration.portal.blockID, 0, 2);

    	return location;
    }
	
	
}
