package armor.item.armor;

import org.lwjgl.input.Keyboard;

import armor.control.KeyBindHandler;
import armor.control.PlayerInputMap;
import armor.control.PlayerUtils;
import armor.core.ArmorLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class Jetpack extends Eletric implements ISpecialArmor {

	public Jetpack(double maxElectricity, float voltage,
			ArmorMaterial material, int armortype, int rendertype) {
		super(maxElectricity, voltage, material, armortype, rendertype);
		this.setUnlocalizedName("jetChestplate");
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase entity,
			ItemStack stack, DamageSource source, double damage, int slot) {
		ArmorProperties armor = new ArmorProperties(10, 100, 2);

		return armor;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

		return 2;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack,
			DamageSource source, int damage, int slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		boolean hasJetpack = false;

		if (player.inventory.armorItemInSlot(2) != null) {
			ItemStack itemstack = player.inventory.armorItemInSlot(2);

			if (itemstack.getItem() instanceof Jetpack
					&& this.getEnergy(player.inventory.armorItemInSlot(2)) > 99) {

				if (Keyboard.isKeyDown(KeyBindHandler.goUpKey.getKeyCode())) {
					if (player.motionY > 0.0D) {
						player.motionY += 0.084999999105930327D;

						this.setEnergy(player.inventory.armorItemInSlot(2),
								this.getEnergy(player.inventory
										.armorItemInSlot(2)) - 500);
						world.spawnParticle("smoke", player.posX,
								player.posY - 1.0D, player.posZ, 0.0D, 0.0D,
								0.0D);

					} else {
						player.motionY += 0.11699999910593033D;
					}
				}

				if (player.motionY < 0.0D) {
					player.motionY /= 1.1499999761581421D;

				}

				if (!player.onGround) {

					player.motionX *= 1.0399999618530273D;
					player.motionZ *= 1.0399999618530273D;

				}

				player.fallDistance = 0.0F;
				hasJetpack = true;
			} else {
				hasJetpack = false;
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
