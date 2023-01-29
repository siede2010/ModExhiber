package exhiber.content;

import exhiber.world.Icon;

public class EXIcons {
    public static Icon
        one,two,three,four,five
            ;
    public static void load()
    {
        one = new Icon("one"){{value = 1;}};
        two = new Icon("two"){{value = 2;}};
        three = new Icon("three"){{value = 3;}};
        four = new Icon("four"){{value = 4;}};
        five = new Icon("five"){{value = 5;}};
    }
}
