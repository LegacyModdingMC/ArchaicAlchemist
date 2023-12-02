package io.github.unix_supremacist.alchemist.block;

import io.github.unix_supremacist.alchemist.interfaces.TransmuteEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class TransmutionCircleBlock extends Block implements TransmuteEntity {
    public TransmutionCircleBlock(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World level, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if (!level.isRemote) {
            AxisAlignedBB aABB = AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1);
            List< EntityVillager> villagerList = level.getEntitiesWithinAABB(EntityVillager.class, aABB);
            if(!villagerList.isEmpty()){
                level.setBlock(x, y, z, Blocks.air);
                return transmuteEntity(villagerList.get(0), level);
            }
        }

        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess ba, int x, int y, int z) {
        setBlockBounds(0, 0.005F, 0, 1, 0.005F, 1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w_, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, 1+x, 0.005+y, 1+z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
