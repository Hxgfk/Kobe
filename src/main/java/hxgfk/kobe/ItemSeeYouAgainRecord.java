package hxgfk.kobe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemSeeYouAgainRecord extends ItemRecord {
    public ItemSeeYouAgainRecord() {
        super("see_you_again", Kobe.SEE_YOU_AGAIN_SOUND);
        this.setUnlocalizedName(Kobe.MODID+".record_see_you_again");
        this.setRegistryName("record_see_you_again");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("See You Again");
    }

    private final Random random = new Random();

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos).getBlock().getRegistryName().equals("minecraft:jukebox")) {
                if (random.nextInt(100) <= 24) {
                    BlockPos p1 = pos.up();
                    BlockPos p2 = p1.up();
                    if (worldIn.getBlockState(p1).getBlock() != Blocks.AIR && worldIn.getBlockState(p2).getBlock() != Blocks.AIR) {
                        worldIn.spawnEntity(new EntityLightningBolt(worldIn, p1.getX(), p1.getY(), p1.getZ(), true));
                        double deltaX = player.posX - pos.getX();
                        double deltaY = player.posY - (pos.getY() + 1);
                        double deltaZ = player.posZ - pos.getZ();
                        float yaw = (float) Math.toDegrees(MathHelper.atan2(deltaZ, deltaX));
                        double distance = MathHelper.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
                        float pitch = (float) -Math.toDegrees(Math.asin(deltaY / distance));
                        EntityKobe entity = new EntityKobe(worldIn);
                        entity.setPositionAndRotation(pos.getX(), pos.getY() + 1, pos.getZ(), yaw, pitch);
                        worldIn.spawnEntity(entity);
                    }
                }
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
