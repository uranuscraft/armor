package armor.client;

import armor.common.CommonProxy;
import armor.control.KeyBindHandler;
//1.6.4 import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public int getArmorIndex(String string) {
		return RenderingRegistry.addNewArmourRendererPrefix(string);
	}

	public static KeyBindHandler keybindHandler;

	@Override
	public void registerHandlers() {
		super.registerHandlers();

		// 1.6.4 KeyBindingRegistry.registerKeyBinding(keybindHandler);
		for (int i = 0; i < keybindHandler.keybindArray.length; i++) {
			ClientRegistry.registerKeyBinding(keybindHandler.keybindArray[i]);
		}
	}
}
