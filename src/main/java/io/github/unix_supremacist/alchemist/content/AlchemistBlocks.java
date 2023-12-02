package io.github.unix_supremacist.alchemist.content;

import cpw.mods.fml.common.registry.GameRegistry;
import io.github.unix_supremacist.alchemist.block.TransmutionCircleBlock;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public enum AlchemistBlocks {
    transmutation_circle(new TransmutionCircleBlock(Material.snow));
    @Getter
    Block block;

    AlchemistBlocks(Block block){
        this.block = block;
        GameRegistry.registerBlock(block, this.name());
    }
}
