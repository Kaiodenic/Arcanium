package com.kaiodenic.arcanium.particles;

public class Vec3 {
	double x;
	double y;
	double z;

	public Vec3(double inX, double inY, double inZ) {
		x = inX;
		y = inY;
		z = inZ;
	}
	
	public Vec3() {
		this(0.0d, 0.0d, 0.0d);
	}

	public double getLength() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double dot(Vec3 other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	public Vec3 getUnitVector() {
		return divide(getLength());
	}
	
	public Vec3 cross(Vec3 other) {
		Vec3 output = new Vec3();
		output.x = y * other.z - z * other.y;
		output.y = z * other.x - x * other.z;
		output.z = x * other.y - y * other.x;
		
		return output;
	}

	public Vec3 divide(double a) {
		return new Vec3(x / a, y / a, z / a);
	}

	public Vec3 multiply(double a) {
		return new Vec3(x * a, y * a, z * a);
	}

	public Vec3 direction(Vec3 other) {
		return new Vec3(other.x - x, other.y - y, other.z - z);
	}

	public Vec3 add(Vec3 other) {
		return new Vec3(x + other.x, y + other.y, z + other.z);
	}

	public Vec3 subtract(Vec3 other) {
		return new Vec3(x - other.x, y - other.y, z - other.z);
	}
}
