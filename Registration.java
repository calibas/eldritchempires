package eldritchempires;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import eldritchempires.block.BlockMarker;
import eldritchempires.block.BlockNode;
import eldritchempires.block.BlockSpawner;
import eldritchempires.entity.MagicEssence;
import eldritchempires.entity.StoneArcher;
import eldritchempires.entity.TileEntityNode;
import eldritchempires.entity.TileEntitySpawner;
import eldritchempires.entity.Zoblin;
import eldritchempires.entity.ZoblinBomber;
import eldritchempires.item.ItemCondensedEssence;
import eldritchempires.item.ItemGolemPart;
import eldritchempires.item.ItemMarker;
import eldritchempires.item.ItemNode;
import eldritchempires.item.ItemSpawner;

public class Registration {

	public static Block node;
	public static Block spawner;
	public static Block marker;
	public static Item golemPart;
	public static Item condensedEssence;
//	public static WorldSavedDataEE worldSavedData;
	
	public static void registration()
	{
		// Add Blocks
		node = new BlockNode(252, Material.wood).setUnlocalizedName("node");
		GameRegistry.registerBlock(node, ItemNode.class, EldritchEmpires.modid + (node.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(node, 1, 0), "Player Node");
		LanguageRegistry.addName(new ItemStack(node, 1, 1), "Zoblin Node");		
		
		spawner = new BlockSpawner(253, Material.rock).setUnlocalizedName("spawner");
		GameRegistry.registerBlock(spawner, ItemSpawner.class, EldritchEmpires.modid + (spawner.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Stone Archer Spawner");
		LanguageRegistry.addName(new ItemStack(spawner, 1, 1), "Placeholder Spawner");
	
		marker = new BlockMarker(254).setUnlocalizedName("marker");
		GameRegistry.registerBlock(marker, ItemMarker.class, EldritchEmpires.modid + (marker.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(marker, 1, 0), "Marker");
		LanguageRegistry.addName(new ItemStack(marker, 1, 1), "Placeholder Marker");
		
		// Add Entities
		registerEntity(Zoblin.class, "Zoblin");
		registerEntity(ZoblinBomber.class, "ZoblinBomber");
		registerEntity(StoneArcher.class, "StoneArcher");
		registerEntity(MagicEssence.class, "MagicEssence");
		
		// Add TileEntities
		GameRegistry.registerTileEntity(TileEntityNode.class, "NodeEntity");
		GameRegistry.registerTileEntity(TileEntitySpawner.class, "SpawnerEntity");
		
		// Add Items
//		Items.addItems();
		golemPart = new ItemGolemPart(5008).setUnlocalizedName("golemPart").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(golemPart, "Golem Part");
		LanguageRegistry.addName(golemPart, "Golem Part");
		
		condensedEssence = new ItemCondensedEssence(5009).setUnlocalizedName("condensedEssence").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(golemPart, "Condensed Essence");
		LanguageRegistry.addName(golemPart, "Condensed Essence");
		
		// Add Recipes
		GameRegistry.addRecipe(new ItemStack(node, 1, 0), new Object[] { "SPS", "SPS", " S ", 'S', Item.stick, 'P', Item.paper});
		GameRegistry.addRecipe(new ItemStack(spawner, 1, 0), new Object[] { " G ", "GBG", "GGG", 'G', golemPart, 'B', Item.bow});
		GameRegistry.addRecipe(new ItemStack(golemPart, 1), new Object[] { "CCC", "III", "CCC", 'C', new ItemStack(Block.cobblestone, 1), 'I', Item.ingotIron});
		
		// Add Event Handler
		MinecraftForge.EVENT_BUS.register(new EldritchEvents());
		
		// Tick Handler
		TickRegistry.registerTickHandler(new EldritchTick(), Side.SERVER);
		
		// Add Spawns
//		EntityRegistry.addSpawn(HoneyBee.class, 15, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.taiga });
		
		// Add NBT Tag Compound
//		eldritchNBTCompound = new NBTTagCompound("EldritchEmpires");
//		eldritchNBTCompound.setInteger("Test", 1);
		

	}

	public static void registerEntity(Class entityClass, String entityName)
	{
	    int entityID = EntityRegistry.findGlobalUniqueEntityId();
	    LanguageRegistry.instance().addStringLocalization("entity." + entityName + ".name", "en_US", entityName);

	    EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
	    EntityRegistry.registerModEntity(entityClass, entityName, entityID, EldritchEmpires.instance, 64, 1, true);
	    EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityEggInfo(entityID, (16777215 - (10 * entityID)), 0));
	}
}
