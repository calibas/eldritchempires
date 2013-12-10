//package eldritchempires;
//
//import java.util.EnumSet;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.world.World;
//import net.minecraft.world.WorldProviderSurface;
//import net.minecraft.world.WorldServer;
//
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class EldritchTick implements ITickHandler{
//
//	public static EldritchWorldData worldData = null;
//	
//	public EldritchTick() {}
//	
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {
//		if(type.equals(EnumSet.of(TickType.WORLDLOAD))){
//
//			final WorldServer world = (WorldServer) tickData[0];
//	         
//	         worldData = EldritchWorldData.forWorld(world);
//	         worldData.markDirty();
//		}
//	}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}
//
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of(TickType.WORLDLOAD);
//	}
//
//	@Override
//	public String getLabel() {
//		return "EldritchTick";
//	}
//
//}
