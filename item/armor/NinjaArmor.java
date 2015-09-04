package armor.item.armor;

import armor.core.ArmorLoader;

import mekanism.api.energy.IEnergizedItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class NinjaArmor extends Eletric implements ISpecialArmor {

	public NinjaArmor(double par2, float par3,
			ArmorMaterial par2EnumArmorMaterial, int par4, int par5) {
		super(par2, par3, par2EnumArmorMaterial, par4, par5);

		if (par5 == 0) {
			this.setUnlocalizedName("ninjaHelmet");
		} else if (par5 == 1) {
			this.setUnlocalizedName("ninjaChestplate");
		} else if (par5 == 2) {
			this.setUnlocalizedName("ninjaLeggings");
		} else if (par5 == 3) {
			this.setUnlocalizedName("ninjaBoots");

		}

	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase entity,
			ItemStack itemstack, DamageSource source, double damage, int slot) {
		EntityPlayer player = (EntityPlayer) entity;

		if (source.equals(source.fall)
				&& this.getEnergy(player.getCurrentArmor(3)) >= 5) {

			this.setEnergy(player.inventory.armorItemInSlot(3),
					this.getEnergy((player.inventory.armorItemInSlot(3))) - 25);

			if (player.getHealth() <= 2.0F) {
				player.setHealth(10);
			}

			ArmorProperties armor = new ArmorProperties(10, 100,
					(int) this.getEnergy(player.getCurrentArmor(3)));

			return armor;
		} else {

			ArmorProperties armor = new ArmorProperties(1, 1, 20);

			return armor;
		}

	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

		PotionEffect invis = null;
		if (player.isPotionActive(Potion.invisibility.id)) {
			invis = player.getActivePotionEffect(Potion.invisibility);
		}

		if (invis == null || invis.getDuration() < 10) {

			double armor = this.getEnergy(player.getCurrentArmor(0))
					+ this.getEnergy(player.getCurrentArmor(1))
					+ this.getEnergy(player.getCurrentArmor(2))
					+ this.getEnergy(player.getCurrentArmor(3));

			if (armor <= 999) {
				inActive(player);
			} else if (armor > 999) {
				player.addPotionEffect(new PotionEffect(Potion.invisibility.id,
						20, -3));

				seethrough = 1;
				this.setEnergy(player.getCurrentArmor(0),
						this.getEnergy(player.getCurrentArmor(0)) - 250);
				this.setEnergy(player.getCurrentArmor(1),
						this.getEnergy(player.getCurrentArmor(1)) - 250);
				this.setEnergy(player.getCurrentArmor(2),
						this.getEnergy(player.getCurrentArmor(2)) - 250);
				this.setEnergy(player.getCurrentArmor(3),
						this.getEnergy(player.getCurrentArmor(3)) - 250);

			}

		}

	}

	public static void inActive(EntityPlayer player) {
		PotionEffect invis = null;
		if (player.isPotionActive(Potion.invisibility.id)) {
			invis = player.getActivePotionEffect(Potion.invisibility);

		}
		if (invis != null && invis.getAmplifier() == -3) {
			if (player.worldObj.isRemote) {
				player.removePotionEffectClient(Potion.invisibility.id);
				seethrough = 0;
			} else {
				player.removePotionEffect(Potion.invisibility.id);
				seethrough = 0;
			}
		}
	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArmorLoader.modid + ":"
				+ getUnlocalizedName().replace("item.", ""));
	}

	public static int seethrough;

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String layer) {

		if (seethrough == 0) {

			return ArmorLoader.modid + ":armor/"
					+ getArmorMaterial().name().toLowerCase() + "_" + layer
					+ ".png";
		} else {
			return ArmorLoader.modid + ":armor/" + "see" + ".png";
		}

	}

	public double E(EntityPlayer player) {

		int damage0 = player.getCurrentArmor(0).getItemDamage();
		int damage1 = player.getCurrentArmor(1).getItemDamage();

		int damage2 = player.getCurrentArmor(2).getItemDamage();
		int damage3 = player.getCurrentArmor(3).getItemDamage();

		int plus0 = damage0 + 100;
		int plus1 = damage1 + 100;
		int plus2 = damage2 + 100;
		int plus3 = damage3 + 100;

		double E0 = Math.abs(plus0
				* this.getMaxEnergy(player.getCurrentArmor(0)));
		double E1 = Math.abs(plus1
				* this.getMaxEnergy(player.getCurrentArmor(1)));
		double E2 = Math.abs(plus2
				* this.getMaxEnergy(player.getCurrentArmor(2)));
		double E3 = Math.abs(plus3
				* this.getMaxEnergy(player.getCurrentArmor(3)));

		double[] e = new double[] { E0, E1, E2, E3 };
		double armor = E0 + E1 + E2 + E3;

		return armor;

	}

}