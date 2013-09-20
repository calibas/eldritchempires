package eldritchempires;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
 
public class EldritchWorldData extends WorldSavedData {
       
        private static int test = 0;
        private static boolean markerSet = false;
        private static int markerX = 0;
        private static int markerY = 0;
        private static int markerZ = 0;
        private static boolean nodeSet = false;
        private static int nodeX = 0;
        private static int nodeY = 0;
        private static int nodeZ = 0;
        
        public static String name = "EldritchData";
    	
        public EldritchWorldData() {
                super(name);
        }
        
        public EldritchWorldData(String key)
        {
        	super (name);
        }
 
        public static EldritchWorldData forWorld(World world) {
          EldritchWorldData data = (EldritchWorldData)world.perWorldStorage.loadData(EldritchWorldData.class, name);
          if (data == null) {
             data = new EldritchWorldData();
             world.perWorldStorage.setData(name, data);
          }
          data.markDirty();
          return data;
       }
        
        @Override
        public void readFromNBT(NBTTagCompound nbt){
               test = nbt.getInteger("test");
               markerX = nbt.getInteger("markerX");
               markerY = nbt.getInteger("markerY");
               markerZ = nbt.getInteger("markerZ");
               markerSet = nbt.getBoolean("markerSet");
               nodeX = nbt.getInteger("nodeX");
               nodeY = nbt.getInteger("nodeY");
               nodeZ = nbt.getInteger("nodeZ");
               nodeSet = nbt.getBoolean("nodeSet");
        }
 
        @Override
        public void writeToNBT(NBTTagCompound nbt){
                nbt.setInteger("test", test);
                nbt.setInteger("markerX", markerX);
                nbt.setInteger("markerY", markerY);
                nbt.setInteger("markerZ", markerZ);
                nbt.setBoolean("markerSet", markerSet);
                nbt.setInteger("nodeX", nodeX);
                nbt.setInteger("nodeY", nodeY);
                nbt.setInteger("nodeZ", nodeZ);
                nbt.setBoolean("nodeSet", nodeSet);
        }
       
        public int getTest(){
                return test;
        }
       
        public void setTest(int newtest){
                test = newtest;
                markDirty();
        }
        
        public void setMarker(int par1, int par2, int par3)
        {
        	markerX = par1;
        	markerY = par2;
        	markerZ = par3;
        	markerSet = true;
        	markDirty();
        }
        
        public void unSetMarker()
        {
        	markerSet = false;
        	markDirty();
        }
        
        public int getMarkerX()
        {
        	return markerX;
        }
        
        public int getMarkerY()
        {
        	return markerY;
        }
        
        public int getMarkerZ()
        {
        	return markerZ;
        }
        
        public boolean checkMarker()
        {
        	return markerSet;
        }

        public void setNode(int par1, int par2, int par3)
        {
        	nodeX = par1;
        	nodeY = par2;
        	nodeZ = par3;
        	nodeSet = true;
        	markDirty();
        }
        
        public void unSetNode()
        {
        	nodeSet = false;
        	markDirty();
        }
        
        public int getNodeX()
        {
        	return nodeX;
        }
        
        public int getNodeY()
        {
        	return nodeY;
        }
        
        public int getNodeZ()
        {
        	return nodeZ;
        }
        
        public boolean checkNode()
        {
        	return nodeSet;
        }
}

