package exhiber.content;

import arc.Core;
import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.type.StatusEffect;

public class EXStatusEffects {
    public static StatusEffect
        scabbed,ballistic,protectivePlating,advancedPlating,crunched,infrafluxed
            , slowing , polishHit
            ;

        public static void EXload(){
            scabbed = new StatusEffect("scabbed"){{
                localizedName = "Scabbed";
                description = "The rain of the Diamond debris has Gotten into your inner circuitry. Ouch!";
                reloadMultiplier = 0.8f;
                damage = 0.0666667f;
                speedMultiplier = 0.9f;
                fullIcon = Core.atlas.find("scabbed");
                uiIcon = Core.atlas.find("scabbed");
                init(() -> {
                    opposite(protectivePlating);
                });
            }};
            ballistic = new StatusEffect("ballistic"){{
                localizedName = "Ballistic";
                description = "The Ammunition has the Side effect of boosting the inner circuitry. in addition to damaging it greatly.";
                reloadMultiplier = 1.2f;
                damageMultiplier = 1.2f;
                damage = 0.025f;
            }};
            protectivePlating = new StatusEffect("protective-plating"){{
                localizedName = "Protecting Plating";
                description = "small temporary Plating that Protects from minor injury's and Diamond Rain";
                healthMultiplier = 1.1f;
                init(() -> {
                    opposite(scabbed);
                });
            }};
            advancedPlating = new StatusEffect("advanced-plating"){{
                localizedName = "Advanced Plating";
                description = "More advanced Plating that Also Protects the unit From Freezing and Burning";
                healthMultiplier = 1.25f;
                buildSpeedMultiplier = 1.2f;
                speedMultiplier = 1.1f;
                init(() -> {
                    opposite(scabbed,StatusEffects.burning,StatusEffects.freezing);
                });
            }};
            crunched = new StatusEffect("crunched"){{
                localizedName = "Crunched";
                healthMultiplier = 1f/1.6f;
                init(() -> {
                    affinity(StatusEffects.blasted,(unit,result,time) -> {
                        unit.damagePierce(120);
                    });
                        });
            }};
            slowing = new StatusEffect("slowing"){{
                localizedName = "Slowing";
                show = false;
                speedMultiplier = 0.3333f;
                dragMultiplier = 0.01f;
            }};
            infrafluxed = new StatusEffect("infrafluxed"){{
                localizedName = "Infrafluxed";
                speedMultiplier = 0.2f;
                reloadMultiplier = 0.2f;
            }};
            polishHit = new StatusEffect("polish-hit"){{
                localizedName = "Polish Hit";
                show = false;
                speedMultiplier = 0.2f;
                dragMultiplier = 0.02f;
            }};
        }
}
