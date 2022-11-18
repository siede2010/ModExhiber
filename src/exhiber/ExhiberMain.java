package exhiber;

import arc.*;
import arc.util.*;
import exhiber.content.*;
import mindustry.game.EventType.*;
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
    }
    @Override
    public void loadContent(){
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

        Log.info("Loading some example content.");
    }
}
