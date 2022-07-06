package techreborn.blockentity.conduit.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import reborncore.api.systems.conduit.functionalfaces.ConduitFunction;
import reborncore.api.systems.conduit.functionalfaces.IConduitItemProvider;
import techreborn.init.TRContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConduitItems implements IConduitItemProvider {

	Map<ConduitFunction, Item> itemFunctionMap = new HashMap<ConduitFunction, Item>() {{
		put(ConduitFunction.EXPORT, TRContent.Conduit.EXPORT_CONDUIT_ITEM.asItem());
		put(ConduitFunction.IMPORT, TRContent.Conduit.IMPORT_CONDUIT_ITEM.asItem());
		put(ConduitFunction.BLOCK, TRContent.Conduit.BLOCK_CONDUIT_ITEM.asItem());
		put(ConduitFunction.ONE_WAY, TRContent.Conduit.ONE_WAY_CONDUIT_ITEM.asItem());
	}};


	@Override
	public Item getItem(ConduitFunction function) {
		return itemFunctionMap.get(function);
	}

	@Override
	public ConduitFunction fromConduitFunction(Item item) {
		for (Map.Entry<ConduitFunction, Item> entry : itemFunctionMap.entrySet()) {
			if (entry.getValue() == item) {
				return entry.getKey();
			}
		}

		return null;
	}

	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add( new TranslatableText("item.techreborn.export_conduit_item.tooltip").formatted(Formatting.RED));
		tooltip.add( new TranslatableText("item.techreborn.import_conduit_item.tooltip").formatted(Formatting.RED));
		tooltip.add( new TranslatableText("item.techreborn.block_conduit_item.tooltip").formatted(Formatting.RED));
		tooltip.add( new TranslatableText("item.techreborn.one_way_conduit_item.tooltip").formatted(Formatting.RED));
	}
}
