package io.github.unix_supremacist.alchemist.item;

import io.github.unix_supremacist.alchemist.interfaces.DurablityBar;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import static io.github.unix_supremacist.alchemist.content.AlchemistItems.chalk;

public class AlchemistBlockItem extends ItemBlock implements DurablityBar {
    public AlchemistBlockItem(Block block) {
        super(block);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionResult;
        ItemStack item = context.getItemInHand();
        if(context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace())) == Blocks.LAVA.defaultBlockState() && item.getDamageValue() < this.getMaxDamage()){
            context.getLevel().setBlock(context.getClickedPos().relative(context.getClickedFace()), Blocks.AIR.defaultBlockState(), 0);
            context.getItemInHand().setDamageValue(item.getDamageValue()+1);
            interactionResult = InteractionResult.SUCCESS;
        } else {
            context.getItemInHand().grow(1);
            interactionResult = this.place(new BlockPlaceContext(context));
            if(interactionResult == InteractionResult.FAIL) context.getItemInHand().shrink(1);
            if (this.getMaxDamage() > 0) context.getItemInHand().hurtAndBreak(1, context.getPlayer(), livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return interactionResult;
    }

    @Override
    public int getBarColor(ItemStack item) {
        return getColor(item.getMaxDamage(), item.getItemDamage());
    }

    public ItemStack getRecipeRemainder(ItemStack stack) {
        if (this.getMaxDamage() > 0) stack.setItemDamage(stack.getItemDamage()-1);
        if (stack.getItemDamage() == getMaxDamage()) stack = null;
        return stack.copy();
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    /*@Override
    public boolean isValidRepairItem(ItemStack itemStack2, ItemStack itemStack) {
        return itemStack.getItem() == Items.BLAZE_POWDER || super.isValidRepairItem(itemStack2, itemStack);
    }*/

    @Override
    public EnumRarity getRarity(ItemStack s){
        return s.getItem()==chalk.getItem() ? EnumRarity.common : EnumRarity.epic;
    }
}
