package armor.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import mekanism.api.ItemRetriever;
import micdoodle8.mods.galacticraft.api.item.GCItems;
//1.6.4import basiccomponents.api.BasicRegistry;
//1.6.4import calclavia.lib.UniversalRecipes;
import armor.common.CommonProxy;
import armor.item.armor.ItemLoader;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// 1.6.4 version import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ArmorLoader.modid, name = "Electric Armor Mod", version = "1.0.0")
// 1.6.4 version@NetworkMod(clientSideRequired=true, serverSideRequired = false)
public class ArmorLoader {
	// The instance of your mod that Forge uses.
	@Instance(value = "uranuscraft_armor")
	public static ArmorLoader instance;
	public static final String modid = "uranuscraft_armor";
	@SidedProxy(clientSide = "armor.client.ClientProxy", serverSide = "armor.common.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// 1.6.4UniversalRecipes.init();
		// 1.6.4BasicRegistry.requestAll();

		proxy.registerEvents();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		ItemLoader.AddItem();

		proxy.registerHandlers();
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (ItemLoader.isMekanismLoaded()) {
			if (isMekanismGeneratorsLoaded()) {
				ItemStack solar = ItemRetriever.getItem("SolarPanel");

				if (solar != null) {
					OreDictionary.registerOre("solarPanel", solar);
				}
			}

		}
		if (ItemLoader.isGalacticraftLoaded()) {
			ItemStack solar = GCItems.requestBlock("solarPanelBasic", 1);
			ItemStack copperwire = GCItems.requestBlock("copperWire", 1);

			if (solar != null) {
				OreDictionary.registerOre("solarPanel", solar);
				OreDictionary.registerOre("copperWire", copperwire);
			}

		}
		proxy.postInit();
	}

	public static boolean isMekanismGeneratorsLoaded() {
		return Loader.isModLoaded("MekanismGenerators");
	}

}
