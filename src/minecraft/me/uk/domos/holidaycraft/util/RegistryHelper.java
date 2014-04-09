package me.uk.domos.holidaycraft.util;

import java.io.File;

import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.block.BlockBauble;
import me.uk.domos.holidaycraft.block.BlockChineseLantern;
import me.uk.domos.holidaycraft.block.BlockGift;
import me.uk.domos.holidaycraft.block.BlockRibbon;
import me.uk.domos.holidaycraft.block.BlockWreath;
import me.uk.domos.holidaycraft.gui.GuiHandler;
import me.uk.domos.holidaycraft.item.ItemBlockBauble;
import me.uk.domos.holidaycraft.item.ItemFood;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLantern;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import me.uk.domos.holidaycraft.tileentity.TileEntityRibbon;
import me.uk.domos.holidaycraft.tileentity.TileEntityWreath;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RegistryHelper {
	
	private static final String[] baubleNames = { 
		"Black Bauble","Red Bauble","Green Bauble","Brown Bauble","Blue Bauble","Purple Bauble",
		"Cyan Bauble","Light Grey Bauble","Grey Bauble","Pink Bauble","Lime Bauble","Yellow Bauble",
		"Light Blue Bauble","Magenta Bauble","Orange Bauble","White Bauble"
	};
	private static final String[] dyeNames = { 
		"dyeBlack","dyeRed","dyeGreen","dyeBrown","dyeBlue","dyePurple",
		"dyeCyan","dyeLightGrey","dyeGrey","dyePink","dyeLime","dyeYellow",
		"dyeLightBlue","dyeMagenta","dyeOrange","dyeWhite"
	};
	
	public static CreativeTabs tabHoliday = new CreativeTabs("tabHoliday") {
	    public ItemStack getIconItemStack() {
	            return new ItemStack(BlockGift.instance, 1, 0);
	    }
	};
	
	private static int baubleID;
	private static int giftID;
	private static int ribbonID;
	private static int foodID;
	private static int lanternID;
	private static int wreathID;
	
	public static void registerNames(){
		LanguageRegistry.addName(BlockGift.instance, "Gift");
		LanguageRegistry.addName(BlockChineseLantern.instance, "Chinese Lantern");
		LanguageRegistry.addName(BlockWreath.instance, "Wreath");
		LanguageRegistry.addName(new ItemStack(ItemFood.instance,1,0), "Christmas Cookie");
		LanguageRegistry.addName(new ItemStack(ItemFood.instance,1,1), "Easter Egg");
		LanguageRegistry.addName(new ItemStack(ItemFood.instance,1,2), "Pancake");
		LanguageRegistry.addName(new ItemStack(ItemFood.instance,1,3), "Candy Cane");
		LanguageRegistry.addName(BlockRibbon.instance, "Ribbon");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabHoliday", "en_US", "HolidayCraft");
		for (int ix = 0; ix < 16; ix++) {
			ItemStack baubleStack = new ItemStack(BlockBauble.instance, 1, ix);
			LanguageRegistry.addName(baubleStack, baubleNames[baubleStack.getItemDamage()]);
		}
	}
	
	public static void registerContent(){

		Configuration config = new Configuration(new File("config/holidaycraft.cfg"));
		config.load();
		baubleID = config.get("Blocks", "Bauble", 500).getInt();
		giftID = config.get("Blocks", "Gift", 501).getInt();
		lanternID = config.get("Blocks", "Chinese_Lantern", 502).getInt();
		wreathID = config.get("Blocks", "Wreath", 503).getInt();
		ribbonID = config.get("Blocks", "Ribbon", 504).getInt();
		foodID = config.get("Items", "Food", 6001).getInt();
		config.save();

		BlockBauble.instance = new BlockBauble(baubleID);
		BlockGift.instance = new BlockGift(giftID);
		BlockChineseLantern.instance = new BlockChineseLantern(lanternID);
		BlockWreath.instance = new BlockWreath(wreathID);
		BlockRibbon.instance = new BlockRibbon(ribbonID);
		
		ItemFood.instance = new ItemFood(foodID - 256, 4, 0.3F, false);
		
		GameRegistry.registerBlock(BlockBauble.instance, ItemBlockBauble.class, "Bauble");
		registerBlockIfNotNull(BlockGift.instance, "Gift");
		GameRegistry.registerBlock(BlockChineseLantern.instance, "Chinese Lantern");
		GameRegistry.registerBlock(BlockWreath.instance, "Wreath");
		GameRegistry.registerBlock(BlockRibbon.instance, "Ribbon");
		GameRegistry.registerItem(ItemFood.instance, "Food", HolidayCraft.modid);
		
		GameRegistry.registerTileEntity(TileEntityBauble.class, "holidaycraft.bauble");
		GameRegistry.registerTileEntity(TileEntityGift.class, "holidaycraft.gift");
		GameRegistry.registerTileEntity(TileEntityChineseLantern.class, "holidaycraft.lantern");
		GameRegistry.registerTileEntity(TileEntityWreath.class, "holidaycraft.wreath");
		GameRegistry.registerTileEntity(TileEntityRibbon.class, "holidaycraft.ribbon");
	}
	
	public static void registerRecipes(){

		ItemStack glassStack = new ItemStack(Block.glass);
		ItemStack ribbonStack = new ItemStack(BlockRibbon.instance);
		ItemStack stringStack = new ItemStack(Item.silk);
		ItemStack woolStack = new ItemStack(Block.cloth);
		ItemStack redWoolStack = new ItemStack(Block.cloth,1,14);
		ItemStack paperStack = new ItemStack(Item.paper);
		ItemStack chestStack = new ItemStack(Block.chest);
		ItemStack eggStack = new ItemStack(Item.egg);
		ItemStack cocoStack = new ItemStack(Item.dyePowder, 1, 3);
		ItemStack cookieStack = new ItemStack(Item.cookie);
		ItemStack sugarStack = new ItemStack(Item.sugar);
		ItemStack milkStack = new ItemStack(Item.bucketMilk);
		ItemStack waterStack = new ItemStack(Item.bucketWater);
		ItemStack glowStack = new ItemStack(Block.glowStone);

		GameRegistry.addRecipe(new ItemStack(BlockRibbon.instance,16), "  w", "www", "w  ", 'w', woolStack); //Ribbon
		GameRegistry.addRecipe(new ItemStack(BlockChineseLantern.instance,16), "wgw", "wgw", "wgw", 'w', redWoolStack, 'g', glowStack); //Chinese Lantern
		GameRegistry.addRecipe(new ItemStack(BlockGift.instance,1), "prp", "rcr", "prp", 'p', paperStack, 'r', ribbonStack, 'c', chestStack); //Gift
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockWreath.instance, 3), "lrl", "r r", "lrl",'r', ribbonStack, 'l', "treeLeaves")); //Wreath
		GameRegistry.addShapelessRecipe(new ItemStack(ItemFood.instance,1,0), cookieStack, sugarStack, sugarStack, sugarStack); //Christmas Cookie
		GameRegistry.addRecipe(new ItemStack(ItemFood.instance,1,1), "pcp", "rer", "pcp", 'p', paperStack, 'r', ribbonStack, 'c', cocoStack, 'e', eggStack); //Easter Egg
		GameRegistry.addShapelessRecipe(new ItemStack(ItemFood.instance,8,2), milkStack, eggStack, sugarStack, waterStack); //Pancake
		GameRegistry.addRecipe(new ItemStack(ItemFood.instance,1,3), "sss", "  s", "  s", 's', sugarStack); //Candy Cane
		
		//Baubles
		for (int i = 0; i < 16; i++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockBauble.instance, 4, i), " s ", "rgr", " d ", 'g', glassStack, 'r', ribbonStack, 's', stringStack, 'd', dyeNames[i])); //vanilla glass
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockBauble.instance, 4, i), " s ", "rgr", " d ", 'g', "blockGlass", 'r', ribbonStack, 's', stringStack, 'd', dyeNames[i])); //oredict glass
		}
	}
	
	public static void registerGUI(){
		NetworkRegistry.instance().registerGuiHandler(HolidayCraft.class, new GuiHandler());
	}
		
	private static void registerBlockIfNotNull(Block block, Class<? extends ItemBlock> blockClass, String name){
		if (block.blockID != -1){
			GameRegistry.registerBlock(block, blockClass, name);
		}
	}
	
	private static void registerBlockIfNotNull(Block block, String name){
		if (block.blockID != -1){
			GameRegistry.registerBlock(block, name);
		}
	}
}

