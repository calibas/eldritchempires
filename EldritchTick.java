package eldritchempires;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class EldritchTick implements ITickHandler{

	public static EldritchWorldData worldData = null;
	private final Minecraft mc;
	
	public EldritchTick() {
		mc = Minecraft.getMinecraft();
	}

	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.WORLDLOAD))){
			System.out.println("tickStart TickType.WORLDLOAD");
//			World world = null;
			final WorldServer world = (WorldServer) tickData[0];
			for(int i = 0; i < tickData.length; i++)
			{
//				if(mc.theWorld != null)
//				{
//					world = mc.theWorld;
//					System.out.println("mc.theWorld != null");
//				}
				
				if(world.provider instanceof WorldProviderSurface)
				{
					System.out.println("world instanceof Surface");
				}
		
			}
	    
//			if(world == null){
//				System.out.println("world == null");
//	            return;
//	        }
	         
	         worldData = EldritchWorldData.forWorld(world);
	         worldData.markDirty();
		}
		
		if(type.equals(EnumSet.of(TickType.WORLD))){
			System.out.println("tickStart TickType.WORLD");
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLDLOAD);
	}

	@Override
	public String getLabel() {
		return "EldritchTick";
	}

}
