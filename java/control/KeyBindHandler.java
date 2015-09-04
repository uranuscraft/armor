package armor.control;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;

public class KeyBindHandler {

	public static KeyBinding light = new KeyBinding(
			"Turn on the lights in the scuba suit/ ev suit", Keyboard.KEY_L,
			"Electric Armor Keybinds");
	public static KeyBinding goDownKey = new KeyBinding("go down",
			Keyboard.KEY_P, "Electric Armor Keybinds");
	public static KeyBinding goUpKey = new KeyBinding("go up", Keyboard.KEY_Z,
			"Electric Armor Keybinds");

	public static KeyBinding[] keybindArray = new KeyBinding[] { light,
			goDownKey, goUpKey };
	public static boolean[] repeats = new boolean[keybindArray.length];

}
