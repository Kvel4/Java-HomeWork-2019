# ITMOJava

### Домашнее задание 7\. Разметка

1.  Разработайте набор классов для текстовой разметки.
2.  Класс <tt>Paragraph</tt> может содержать произвольное число других элементов разметки и текстовых элементов.
3.  Класс <tt>Text</tt> – текстовый элемент.
4.  Классы разметки <tt>Emphasis</tt>, <tt>Strong</tt>, <tt>Strikeout</tt> – выделение, сильное выделение и зачеркивание. Элементы разметки могут содержать произвольное число других элементов разметки и текстовых элементов.
5.  Все классы должны реализовывать метод <tt>toMarkdown([StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html))</tt>, которой должен генерировать [Markdown](https://ru.wikipedia.org/wiki/Markdown)-разметку по следующим правилам:
    1.  текстовые элементы выводятся как есть;
    2.  выделенный текст окружается символами '<tt>*</tt>';
    3.  сильно выделенный текст окружается символами '<tt>__</tt>';
    4.  зачеркнутый текст окружается символами '<tt>~</tt>'.
6.  Следующий код должен успешно компилироваться:

    <pre>    Paragraph paragraph = new Paragraph(List.of(
            new Strong(List.of(
                new Text("1"),
                new Strikeout(List.of(
                    new Text("2"),
                    new Emphasis(List.of(
                        new Text("3"),
                        new Text("4")
                    )),
                    new Text("5")
                )),
                new Text("6")
            ))
        ));
    </pre>

    Вызов <tt>paragraph.toMakdown(new StringBuilder())</tt> должен заполнять переданный <tt>StringBuilder</tt> следующим содержимым:

    <pre>    __1~2*34*5~6__
    </pre>

7.  Разработанные классы должны находиться в пакете <tt>markup</tt>.


### Домашнее задание 9\. Markdown to HTML
#### Тут вёрстка ушла в параллельный мир к котикам

1.  Разработайте конвертер из [Markdown](https://ru.wikipedia.org/wiki/Markdown)-разметки в [HTML](https://ru.wikipedia.org/wiki/HTML).
2.  Конвертер должен поддерживать следующие возможности:
    1.  Абзацы текста разделяются пустыми строками.
    2.  Элементы строчной разметки: выделение (<tt>*</tt> или <tt>_</tt>), сильное выделение (<tt>**</tt> или <tt>__</tt>), зачеркивание (<tt>--</tt>), код (<tt>`</tt>)
    3.  Заголовки (<tt>#</tt> * уровень заголовка)
3.  Конвертер должен называться <tt>Md2Html</tt> и принимать два аргумента: название входного файла с Markdown-разметкой и название выходного файла c HTML-разметкой. Оба файла должны иметь кодировку UTF-8.
4.  Пример
    *   Входной файл

        <pre># Заголовок первого уровня

        ## Второго

        ### Третьего ## уровня

        #### Четвертого
        # Все еще четвертого

        Этот абзац текста,
        содержит две строки.

            # Может показаться, что это заголовок.
        Но нет, это абзац начинающийся с `#`.

        #И это не заголовок.

        ###### Заголовки могут быть многострочными
        (и с пропуском заголовков предыдущих уровней)

        Мы все любим *выделять* текст _разными_ способами.
        **Сильное выделение**, используется гораздо реже,
        но __почему бы и нет__?
        Немного --зачеркивания-- еще ни кому не вредило.
        Код представляется элементом `code`.

        Обратите внимание, как экранируются специальные
        HTML-символы, такие как `<`, `>` и `&`.

        Знаете ли вы, что в Markdown, одиночные * и _
        не означают выделение?
        Они так же могут быть заэкранированы
        при помощи обратного слэша: \*.

        Лишние пустые строки должны игнорироваться.

        Любите ли вы *вложеные __выделения__* так,
        как __--люблю--__ их я?
                    </pre>

    *   Выходной файл

        <pre><h1>Заголовок первого уровня</h1>
        <h2>Второго</h2>
        <h3>Третьего ## уровня</h3>
        <h4>Четвертого
        # Все еще четвертого</h4>
        <p>Этот абзац текста,
        содержит две строки.</p>
        <p>    # Может показаться, что это заголовок.
        Но нет, это абзац начинающийся с <code>#</code>.</p>
        <p>#И это не заголовок.</p>
        <h6>Заголовки могут быть многострочными
        (и с пропуском заголовков предыдущих уровней)</h6>
        <p>Мы все любим <em>выделять</em> текст <em>разными</em> способами.
        <strong>Сильное выделение</strong>, используется гораздо реже,
        но <strong>почему бы и нет</strong>?
        Немного <s>зачеркивания</s> еще ни кому не вредило.
        Код представляется элементом <code>code</code>.</p>
        <p>Обратите внимание, как экранируются специальные
        HTML-символы, такие как <code>&lt;</code>, <code>&gt;</code> и <code>&amp;</code>.</p>
        <p>Знаете ли вы, что в Markdown, одиночные * и _
        не означают выделение?
        Они так же могут быть заэкранированы
        при помощи обратного слэша: *.</p>
        <p>Лишние пустые строки должны игнорироваться.</p>
        <p>Любите ли вы <em>вложеные <strong>выделения</strong></em> так,
        как <strong><s>люблю</s></strong> их я?</p>
                    </pre>

    *   Реальная разметка

        # Заголовок первого уровня

        ## Второго

        ### Третьего ## уровня

        #### Четвертого # Все еще четвертого

        Этот абзац текста, содержит две строки.

        # Может показаться, что это заголовок. Но нет, это абзац начинающийся с `#`.

        #И это не заголовок.

        ###### Заголовки могут быть многострочными (и с пропуском заголовков предыдущих уровней)

        Мы все любим _выделять_ текст _разными_ способами. **Сильное выделение**, используется гораздо реже, но **почему бы и нет**? Немного <s>зачеркивания</s> еще ни кому не вредило. Код представляется элементом `code`.

        Обратите внимание, как экранируются специальные HTML-символы, такие как `<`, `>` и `&`.

        Знаете ли вы, что в Markdown, одиночные * и _ не означают выделение? Они так же могут быть заэкранированы при помощи обратного слэша: *.

        Лишние пустые строки должны игнорироваться.

        Любите ли вы _вложеные **выделения**_ так, как **<s>люблю</s>** их я?

### Домашнее задание 10\. Игра m,n,k

1.  Реализуйте [игру m,n,k](https://en.wikipedia.org/wiki/M,n,k-game).
2.  Добавьте обработку ошибок ввода пользователя.
3.  _Простая версия_. Проверку выигрыша можно производить за _O(nmk)_.
4.  _Сложная версия_.
    *   Проверку выигрыша нужно производить за _O(k)_.
    *   Предотвратите жульничество: у игрока не должно быть возможности достать `Board` из `Position`.
5.  _Бонусная версия_. Реализуйте `Winner` — игрок, который выигрывает всегда, когда это возможно.


### Начиная отсюда идут задания, которые я не делал
### Домашнее задание 11\. Выражения

1.  Разработайте классы `Const`, `Variable`, `Add`, `Subtract`, `Multiply`, `Divide` для вычисления выражений с одной переменной.
2.  Классы должны позволять составлять выражения вида

    <pre>new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).evaluate(5)
                </pre>

    При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу `evaluate` (на данном этапе имена переменных игнорируются). Таким образом, результатом вычисления приведенного примера должно стать число 7.
3.  Метод `toString` должен выдавать запись выражения в полноскобочной форме. Например

    <pre>new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toString()
                </pre>

    должен выдавать `((2 * x) - 3)`.
4.  _Сложный вариант._ Метод `toMiniString` должен выдавать выражение с минимальным числом скобок. Например

    <pre>new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toMiniString()
                </pre>

    должен выдавать `2 * x - 3`.
5.  Реализуйте метод `equals`, проверяющий, что два выражения совпадают. Например,

    <pre>new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Const(2), new Variable("x")))
                </pre>

    должно выдавать `true`, а

    <pre>new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Variable("x"), new Const(2)))
                </pre>

    должно выдавать `false`.
