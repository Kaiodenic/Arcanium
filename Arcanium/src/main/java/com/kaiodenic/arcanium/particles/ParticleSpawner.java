package com.kaiodenic.arcanium.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;

public class ParticleSpawner {
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static Particle spawnParticle(ACNMEnumParticleTypes type, double x, double y, double z, double speedX, double speedY, double speedZ) {
		// if particles are enabled
		if (mc != null && mc.getRenderViewEntity() != null && mc.effectRenderer != null) {
			int particleSetting = mc.gameSettings.particleSetting;
			
			if (particleSetting == 1 && mc.world.rand.nextInt(3) == 0) {
				particleSetting = 2;
			}

			double renderViewX = mc.getRenderViewEntity().posX - x;
			double renderViewY = mc.getRenderViewEntity().posY - y;
			double renderViewZ = mc.getRenderViewEntity().posZ - z;
			
			Particle particle = null;
			double var22 = 16.0D;
			
			if (renderViewX * renderViewX + renderViewY * renderViewY + renderViewZ * renderViewZ > var22 * var22) {
				return null;
			}
			else if (particleSetting > 1) {
				return null;
			}
			else {
				if (type == ACNMEnumParticleTypes.WAY_PULSE) {
					particle = new ParticleWayPulse(mc.world, x, y, z, speedX, speedY, speedZ);
				}
				
				mc.effectRenderer.addEffect(particle);
				return particle;
			}
		}
		
		return null;
	}
}
