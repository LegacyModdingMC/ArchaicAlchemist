package io.github.unix_supremacist.alchemist.content;

import cpw.mods.fml.common.registry.GameRegistry;
import io.github.unix_supremacist.alchemist.Tags;
import io.github.unix_supremacist.alchemist.item.*;
import lombok.Getter;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public enum AlchemistItems {
    alchemical_coal(25600),
    aeternalis_fuel(1638400),
    //black_hole_band,
    catalytic_lens(new DestructionItem(3, 10, 5).setMaxStackSize(1)),
    chalk(new AlchemistBlockItem(AlchemistBlocks.transmutation_circle.getBlock()).setMaxDamage(64)),
    coal_coke(3200),
    dark_matter,
    //dark_matter_pickaxe,
    //dark_matter_shovel,
    //dark_matter_hoe,
    //dark_matter_sword,
    //dark_matter_axe,
    //dark_matter_shears,
    //dark_matter_hammer(new AlchemistHammerItem(Tiers.NETHERITE, 1, -2.8f, new Properties())),
    destruction_catalyst(new DestructionItem(3, 5, 1).setMaxStackSize(16)),
    evertide_amulet(new AlchemistBlockItem(Blocks.water).setMaxStackSize(1)),
    //gem_of_eternal_density,
    //harvest_goddess_band,
    //hyperkinetic_lens,
    iron_band,
    mobius_fuel(204800),
    philosophers_stone(new PhilosophersStoneItem().setMaxStackSize(1)),
    red_matter,
    swiftwolfs_rending_gale(new GaleItem().setMaxStackSize(1)),
    volcanite_amulet(new AlchemistBlockItem(Blocks.lava).setMaxDamage(64)),
    ;
    @Getter Item item;
    AlchemistItems(){
        this(0);
    }

    AlchemistItems(int burntime){
        //this(new Properties(), burntime);
    }

    AlchemistItems(Properties properties){
        //this(new Item(properties), 0);
    }

    AlchemistItems(Properties properties, int burntime){
        //this(new Item(properties), burntime);
    }

    AlchemistItems(Item item){
        //this(item, 0);
    }

    AlchemistItems(Item item, int burntime){
        this.item = item;
        FuelRegistry.INSTANCE.add(item, burntime);
        GameRegistry.registerItem(item, this.name());
        ItemGroupEvents.modifyEntriesEvent(ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), new ResourceLocation(Tags.MODID, "tab"))).register(content -> {
            content.accept(item);
        });
    }
}
