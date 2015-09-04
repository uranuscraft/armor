package armor.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;

public class Ic2Recipe implements IRecipeInput {

	public Ic2Recipe(ItemStack input, int amount) {
		this.input = input;
		this.amount = amount;
	}

	@Override
	public boolean matches(ItemStack subject) {
		return subject.getItem().toString().equals(input.getItem().toString())
				&& (subject.getItemDamage() == input.getItemDamage() || input
						.getItemDamage() == OreDictionary.WILDCARD_VALUE);
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public List<ItemStack> getInputs() {
		return Arrays.asList(input);
	}

	public final ItemStack input;
	public final int amount;
}
