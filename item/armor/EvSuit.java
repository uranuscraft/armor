package armor.item.armor;

import armor.control.KeyBindHandler;
import armor.core.ArmorLoader;
//1.6.4import atomicscience.api.IAntiPoisonArmor;
//1.6.4import atomicscience.api.poison.Poison;
//1.6.4import atomicscience.api.poison.PotionRadiation;
//1.6.4import atomicscience.api.poison.Poison.ArmorType;
import cpw.mods.fml.common.FMLCommonHandler;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.item.IBreathableArmor;
import micdoodle8.mods.galacticraft.api.world.*;
import net.minecraft.client.renderer.texture.IIconRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class EvSuit extends Eletric implements ISpecialArmor, IBreathableArmor { // ,
																					// IAntiPoisonArmor{]

	public EvSuit(double maxElectricity, float voltage, ArmorMaterial material,
			int armortype, int par5) {
		super(maxElectricity, voltage, material, armortype, par5);
		if (par5 == 0) {
			this.setUnlocalizedName("evHelmet");
		} else if (par5 == 1) {
			this.setUnlocalizedName("evChestplate");
		} else if (par5 == 2) {
			this.setUnlocalizedName("evLeggings");
		} else if (par5 == 3) {
			this.setUnlocalizedName("evBoots");

		}

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

		ArmorProperties armor = new ArmorProperties(10, 100, 4);

		return armor;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 4;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {

		EntityPlayer player = (EntityPlayer) entity;

		if (player.worldObj.provider instanceof IOrbitDimension) {

		}

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

		thePlayer = player;
		if (player.isInWater() && !(player.isRiding())) {

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

	public static EntityPlayer thePlayer;

	@Override
	public boolean handleGearType(EnumGearType gearType) {

		return true;
	}

	@Override
	public boolean canBreathe(ItemStack helmetInSlot, EntityPlayer player,
			EnumGearType type) {

		if (player.getCurrentArmor(0) != null) {

			if (player.getCurrentArmor(0).getItem() == this
					&& this.getEnergy(player.getCurrentArmor(0)) > 499999) {

				if (player.getCurrentArmor(1) != null) {

					if (player.getCurrentArmor(1).getItem() == this
							&& this.getEnergy(player.getCurrentArmor(1)) > 499999) {

						if (player.getCurrentArmor(2) != null) {
							if (player.getCurrentArmor(2).getItem() == this
									&& this.getEnergy(player.getCurrentArmor(2)) > 499999) {

								if (player.getCurrentArmor(3) != null) {
									if (player.getCurrentArmor(3).getItem() == this
											&& this.getEnergy(player
													.getCurrentArmor(3)) > 499999) {

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

	public void lightInActive(EntityPlayer player) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			if (player.isPotionActive(Potion.nightVision.id)
					&& KeyBindHandler.light.isPressed()) {

				PotionEffect invis = null;
				if (player.isPotionActive(Potion.nightVision.id)) {
					invis = player.getActivePotionEffect(Potion.nightVision);

				}
				if (invis != null && invis.getAmplifier() == -3) {
					if (player.worldObj.isRemote) {
						player.removePotionEffectClient(Potion.nightVision.id);

					} else {
						player.removePotionEffect(Potion.nightVision.id);

					}

				}
			}
		}

	}

	/**
	 * 1.6.4 @Override public boolean isProtectedFromPoison(ItemStack itemStack,
	 * EntityLivingBase entityLiving, Poison type) {
	 * 
	 * EntityPlayer player = (EntityPlayer) entityLiving;
	 * if(type.equals(PotionRadiation.INSTANCE) ) {
	 * 
	 * if(player.getCurrentArmor(0) != null){
	 * 
	 * if(player.getCurrentArmor(0).getItem() == this &&
	 * this.getEnergy(player.getCurrentArmor(0)) > 499999) {
	 * 
	 * if(player.getCurrentArmor(1) != null ){
	 * 
	 * 
	 * 
	 * if(player.getCurrentArmor(1).getItem() == this &&
	 * this.getEnergy(player.getCurrentArmor(1)) > 499999) {
	 * 
	 * if(player.getCurrentArmor(2) != null) {
	 * if(player.getCurrentArmor(2).getItem() == this &&
	 * this.getEnergy(player.getCurrentArmor(2)) > 499999) {
	 * 
	 * 
	 * if(player.getCurrentArmor(3) != null) {
	 * if(player.getCurrentArmor(3).getItem() == this &&
	 * this.getEnergy(player.getCurrentArmor(3)) > 499999) {
	 * 
	 * 
	 * 
	 * return true; } } } }
	 * 
	 * } } } } }
	 * 
	 * return false;
	 * 
	 * 
	 * }
	 * 
	 * @Override public void onProtectFromPoison(ItemStack itemStack,
	 *           EntityLivingBase entityLiving, Poison type) { EntityPlayer
	 *           player = (EntityPlayer) entityLiving;
	 *           this.setEnergy(player.getCurrentArmor
	 *           (3),this.getEnergy(player.getCurrentArmor(3)) - 500000);
	 *           this.setEnergy(player.getCurrentArmor(2),this.getEnergy(player.
	 *           getCurrentArmor(2)) - 500000);
	 *           this.setEnergy(player.getCurrentArmor
	 *           (1),this.getEnergy(player.getCurrentArmor(1)) - 500000);
	 *           this.setEnergy(player.getCurrentArmor(3),this.getEnergy(player.
	 *           getCurrentArmor(0)) - 500000);
	 * 
	 *           }
	 **/

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
