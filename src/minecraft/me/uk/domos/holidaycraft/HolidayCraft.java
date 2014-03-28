package me.uk.domos.holidaycraft;

import java.io.File;

import me.uk.domos.holidaycraft.block.BlockBauble;
import me.uk.domos.holidaycraft.block.BlockGift;
import me.uk.domos.holidaycraft.client.ClientProxy;
import me.uk.domos.holidaycraft.common.CommonProxy;
import me.uk.domos.holidaycraft.gui.GuiHandler;
import me.uk.domos.holidaycraft.item.ItemBlockBauble;
import me.uk.domos.holidaycraft.item.ItemRibbon;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "holidaycraft", name = "HolidayCraft", version = "0.1")
@NetworkMod(channels = "holidaycraft", packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = false)

public class HolidayCraft {
	
	public static final String modid = "holidaycraft";
    @Instance(modid)
    public static HolidayCraft instance;
	
	public static int baubleID;
	public static int giftID;
	public static int ribbonID;
	
	private static final String[] baubleNames = { 
		"Black Bauble","Red Bauble","Green Bauble","Brown Bauble","Blue Bauble","Purple Bauble",
		"Cyan Bauble","Light Grey Bauble","Grey Bauble","Pink Bauble","Lime Bauble","Yellow Bauble",
		"Light Blue Bauble","Magenta Bauble","Orange Bauble","White Bauble"
	};
	
    public static CreativeTabs tabHoliday = new CreativeTabs("tabHoliday") {
        public ItemStack getIconItemStack() {
                return new ItemStack(BlockGift.instance, 1, 0);
        }
    };
	
	@SidedProxy(clientSide = "me.uk.domos.holidaycraft.client.ClientProxy", serverSide = "me.uk.domos.holidaycraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		Configuration config = new Configuration(new File("config/holidaycraft.cfg"));
		config.load();
		baubleID = config.get("Blocks", "Bauble", 500).getInt();
		giftID = config.get("Blocks", "Gift", 501).getInt();
		ribbonID = config.get("Items", "Ribbon", 6000).getInt();
		config.save();
		BlockBauble.instance = new BlockBauble(baubleID);
		BlockGift.instance = new BlockGift(giftID);
		ItemRibbon.instance = new ItemRibbon(ribbonID - 256);
		GameRegistry.registerBlock(BlockBauble.instance, ItemBlockBauble.class);
		GameRegistry.registerBlock(BlockGift.instance, "Gift");
		GameRegistry.registerTileEntity(TileEntityBauble.class, "holidaycraft.bauble");
		GameRegistry.registerTileEntity(TileEntityGift.class, "holidaycraft.gift");
		GameRegistry.registerItem(ItemRibbon.instance, "Ribbon", modid);
		LanguageRegistry.addName(BlockGift.instance, "Gift");
		LanguageRegistry.addName(ItemRibbon.instance, "Ribbon");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabHoliday", "en_US", "HolidayCraft");
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		proxy.registerRenderInformation();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		ItemStack glassStack = new ItemStack(Block.glass);
		ItemStack ribbonStack = new ItemStack(ItemRibbon.instance);
		ItemStack stringStack = new ItemStack(Item.silk);
		ItemStack woolStack = new ItemStack(Block.cloth);
		ItemStack paperStack = new ItemStack(Item.paper);
		ItemStack chestStack = new ItemStack(Block.chest);
		
		GameRegistry.addRecipe(new ItemStack(ItemRibbon.instance,16), "  w", "www", "w  ", 'w', woolStack);
		GameRegistry.addRecipe(new ItemStack(BlockGift.instance,1), "prp", "rcr", "prp", 'p', paperStack, 'r', ribbonStack, 'c', chestStack);
		
		for (int ix = 0; ix < 16; ix++) {
			ItemStack dyeStack = new ItemStack(Item.dyePowder, 1, ix);
			ItemStack baubleStack = new ItemStack(BlockBauble.instance, 4, ix);
			
			GameRegistry.addRecipe(baubleStack, " s ", "rgr", " d ", 'g', glassStack, 'r', ribbonStack, 's', stringStack, 'd', dyeStack);
			LanguageRegistry.addName(baubleStack, baubleNames[baubleStack.getItemDamage()]);
		}
	}
	
	public HolidayCraft(){
	}

}
