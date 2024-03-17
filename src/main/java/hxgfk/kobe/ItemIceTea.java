package hxgfk.kobe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;

public class ItemIceTea extends Item {
    private boolean start = false;
    private int timer = 0;
    private final int effect_time = 6000;

    public ItemIceTea() {
        super();
        this.setRegistryName("ice_tea");
        this.setUnlocalizedName(Kobe.MODID+".ice_tea");
        this.setCreativeTab(CreativeTabs.BREWING);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            start = true;
            timer = 0;
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        if (start) {
            timer++;
            player.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1F, 1F);
            if (timer >= 50) {
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("speed")), effect_time, 3));
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("jump_boost")), effect_time, 2));
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("regeneration")), 2400, 2));
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("haste")), effect_time, 3));
                player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("strength")), effect_time, 2));
                stack.setCount(0);
                start = false;
                timer = 0;
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        start = false;
        timer = 0;
    }
}
