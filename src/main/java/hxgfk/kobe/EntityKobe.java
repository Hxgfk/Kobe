package hxgfk.kobe;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityKobe extends EntityMob {
    public static final String entity_id = "entity_kobe";

    public EntityKobe(World world) {
        super(world);
        this.setSize(0.8F, 2.8F);
        this.setHealth(50F);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.96D, true));
        this.tasks.addTask(1, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(2, new EntityAINearestMonsterAttack(this, 15));
        this.tasks.addTask(2, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.65D));
        this.tasks.addTask(3, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(4, new EntityAIWatchClosest2(this, EntityPlayer.class, 4.5F, 5.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 4F));
    }

    public static class EntityAINearestMonsterAttack extends EntityAIBase {
        private final EntityCreature entity;
        private final double attractRange;
        private int maxTargets = 30;
        private int attractedTargets = 0;

        public EntityAINearestMonsterAttack(EntityCreature entity, double range) {
            this.entity = entity;
            this.attractRange = range;
        }

        @Override
        public boolean shouldExecute() {
            return true;
        }

        @Override
        public void updateTask() {
            List<EntityMob> nearbyMobs = this.entity.world.getEntitiesWithinAABB(EntityMob.class, this.entity.getEntityBoundingBox().grow(this.attractRange));

            for (EntityMob mob : nearbyMobs) {
                if (this.attractedTargets >= this.maxTargets) {
                    break;
                }

                if (mob.getAttackTarget() == null) {
                    mob.setAttackTarget(this.entity);
                    this.attractedTargets++;
                }
            }
        }

        @Override
        public void resetTask() {
            this.attractedTargets = 0;
        }
    }

    public static class model extends ModelPlayer {
        public model() {
            super(0, false);

            int[] leg_size = new int[]{5, 26};
            int[] arm_size = new int[]{5, 22};

            this.bipedLeftLeg = new ModelRenderer(this, 16, 48)
                    .addBox(-2.0F, 0, -2.0F, leg_size[0], leg_size[1], leg_size[0]);
            this.bipedRightLeg = new ModelRenderer(this, 0, 16)
                    .addBox(-2.0F, 0, -2.0F, leg_size[0], leg_size[1], leg_size[0]);
            this.bipedLeftArm = new ModelRenderer(this, 40, 16)
                    .addBox(-1.0F, -2.0F, -2.0F, arm_size[0], arm_size[1], arm_size[0]);
            this.bipedRightArm = new ModelRenderer(this, 40, 16)
                    .addBox(-3.0F, -2.0F, -2.0F, arm_size[0], arm_size[1], arm_size[0]);
        }
    }

    public static class render extends RenderLiving {
        private static final ResourceLocation texture = new ResourceLocation(Kobe.MODID+":textures/entity/"+entity_id+".png");

        public render(RenderManager manager) {
            super(manager, new ModelPlayer(0, false), 0.5F);
        }

        @Nullable
        @Override
        protected ResourceLocation getEntityTexture(Entity entity) {
            return texture;
        }
    }
}