6.  Для тестирования программы должен быть создан класс `Main`, который вычисляет значение выражения `x<sup>2</sup>−2x+1`, для `x`, заданного в командной строке.
7.  При выполнение задания следует обратить внимание на:
    *   Выделение общего интерфейса создаваемых классов.
    *   Выделение абстрактного базового класса для бинарных операций.

### Домашнее задание 12\. Разбор выражений

1.  Доработайте предыдущее домашнее задание, так что бы выражение строилось по записи вида

    <pre>x * (x - 2)*x + 1</pre>

2.  В записи выражения могут встречаться: умножение `*`, деление `/`, сложение `+`, вычитание `-`, унарный минус `-`, целочисленные константы (в десятичной системе счисления, которые помещаются в 32-битный знаковый целочисленный тип), круглые скобки, переменные (`x`) и произвольное число пробельных символов в любом месте (но не внутри констант).
3.  Приоритет операторов, начиная с наивысшего
    1.  унарный минус;
    2.  умножение и деление;
    3.  сложение и вычитание.
4.  Разбор выражений рекомендуется производить [методом рекурсивного спуска](https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D0%B2%D0%BD%D0%BE%D0%B3%D0%BE_%D1%81%D0%BF%D1%83%D1%81%D0%BA%D0%B0). Алгоритм должен работать за линейное время.

### Домашнее задание 13\. Обработка ошибок

1.  Добавьте в программу вычисляющую выражения обработку ошибок, в том числе:
    *   ошибки разбора выражений;
    *   ошибки вычисления выражений.
2.  Для выражения `1000000*x*x*x*x*x/(x-1)` вывод программы должен иметь следующий вид:

    <pre>x       f
    0       0
    1       division by zero
    2       32000000
    3       121500000
    4       341333333
    5       overflow
    6       overflow
    7       overflow
    8       overflow
    9       overflow
    10      overflow
                </pre>

    Результат `division by zero` (`overflow`) означает, что в процессе вычисления произошло деление на ноль (переполнение).
3.  При выполнении задания следует обратить внимание на дизайн и обработку исключений.
4.  Человеко-читаемые сообщения об ошибках должны выводится на консоль.
5.  Программа не должна «вылетать» с исключениями (как стандартными, так и добавленными).
