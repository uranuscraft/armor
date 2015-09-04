package armor.event;

import armor.item.armor.EvSuit;
import armor.item.armor.ItemLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent.Pre;

public class Oxygen extends Pre {

	public Oxygen(EntityLivingBase entity) {
		super(entity);

		EntityPlayer player = (EntityPlayer) entity;

		boolean helmet = false;
		boolean chestplate = false;
		boolean leggings = false;
		boolean boots = false;
		if (player.getCurrentArmor(0) != null) {

			if (player.getCurrentArmor(0).getItem() == ItemLoader.evHelmet
					&& ItemLoader.evHelmet.getEnergy(player.getCurrentArmor(0)) > 499999) {

				helmet = true;

			}

		}

		if (player.getCurrentArmor(1) != null) {

			if (player.getCurrentArmor(1).getItem() == ItemLoader.evChestplate
					&& ItemLoader.evChestplate.getEnergy(player
							.getCurrentArmor(1)) > 499999) {

				chestplate = true;

			}

		}
		if (player.getCurrentArmor(2) != null) {
			if (player.getCurrentArmor(2).getItem() == ItemLoader.evLeggings
					&& ItemLoader.evLeggings.getEnergy(player
							.getCurrentArmor(2)) > 499999) {

				leggings = true;

			}
		}
		if (player.getCurrentArmor(3) != null) {
			if (player.getCurrentArmor(3).getItem() == ItemLoader.evBoots
					&& ItemLoader.evBoots.getEnergy(player.getCurrentArmor(3)) > 499999) {

				boots = true;

			}
		}

		boolean cancel = helmet && chestplate && leggings && boots;
		if (cancel) {
			this.setCanceled(cancel);
			ItemLoader.evBoots
					.setEnergy(player.getCurrentArmor(3), ItemLoader.evBoots
							.getEnergy(player.getCurrentArmor(3)) - 500000);
			ItemLoader.evLeggings
					.setEnergy(player.getCurrentArmor(2), ItemLoader.evLeggings
							.getEnergy(player.getCurrentArmor(2)) - 500000);
			ItemLoader.evChestplate
					.setEnergy(player.getCurrentArmor(1),
							ItemLoader.evChestplate.getEnergy(player
									.getCurrentArmor(1)) - 500000);
			ItemLoader.evHelmet
					.setEnergy(player.getCurrentArmor(3), ItemLoader.evHelmet
							.getEnergy(player.getCurrentArmor(0)) - 500000);

		}
	}

}
