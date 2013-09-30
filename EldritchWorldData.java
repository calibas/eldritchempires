package eldritchempires;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
 
public class EldritchWorldData extends WorldSavedData {
       
        private static boolean portalSet = false;
        private static int portalX = 0;
        private static int portalY = 0;
        private static int portalZ = 0;
        private static boolean collectorSet = false;
        private static int collectorX = 0;
        private static int collectorY = 0;
        private static int collectorZ = 0;
        private static boolean waveActive = false;
        private static int wave = 0;
        
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
               portalX = nbt.getInteger("portalX");
               portalY = nbt.getInteger("portalY");
               portalZ = nbt.getInteger("portalZ");
               portalSet = nbt.getBoolean("portalSet");
               collectorX = nbt.getInteger("collectorX");
               collectorY = nbt.getInteger("collectorY");
               collectorZ = nbt.getInteger("collectorZ");
               collectorSet = nbt.getBoolean("collectorSet");
               waveActive = nbt.getBoolean("waveActive");
               wave = nbt.getInteger("wave");
        }
 
        @Override
        public void writeToNBT(NBTTagCompound nbt){
                nbt.setInteger("portalX", portalX);
                nbt.setInteger("portalY", portalY);
                nbt.setInteger("portalZ", portalZ);
                nbt.setBoolean("portalSet", portalSet);
                nbt.setInteger("collectorX", collectorX);
                nbt.setInteger("collectorY", collectorY);
                nbt.setInteger("collectorZ", collectorZ);
                nbt.setBoolean("collectorSet", collectorSet);
                nbt.setBoolean("waveActive", waveActive);
                nbt.setInteger("wave", wave);
        }
        
        public void setActiveWave(boolean par1)
        {
        	if (par1 == false)
        	{
        		wave = 0;
        	}
        	waveActive = par1;
        }
        
        public boolean isWaveActive()
        {
        	return waveActive;
        }
        
        public int getWave()
        {
        	return wave;
        }
        
        public void setWave(int newWave)
        {
        	wave = newWave;
        }
       
        public void setPortal(int par1, int par2, int par3)
        {
        	portalX = par1;
        	portalY = par2;
        	portalZ = par3;
        	portalSet = true;
        	markDirty();
        }
        
        public void unSetPortal()
        {
        	portalSet = false;
        	markDirty();
        }
        
        public int getPortalX()
        {
        	if (checkPortal())
        		return portalX;
        	else
        		return 0;
        }
        
        public int getPortalY()
        {
        	if (checkPortal())
        		return portalY;
        	else
        		return 0;
        }
        
        public int getPortalZ()
        {
        	if (checkPortal())
        		return portalZ;
        	else
        		return 0;
        }
        
        public boolean checkPortal()
        {
        	return portalSet;
        }

        public void setCollector(int par1, int par2, int par3)
        {
        	collectorX = par1;
        	collectorY = par2;
        	collectorZ = par3;
        	collectorSet = true;
        	markDirty();
        }
        
        public void unSetCollector()
        {
        	collectorSet = false;
        	markDirty();
        }
        
        public int getCollectorX()
        {
        	if (checkCollector())
        		return collectorX;
        	else
        		return 0;
        }
        
        public int getCollectorY()
        {
        	if (checkCollector())
        		return collectorY;
        	else
        		return 0;
        }
        
        public int getCollectorZ()
        {
        	if (checkCollector())
        		return collectorZ;
        	else
        		return 0;
        }
        
        public boolean checkCollector()
        {
        	return collectorSet;
        }
}

