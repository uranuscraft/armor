package armor.item.armor;

import ic2.api.item.IElectricItemManager;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class IC2ItemManager implements IElectricItemManager {
	public Eletric energizedItem;

	public static IC2ItemManager getManager(Eletric itemEletric) {
		IC2ItemManager manager = new IC2ItemManager();
		manager.energizedItem = itemEletric;

		return manager;
	}

	@Override
	public double charge(ItemStack itemStack, double amount, int tier,
			boolean ignoreTransferLimit, boolean simulate) {
		if (energizedItem.canReceive(itemStack)) {
			double energyNeeded = energizedItem.getMaxEnergy(itemStack)
					- energizedItem.getEnergy(itemStack);
			double energyToStore = Math.min(
					Math.min(amount * Eletric.FROM_IC2,
							energizedItem.getMaxEnergy(itemStack) * 0.01),
					energyNeeded);

			if (!simulate) {
				energizedItem.setEnergy(itemStack,
						energizedItem.getEnergy(itemStack) + energyToStore);
			}

			return (int) Math.round(energyToStore * Eletric.TO_IC2);
		}

		return 0;
	}

	@Override
	public double discharge(ItemStack itemStack, double amount, int tier,
			boolean ignoreTransferLimit, boolean externally, boolean simulate) {
		if (energizedItem.canSend(itemStack)) {
			double energyWanted = amount * Eletric.FROM_IC2;
			double energyToGive = Math.min(
					Math.min(energyWanted,
							energizedItem.getMaxEnergy(itemStack) * 0.01),
					energizedItem.getEnergy(itemStack));

			if (!simulate) {
				energizedItem.setEnergy(itemStack,
						energizedItem.getEnergy(itemStack) - energyToGive);
			}

			return (int) Math.round(energyToGive * Eletric.TO_IC2);
		}

		return 0;
	}

	/**
	 * 1.6.4 @Override public boolean canUse(ItemStack itemStack, int amount) {
	 * }
	 */
	@Override
	public double getCharge(ItemStack itemStack) {
		return Math.round(energizedItem.getEnergy(itemStack) * Eletric.TO_IC2);
	}

	@Override
	public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entity) {

	}

	@Override
	public String getToolTip(ItemStack itemStack) {
		return null;
	}

	@Override
	public boolean canUse(ItemStack itemStack, double amount) {
		return energizedItem.getEnergy(itemStack) >= amount * Eletric.FROM_IC2;

	}

	@Override
	public boolean use(ItemStack stack, double amount, EntityLivingBase entity) {

		return false;
	}
}
