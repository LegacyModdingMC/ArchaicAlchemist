package io.github.unix_supremacist.alchemist.item;

import io.github.unix_supremacist.alchemist.Tags;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class GaleItem extends TrinketItem {
    public GaleItem() {
        super();
    }
    public static final AbilitySource gale_ability = Pal.getAbilitySource(Tags.MODID, "swiftwolfs_rending_gale");

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!entity.level().isClientSide())
            if (entity instanceof Player)
                gale_ability.grantTo((Player) entity, VanillaAbilities.ALLOW_FLYING);
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!entity.level().isClientSide())
            if (entity instanceof Player)
                gale_ability.revokeFrom((Player) entity, VanillaAbilities.ALLOW_FLYING);
    }

    @Override
    public EnumRarity getRarity(ItemStack s){
        return EnumRarity.epic;
    }
}
