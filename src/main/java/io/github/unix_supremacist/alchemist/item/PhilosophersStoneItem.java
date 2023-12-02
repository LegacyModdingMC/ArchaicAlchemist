package io.github.unix_supremacist.alchemist.item;

import ibxm.Player;
import io.github.unix_supremacist.alchemist.data.BlockTag;
import io.github.unix_supremacist.alchemist.interfaces.AreaBox;
import io.github.unix_supremacist.alchemist.interfaces.TransmuteEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class PhilosophersStoneItem extends AbstractEmpowerableItem implements AreaBox, TransmuteEntity {
    public PhilosophersStoneItem() {
        super(3);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ArrayList<BlockPos> blocks = getAreaFromFacing(context.getClickedFace(), context.getClickedPos(), 2 - getPower(context.getItemInHand()), 0);
        ArrayList<Block> exchange = new ArrayList<>();
        InteractionResult result = InteractionResult.PASS;
        for (ArrayList<Block> e : BlockTag.exchanges){
            if (e.contains(context.getLevel().getBlockState(context.getClickedPos()).getBlock())){
                exchange = e;
                result = InteractionResult.SUCCESS;
                break;
            }
        }
        if(!blocks.isEmpty()){
            if (!context.getLevel().isClientSide()) for (BlockPos b : blocks){
                Block block = context.getLevel().getBlockState(b).getBlock();
                if (exchange.contains(block)) {
                    Block newBlock;
                    if(context.getPlayer().isShiftKeyDown())
                        newBlock = BlockTag.getBlockWithOffset(block, exchange,-1);
                    else
                        newBlock = BlockTag.getBlockWithOffset(block, exchange,+1);
                    context.getLevel().setBlock(b, newBlock.defaultBlockState(), 3);
                    ItemStack stack = context.getPlayer().getItemInHand(context.getHand()).copy();
                    context.getPlayer().setItemInHand(context.getHand(), stack);
                }
            }
        }

        return result;
    }

    public InteractionResult interactLivingEntity(ItemStack stack, Player player, EntityLiving entity, InteractionHand hand) {
        return transmuteEntity(entity, player.level());
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return stack.copy();
    }
}
