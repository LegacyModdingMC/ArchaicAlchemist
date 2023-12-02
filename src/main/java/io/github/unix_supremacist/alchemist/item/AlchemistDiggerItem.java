package io.github.unix_supremacist.alchemist.item;

import io.github.unix_supremacist.alchemist.interfaces.AreaBox;
import io.github.unix_supremacist.alchemist.interfaces.Destroyer;
import io.github.unix_supremacist.alchemist.interfaces.DurablityBar;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class AlchemistDiggerItem extends DiggerItem implements AreaBox, Destroyer, DurablityBar {
    public AlchemistDiggerItem(float f, float g, Tier tier, TagKey<Block> tagKey, Properties properties) {
        super(f, g, tier, tagKey, properties);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, World level, BlockState state, BlockPos pos, LivingEntity entity) {
        Vec3 look = entity.getLookAngle();
        ArrayList<BlockPos> blocks = getAreaFromFacing(Direction.getNearest(look.x(), look.y(), look.z()), pos, 1, 0);
        destroyArea(level, blocks, entity);
        return super.mineBlock(itemStack, level, state, pos, entity);
    }

    @Override
    public int getBarColor(ItemStack item) {
        return getColor(item.getMaxDamage(), item.getDamageValue());
    }

    @Override
    public Rarity getRarity(ItemStack s){
        return Rarity.EPIC;
    }
}
