package armor.api;

import java.util.HashMap;


import net.minecraft.item.ItemStack;

public class APIMaterials {
	
	public HashMap<ItemStack, Integer> semimap = new HashMap<ItemStack, Integer>();
	public HashMap<ItemStack,Integer> condmap = new HashMap<ItemStack, Integer>();
	public HashMap<ItemStack, Integer> glassmap = new HashMap<ItemStack, Integer>();
	
	public void AddConductor(ItemStack stack,int multiplier, String name){
		this.condmap.put(stack, multiplier);
	}
public void AddGlass(ItemStack stack,int multiplier, String name){
	this.glassmap.put(stack, multiplier);
	}
public void AddSemiConductor(ItemStack stack,int multiplier, String name){
	this.semimap.put(stack, multiplier);
	
}
	public HashMap<ItemStack, Integer> GetConductors() {
		return condmap;
	}
	public HashMap<ItemStack, Integer> GetGlasses() {
		return glassmap;
	}
	public HashMap<ItemStack, Integer> GetSemiConductors() {
		return semimap;
	}
}
