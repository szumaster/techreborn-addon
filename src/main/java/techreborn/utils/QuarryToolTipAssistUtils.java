package techreborn.utils;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import techreborn.config.TechRebornConfig;
import techreborn.init.TRContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://github.com/TED-inc/FabricQuarry">Created by TED-INC</a>
 * **/
public class QuarryToolTipAssistUtils {

	public static List<Text> getUpgradeStats(TRContent.QuarryUpgrades upgradeType, int count, boolean shiftHeld) {
		List<Text> tips = new ArrayList<>();

		switch (upgradeType) {
			case RANGE_EXTENDER_LVL1 :
                getTextForrangeExtender(1, tips);
                break;
			case RANGE_EXTENDER_LVL2 :
                getTextForrangeExtender(2, tips);    
                break;
			case RANGE_EXTENDER_LVL3 :
                getTextForrangeExtender(3, tips);
                break;
            default:
                tips.add(
                    new TranslatableText("tooltip.techreborn." + upgradeType.name)
                    .formatted(Formatting.GOLD));   
		}

        if (shiftHeld && upgradeType.name.contains("lvl")) {
            tips.add(new LiteralText(""));
            String translation = I18n.translate("tooltip.techreborn.upgrade_leveled_warining");
            Arrays.stream(translation.split("\n"))
                .forEach(line -> tips.add(new LiteralText(line).formatted(Formatting.RED)));
        }

		return tips;
	}

    private static void getTextForrangeExtender(int level, List<Text> tips) {
        tips.add( 
            new TranslatableText("tooltip.techreborn.range_extender_effect")
            .formatted(Formatting.GOLD));
        tips.add( 
            new TranslatableText("tooltip.techreborn.range_extender_value")
            .formatted(Formatting.GREEN)
            .append(
                new LiteralText(String.valueOf(TechRebornConfig.quarrySqrWorkRadiusByUpgradeLevel.get(level).intValue()))
                .formatted(Formatting.GOLD))
            .append(new TranslatableText("tooltip.techreborn.range_extender_blocks")
                .formatted(Formatting.GOLD)));
    }
}
