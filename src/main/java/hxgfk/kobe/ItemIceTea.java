package hxgfk.kobe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIceTea extends Item {
    public ItemIceTea() {
        super();
        this.setRegistryName("ice_tea");
        this.setUnlocalizedName(Kobe.MODID+".ice_tea");
        this.setCreativeTab(CreativeTabs.BREWING);
    }
}
