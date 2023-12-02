package io.github.unix_supremacist.alchemist.interfaces;

import io.github.unix_supremacist.alchemist.content.AlchemistItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface TransmuteEntity {
        default boolean transmuteEntity(EntityLiving entity, World level){
        if(entity instanceof EntityVillager){
            level.spawnEntityInWorld(new EntityItem(level, entity.getPosition(0).xCoord, entity.getPosition(0).yCoord, entity.getPosition(0).zCoord, new ItemStack(AlchemistItems.philosophers_stone.getItem())));
            entity.kill();
            return true;
        }
        return false;
    }
}
