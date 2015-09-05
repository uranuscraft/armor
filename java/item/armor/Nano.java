package armor.item.armor;

import armor.core.ArmorLoader;
import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class Nano extends Eletric implements ISpecialArmor, IBreathableArmor {

	public Nano(double maxElectricity, float voltage, ArmorMaterial material,
			int armortype, int par5) {
		super(maxElectricity, voltage, material, armortype, par5);
		if (par5 == 0) {
			this.setUnlocalizedName("nanohelmet");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 1) {
			this.setUnlocalizedName("nanochestplate");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 2) {
			this.setUnlocalizedName("nanoleggings");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 3) {
			this.setUnlocalizedName("nanoboots");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			

		}
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack stack, DamageSource source, double damage, int slot) {
		
		if (source.equals(source.fall)) {
			ArmorProperties armor = new ArmorProperties(10, 100, 150);

			return armor;
		}

		ArmorProperties armor = new ArmorProperties(10, 100, 100);

		return armor;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 16;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {

	}

	@Override
	public boolean handleGearType(EnumGearType gearType) {

		return false;
	}

	@Override
	public boolean canBreathe(ItemStack helmetInSlot, EntityPlayer player,
			EnumGearType type) {

		if (player.getCurrentArmor(0) != null) {

			if (player.getCurrentArmor(0).getItem() == this
					&& this.getEnergy(player.getCurrentArmor(0)) > 499) {

				if (player.getCurrentArmor(1) != null) {

					if (player.getCurrentArmor(1).getItem() == this
							&& this.getEnergy(player.getCurrentArmor(1)) > 499) {

						if (player.getCurrentArmor(2) != null) {
							if (player.getCurrentArmor(2).getItem() == this
									&& this.getEnergy(player.getCurrentArmor(2)) > 499) {

								if (player.getCurrentArmor(3) != null) {
									if (player.getCurrentArmor(3).getItem() == this
											&& this.getEnergy(player
													.getCurrentArmor(3)) > 499) {

										return true;
									}
								}
							}
						}
					}
				}
			}

		}

		return false;

	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArmorLoader.modid + ":"
				+ getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		int layer = (slot == 2) ? 2 : 1;
		return "uranuscraft_armor:armor/" + getArmorMaterial().name().toLowerCase() + "_" + layer + ".png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
if(this.getEnergy(stack) > 10000) {
	player.motionX *=10;
	player.motionY *=10;
	this.setEnergy(stack,this.getEnergy(stack) - 10000);
}
		
	}
}
