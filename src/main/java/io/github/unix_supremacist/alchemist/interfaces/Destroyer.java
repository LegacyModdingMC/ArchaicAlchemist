package io.github.unix_supremacist.alchemist.interfaces;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface Destroyer {
    default void destroy(World level, BlockPos pos, EntityLivingBase entity){
        if(!level.isRemote && !(level.getBlockState(pos).getBlock().defaultDestroyTime() < 0)){
            if (entity instanceof EntityPlayer)
                level.destroyBlock(pos, !((EntityPlayer) entity).isCreative());
            else
                level.destroyBlock(pos, true);
        }
    }

    default boolean destroyArea(World level, ArrayList<BlockPos> blocks, EntityLivingBase entity){
        if(!level.isRemote){
            if(!blocks.isEmpty()){
                for (BlockPos b : blocks){
                    destroy(level, b, entity);
                }
                return true;
            }
        }
        return false;
    }
}
