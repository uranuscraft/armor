package armor.control;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class PlayerUtils {
	static final double root2 = Math.sqrt(2);

	public static double thrust(EntityPlayer player, double thrust,
			boolean flightControl) {
		PlayerInputMap movementInput = PlayerInputMap.getInputMapFor(player
				.getDisplayName());
		boolean jumpkey = movementInput.jumpKey;
		float forwardkey = movementInput.forwardKey;
		float strafekey = movementInput.strafeKey;
		boolean downkey = movementInput.downKey;
		boolean sneakkey = movementInput.sneakKey;
		double thrustUsed = 0;
		if (flightControl) {
			Vec3 desiredDirection = player.getLookVec().normalize();
			double strafeX = desiredDirection.zCoord;
			double strafeZ = -desiredDirection.xCoord;
			double scaleStrafe = (strafeX * strafeX + strafeZ * strafeZ);
			double flightVerticality = 0;
			ItemStack helm = player.getCurrentArmor(3);

			desiredDirection.xCoord = desiredDirection.xCoord
					* Math.signum(forwardkey) + strafeX
					* Math.signum(strafekey);
			desiredDirection.yCoord = flightVerticality
					* desiredDirection.yCoord * Math.signum(forwardkey)
					+ (jumpkey ? 1 : 0) - (downkey ? 1 : 0);
			desiredDirection.zCoord = desiredDirection.zCoord
					* Math.signum(forwardkey) + strafeZ
					* Math.signum(strafekey);

			desiredDirection = desiredDirection.normalize();
			// Gave up on this... I suck at math apparently
			// double ux = player.motionX / thrust;
			// double uy = player.motionY / thrust;
			// double uz = player.motionZ / thrust;
			// double vx = desiredDirection.xCoord;
			// double vy = desiredDirection.yCoord;
			// double vz = desiredDirection.zCoord;
			// double b = (2 * ux * vx + 2 * uy * vy + 2 * uz * vz);
			// double c = (ux * ux + uy * uy + uz * uz - 1);
			//
			// double actualThrust = (-b + Math.sqrt(b * b - 4 * c))
			// / (2);
			//
			// player.motionX = desiredDirection.xCoord *
			// actualThrust;
			// player.motionY = desiredDirection.yCoord *
			// actualThrust;
			// player.motionZ = desiredDirection.zCoord *
			// actualThrust;

			// Brakes
			if (player.motionY < 0 && desiredDirection.yCoord >= 0) {
				if (-player.motionY > thrust) {
					player.motionY += thrust;
					thrustUsed += thrust;
					thrust = 0;
				} else {
					thrust -= player.motionY;
					thrustUsed += player.motionY;
					player.motionY = 0;
				}
			}
			if (player.motionY < -1) {
				thrust += 1 + player.motionY;
				thrustUsed -= 1 + player.motionY;
				player.motionY = -1;
			}
			if (Math.abs(player.motionX) > 0
					&& desiredDirection.lengthVector() == 0) {
				if (Math.abs(player.motionX) > thrust) {
					player.motionX -= Math.signum(player.motionX) * thrust;
					thrustUsed += thrust;
					thrust = 0;
				} else {
					thrust -= Math.abs(player.motionX);
					thrustUsed += Math.abs(player.motionX);
					player.motionX = 0;
				}
			}
			if (Math.abs(player.motionZ) > 0
					&& desiredDirection.lengthVector() == 0) {
				if (Math.abs(player.motionZ) > thrust) {
					player.motionZ -= Math.signum(player.motionZ) * thrust;
					thrustUsed += thrust;
					thrust = 0;
				} else {
					thrustUsed += Math.abs(player.motionZ);
					thrust -= Math.abs(player.motionZ);
					player.motionZ = 0;
				}
			}

			// Thrusting, finally :V
			double vx = thrust * desiredDirection.xCoord;
			double vy = thrust * desiredDirection.yCoord;
			double vz = thrust * desiredDirection.zCoord;
			player.motionX += vx;
			player.motionY += vy;
			player.motionZ += vz;
			thrustUsed += thrust;

		} else {
			Vec3 playerHorzFacing = player.getLookVec();
			playerHorzFacing.yCoord = 0;
			playerHorzFacing.normalize();
			if (forwardkey == 0) {
				player.motionY += thrust;
			} else {
				player.motionY += thrust / root2;
				player.motionX += playerHorzFacing.xCoord * thrust / root2
						* Math.signum(forwardkey);
				player.motionZ += playerHorzFacing.zCoord * thrust / root2
						* Math.signum(forwardkey);
			}
			thrustUsed += thrust;
		}

		// Slow the player if they are going too fast
		double horzm2 = player.motionX * player.motionX + player.motionZ
				* player.motionZ;
		double horzmlim = 25 * 25 / 400;
		if (sneakkey && horzmlim > 0.05) {
			horzmlim = 0.05;
		}

		if (horzm2 > horzmlim) {
			double ratio = Math.sqrt(horzmlim / horzm2);
			player.motionX *= ratio;
			player.motionZ *= ratio;
		}

		return thrustUsed;
	}

}
