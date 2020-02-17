package com.kaiodenic.arcanium.tilentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityCrystal extends TileEntity implements ITickable {
	int direction;
	
	public TileEntityCrystal() {
		//System.out.println("CREATED ------------------------------------- 2");
		direction = 0;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int dir) {
		direction = dir;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		direction = nbt.getInteger("direction");
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("direction", direction);
		return super.writeToNBT(nbt);
	}

	@Override
	public void update() {
		
	}
}
