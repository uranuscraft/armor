package armor.item.armor;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.Map;
import java.util.Random;

import cofh.api.energy.*;
import mekanism.api.energy.IEnergizedItem;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.ISpecialArmor;
import armor.core.ArmorLoader;

import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SolarHelmet extends ItemArmor implements ISpecialArmor,
		ISolarLevel {

	public SolarHelmet(ArmorMaterial material, int armortype, int rendertype) {
		super(material, armortype, rendertype);
		this.setUnlocalizedName("solarhelmet");
		this.setTextureName(ArmorLoader.modid + ":" + this.getUnlocalizedName() + ".png");
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	private static Map<EntityPlayer, PlayerState> playerState = new MapMaker()
			.weakKeys().makeMap();

	private class PlayerState {
		boolean canRain;
		public long buildUp;
		public long lastTick;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase entity,
			ItemStack stack, DamageSource source, double damage, int slot) {
		EntityPlayer player = (EntityPlayer) entity;
		ArmorProperties armor = new ArmorProperties(10, 100, 2);

		return armor;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

		if (world.provider instanceof ISolarLevel) {
			int j = 5000 * (int) ((ISolarLevel) world.provider)
					.getSolarEnergyMultiplier();

			if (player.getCurrentEquippedItem() != null) {

				if (player.getCurrentEquippedItem().getItem() instanceof IEnergizedItem) {

					ItemStack itemstack = player.getCurrentEquippedItem();
					IEnergizedItem k = (IEnergizedItem) itemstack.getItem();

					k.setEnergy(itemstack, k.getEnergy(itemstack) + j);

				}

				else if (player.getCurrentEquippedItem().getItem() instanceof IElectricItem) {

					ElectricItem.manager
							.charge(player.getCurrentEquippedItem(), j, 4,
									false, false);
				} else if (player.getCurrentEquippedItem().getItem() instanceof IEnergyContainerItem) {
					charge.receiveEnergy(player.getCurrentEquippedItem(), j,
							true);

				}
			}

			if (player.getCurrentArmor(1) != null) {
				if (player.getCurrentArmor(1).getItem() instanceof IEnergizedItem) {
					ItemStack itemstack = player.getCurrentArmor(1);
					IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
					k.setEnergy(itemstack, k.getEnergy(itemstack) + j);

				} else if (player.getCurrentArmor(1).getItem() instanceof IElectricItem) {

					ElectricItem.manager.charge(player.getCurrentArmor(1), j,
							4, false, false);
				} else if (player.getCurrentArmor(1).getItem() instanceof IEnergyContainerItem) {
					charge.receiveEnergy(player.getCurrentArmor(1), j, true);

				}
			}
			if (player.getCurrentArmor(2) != null) {
				if (player.getCurrentArmor(2).getItem() instanceof IEnergizedItem) {
					ItemStack itemstack = player.getCurrentArmor(2);
					IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
					k.setEnergy(itemstack, k.getEnergy(itemstack) + j);

				} else if (player.getCurrentArmor(2).getItem() instanceof IElectricItem) {

					ElectricItem.manager.charge(player.getCurrentArmor(2), j,
							4, false, false);
				}

				else if (player.getCurrentArmor(2).getItem() instanceof IEnergyContainerItem) {
					charge.receiveEnergy(player.getCurrentArmor(2), j, true);

				}

			}
			if (player.getCurrentArmor(3) != null) {
				if (player.getCurrentArmor(3).getItem() instanceof IEnergizedItem) {
					ItemStack itemstack = player.getCurrentArmor(3);
					IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
					k.setEnergy(itemstack, k.getEnergy(itemstack) + j);

				}

				else if (player.getCurrentArmor(3).getItem() instanceof IElectricItem) {

					ElectricItem.manager.charge(player.getCurrentArmor(3), j,
							4, false, false);
				} else if (player.getCurrentArmor(3).getItem() instanceof IEnergyContainerItem) {
					charge.receiveEnergy(player.getCurrentArmor(3), j, true);

				}
			}
		}

		if (world.isRemote || world.provider.hasNoSky) {
			return;
		}

		int xCoord = MathHelper.floor_double(player.posX);
		int zCoord = MathHelper.floor_double(player.posZ);
		boolean isRaining = false;

		if (!this.playerState.containsKey(player)) {
			this.playerState.put(player, new PlayerState());

		}
		PlayerState state = playerState.get(player);

		if (world.getTotalWorldTime() % 20 == 0) {
			boolean canRain = world.getWorldChunkManager()
					.getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0;
			state.canRain = canRain;

		}
		isRaining = state.canRain
				&& (world.isRaining() || world.isThundering());

		boolean theSunIsVisible = world.isDaytime()
				&& !isRaining
				&& world.canBlockSeeTheSky(xCoord,
						MathHelper.floor_double(player.posY) + 1, zCoord);
		boolean theSunIsNotVisible = !world.isDaytime()
				&& isRaining
				&& !world.canBlockSeeTheSky(xCoord,
						MathHelper.floor_double(player.posY) + 1, zCoord);

		if (!theSunIsVisible) {
			return;

		}

		if (player.getCurrentEquippedItem() != null && theSunIsVisible == true) {
			if (player.getCurrentEquippedItem().getItem() instanceof IEnergizedItem) {

				ItemStack itemstack = player.getCurrentEquippedItem();
				IEnergizedItem k = (IEnergizedItem) itemstack.getItem();

				k.setEnergy(itemstack, k.getEnergy(itemstack) + 5000);

			}

			else if (player.getCurrentEquippedItem().getItem() instanceof IElectricItem) {

				ElectricItem.manager.charge(player.getCurrentEquippedItem(),
						5000, 4, false, false);
			} else if (player.getCurrentEquippedItem().getItem() instanceof IEnergyContainerItem) {
				charge.receiveEnergy(player.getCurrentEquippedItem(), 5000,
						true);

			}
		}
		if (player.getCurrentArmor(1) != null && theSunIsVisible == true) {
			if (player.getCurrentArmor(1).getItem() instanceof IEnergizedItem) {
				ItemStack itemstack = player.getCurrentArmor(1);
				IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
				k.setEnergy(itemstack, k.getEnergy(itemstack) + 5000);

			} else if (player.getCurrentArmor(1).getItem() instanceof IElectricItem) {

				ElectricItem.manager.charge(player.getCurrentArmor(1), 5000, 4,
						false, false);
			} else if (player.getCurrentArmor(1).getItem() instanceof IEnergyContainerItem) {
				charge.receiveEnergy(player.getCurrentArmor(1), 5000, true);

			}

		}
		if (player.getCurrentArmor(2) != null && theSunIsVisible == true) {
			if (player.getCurrentArmor(2).getItem() instanceof IEnergizedItem) {
				ItemStack itemstack = player.getCurrentArmor(2);
				IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
				k.setEnergy(itemstack, k.getEnergy(itemstack) + 5000);

			} else if (player.getCurrentArmor(2).getItem() instanceof IElectricItem) {

				ElectricItem.manager.charge(player.getCurrentArmor(2), 5000, 4,
						false, false);
			}

			else if (player.getCurrentArmor(2).getItem() instanceof IEnergyContainerItem) {
				charge.receiveEnergy(player.getCurrentArmor(2), 5000, true);

			}

		}
		if (player.getCurrentArmor(3) != null && theSunIsVisible == true) {
			if (player.getCurrentArmor(3).getItem() instanceof IEnergizedItem) {
				ItemStack itemstack = player.getCurrentArmor(3);
				IEnergizedItem k = (IEnergizedItem) itemstack.getItem();
				k.setEnergy(itemstack, k.getEnergy(itemstack) + 5000);

			}

			else if (player.getCurrentArmor(3).getItem() instanceof IElectricItem) {

				ElectricItem.manager.charge(player.getCurrentArmor(3), 5000, 4,
						false, false);
			} else if (player.getCurrentArmor(3).getItem() instanceof IEnergyContainerItem) {
				charge.receiveEnergy(player.getCurrentArmor(3), 5000, true);

			}
		}
	}

	public static void clearRaining() {
		SolarHelmet.playerState.clear();
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

	public static IEnergyContainerItem charge;

	@Override
	public double getSolarEnergyMultiplier() {

		return 10;
	}

}
