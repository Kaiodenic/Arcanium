package com.kaiodenic.arcanium.tilentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kaiodenic.arcanium.particles.ACNMEnumParticleTypes;
import com.kaiodenic.arcanium.particles.ParticleSpawner;
import com.kaiodenic.arcanium.particles.ParticleWayPulse;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBlockWayCrystal extends TileEntity implements ITickable {
	int direction;
	int k;
	List<TileEntityBlockWayCrystal> partners;
	List<Integer> particleTimers;
	List<Integer> particleSpawnCooldowns;
	int particleLife;
	// Want to spawn roughly 1 particles per 4 blocks
	double distancePerParticle = 4.0f;
	int particleSpawnTime = 10;
	
	
	float particleGrowthDistance = 4.0f;
	float particleMaxDistance = particleGrowthDistance * 2.0f;
	double defaultParticleSpeed = ParticleWayPulse.defaultParticleSpeed;
	float particleLifetime = (float) (particleMaxDistance / defaultParticleSpeed);
	
	public TileEntityBlockWayCrystal() {
		direction = 0;
		k = 0;
		partners = new ArrayList<TileEntityBlockWayCrystal>();
		particleTimers = new ArrayList<Integer>();
		particleSpawnCooldowns = new ArrayList<Integer>();
	}
	
	public List<TileEntityBlockWayCrystal> getPartners() {
		return partners;
	}
	
	public void addTarget(TileEntityBlockWayCrystal partner) {
		if (!partners.contains(partner)) {
			partners.add(partner);
			
			BlockPos origin = getPos();
			BlockPos target = partner.getPos();
			double distance = origin.getDistance(target.getX(), target.getY(), target.getZ());
			
			if (distance > particleMaxDistance) {
				double particlesUnrounded = distance / particleMaxDistance;
				int particlesRounded = (int)Math.floor(particlesUnrounded);
				
				if ((particlesUnrounded - (double)particlesRounded) > 0.5f) {
					particlesRounded++;
				}
				
				int cooldownPerParticle = (int) (particleLifetime / particlesRounded);

				System.out.println("[0]particleMaxDistance ----------------- " + particleMaxDistance);
				System.out.println("[0]distance ----------------- " + distance);
				System.out.println("[0]particlesUnrounded ----------------- " + particlesUnrounded);
				System.out.println("[0]particlesRounded ----------------- " + particlesRounded);
				System.out.println("[0]cooldownPerParticle ----------------- " + cooldownPerParticle);
				System.out.println();
				System.out.println();
				
				particleSpawnCooldowns.add(cooldownPerParticle);
				particleTimers.add(0);
			}
			else {
				double totalTravelTime = distance / defaultParticleSpeed;
				int cooldownPerParticle = (int) (totalTravelTime);

				System.out.println("[1]distance ----------------- " + distance);
				System.out.println("[1]defaultParticleSpeed -------------- " + defaultParticleSpeed);
				System.out.println("[1]totalTravelTime -------------- " + totalTravelTime);
				System.out.println("[1]particleLifetime ----------------- " + particleLifetime);
				System.out.println("[1]cooldownPerParticle -------------- " + cooldownPerParticle);
				
				particleSpawnCooldowns.add(cooldownPerParticle);
				particleTimers.add(0);
			}
		}
	}
	
	public void removeTarget(TileEntityBlockWayCrystal partner) {
		int index = partners.indexOf(partner);
		partners.remove(partner);
		particleTimers.remove(index);
		particleSpawnCooldowns.remove(index);
	}
	
	public void pair(TileEntityBlockWayCrystal partner) {
		addTarget(partner);
		partner.addTarget(this);
	}
	
	public void onBlockBreak() {
		System.out.println("Breaking at :: " + getPos());
		for (TileEntityBlockWayCrystal partner : partners) {
			partner.removeTarget(this);
		}
		
		partners.clear();
		particleTimers.clear();
		particleSpawnCooldowns.clear();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		int[] partnerCoords = nbt.getIntArray("partner_coords");
		
		World world = getWorld();
		for (int i = 0; i < partnerCoords.length; i += 3) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(partnerCoords[i], partnerCoords[i + 1], partnerCoords[i + 2]));
			
			if (tileEntity instanceof TileEntityBlockWayCrystal) {
				partners.add((TileEntityBlockWayCrystal)tileEntity);
			}
		}
		
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		int[] partnerCoords = new int[partners.size() * 3];
		
		for (int i = 0; i < partners.size(); i++) {
			partnerCoords[i * 3] = partners.get(i).getPos().getX();
			partnerCoords[i * 3 + 1] = partners.get(i).getPos().getY();
			partnerCoords[i * 3 + 2] = partners.get(i).getPos().getZ();
		}
		
		nbt.setIntArray("partner_coords", partnerCoords);
		
		return super.writeToNBT(nbt);
	}
	
	public void printTestStatement(String text) {
		System.out.println("[" + getDisplayName() + " @ (" + getPos().getX() + ", " + getPos().getY() + ", " + getPos().getZ() + ") :: " + text);
	}
	
	public float getLength(float[] pos) {
		return (float)Math.sqrt(pos[0] * pos[0] + pos[1] * pos[1] + pos[2] * pos[2]);
	}
	
	public float[] divide(float[] pos, float a) {
		float[] output = new float[3];

		output[0] = pos[0] / a;
		output[1] = pos[1] / a;
		output[2] = pos[2] / a;
		
		return output;
	}
	
	public float[] multiply(float[] pos, float a) {
		float[] output = new float[3];

		output[0] = pos[0] * a;
		output[1] = pos[1] * a;
		output[2] = pos[2] * a;
		
		return output;
	}
	
	public float[] direction(float[] a, float[] b) {
		float[] output = new float[3];

		output[0] = b[0] - a[0];
		output[1] = b[1] - a[1];
		output[2] = b[2] - a[2];
		
		return output;
	}
	
	public float[] add(float[] a, float[] b) {
		float[] output = new float[3];

		output[0] = a[0] + b[0];
		output[1] = a[1] + b[1];
		output[2] = a[2] + b[2];
		
		return output;
	}
	
	public float[] subtract(float[] a, float[] b) {
		float[] output = new float[3];

		output[0] = a[0] - b[0];
		output[1] = a[1] - b[1];
		output[2] = a[2] - b[2];
		
		return output;
	}
	
	public void spawnWayParticle() {
		BlockPos thisPos = getPos();
		float[] origin = new float[] { thisPos.getX() + 0.5f, thisPos.getY() + 0.5f, thisPos.getZ() + 0.5f}; 
		
		for (int partnerID = 0; partnerID < partners.size(); partnerID++) {
			//System.out.println("TIMER ----------------- [" + partnerID + "]" + particleTimers.get(partnerID));
			particleTimers.set(partnerID, particleTimers.get(partnerID) - 1);
			//System.out.println("TIMER ----------------- [" + partnerID + "]" + particleTimers.get(partnerID));
				
			if (particleTimers.get(partnerID) <= 0) {
				particleTimers.set(partnerID, particleSpawnCooldowns.get(partnerID));
				
				TileEntityBlockWayCrystal partner = partners.get(partnerID);
				
				BlockPos targetPos = new BlockPos(partner.getPos().getX(), partner.getPos().getY(), partner.getPos().getZ());
				float[] target = new float[] { targetPos.getX() + 0.5f, targetPos.getY() + 0.5f, targetPos.getZ() + 0.5f};
				
				float[] direction = direction(origin, target);
				float length = getLength(direction);
				float[] directionUnit = divide(direction, length);
				
				float travelDistance = 0.1f;
				
				// GrowthTime = 20, speed = 0.2, therefore growthDistance = 4 blocks
				float growthDistance = 4.0f;
				float maxDistance = growthDistance * 2.0f;
				
				float[] movement = multiply(directionUnit, travelDistance);
				
				World world = getWorld();
				Random random = new Random();
				
				if (length >= maxDistance) {
					// Generate a random point between 0.0 - (length - maxDistance)
					float possibleSpawnDistance = length - maxDistance;
					float spawnPoint = random.nextFloat() * possibleSpawnDistance;
					
					// Generates a point across the line attaching origin and target, spawnPos-distance away across
					// (If origin is at (o), target is at (t) and is of distance 10 away from origin, and spawnPoint is at (s) and is equal to 2,
					// o-s-------t, while if t is 10 distance away from origin and spawnPoint is still equal to 2,
					// o---s---------------t
					float[] startVector = multiply(directionUnit, spawnPoint);
					
					// Generates a vector from the world origin to our crytal origin + startVector
					float[] startPos = add(origin, startVector);
					// Generate a point maxDistance further alone the line between origin and target (making the distance(startPoint->endPos) = maxDistance)
					float[] endPos = add(origin, multiply(directionUnit, spawnPoint + maxDistance));
					
					ParticleSpawner.spawnParticle(ACNMEnumParticleTypes.WAY_PULSE, startPos[0], startPos[1], startPos[2], endPos[0], endPos[1], endPos[2]);
				}
				else {
					float[] startPos = origin;
					float[] endPos = direction;
					
					ParticleSpawner.spawnParticle(ACNMEnumParticleTypes.WAY_PULSE, startPos[0], startPos[1], startPos[2], endPos[0], endPos[1], endPos[2]);
				}
			}
		}
		
	}

	
	@Override
	public void update() {
		if (world.isRemote) {
			spawnWayParticle();
		}
	}
}
