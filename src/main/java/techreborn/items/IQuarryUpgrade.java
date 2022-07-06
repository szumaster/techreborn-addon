package techreborn.items;

import org.jetbrains.annotations.NotNull;

import net.minecraft.item.ItemStack;
import techreborn.blockentity.machine.tier2.QuarryBlockEntity;

/**
 * <a href="https://github.com/TED-inc/FabricQuarry">Created by TED-INC</a>
 * **/
public interface IQuarryUpgrade {
    void process(
        @NotNull QuarryBlockEntity quarryBlockEntity,
        @NotNull ItemStack stack);
}
