package armor.item.armor;

import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;

import java.util.List;

import armor.core.ArmorLoader;
import mekanism.api.EnumColor;
import mekanism.api.energy.IEnergizedItem;
import mekanism.api.util.UnitDisplayUtils;
import mekanism.api.util.UnitDisplayUtils.ElectricUnit;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
//1.6.4import universalelectricity.core.electricity.ElectricityDisplay;
//1.6.4import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
//1.6.4import universalelectricity.core.item.IItemElectric;
import cofh.api.energy.IEnergyContainerItem;

public class Eletric extends ItemArmor implements IEnergizedItem { // 1.6.4IItemElectric{
	/** The maximum amount of energy this item can hold. */
	public double MAX_ELECTRICITY;

	/** How fast this item can transfer energy. */
	public float VOLTAGE;

	public Eletric(double maxElectricity, float voltage,
			ArmorMaterial material, int armortype, int rendertype) {
		super(material, armortype, rendertype);
		MAX_ELECTRICITY = maxElectricity;
		VOLTAGE = voltage;
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer,
			List list, boolean flag) {
		list.add(EnumColor.AQUA + "Stored Energy: " + EnumColor.GREY
				+ getEnergyDisplay(getEnergy(itemstack)));

	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArmorLoader.modid
				+ getUnlocalizedName().replace("item.", ""));
	}

	/**
	 * 1.6.4 @Override public float getVoltage(ItemStack itemStack) { return
	 * VOLTAGE; }
	 **/

	@Override
	public double getEnergy(ItemStack itemStack) {

		if (itemStack != null) {
			if (itemStack.stackTagCompound == null) {
				return 0;
			}

			double electricityStored = itemStack.stackTagCompound
					.getDouble("electricity");
			itemStack
					.setItemDamage((int) Math.max(
							1,
							(Math.abs(((electricityStored / getMaxEnergy(itemStack)) * 100) - 100))));

			return electricityStored;

		} else {
			return 0;
		}

	}

	@Override
	public void setEnergy(ItemStack itemStack, double amount) {

		if (itemStack != null) {
			if (itemStack.stackTagCompound == null) {
				itemStack.setTagCompound(new NBTTagCompound());
			}

			double electricityStored = Math.max(
					Math.min(amount, getMaxEnergy(itemStack)), 0);
			itemStack.stackTagCompound.setDouble("electricity",
					electricityStored);
			itemStack
					.setItemDamage((int) Math.max(
							1,
							(Math.abs(((electricityStored / getMaxEnergy(itemStack)) * 100) - 100))));
		} else {

		}

	}

	@Override
	public double getMaxEnergy(ItemStack itemStack) {
		return MAX_ELECTRICITY;
	}

	@Override
	public double getMaxTransfer(ItemStack itemStack) {
		return getMaxEnergy(itemStack) * 0.005;
	}

	@Override
	public boolean canReceive(ItemStack itemStack) {
		return true;
	}

	@Override
	public boolean canSend(ItemStack itemStack) {
		return true;
	}

	@Override
	public void onCreated(ItemStack itemstack, World world,
			EntityPlayer entityplayer) {
		itemstack = getUnchargedItem();
	}

	/**
	 * 1.6.4
	 * 
	 * @Override public void getSubItems(int i, CreativeTabs tabs, List list) {
	 *           ItemStack discharged = new ItemStack(this);
	 *           discharged.setItemDamage(100); list.add(discharged); ItemStack
	 *           charged = new ItemStack(this); setEnergy(charged,
	 *           ((IEnergizedItem)charged.getItem()).getMaxEnergy(charged));
	 *           list.add(charged); }
	 **/

	/**
	 * 1.6.4
	 * 
	 * @Override public float recharge(ItemStack itemStack, float energy,
	 *           boolean doRecharge) { if(canReceive(itemStack)) { double
	 *           energyNeeded = getMaxEnergy(itemStack)-getEnergy(itemStack);
	 *           double toReceive = Math.min(energy*this.FROM_UE, energyNeeded);
	 * 
	 *           if(doRecharge) { setEnergy(itemStack, getEnergy(itemStack) +
	 *           toReceive); }
	 * 
	 *           return (float)(toReceive*this.TO_UE); }
	 * 
	 *           return 0; }
	 * @Override public float discharge(ItemStack itemStack, float energy,
	 *           boolean doDischarge) { if(canSend(itemStack)) { double
	 *           energyRemaining = getEnergy(itemStack); double toSend =
	 *           Math.min((energy*this.FROM_UE), energyRemaining);
	 * 
	 *           if(doDischarge) { setEnergy(itemStack, getEnergy(itemStack) -
	 *           toSend); }
	 * 
	 *           return (float)(toSend*this.TO_UE); }
	 * 
	 *           return 0; }
	 * @Override public float getElectricityStored(ItemStack theItem) { return
	 *           (float)(getEnergy(theItem)*this.TO_UE); }
	 * @Override public float getMaxElectricityStored(ItemStack theItem) {
	 *           return (float)(getMaxEnergy(theItem)*this.TO_UE); }
	 * @Override public void setElectricity(ItemStack itemStack, float joules) {
	 *           setEnergy(itemStack, joules*this.TO_UE); }
	 * @Override public float getTransfer(ItemStack itemStack) { return
	 *           (float)(getMaxTransfer(itemStack)*this.TO_UE); }
	 **/

	public static double ENERGY_PER_REDSTONE = 10000;

	public static String getEnergyDisplay(double energy) {
		return UnitDisplayUtils.getDisplayShort((float) (energy * TO_UE),
				ElectricUnit.JOULES);
	}

	public static double TO_IC2;
	public static double TO_BC;
	public static double TO_TE;
	public static double TO_UE = .001;
	public static double FROM_IC2;
	public static double FROM_BC;
	public static double FROM_TE;
	public static double FROM_UE = 1000;

	public ItemStack getChargedItem() {
		ItemStack charged = new ItemStack(this);

		charged.setItemDamage(0);
		return charged;
	}

	public ItemStack getUnchargedItem() {

		ItemStack charged = new ItemStack(this);

		this.setEnergy(charged, 0);

		return charged;
	}

	@Override
	public boolean isMetadataSpecific(ItemStack itemStack) {

		return false;
	}

}
