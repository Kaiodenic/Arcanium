package com.kaiodenic.arcanium.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleWayPulse extends Particle {
	public static final double defaultParticleSpeed = 0.4d;
	private double particleSpeed;
	private Vec3 delta;
	private static final int growthTime = 10;
	private static final float startScale = 0.25f;
	private static final float finalScale = 1.0f;
	
	protected ParticleWayPulse (World world, double x, double y, double z, double speedX, double speedY, double speedZ) {
		this (world, x, y, z, 1.0f, speedX, speedY, speedZ);
	}
	
	protected ParticleWayPulse(World world, double originX, double originY, double originZ, float scale, double speedX, double speedY, double speedZ) {
		super(world, originX, originY, originZ, 0.0D, 0.0D, 0.0D);
		
		// For these particles, we treat the speed(x, y, z) as a target_position(x, y, z)
		Vec3 speed = new Vec3(speedX, speedY, speedZ);
		Vec3 origin = new Vec3(originX, originY, originZ);
		
		Vec3 direction = speed.subtract(origin);
		double distanceToTarget = direction.getLength();
		
		// distance travelled per tick
        this.particleMaxAge = (int) Math.ceil(distanceToTarget / defaultParticleSpeed);
        
        // Now that we had to round the particle age, let's change the travel speed to match
        particleSpeed = distanceToTarget / this.particleMaxAge;
        
        // Though I've created a Vec3.getUniVector() function, that also does its own getLength() function.
        // Since that's already been calculated, it's an unnecessary use of resources to re-calculate that.
		Vec3 unitDirection = direction.divide(distanceToTarget);
		delta = unitDirection.multiply(defaultParticleSpeed);
		
		motionX = delta.x;
		motionY = delta.y;
		motionZ = delta.z;
		
		posX = originX;
		posY = originY;
		posZ = originZ;
		
		particleRed = 1.0f;
		particleGreen = 1.0f;
		particleBlue = 1.0f;
		this.setParticleTextureIndex(48);
	}
	
	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}
	
	public static float mix(float a, float b, float scale) {
		return (1.0f - scale) * a + scale * b;
	}

    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
    	// Make the particle grow when its spawned and shrink as it reaches its destination
    	int startGrowth = particleAge;
    	int endGrowth = particleMaxAge - particleAge;
    	float growth = (float)MathHelper.clamp(Math.min(startGrowth, endGrowth), 0, growthTime);
    	
    	particleScale = mix(startScale, finalScale, growth / growthTime);
    	
    	super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
    
    // No idea. Input is undefined in Forge
    public int getBrightessForRender(float input) {
    	float f = ((float)this.particleAge + input) / (float) this.particleMaxAge;
    	f = MathHelper.clamp(f,  0.0f, 1.0f);
    	int i = super.getBrightnessForRender(input);
    	int j = i & 255;
    	int k = i >> 16 & 255;
    	j = j + (int)(f * 15.0f * 16.0f);
    	
    	if (j > 240) {
    		j = 250;
    	}
    	
    	return j | k << 16;
    }
    
    public void onUpdate() {
    	prevPosX = posX;
    	prevPosY = posY;
    	prevPosZ = posZ;
    	
    	if (particleAge++ >= particleMaxAge) {
    		this.setExpired();
    	}
    	
    	this.move(motionX, motionY, motionZ);
    }
    
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
    	public Particle createParticle(int id, World world, double originX, double originY, double originZ, double speedX, double speedY, double speedZ, int... otherArguments) {
    		return new ParticleWayPulse(world, originX, originY, originZ, speedX, speedY, speedZ);
    	}
    }
}
