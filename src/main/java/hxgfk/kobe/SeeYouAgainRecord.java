package hxgfk.kobe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class SeeYouAgainRecord extends ItemRecord {
    public static final String ID = "record";

    public SeeYouAgainRecord() {
        super(ID, Kobe.SEE_YOU_AGAIN_SOUND);
        this.setRegistryName(ID);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("See You Again");
    }
}
