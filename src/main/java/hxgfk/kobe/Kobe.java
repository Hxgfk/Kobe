package hxgfk.kobe;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.SoundEvent;

import java.util.Objects;

@Mod(modid=Kobe.MODID, name = "Kobe Mod", version = "1.0")
public class Kobe {
    public static final String MODID = "kobe";
    public static final SoundEvent SEE_YOU_AGAIN_SOUND = new SoundEvent(new ResourceLocation(MODID, "see_you_again")).setRegistryName("kobe.record.music");
    public static final SeeYouAgainRecord INS_ITEM_RECORD = new SeeYouAgainRecord();
    public static final EntityEntry INS_KOBE_ENTITY = EntityEntryBuilder.create()
            .entity(EntityKobe.class)
            .id(EntityKobe.entity_id, 0)
            .name(MODID+".entity_kobe")
            .tracker(80, 3, true)
            .build();

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public static void preInitClient(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityKobe.class, EntityKobe.render::new);
    }

    @Mod.EventBusSubscriber
    public static class RegistryContent {
        @SubscribeEvent
        public static void registryEntity(RegistryEvent.Register<EntityEntry> event) {
            event.getRegistry().register(INS_KOBE_ENTITY);
        }

        @SubscribeEvent
        public static void registryItem(RegistryEvent.Register<Item> event) {
            event.getRegistry().register(INS_ITEM_RECORD);
        }

        @SubscribeEvent
        public static void registrySound(RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().register(SEE_YOU_AGAIN_SOUND);
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void registryItemModel(ModelRegistryEvent event) {
            ModelLoader.setCustomModelResourceLocation(INS_ITEM_RECORD, 0,
                    new ModelResourceLocation(Objects.requireNonNull(INS_ITEM_RECORD.getRegistryName()), "inventory"));
        }
    }
}