package exhiber.content;

import static exhiber.content.EXBlocks.*;
import static mindustry.content.TechTree.*;
import static exhiber.content.EXUnits.*;
import static exhiber.content.EXItems.*;
import static exhiber.content.EXLiquids.*;
import static mindustry.content.Liquids.*;

public class ExhiberTechTree {
    public static void load() {
        EXPlanets.exhiber.techTree = nodeRoot("Exhiber", corePad, () -> {
            node(zincGutter, () -> {
                node(zincDispatcher, () -> {
                    node(zincJunction, () -> {
                        node(payloadGutter);
                        node(isolatorVault);
                    });
                });
                node(zincConduit);
                node(zincWires, () -> {
                    node(powerRouter, () -> {
                        node(kitePad, () -> {
                            node(windmill);
                            node(watermill, () -> {
                                //Todo
                            });
                        });
                    });
                });
            });
            node(coreVessel);
            node(chalkScraper, () -> {
                node(rainCollector);
                node(turboDrill, () -> {
                    node(powerDrill);
                    //Todo
                });
                //Todo
                node(magnifyingGlass, () -> {
                    node(brassForge);
                });
                node(quartzRecrystallizer, () -> {
                    node(tartarusRadiator);
                });
            });
            node(swift, () -> {
                node(dispel, () -> {
                    node(reflect);
                    node(tensor);
                    node(flow);
                });
                node(brassWall, () -> {
                    node(brassWallLarge, () -> {
                        node(clayWall, () -> {
                            node(clayWallLarge);
                        });
                    });
                    node(vanadiumWall, () -> {
                        node(vanadiumWallLarge);
                    });
                });
            });
            nodeProduce(zinc, () -> {
                nodeProduce(liquidZinc,()->{});
                nodeProduce(ammonia,()->{});
                nodeProduce(nitrogen,()->{});
                
                nodeProduce(chalk, () -> {
                    nodeProduce(diamond, () -> {
                        nodeProduce(tenorite, () -> {
                            nodeProduce(liquidTenorite, () -> {
                                nodeProduce(brass, () -> {
                                    nodeProduce(liquidBrass,()->{});
                                });
                                //Todo node(fiberGlass,() -> {
                                nodeProduce(radium, () -> {
                                    //Todo
                                });
                            });
                        });
                    });
                    nodeProduce(vanadium, () -> {
                        nodeProduce(hyperfluxMatter,()->{});
                        nodeProduce(liquidVanadium,()->{});
                    });
                    nodeProduce(rawClay, () -> {
                        nodeProduce(quartz, () -> {
                            nodeProduce(fiberGlass,()->{});
                        });
                    });
                });


            });
            node(moduleAssembler,()->{
                node(magnumAssembler,() ->{
                    node(loner,() -> {
                        node(rouge,() -> {
                            //T3 Todo
                        });
                    });
                    node(sentry,() -> {
                        node(stalker,() -> {
                             //T3 Todo
                        });
                        node(polar,() -> {
                            node(brainFreeze,()->{
                                //T3 Todo
                            });
                        });
                        node(bonfire,()->{
                            node(campfire,()->{
                                //T3 Todo
                            });
                        });
                    });
                });
                node(moduleMK1,() -> {
                   node(moduleMK2);
                   node(electricModuleEM1);
                });

            });
        });
    }
}