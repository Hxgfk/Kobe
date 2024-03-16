package hxgfk.kobe;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityKobe extends EntityMob {
    public static final String entity_id = "entity_kobe";

    public EntityKobe(World world) {
        super(world);
        this.setSize(0.6F, 1.2F);
        this.setHealth(50F);
    }

    public static class model extends ModelPlayer {
        public model() {
            super(0, false);
            this.bipedLeftLeg = new ModelRenderer(this, 16, 48)
                    .addBox(-2.0F, 0, -2.0F, 4, 16, 4);
            this.bipedRightLeg = new ModelRenderer(this, 0, 16)
                    .addBox(-2.0F, 0, -2.0F, 4, 16, 4);
        }
    }

    public static class render extends RenderLiving {
        private static final ResourceLocation texture = new ResourceLocation(Kobe.MODID+":textures/entity/"+entity_id+".png");

        public render(RenderManager manager) {
            super(manager, new ModelPlayer(0, false), 0.8F);
        }

        @Nullable
        @Override
        protected ResourceLocation getEntityTexture(Entity entity) {
            return texture;
        }
    }
}
