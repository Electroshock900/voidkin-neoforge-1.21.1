package net.voidkin.voidkin.worldgen.portal;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.component.XionPortalAttachment;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Voidkin.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<XionPortalAttachment>> XION_PORTAL_COOLDOWN = ATTACHMENT_TYPES.register("xion_portal_cooldown", () -> AttachmentType.builder(XionPortalAttachment::new).build());

}
