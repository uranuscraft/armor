package armor.item;

import java.util.ArrayList;
import java.util.List;

import ic2.api.recipe.IRecipeInput;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import armor.core.ArmorLoader;
import armor.item.armor.ItemLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Carbon extends Item {

	public Carbon() {
		super();
		this.setUnlocalizedName("carbon");
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ArmorLoader.modid + ":"
				+ (this.getUnlocalizedName().substring(5)));
	}

}
