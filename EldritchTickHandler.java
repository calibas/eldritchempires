package eldritchempires;

import java.util.EnumSet;

import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class EldritchTickHandler implements ITickHandler {
   

   public static EldritchWorldData worldData = null;
   
   @Override
   public void tickStart(EnumSet<TickType> type, Object... tickData) {
      if(type.equals(EnumSet.of(TickType.WORLDLOAD))){
         World world = null;
         for(int i = 0; i < tickData.length; i++)
//            if(tickData instanceof World)
//               world = (World)tickData;
         if(world == null){
            return;
         }
         
//         worldData = EldritchWorldData.forWorld(world);
         worldData.markDirty();
      }
   }

   @Override
   public void tickEnd(EnumSet<TickType> type, Object... tickData) {
      
   }

   @Override
   public EnumSet<TickType> ticks() {
      // TODO Auto-generated method stub
      return EnumSet.of(TickType.WORLDLOAD);
   }

   @Override
   public String getLabel() {
      // TODO Auto-generated method stub
      return "Eldritch_Ticker";
   }
   
}