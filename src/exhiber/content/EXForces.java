package exhiber.content;

import exhiber.type.Forces;

public class EXForces {
    public static Forces
            gravitaniumPod,nuculiPod,electumPod,magnitiumPod
            ;
    public static void load()
    {
        gravitaniumPod = new Forces("gravitanium-pod")
        {{
            localizedName = "Gravitanium Pod";
            description = "We do a lil Trollin";
        }};
        nuculiPod = new Forces("nuculi-pod")
        {{
            localizedName = "Nuculi Pod";
            description = "Look i wonder why The sun is comming up at 1 am in the morning";
        }};
        electumPod = new Forces("electum-pod")
        {{
            localizedName = "Electum Pod";
            description = "coil go brrrr";
        }};
        magnitiumPod = new Forces("magnitium-pod")
        {{
            localizedName = "Magnitium Pod";
            description = "oh boi cant wait to learn faster with this!";
        }};
    }
}
