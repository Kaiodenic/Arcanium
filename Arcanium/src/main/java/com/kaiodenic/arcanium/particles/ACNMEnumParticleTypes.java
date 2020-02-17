package com.kaiodenic.arcanium.particles;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public enum ACNMEnumParticleTypes {
	WAY_PULSE("way_pulse", 30, false);
	
	private final String name;
	private final int id;
	private final boolean shouldIgnoreRange;
	private final int argumentCount;
	private static final Map<Integer, ACNMEnumParticleTypes> PARTICLES = Maps.<Integer, ACNMEnumParticleTypes>newHashMap();
	private static final Map<String, ACNMEnumParticleTypes> BY_NAME = Maps.<String, ACNMEnumParticleTypes>newHashMap();
	
	private ACNMEnumParticleTypes(String name, int id, boolean shouldIgnoreRange, int argumentCount) {
		this.name = name;
		this.id = id;
		this.shouldIgnoreRange = shouldIgnoreRange;
		this.argumentCount = argumentCount;
	}
	
	private ACNMEnumParticleTypes(String name, int id, boolean shouldIgnoreRange) {
		this(name, id, shouldIgnoreRange, 0);
	}
	
	public static Set<String> getParticleNames() {
		return BY_NAME.keySet();
	}
	
	public String getParticleName() {
		return this.name;
	}
	
	public int getParticleID() {
		return id;
	}
	
	public int getArgumentCount() {
		return argumentCount;
	}
	
	public boolean getShouldIgnoreRange() {
		return shouldIgnoreRange;
	}
	
	@Nullable
	public static ACNMEnumParticleTypes getParticleFromID(int id) {
		return PARTICLES.get(Integer.valueOf(id));
	}
	
	@Nullable
	public static ACNMEnumParticleTypes getByName(String name) {
		return BY_NAME.get(name);
	}
	
	static {
		for (ACNMEnumParticleTypes enumParticleTypes : values()) {
			PARTICLES.put(Integer.valueOf(enumParticleTypes.getParticleID()), enumParticleTypes);
			BY_NAME.put(enumParticleTypes.getParticleName(), enumParticleTypes);
		}
	}
}
