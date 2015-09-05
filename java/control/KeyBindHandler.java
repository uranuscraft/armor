package armor.control;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;

public class KeyBindHandler {

	public static KeyBinding light = new KeyBinding(
			"Turn on the lights", Keyboard.KEY_L,
			"Electric Armor Keybinds");
	public static KeyBinding goDownKey = new KeyBinding("go down",
			Keyboard.KEY_P, "Electric Armor Keybinds");
	public static KeyBinding goUpKey = new KeyBinding("go up", Keyboard.KEY_Z,
			"Electric Armor Keybinds");
	public static KeyBinding dark = new KeyBinding(
			"Turn off the lights", Keyboard.KEY_U,
			"Electric Armor Keybinds");
	public static KeyBinding[] keybindArray = new KeyBinding[] { light,
			goDownKey, goUpKey,dark };
	public static boolean[] repeats = new boolean[keybindArray.length];

}
