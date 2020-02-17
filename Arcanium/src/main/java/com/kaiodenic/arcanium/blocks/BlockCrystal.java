package com.kaiodenic.arcanium.blocks;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.kaiodenic.arcanium.init.Blocks;
import com.kaiodenic.arcanium.init.Items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends Block /*implements ITileEntityProvider*/ {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>(){
        public boolean apply(@Nullable EnumFacing p_apply_1_) {
            return true;
        }
    });

    protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3375D, -0.168268D, 0.3375D, 0.6625D, 0.58488D, 0.6625D);
    protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3375D, 0.41512D, 0.3375D, 0.6625D, 1.168268D, 0.6625D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.349311D, 0.221889D, 0.742874D, 0.645726D, 0.87206D, 1.242161D);
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.349311D, 0.221889D, -0.242161D, 0.645726D, 0.87206D, 0.257126D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(-0.242161D, 0.221889D, 0.349311D,  0.257126D, 0.87206D, 0.645726D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.742874D, 0.221889D, 0.349311D,  1.242161D, 0.87206D, 0.645726D);
    
	public BlockCrystal(String name, Material material) {
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(2.0f);
		setLightLevel(1.0f);
		setCreativeTab(CreativeTabs.REDSTONE);
		
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		Blocks.BLOCKS.add(this);
		Items.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		System.out.println("Crystal Broken");
	}
	
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }
    

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch ((EnumFacing)state.getValue(FACING)) {
            case EAST:
                return EAST_AABB;
            case WEST:
                return WEST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case NORTH:
                return NORTH_AABB;
            case DOWN:
                return DOWN_AABB;
            default:
                return UP_AABB;
        }
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public net.minecraft.util.BlockRenderLayer getBlockLayer() {
    	return BlockRenderLayer.TRANSLUCENT;
    }
	
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		System.out.println("------------------------------- Facing :: " + facing);
        return this.getDefaultState().withProperty(FACING, facing);
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	/*
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		System.out.println("FACING ------------------------------------- " + facing);
		return this.getDefaultState().withProperty(FACING, facing);
		
		
		
        if (this.canPlaceAt(worldIn, pos, facing)) {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                if (this.canPlaceAt(worldIn, pos, enumfacing)) {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }*/

	
	public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta) {
            case 1:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
                break;
            case 5:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN);
                break;
            default:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
        }

        return iblockstate;
    }
	
	public int getMetaFromState(IBlockState state) {
        int i = 0;

        switch ((EnumFacing)state.getValue(FACING)) {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case DOWN:
                i = i | 5;
                break;
            default:
                i = i | 0;
                break;
        }
        
        return i;
    }
	
	/*
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		System.out.println("FACING ------------------------------------- " + facing);
		return this.getDefaultState().withProperty(FACING, facing);
		
		
		
        if (this.canPlaceAt(worldIn, pos, facing)) {
            return this.getDefaultState().withProperty(FACING, facing);
        }
        else {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                if (this.canPlaceAt(worldIn, pos, enumfacing)) {
                    return this.getDefaultState().withProperty(FACING, enumfacing);
                }
            }

            return this.getDefaultState();
        }
    }
	
	public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta) {
            case 1:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
                break;
            case 5:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN);
                break;
            default:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
        }

        return iblockstate;
    }
	
	public int getMetaFromState(IBlockState state) {
        int i = 0;

        switch ((EnumFacing)state.getValue(FACING)) {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case DOWN:
                i = i | 6;
                break;
            case UP:
                i = i | 0;
                break;
            default:
                i = i | 5;
        }

        
        return i;
    }
	
	
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	if (placer == null) {
    		return;
    	}

		System.out.println("FACING 2 ----------------------------------- " + FACING);

    }
	*/
	
	/*
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	if (placer == null) {
    		return;
    	}

		System.out.println("CREATED ------------------------------------- 3");
    	TileEntityCrystal tileEntity = (TileEntityCrystal)world.getTileEntity(pos);
		System.out.println("POSITION ------------------------------------- (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
		System.out.println("DIRECTION ------------------------------------- " + tileEntity.getDirection());

    	//TileEntityCrystalSarqodite tile = (TileEntityCrystalSarqodite) par1World.getBlockTileEntity(x, y, z);
    	//tile.direction = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

    }*/
	
	/*
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		//System.out.println("CREATED ------------------------------------- 1");
		TileEntityCrystal tileEntity = new TileEntityCrystal();
		return new TileEntityCrystal();
	}*/

}
