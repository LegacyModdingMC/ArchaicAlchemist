package io.github.unix_supremacist.alchemist.item;

import io.github.unix_supremacist.alchemist.interfaces.AreaBox;
import io.github.unix_supremacist.alchemist.interfaces.Destroyer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class AlchemistHammerItem extends AlchemistDiggerItem implements AreaBox, Destroyer {

    public AlchemistHammerItem(Tier tier, int i, float f, Properties properties) {
        super(i, f, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, World level, BlockState state, BlockPos pos, EntityLivingBase entity) {
        Vec3 look = entity.getLookAngle();
        Direction dir = Direction.getNearest(look.x(), look.y(), look.z());
        ArrayList<BlockPos> blocks = getAreaFromFacing(dir, pos, 1, 0);
        destroyArea(level, blocks, entity);
        return super.mineBlock(itemStack, level, state, pos, entity);
    }
}
