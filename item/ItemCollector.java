package eldritchempires.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCollector extends ItemBlock {
    public ItemCollector(int par1)
   {
         super(par1);
         setHasSubtypes(true);
   }
  
   public String getUnlocalizedName(ItemStack itemstack)
   {
         String name = "";
         switch(itemstack.getItemDamage())
         {
                case 0:
                {
                       name = "world";
                       break;
                }
                case 1:
                {
                       name = "nether";
                       break;
                }
                default: name = "node";
         }
         return getUnlocalizedName() + "." + name;
   }
  
   public int getMetadata(int par1)
   {
         return par1;
   }
	
}
