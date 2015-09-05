package armor.item.armor;

import armor.core.ArmorLoader;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class LavaSuit extends Eletric implements ISpecialArmor {

	public LavaSuit(double maxElectricity, float voltage,
			ArmorMaterial material, int armortype, int par5) {
		super(maxElectricity, voltage, material, armortype, par5);
		if (par5 == 0) {
			this.setUnlocalizedName("lavahelmet");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 1) {
			this.setUnlocalizedName("lavachestplate");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 2) {
			this.setUnlocalizedName("lavaleggings");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			
		} else if (par5 == 3) {
			this.setUnlocalizedName("lavaboots");
			this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
			

		}
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase entity,
			ItemStack stack, DamageSource source, double damage, int slot) {
		EntityPlayer player = (EntityPlayer) entity;
	
		if (source.equals(source.lava)
				&& this.getEnergy(player.getCurrentArmor(0)) > 999) {

			this.setEnergy(player.inventory.armorItemInSlot(3),
					this.getEnergy((player.inventory.armorItemInSlot(3))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(2),
					this.getEnergy((player.inventory.armorItemInSlot(2))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(1),
					this.getEnergy((player.inventory.armorItemInSlot(1))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(0),
					this.getEnergy((player.inventory.armorItemInSlot(0))) - 250);

			ArmorProperties armor = new ArmorProperties(10, 100,
					(int) Float.POSITIVE_INFINITY);

			return armor;
		} else if (source.equals(source.onFire)
				&& this.getEnergy(player.getCurrentArmor(0)) > 999) {

			this.setEnergy(player.inventory.armorItemInSlot(3),
					this.getEnergy((player.inventory.armorItemInSlot(3))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(2),
					this.getEnergy((player.inventory.armorItemInSlot(2))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(1),
					this.getEnergy((player.inventory.armorItemInSlot(1))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(0),
					this.getEnergy((player.inventory.armorItemInSlot(0))) - 250);

			ArmorProperties armor = new ArmorProperties(10, 100,
					(int) Float.POSITIVE_INFINITY);

			return armor;
		} else if (source.equals(source.inFire)
				&& this.getEnergy(player.getCurrentArmor(0)) > 999) {

			this.setEnergy(player.inventory.armorItemInSlot(3),
					this.getEnergy((player.inventory.armorItemInSlot(3))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(2),
					this.getEnergy((player.inventory.armorItemInSlot(2))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(1),
					this.getEnergy((player.inventory.armorItemInSlot(1))) - 250);
			this.setEnergy(player.inventory.armorItemInSlot(0),
					this.getEnergy((player.inventory.armorItemInSlot(0))) - 250);

			ArmorProperties armor = new ArmorProperties(10, 100,
					(int) Float.POSITIVE_INFINITY);

			return armor;
		}

		ArmorProperties armor = new ArmorProperties(10, 100, 20);

		return armor;

	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 20;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {

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
}
