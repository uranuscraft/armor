package armor.item.armor;

import java.util.ArrayList;
import java.util.List;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import armor.core.ArmorLoader;
//1.6.4 import armor.core.Config;
import armor.item.Carbon;
import armor.item.CarbonFiber;
import armor.item.Ic2Recipe;
import mekanism.api.recipe.RecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemLoader {
	static ArmorMaterial NinjaARMOR = EnumHelper.addArmorMaterial("ninja",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);

	public static Eletric ninjaHelmet = new NinjaArmor(10000000, 60,
			NinjaARMOR, ArmorLoader.proxy.getArmorIndex("ninja"), 0);
	public static Eletric ninjaChestplate = new NinjaArmor(10000000, 60,
			NinjaARMOR, ArmorLoader.proxy.getArmorIndex("ninja"), 1);
	public static Eletric ninjaLeggings = new NinjaArmor(10000000, 60,
			NinjaARMOR, ArmorLoader.proxy.getArmorIndex("ninja"), 2);
	public static Eletric ninjaBoots = new NinjaArmor(10000000, 60, NinjaARMOR,
			ArmorLoader.proxy.getArmorIndex("ninja"), 3);

	static ArmorMaterial ScubaARMOR = EnumHelper.addArmorMaterial("scuba",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);

	static ArmorMaterial LavaARMOR = EnumHelper.addArmorMaterial("lava",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);

	static ArmorMaterial SolarARMOR = EnumHelper.addArmorMaterial("solar",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);
	static ArmorMaterial JetARMOR = EnumHelper.addArmorMaterial("jet",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);
	static ArmorMaterial EvARMOR = EnumHelper.addArmorMaterial("ev",
			(int) Float.POSITIVE_INFINITY, new int[] { 2, 6, 5, 2 }, 9);

	static ArmorMaterial NanoARMOR = EnumHelper.addArmorMaterial("nano",
			(int) Float.POSITIVE_INFINITY, new int[] { 5, 9, 7, 5 }, 9);

	public static Eletric scubaHelmet = new Scuba(10000000, 60, ScubaARMOR,
			ArmorLoader.proxy.getArmorIndex("scuba"), 0);
	public static Eletric scubaChestplate = new Scuba(10000000, 60, ScubaARMOR,
			ArmorLoader.proxy.getArmorIndex("scuba"), 1);
	public static Eletric scubaLeggings = new Scuba(10000000, 60, ScubaARMOR,
			ArmorLoader.proxy.getArmorIndex("scuba"), 2);
	public static Eletric scubaBoots = new Scuba(10000000, 60, ScubaARMOR,
			ArmorLoader.proxy.getArmorIndex("scuba"), 3);

	public static Eletric lavaHelmet = new LavaSuit(10000000, 120, LavaARMOR,
			ArmorLoader.proxy.getArmorIndex("lava"), 0);
	public static Eletric lavaChestplate = new LavaSuit(10000000, 120,
			LavaARMOR, ArmorLoader.proxy.getArmorIndex("lava"), 1);
	public static Eletric lavaLeggings = new LavaSuit(10000000, 120, LavaARMOR,
			ArmorLoader.proxy.getArmorIndex("lava"), 2);
	public static Eletric lavaBoots = new LavaSuit(10000000, 120, LavaARMOR,
			ArmorLoader.proxy.getArmorIndex("lava"), 3);
	public static Eletric jetChestplate = new Jetpack(10000000, 120, JetARMOR,
			ArmorLoader.proxy.getArmorIndex("jet"), 1);

	public static ItemArmor solarHelmet = new SolarHelmet(SolarARMOR,
			ArmorLoader.proxy.getArmorIndex("solar"), 0);

	public static Eletric evHelmet = new EvSuit(10000000, 120, EvARMOR,
			ArmorLoader.proxy.getArmorIndex("ev"), 0);
	public static Eletric evChestplate = new EvSuit(10000000, 120, EvARMOR,
			ArmorLoader.proxy.getArmorIndex("ev"), 1);
	public static Eletric evLeggings = new EvSuit(10000000, 120, EvARMOR,
			ArmorLoader.proxy.getArmorIndex("ev"), 2);
	public static Eletric evBoots = new EvSuit(10000000, 120, EvARMOR,
			ArmorLoader.proxy.getArmorIndex("ev"), 3);

	public static Eletric nanoHelmet = new Nano(10000000, 120, NanoARMOR,
			ArmorLoader.proxy.getArmorIndex("nano"), 0);
	public static Eletric nanoChestplate = new Nano(10000000, 120, NanoARMOR,
			ArmorLoader.proxy.getArmorIndex("nano"), 1);
	public static Eletric nanoLeggings = new Nano(10000000, 120, NanoARMOR,
			ArmorLoader.proxy.getArmorIndex("nano"), 2);
	public static Eletric nanoBoots = new Nano(10000000, 120, NanoARMOR,
			ArmorLoader.proxy.getArmorIndex("nano"), 3);
	public static Item carbonfiber = new CarbonFiber();
	public static Item carbon = new Carbon();

	public static void AddItem() {
		Crafting();
		// 1.6.4 Namer();
	}

	public static void Crafting() {
		ItemStack woolStack = new ItemStack(Blocks.wool, 1, 15);
		ItemStack dyeStack = new ItemStack(Items.dye, 1, 15);
		ItemStack leatherStack = new ItemStack(Items.leather);
		ItemStack glassStack = new ItemStack(Blocks.glass);
		ItemStack ironBlockStack = new ItemStack(Blocks.iron_block);
		ItemStack stringStack = new ItemStack(Items.string);
		ItemStack coalStack = new ItemStack(Items.coal, 1, 0);
		ItemStack carbonfiberStack = new ItemStack(carbonfiber);
		ItemStack carbonStack = new ItemStack(carbon);
		GameRegistry.registerItem(carbon, "carbon");
		GameRegistry.registerItem(carbonfiber, "carbonfiber");
		GameRegistry.registerItem(solarHelmet, "solarhelmet");
		GameRegistry.registerItem(scubaHelmet, "scubahelmet");
		GameRegistry.registerItem(scubaChestplate, "scubachestplate");
		GameRegistry.registerItem(scubaLeggings, "scubaleggings");
		GameRegistry.registerItem(scubaBoots, "scubaboots");
		
		GameRegistry.registerItem(ninjaHelmet, "ninjahelmet");
		GameRegistry.registerItem(ninjaChestplate, "ninjachestplate");
		GameRegistry.registerItem(ninjaLeggings, "ninjaleggings");
		GameRegistry.registerItem(ninjaBoots, "ninjaboots");
		
		GameRegistry.registerItem(nanoHelmet, "nanohelmet");
		GameRegistry.registerItem(nanoChestplate, "nanochestplate");
		GameRegistry.registerItem(nanoLeggings, "nanoleggings");
		GameRegistry.registerItem(nanoBoots, "nanoboots");
		
		GameRegistry.registerItem(lavaHelmet, "lavahelmet");
		GameRegistry.registerItem(lavaChestplate, "lavachestplate");
		GameRegistry.registerItem(lavaLeggings, "lavaleggings");
		GameRegistry.registerItem(lavaBoots, "lavaboots");
		
		GameRegistry.registerItem(evHelmet, "evhelmet");
		GameRegistry.registerItem(evChestplate, "evchestplate");
		GameRegistry.registerItem(evLeggings, "evleggings");
		GameRegistry.registerItem(evBoots, "evboots");
		
		GameRegistry.registerItem(jetChestplate, "jetchestplate");
		
		
		GameRegistry.addRecipe(ninjaHelmet.getUnchargedItem(), "xxx", "x x",
				'x', woolStack);

		GameRegistry.addRecipe(ninjaChestplate.getUnchargedItem(), "x x",
				"xxx", "xxx", 'x', woolStack);
		GameRegistry.addRecipe(ninjaLeggings.getUnchargedItem(), "xxx", "x x",
				"x x", 'x', woolStack);
		GameRegistry.addRecipe(ninjaBoots.getUnchargedItem(), "x x", "x x",
				'x', woolStack);
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.lavaHelmet
				.getUnchargedItem(), true, "xxx", "x x", 'x', "plateSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.lavaChestplate
				.getUnchargedItem(), true, "x x", "xxx", "xxx", 'x',
				"plateSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.lavaLeggings
				.getUnchargedItem(), true, "xxx", "x x", "x x", 'x',
				"plateSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.lavaBoots
				.getUnchargedItem(), true, "x x", "x x", 'x', "plateSteel"));
		GameRegistry.addRecipe(scubaHelmet.getUnchargedItem(), "xxx", "xyx",
				'x', leatherStack, 'y', glassStack);

		GameRegistry.addRecipe(scubaChestplate.getUnchargedItem(), "x x",
				"xyx", "xxx", 'x', leatherStack, 'y', ironBlockStack);

		GameRegistry.addRecipe(scubaLeggings.getUnchargedItem(), "xyx", "x x",
				"x x", 'x', leatherStack, 'y', stringStack);
		GameRegistry.addRecipe(scubaBoots.getUnchargedItem(), "x x", "y y",
				'x', leatherStack, 'y', dyeStack);
		GameRegistry.addRecipe(new ItemStack(carbon), "xyx", "yxy", "xyx", 'x',
				coalStack, 'y', stringStack);

		GameRegistry.addRecipe(nanoHelmet.getUnchargedItem(), "xxx", "x x",
				'x', carbonfiberStack);

		GameRegistry.addRecipe(nanoChestplate.getUnchargedItem(), "x x", "xxx",
				"xxx", 'x', carbonfiberStack);
		GameRegistry.addRecipe(nanoLeggings.getUnchargedItem(), "xxx", "x x",
				"x x", 'x', carbonfiberStack);
		GameRegistry.addRecipe(nanoBoots.getUnchargedItem(), "x x", "x x", 'x',
				carbonfiberStack);

		GameRegistry
				.addRecipe(new ShapedOreRecipe(ItemLoader.evHelmet
						.getUnchargedItem(), true, "yxz", "x x", 'x',
						"plateBronze", 'y', new ItemStack(scubaHelmet), 'z',
						new ItemStack(lavaHelmet)));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.evChestplate
				.getUnchargedItem(), true, "x x", "xyx", "xzx", 'x',
				"plateBronze", 'y', new ItemStack(scubaChestplate), 'z',
				new ItemStack(lavaChestplate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.evLeggings
				.getUnchargedItem(), true, "xxx", "x x", "x x", 'x',
				"plateBronze", 'y', new ItemStack(scubaLeggings), 'z',
				new ItemStack(lavaLeggings)));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.evBoots
				.getUnchargedItem(), true, "x x", "x x", 'x', "plateBronze",
				'y', new ItemStack(scubaBoots), 'z', new ItemStack(lavaBoots)));

		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.solarHelmet,
				true, "xxx", "xyx", "zzz", 'x',
				new ItemStack(Items.iron_ingot), 'y', "solarPanel", 'z',
				"universalCable"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.solarHelmet,
				true, "xxx", "xyx", "zzz", 'x',
				new ItemStack(Items.iron_ingot), 'y', "solarPanel", 'z',
				"copperWire"));

		GameRegistry.addRecipe(new ShapedOreRecipe(ItemLoader.jetChestplate
				.getUnchargedItem(), true, "xyx", "xzx", "c c", 'x',
				"ingotSteel", 'y', "circuitAdvanced", 'z', "battery", 'c',
				new ItemStack(Items.glowstone_dust)));

		if (isMekanismLoaded()) {
			RecipeHelper.addOsmiumCompressorRecipe(new ItemStack(carbon),
					new ItemStack(carbonfiber));
		}
		if (isIndustrialCraftLoaded()) {

			IRecipeInput input = new Ic2Recipe(carbonStack, 1);
			Recipes.compressor.addRecipe(input, null, carbonfiberStack);
		}
		if (isThermalExpansionLoaded()) {
			// 1.6.4CraftingManagers.smelterManager.addRecipe(5,carbonStack,carbonStack,
			// new ItemStack(carbonfiber, 2), coalStack, false);
		}
	}

	public static void Namer() {
		/**
		 * 1.6.4 LanguageRegistry.addName(ninjaHelmet, "Ninja Helmet");
		 * LanguageRegistry.addName(ninjaChestplate, "Ninja Chestplate");
		 * LanguageRegistry.addName(ninjaLeggings, "Ninja Leggings");
		 * LanguageRegistry.addName(ninjaBoots, "Ninja Boots");
		 * 
		 * 
		 * LanguageRegistry.addName(scubaHelmet, "Scuba Helmet");
		 * LanguageRegistry.addName(scubaChestplate, "Scuba Chestplate");
		 * LanguageRegistry.addName(scubaLeggings, "Scuba Leggings");
		 * LanguageRegistry.addName(scubaBoots, "Scuba Boots");
		 * 
		 * 
		 * LanguageRegistry.addName(solarHelmet, "Solar helmet");
		 * LanguageRegistry.addName(jetChestplate, "Jet pack");
		 * 
		 * LanguageRegistry.addName(lavaHelmet, "Lava proof Helmet");
		 * LanguageRegistry.addName(lavaChestplate, "Lava proof Chestplate");
		 * LanguageRegistry.addName(lavaLeggings, "Lava proof Leggings");
		 * LanguageRegistry.addName(lavaBoots, "Lava proof Boots");
		 * 
		 * LanguageRegistry.addName(evHelmet, "Environment proof Helmet");
		 * LanguageRegistry.addName(evChestplate,
		 * "Environment proof Chestplate"); LanguageRegistry.addName(evLeggings,
		 * "Environment proof Leggings"); LanguageRegistry.addName(evBoots,
		 * "Environment proof Boots");
		 * 
		 * LanguageRegistry.addName(nanoHelmet, "Nano Helmet");
		 * LanguageRegistry.addName(nanoChestplate, "Nano Chestplate");
		 * LanguageRegistry.addName(nanoLeggings, "Nano Leggings");
		 * LanguageRegistry.addName(nanoBoots, "Nano Boots");
		 * 
		 * LanguageRegistry.addName(carbonfiber, "Carbon Fiber");
		 * LanguageRegistry.addName(carbon, "Carbon chunk");
		 **/
	}

	public static boolean isThermalExpansionLoaded() {
		return Loader.isModLoaded("ThermalExpansion");
	}

	public static boolean isIndustrialCraftLoaded() {
		return Loader.isModLoaded("IC2");
	}

	public static boolean isMekanismLoaded() {
		return Loader.isModLoaded("Mekanism");

	}

	public static boolean isGalacticraftLoaded() {
		return Loader.isModLoaded("GalacticraftCore");
	}
}
