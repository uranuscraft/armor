package armor.item.armor;

import armor.control.KeyBindHandler;
import armor.core.ArmorLoader;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class Scuba extends Eletric implements ISpecialArmor {

	public Scuba(double maxElectricity, float voltage, ArmorMaterial material,
			int armortype, int par5) {
		super(maxElectricity, voltage, material, armortype, par5);

		if (par5 == 0) {
			this.setUnlocalizedName("scubaHelmet");
		} else if (par5 == 1) {
			this.setUnlocalizedName("scubaChestplate");
		} else if (par5 == 2) {
			this.setUnlocalizedName("scubaLeggings");
		} else if (par5 == 3) {
			this.setUnlocalizedName("scubaBoots");

		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase entity,
			ItemStack stack, DamageSource source, double damage, int slot) {
		EntityPlayer player = (EntityPlayer) entity;

		if (source.equals(source.drown)
				&& this.getEnergy(player.getCurrentArmor(0)) > 4) {
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

		} else {
			ArmorProperties armor = new ArmorProperties(10, 100, 20);

			return armor;
		}
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
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

		if (player.isInWater() && !(player.isRiding())) {

			if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
				boolean key = KeyBindHandler.light.isPressed();

				if (key) {

					PotionEffect invis = null;
					if (player.isPotionActive(Potion.nightVision.id)) {
						invis = player
								.getActivePotionEffect(Potion.nightVision);
					}

					if (invis == null || invis.getDuration() < 10) {

						double armor = this
								.getEnergy(player.getCurrentArmor(0))
								+ this.getEnergy(player.getCurrentArmor(1))
								+ this.getEnergy(player.getCurrentArmor(2))
								+ this.getEnergy(player.getCurrentArmor(3));

						if (armor <= 999) {
							inActive(player);
						} else if (armor > 999) {
							player.addPotionEffect(new PotionEffect(
									Potion.nightVision.id, 20, -3));
							this.setEnergy(
									player.getCurrentArmor(0),
									this.getEnergy(player.getCurrentArmor(0)) - 250);
							this.setEnergy(
									player.getCurrentArmor(1),
									this.getEnergy(player.getCurrentArmor(1)) - 250);
							this.setEnergy(
									player.getCurrentArmor(2),
									this.getEnergy(player.getCurrentArmor(2)) - 250);
							this.setEnergy(
									player.getCurrentArmor(3),
									this.getEnergy(player.getCurrentArmor(3)) - 250);

						}
					}

				}
			}
			double armore = this.getEnergy(player.getCurrentArmor(0))
					+ this.getEnergy(player.getCurrentArmor(1))
					+ this.getEnergy(player.getCurrentArmor(2))
					+ this.getEnergy(player.getCurrentArmor(3));

			if (armore > 999 && player.getAir() < 10) {

				this.setEnergy(
						player.inventory.armorItemInSlot(3),
						this.getEnergy((player.inventory.armorItemInSlot(3))) - 250);
				this.setEnergy(
						player.inventory.armorItemInSlot(2),
						this.getEnergy((player.inventory.armorItemInSlot(2))) - 250);
				this.setEnergy(
						player.inventory.armorItemInSlot(1),
						this.getEnergy((player.inventory.armorItemInSlot(1))) - 250);
				this.setEnergy(
						player.inventory.armorItemInSlot(0),
						this.getEnergy((player.inventory.armorItemInSlot(0))) - 250);

				player.setAir(300);
			}

		}

	}

	public static void inActive(EntityPlayer player) {
		PotionEffect invis = null;
		if (player.isPotionActive(Potion.nightVision.id)) {
			invis = player.getActivePotionEffect(Potion.nightVision);

		}
		if (invis != null && invis.getAmplifier() == -3) {
			if (player.worldObj.isRemote) {
				player.removePotionEffectClient(Potion.nightVision.id);
				KeyBindHandler.light.unPressAllKeys();
			} else {
				player.removePotionEffect(Potion.nightVision.id);
				KeyBindHandler.light.unPressAllKeys();
			}
		}
	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArmorLoader.modid + ":"
				+ getUnlocalizedName().replace("item.", ""));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String layer) {

		return ArmorLoader.modid + ":armor/"
				+ getArmorMaterial().name().toLowerCase() + "_" + layer
				+ ".png";
	}

}
