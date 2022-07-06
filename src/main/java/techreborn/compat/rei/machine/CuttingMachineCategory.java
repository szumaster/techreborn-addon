package techreborn.compat.rei.machine;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.ClientHelper;
import me.shedaniel.rei.api.widgets.Tooltip;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.widget.Widget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.crafting.RebornRecipeType;
import techreborn.compat.rei.MachineRecipeDisplay;
import techreborn.compat.rei.ReiPlugin;

import java.text.DecimalFormat;
import java.util.List;

public class CuttingMachineCategory<R extends RebornRecipe> extends AbstractMachineCategory<R> {
	public CuttingMachineCategory(RebornRecipeType<R> rebornRecipeType) {
		super(rebornRecipeType);
	}

	@Override
	public List<Widget> setupDisplay(MachineRecipeDisplay<R> recipeDisplay, Rectangle bounds) {
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(ReiPlugin.createEnergyDisplay(new Rectangle(bounds.x + 8, bounds.y + 8, 14, 50), recipeDisplay.getEnergy(), ReiPlugin.EntryAnimation.downwards(5000), point -> {
			List<Text> list = Lists.newArrayList();
			list.add(Text.of("Energy"));
			list.add(new TranslatableText("techreborn.jei.recipe.running.cost", "E", recipeDisplay.getEnergy()).formatted(Formatting.GRAY));
			list.add(Text.of(""));
			list.add(ClientHelper.getInstance().getFormattedModFromIdentifier(new Identifier("techreborn", "")));
			return Tooltip.create(point, list);
		}));

		widgets.add(Widgets.createSlot(new Point(bounds.x + 60, bounds.y + 26)).entries(getInput(recipeDisplay, 0)).markInput());
		widgets.add(Widgets.createSlot(new Point(bounds.x + 60 + 43, bounds.y + 26)).entries(getOutput(recipeDisplay, 0)).markOutput());
		widgets.add(ReiPlugin.createProgressBar(bounds.x + 60 + 21, bounds.y + 30, recipeDisplay.getTime() * 50, GuiBuilder.ProgressDirection.RIGHT));
		widgets.add(ReiPlugin.createFluidDisplay(new Rectangle(bounds.x + 55 - 26, bounds.y + 8, 16, 50), getInput(recipeDisplay, 1).get(0), ReiPlugin.EntryAnimation.downwards(5000)));

		widgets.add(Widgets.createLabel(new Point(bounds.x + 51, bounds.y + 5), new TranslatableText("techreborn.jei.recipe.processing.time.3", new DecimalFormat("###.##").format(recipeDisplay.getTime() / 20.0)))
				.shadow(false)
				.leftAligned()
				.color(0xFF404040, 0xFFBBBBBB)
		);
		return widgets;
	}
}
