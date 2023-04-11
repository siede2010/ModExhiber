package exhiber;

import arc.*;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.util.*;
import exhiber.content.*;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.graphics.Layer;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ExhiberMain extends Mod {

    public ExhiberMain() {
        Log.info("Loaded ExhiberMain constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("We judge uppon Thee :").row();
                dialog.cont.add("The Frogs").row();
                dialog.cont.image(Core.atlas.find("exhiber-frog")).pad(40f);
                dialog.cont.image(Core.atlas.find("exhiber-frog")).pad(40f);
                dialog.cont.image(Core.atlas.find("exhiber-frog")).pad(40f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
        //Overlay Render
        Events.run(Trigger.drawOver, () -> {
            if (Vars.renderer.animateShields && EXShaders.rainShield != null)
                Draw.drawRange(Layer.flyingUnit + 3f, 1f, () -> {
                    if (!Vars.renderer.effectBuffer.isBound())
                        Vars.renderer.effectBuffer.begin(Color.clear);
                    }, () ->
                {
                    Vars.renderer.effectBuffer.end();
                    Vars.renderer.effectBuffer.blit(EXShaders.rainShield);
                    if (Vars.renderer.effectBuffer.isBound())
                        Vars.renderer.effectBuffer.endBind();
                });
        });
    }
    @Override
    public void loadContent(){
        EXStats.load();
        EXShaders.init();
        EXCacheLayer.init();
        ExEffects.load();
        EXAttributes.load();
        EXTeams.load();
        EXItems.EXload();
        EXForces.load();
        EXLiquids.EXload();
        EXStatusEffects.EXload();
        EXUnits.EXload();
        EXWeather.EXload();
        EXBlocks.EXload();
        ExhiberLoadouts.load();
        EXPlanets.EXload();
        ExhiberTechTree.load();
        EXEventType.load();

        Log.info("Loading some example content.");
    }

    @Override
    public void init(){
        super.init();
    }
}
