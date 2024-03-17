package hxgfk.kobe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.Objects;

public class ItemIceTea extends Item {

    public ItemIceTea() {
        super();
        this.setRegistryName("ice_tea");
        this.setUnlocalizedName(Kobe.MODID+".ice_tea");
        this.setCreativeTab(CreativeTabs.BREWING);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            int effect_time = 2*60*20;
            playerIn.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("speed")), effect_time, 3));
            playerIn.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("jump_boost")), effect_time, 2));
            playerIn.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("regeneration")), 2400, 2));
            playerIn.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("haste")), effect_time, 3));
            playerIn.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("strength")), effect_time, 2));
            ItemStack is = playerIn.getHeldItem(handIn);
            is.setCount(is.getCount()-1);
            playerIn.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE, 1));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
