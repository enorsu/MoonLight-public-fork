package wtf.moonlight.features.modules.impl.combat;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import org.lwjglx.input.Keyboard;
import wtf.moonlight.events.annotations.EventTarget;
import wtf.moonlight.events.impl.packet.PacketEvent;
import wtf.moonlight.events.impl.player.UpdateEvent;
import wtf.moonlight.features.modules.Module;
import wtf.moonlight.features.modules.ModuleCategory;
import wtf.moonlight.features.modules.ModuleInfo;
import wtf.moonlight.utils.math.TimerUtils;
import wtf.moonlight.utils.player.PlayerUtils;

@ModuleInfo(name = "BlockHit",category = ModuleCategory.Combat)
public class BlockHit extends Module {

    public TimerUtils timer = new TimerUtils();
    public EntityPlayer target;

    @Override
    public void onEnable() {
        timer.reset();
    }

    @EventTarget
    public void onUpdate(UpdateEvent event){
        setTag("Predict Test");
        target = PlayerUtils.getTarget(16);
    }

    @EventTarget
    public void onPacket(PacketEvent event) {

        Packet packet = event.getPacket();

        /*if (packet instanceof S19PacketEntityStatus s19 && s19.getEntity(mc.theWorld) == mc.thePlayer && getModule(KillAura.class).isHoldingSword()) {
            event.setCancelled(true);
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
            s19.processPacket(mc.getNetHandler());
        }*/

        /*if (packet instanceof C02PacketUseEntity c02 &&
                c02.getAction() == C02PacketUseEntity.Action.ATTACK &&
                mc.theWorld.getEntityByID(c02.getEntityId()) instanceof EntityPlayer target &&
                timer.hasTimeElapsed(500) &&
                target.hurtTime == 0 &&
                getModule(KillAura.class).isHoldingSword()
        ) {
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
            timer.reset();
        }*/

        if (!(packet instanceof C02PacketUseEntity)&& getModule(KillAura.class).isHoldingSword() && target != null && target.swingProgressInt > 0 && (mc.thePlayer.hurtTime == 0 && timer.hasTimeElapsed(500) || mc.thePlayer.hurtTime == 9)) {
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
            timer.reset();
        }
    }
}