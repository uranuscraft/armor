package armor.item;

import armor.core.ArmorLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CarbonFiber extends Item {

	public CarbonFiber() {
		super();
		this.setUnlocalizedName("carbonfiber");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ArmorLoader.modid + ":"
				+ (this.getUnlocalizedName().substring(5)));
	}
}
