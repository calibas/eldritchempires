package eldritchempires;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EldritchMethods {

    public int getFirstUncoveredBlockHeight(int par1, int par2, World world)
    {
        int k;

        for (k = 63; !world.isAirBlock(par1, k + 1, par2); ++k)
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
	
	
}
