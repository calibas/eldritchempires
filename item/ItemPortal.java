package eldritchempires.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPortal extends ItemBlock {
	
    public ItemPortal(int par1)
   {
         super(par1);
         setHasSubtypes(true);
   }
  
   @Override
   public String getUnlocalizedName(ItemStack itemstack)
   {
         return "itemPortal";
   }
  
   @Override
   public int getMetadata(int par1)
   {
         return par1;
   }
	
}
